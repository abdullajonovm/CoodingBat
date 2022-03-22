package com.example.codingbat.controller;

import com.example.codingbat.dto.ApiResponse;
import com.example.codingbat.dto.QuestionDTO;
import com.example.codingbat.service.QusetionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/question")
@RequiredArgsConstructor
public class QusetionController {
    final QusetionService qusetionService;

    @GetMapping("/{id}")
    public HttpEntity<?> getOne(@PathVariable Integer id) {
        ApiResponse apiResponse = qusetionService.findById(id);
        return ResponseEntity.status(apiResponse.getSucces() ? HttpStatus.OK : HttpStatus.NO_CONTENT).body(apiResponse);
    }

    @GetMapping
    public HttpEntity<?> getAll() {
        ApiResponse apiResponse = qusetionService.getAll();
        return ResponseEntity.status(apiResponse.getSucces() ? HttpStatus.OK : HttpStatus.NO_CONTENT).body(apiResponse);
    }

    @PostMapping
    public HttpEntity<?> add(@RequestBody QuestionDTO questionDTO) {
        ApiResponse apiResponse = qusetionService.add(questionDTO);
        return ResponseEntity.status(apiResponse.getSucces() ? HttpStatus.OK : HttpStatus.NO_CONTENT).body(apiResponse);
    }

    @PutMapping("/{id}")
    public HttpEntity<?> edit(@PathVariable Integer id, @RequestBody QuestionDTO questionDTO) {
        ApiResponse apiResponse = qusetionService.edit(id, questionDTO);
        return ResponseEntity.status(apiResponse.getSucces() ? HttpStatus.OK : HttpStatus.NO_CONTENT).body(apiResponse);
    }

    @DeleteMapping("/{id}")
    public HttpEntity<?> delet(@PathVariable Integer id) {
        ApiResponse apiResponse = qusetionService.delet(id);
        return ResponseEntity.status(apiResponse.getSucces() ? HttpStatus.OK : HttpStatus.NO_CONTENT).body(apiResponse);
    }


}
