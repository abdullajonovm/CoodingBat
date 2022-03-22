package com.example.codingbat.service;

import com.example.codingbat.dto.ApiResponse;
import com.example.codingbat.dto.CategoryDTO;
import com.example.codingbat.entity.Category;
import com.example.codingbat.entity.Subject;
import com.example.codingbat.repository.CategoryRepository;
import com.example.codingbat.repository.SubjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryService {

    final CategoryRepository categoryRepository;
    final SubjectRepository subjectRepository;

    public ApiResponse findById(Integer id) {
        Optional<Category> byId = categoryRepository.findById(id);
        if (byId.isPresent()) {
            return new ApiResponse("Mana", true, byId.get());
        }
        return new ApiResponse("The category in this id does not exist", false);
    }

    public ApiResponse getAll() {
        List<Category> all = categoryRepository.findAll();
        if (all.size() == 0) {
            return new ApiResponse("The categorys list is Empty", false);
        }
        return new ApiResponse("Mana", true, all);
    }

    public ApiResponse add(CategoryDTO categoryDTO) {
        if (categoryRepository.existsByName(categoryDTO.getName())) {
            return new ApiResponse("This category allready exist", false);
        }

        Optional<Subject> byId = subjectRepository.findById(categoryDTO.getSubjectId());
        if (!byId.isPresent()) {
            return new ApiResponse("There is no subject in this id", false);
        }

        Category category = new Category();
        category.setName(categoryDTO.getName());
        category.setSubject(byId.get());
        categoryRepository.save(category);
        return new ApiResponse("This category succesfully added", true);

    }

    public ApiResponse edit(Integer id, CategoryDTO categoryDTO) {
        if (categoryRepository.existsByName(categoryDTO.getName())) {
            return new ApiResponse("This category allready exist", false);
        }

        Optional<Subject> byId = subjectRepository.findById(categoryDTO.getSubjectId());
        if (!byId.isPresent()) {
            return new ApiResponse("There is no subject in this id", false);
        }

        Optional<Category> categoryOptional = categoryRepository.findById(id);
        if (!categoryOptional.isPresent()) {
            return new ApiResponse("There is no category in this id", false);
        }
        Category category = categoryOptional.get();
        category.setName(categoryDTO.getName());
        category.setSubject(byId.get());
        categoryRepository.save(category);
        return new ApiResponse("This category succesfully edited", true);
    }

    public ApiResponse delet(Integer id) {
        Optional<Category> categoryOptional = categoryRepository.findById(id);
        if (!categoryOptional.isPresent()) {
            return new ApiResponse("There is no category in this id", false);
        }
        categoryRepository.deleteById(id);
        return new ApiResponse("This category succesfully deleted", true);
    }
}
