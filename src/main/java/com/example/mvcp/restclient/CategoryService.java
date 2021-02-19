package com.example.mvcp.restclient;

import com.example.mvcp.restclient.models.category.CategoryRest;
import com.example.mvcp.restclient.models.category.Result;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
@Service
public class CategoryService {

    public List<Result> categoryList(){
        String url = "http://localhost:9090/category/list";
        RestTemplate restTemplate = new RestTemplate();
        CategoryRest dataObj = restTemplate.getForObject(url,CategoryRest.class);
        List<Result> resultList = dataObj.getResult();
        return resultList;
    }

}
