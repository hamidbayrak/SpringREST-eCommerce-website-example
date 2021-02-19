package com.example.mvcp.restcontrollers;

import com.example.mvcp.entities.Cart;
import com.example.mvcp.repositories.CartPropsRepository;
import com.example.mvcp.repositories.CartRepositoy;
import com.example.mvcp.util.RestEnum;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.LinkedHashMap;
import java.util.Map;

@RestController
@RequestMapping("/cart")
public class CartRestController {

    final CartPropsRepository cartPropsRepo;
    final CartRepositoy cartRepo;
    final HttpServletResponse response;

    public CartRestController(CartPropsRepository cartPropsRepo, CartRepositoy cartRepo, HttpServletResponse response) {
        this.cartPropsRepo = cartPropsRepo;
        this.cartRepo = cartRepo;
        this.response = response;
    }

    @GetMapping("/list")
    public Map<RestEnum,Object> list(@RequestParam (value = "id", required = true) String tuid){
        int uid = Integer.parseInt(tuid);
        Map<RestEnum,Object> cartPropsMap = new LinkedHashMap<>();
        cartPropsMap.put(RestEnum.status, true);
        cartPropsMap.put(RestEnum.message,"Success");
        cartPropsMap.put(RestEnum.result, cartPropsRepo.fillCart(uid));
        return cartPropsMap;
    }

    @PostMapping("/insert")
    public Map<RestEnum,Object> insert(@RequestBody Cart cart){
        Map<RestEnum,Object> cartMap = new LinkedHashMap<>();
        try {
            Cart newCart = cartRepo.saveAndFlush(cart);
            cartMap.put(RestEnum.status,true);
            cartMap.put(RestEnum.message,"Successfully added to cart");
            cartMap.put(RestEnum.result, newCart);
            response.setStatus(HttpServletResponse.SC_OK);
        } catch (Exception e){
            cartMap.put(RestEnum.status,false);
            cartMap.put(RestEnum.message,"Cart insert Fail");
            cartMap.put(RestEnum.result, cart);
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
        return cartMap;
    }

    @PostMapping("/delete")
    public ResponseEntity<Map<RestEnum,Object>> delete(@RequestBody Cart cart){
        Map<RestEnum,Object> cartMap = new LinkedHashMap<>();
        ResponseEntity<Map<RestEnum,Object>> responseEntity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        try {
            cartRepo.deleteById(cart.getCartid());
            cartMap.put(RestEnum.status,true);
            cartMap.put(RestEnum.message,"Successfully removed from cart");
            cartMap.put(RestEnum.result,cart.getCartid());
            responseEntity = new ResponseEntity<>(cartMap,HttpStatus.OK);
        } catch (Exception e){
            cartMap.put(RestEnum.status,false);
            cartMap.put(RestEnum.message,"Cart delete fail");
            cartMap.put(RestEnum.result,cart.getCartid());
            responseEntity = new ResponseEntity<>(cartMap,HttpStatus.BAD_REQUEST);
        }
        return responseEntity;
    }
}
