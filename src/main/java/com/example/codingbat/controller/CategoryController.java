package com.example.codingbat.controller;

import com.example.codingbat.dto.ApiResponse;
import com.example.codingbat.dto.CategoryDTO;
import com.example.codingbat.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/category")
@RequiredArgsConstructor
public class CategoryController {
    final CategoryService categoryService;

    @GetMapping("/{id}")
    public HttpEntity<?> getOne(@PathVariable Integer id) {
        ApiResponse apiResponse = categoryService.findById(id);
        return ResponseEntity.status(apiResponse.getSucces() ? HttpStatus.OK : HttpStatus.NO_CONTENT).body(apiResponse);
    }

    @GetMapping
    public HttpEntity<?> getAll() {
        ApiResponse apiResponse = categoryService.getAll();
        return ResponseEntity.status(apiResponse.getSucces() ? HttpStatus.OK : HttpStatus.NO_CONTENT).body(apiResponse);
    }

    @PostMapping
    public HttpEntity<?> add(@RequestBody CategoryDTO categoryDTO) {
        ApiResponse apiResponse = categoryService.add(categoryDTO);
        return ResponseEntity.status(apiResponse.getSucces() ? HttpStatus.OK : HttpStatus.NO_CONTENT).body(apiResponse);
    }

    @PutMapping("/{id}")
    public HttpEntity<?> edit(@PathVariable Integer id, @RequestBody CategoryDTO categoryDTO) {
        ApiResponse apiResponse = categoryService.edit(id, categoryDTO);
        return ResponseEntity.status(apiResponse.getSucces() ? HttpStatus.OK : HttpStatus.NO_CONTENT).body(apiResponse);
    }

    @DeleteMapping("/{id}")
    public HttpEntity<?> delet(@PathVariable Integer id) {
        ApiResponse apiResponse = categoryService.delet(id);
        return ResponseEntity.status(apiResponse.getSucces() ? HttpStatus.OK : HttpStatus.NO_CONTENT).body(apiResponse);
    }

    

}
