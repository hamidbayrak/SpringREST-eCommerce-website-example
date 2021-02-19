package com.example.mvcp.restclient;


import com.example.mvcp.restclient.models.product.ProductRest;
import com.example.mvcp.restclient.models.product.Result;
import com.example.mvcp.restclient.models.product.ProductSingleRest;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class ProductService {

    public List<Result> productList() {
        String url = "http://localhost:9090/product/list";
        RestTemplate restTemplate = new RestTemplate();
        ProductRest dataObj = restTemplate.getForObject(url, ProductRest.class);
        List<Result> resultList = dataObj.getResult();
        return resultList;
    }

    public List<Result> findByCid(int cid) {
        String url = "http://localhost:9090/product/findbycid?id="+cid;
        RestTemplate restTemplate = new RestTemplate();
        ProductRest dataObj = restTemplate.getForObject(url, ProductRest.class);
        List<Result> resultList = dataObj.getResult();
        return resultList;
    }

    public Result findByPid(int pid){
        String url="http://localhost:9090/product/findbypid?id="+pid;
        RestTemplate restTemplate = new RestTemplate();
        ProductSingleRest dataObj = restTemplate.getForObject(url,ProductSingleRest.class);
        Result productResult = dataObj.getResult();
        return productResult;
    }
}
