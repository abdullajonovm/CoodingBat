package com.example.codingbat.controller;

import com.example.codingbat.dto.ApiResponse;
import com.example.codingbat.dto.TestingOptionsDTO;
import com.example.codingbat.service.TestingOptionsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/testingOptions")
@RequiredArgsConstructor
public class TestingOptionsController {
    final TestingOptionsService testingOptionsService;

    @GetMapping("/{id}")
    public HttpEntity<?> getOne(@PathVariable Integer id) {
        ApiResponse apiResponse = testingOptionsService.findById(id);
        return ResponseEntity.status(apiResponse.getSucces() ? HttpStatus.OK : HttpStatus.NO_CONTENT).body(apiResponse);
    }

    @GetMapping
    public HttpEntity<?> getAll() {
        ApiResponse apiResponse = testingOptionsService.getAll();
        return ResponseEntity.status(apiResponse.getSucces() ? HttpStatus.OK : HttpStatus.NO_CONTENT).body(apiResponse);
    }

    @PostMapping
    public HttpEntity<?> add(@RequestBody TestingOptionsDTO testingOptionsDTO) {
        ApiResponse apiResponse = testingOptionsService.add(testingOptionsDTO);
        return ResponseEntity.status(apiResponse.getSucces() ? HttpStatus.OK : HttpStatus.NO_CONTENT).body(apiResponse);
    }

    @PutMapping("/{id}")
    public HttpEntity<?> edit(@PathVariable Integer id, @RequestBody TestingOptionsDTO testingOptionsDTO) {
        ApiResponse apiResponse = testingOptionsService.edit(id, testingOptionsDTO);
        return ResponseEntity.status(apiResponse.getSucces() ? HttpStatus.OK : HttpStatus.NO_CONTENT).body(apiResponse);
    }

    @DeleteMapping("/{id}")
    public HttpEntity<?> delet(@PathVariable Integer id) {
        ApiResponse apiResponse = testingOptionsService.delet(id);
        return ResponseEntity.status(apiResponse.getSucces() ? HttpStatus.OK : HttpStatus.NO_CONTENT).body(apiResponse);
    }


}
