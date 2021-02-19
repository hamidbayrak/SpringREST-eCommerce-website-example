package com.example.mvcp.restcontrollers;

import com.example.mvcp.repositories.CategoryRepository;
import com.example.mvcp.util.RestEnum;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.Map;

@RestController
@RequestMapping("/category")
public class CategoryRestController {

    final CategoryRepository categoryRepo;

    public CategoryRestController(CategoryRepository categoryRepo) {
        this.categoryRepo = categoryRepo;
    }

    @GetMapping("/list")
    public Map<RestEnum,Object> list(){
        Map<RestEnum,Object> categoryMap = new LinkedHashMap<>();
        categoryMap.put(RestEnum.status, true);
        categoryMap.put(RestEnum.message,"Success");
        categoryMap.put(RestEnum.result,categoryRepo.findAll());
        return categoryMap;
    }
}
