package com.example.codingbat.controller;

import com.example.codingbat.dto.ApiResponse;
import com.example.codingbat.dto.UserDTO;
import com.example.codingbat.service.UserCervice;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {
    final UserCervice userCervice;

    @PostMapping
    public HttpEntity<?> add(@RequestBody UserDTO userDTO) {
        ApiResponse apiResponse = userCervice.add(userDTO);
        return ResponseEntity.status(apiResponse.getSucces() ? HttpStatus.OK : HttpStatus.NO_CONTENT).body(apiResponse);
    }

    @PutMapping("/{id}")
    public HttpEntity<?> edit(@PathVariable Integer id, @RequestBody UserDTO userDTO) {
        ApiResponse apiResponse = userCervice.edit(id, userDTO);
        return ResponseEntity.status(apiResponse.getSucces() ? HttpStatus.OK : HttpStatus.NO_CONTENT).body(apiResponse);
    }

}
