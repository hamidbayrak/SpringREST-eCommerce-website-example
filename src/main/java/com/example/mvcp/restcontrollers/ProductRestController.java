package com.example.mvcp.restcontrollers;

import com.example.mvcp.entities.Product;
import com.example.mvcp.repositories.ProductRepository;
import com.example.mvcp.util.RestEnum;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.Map;

@RestController
@RequestMapping("/product")
public class ProductRestController {

    final ProductRepository productRepo;

    public ProductRestController(ProductRepository productRepo) {
        this.productRepo = productRepo;
    }

    @GetMapping("/list")
    public Map<RestEnum, Object> list(){
        Map<RestEnum,Object> productMap = new LinkedHashMap<>();
        productMap.put(RestEnum.status, true);
        productMap.put(RestEnum.message,"Success");
        productMap.put(RestEnum.result, productRepo.findAll());
        return productMap;
    }

    @GetMapping("/findbycid{tcid}")
    public Map<RestEnum, Object> productByCid(@RequestParam(value = "id", required = true) String tcid){
        int cid = Integer.parseInt(tcid);
        Map<RestEnum,Object> productMap = new LinkedHashMap<>();
        productMap.put(RestEnum.status, true);
        productMap.put(RestEnum.message,"Success");
        productMap.put(RestEnum.result, productRepo.findByCid(cid));
        return productMap;
    }

    @GetMapping("/findbypid{tpid}")
    public Map<RestEnum, Object> productByPid(@RequestParam(value = "id", required = true) String tpid){
        int pid = Integer.parseInt(tpid);
        Map<RestEnum,Object> productMap = new LinkedHashMap<>();
        productMap.put(RestEnum.status, true);
        productMap.put(RestEnum.message,"Success");
        productMap.put(RestEnum.result, productRepo.findByPid(pid));
        return productMap;
    }
}
