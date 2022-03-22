package com.example.codingbat.service;

import com.example.codingbat.dto.ApiResponse;
import com.example.codingbat.dto.UserDTO;
import com.example.codingbat.entity.User;
import com.example.codingbat.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserCervice {
    final UserRepository userRepository;
    public ApiResponse add(UserDTO userDTO) {
        if (userRepository.existsByEmail(userDTO.getEmail())) {
            return new ApiResponse("This email allready exist", false);
        }
        User user = new User();
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());
        userRepository.save(user);
        return new ApiResponse("This user succesfully added", true);
    }

    public ApiResponse edit(Integer id, UserDTO userDTO) {
        Optional<User> byId = userRepository.findById(id);

        if (!byId.isPresent()){
            return new ApiResponse("The user in this id does not exist", false);
        }

        User user = byId.get();

        if (userDTO.getEmail().equals(user.getEmail()) && userRepository.existsByEmail(userDTO.getEmail())) {
            return new ApiResponse("This email allready exist", false);
        }
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());
        userRepository.save(user);
        return new ApiResponse("This user succesfully edited", true);
    }
}
