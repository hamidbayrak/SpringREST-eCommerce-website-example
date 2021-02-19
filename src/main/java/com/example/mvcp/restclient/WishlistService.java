package com.example.mvcp.restclient;

import com.example.mvcp.restclient.models.wishlist.WishlistPush;
import com.example.mvcp.restclient.models.wishlistprops.*;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


import java.util.List;

@Service
public class WishlistService {

    public List<Result> fillWishlist(int uid){
        String url = "http://localhost:9090/wishlist/list?id="+uid;
        RestTemplate restTemplate = new RestTemplate();
        WishlistPropsRest dataObj = restTemplate.getForObject(url, WishlistPropsRest.class);
        List<com.example.mvcp.restclient.models.wishlistprops.Result> resultList = dataObj.getResult();
        return resultList;
    }

    public void addToWishlist(int pid, int uid){
        String url = "http://localhost:9090/wishlist/insert";
        WishlistPush wishlistPush = new WishlistPush();
        wishlistPush.setPid(pid);
        wishlistPush.setUid(uid);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(url, wishlistPush, String.class);
    }

    public void delFromWishlist(int wid){
        String url = "http://localhost:9090/wishlist/delete";
        WishlistPush wishlistPush = new WishlistPush();
        wishlistPush.setWid(wid);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(url, wishlistPush, String.class);
    }

}
