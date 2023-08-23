package com.example.hudamilktea.api;

import com.example.hudamilktea.model.Category;
import com.example.hudamilktea.service.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/category")
@AllArgsConstructor
public class CategoryRestController {

    private final CategoryService categoryService;

    @GetMapping
    private List<Category> findAll() {
        return categoryService.findAll();
    }
}
