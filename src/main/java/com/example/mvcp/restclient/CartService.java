package com.example.mvcp.restclient;

import com.example.mvcp.restclient.models.cart.CartPush;
import com.example.mvcp.restclient.models.cartprops.CartPropsRest;
import com.example.mvcp.restclient.models.cartprops.Result;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class CartService {

    public List<Result> fillCart(int uid){
        String url = "http://localhost:9090/cart/list?id="+uid;
        RestTemplate restTemplate = new RestTemplate();
        CartPropsRest dataObj = restTemplate.getForObject(url,CartPropsRest.class);
        List<Result> resultList = dataObj.getResult();
        return resultList;
    }

    public void addToCart(int uid, int pid, int quantity){
        String url = "http://localhost:9090/cart/insert";
        CartPush cartPush = new CartPush();
        cartPush.setUid(uid);
        cartPush.setPid(pid);
        cartPush.setQuantity(quantity);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(url,cartPush,String.class);
    }

    public void delFromCart(int cartid){
        String url = "http://localhost:9090/cart/delete";
        CartPush cartPush = new CartPush();
        cartPush.setCartid(cartid);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(url,cartPush,String.class);
    }
}
