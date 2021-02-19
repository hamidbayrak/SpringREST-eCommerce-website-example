package com.example.mvcp.restcontrollers;

import com.example.mvcp.entities.Wishlist;
import com.example.mvcp.repositories.WishlistPropsRepository;
import com.example.mvcp.repositories.WishlistRepository;
import com.example.mvcp.util.RestEnum;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.LinkedHashMap;
import java.util.Map;

@RestController
@RequestMapping("/wishlist")
public class WishlistRestController {

    final WishlistRepository wishRepo;
    final WishlistPropsRepository wishlistPropsRepo;
    final HttpServletResponse response;

    public WishlistRestController(WishlistPropsRepository wishlistPropsRepo, WishlistRepository wishRepo, HttpServletResponse response) {
        this.wishlistPropsRepo = wishlistPropsRepo;
        this.response = response;
        this.wishRepo = wishRepo;
    }

    @GetMapping("/list")
    public Map<RestEnum, Object> list(@RequestParam (value = "id", required = true) String tuid) {
        int uid = Integer.parseInt(tuid);
        Map<RestEnum, Object> wishlistMap = new LinkedHashMap<>();
        wishlistMap.put(RestEnum.status, true);
        wishlistMap.put(RestEnum.message, "Success");
        wishlistMap.put(RestEnum.result, wishlistPropsRepo.fillwishlist(uid));
        return wishlistMap;
    }

    @PostMapping("/insert")
    public Map<RestEnum, Object> insert(@RequestBody Wishlist wishlist) {
        Map<RestEnum, Object> wishlistMap = new LinkedHashMap<>();
        try {
            Wishlist wish = wishRepo.saveAndFlush(wishlist);
            wishlistMap.put(RestEnum.status, true);
            wishlistMap.put(RestEnum.message, "Successfully added to Wishlist");
            wishlistMap.put(RestEnum.result, wish);
            response.setStatus(HttpServletResponse.SC_OK);
        } catch (Exception e) {
            wishlistMap.put(RestEnum.status, false);
            wishlistMap.put(RestEnum.message, "Insert Fail");
            wishlistMap.put(RestEnum.result, wishlist);
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
        return wishlistMap;
    }

    @PostMapping("/delete")
    public ResponseEntity<Map<RestEnum, Object>> delete(@RequestBody Wishlist wishlist) {
        Map<RestEnum, Object> wishlistMap = new LinkedHashMap<>();
        ResponseEntity<Map<RestEnum, Object>> responseEntity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        try {
            wishRepo.deleteById(wishlist.getWid());
            wishlistMap.put(RestEnum.status, true);
            wishlistMap.put(RestEnum.message, "Successfully removed from Wishlist");
            wishlistMap.put(RestEnum.result, wishlist.getWid());
            responseEntity = new ResponseEntity<>(wishlistMap, HttpStatus.OK);
        } catch (Exception e) {
            wishlistMap.put(RestEnum.status, false);
            wishlistMap.put(RestEnum.message, "Delete Fail");
            wishlistMap.put(RestEnum.result, wishlist.getWid());
            responseEntity = new ResponseEntity<>(wishlistMap, HttpStatus.BAD_REQUEST);
        }
        return responseEntity;
    }
}
