package com.example.codingbat.service;

import com.example.codingbat.dto.ApiResponse;
import com.example.codingbat.dto.QuestionDTO;
import com.example.codingbat.entity.Category;
import com.example.codingbat.entity.Question;
import com.example.codingbat.repository.CategoryRepository;
import com.example.codingbat.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class QusetionService {

    final QuestionRepository questionRepository;
    final CategoryRepository categoryRepository;

    public ApiResponse findById(Integer id) {
        Optional<Question> byId = questionRepository.findById(id);
        if (byId.isPresent()) {
            return new ApiResponse("Mana", true, byId.get());
        }
        return new ApiResponse("The question in this id does not exist", false);
    }

    public ApiResponse getAll() {
        List<Question> all = questionRepository.findAll();
        if (all.size() == 0) {
            return new ApiResponse("The question list is Empty", false);
        }
        return new ApiResponse("Mana", true, all);
    }

    public ApiResponse add(QuestionDTO questionDTO) {
        if (questionRepository.existsByQuestion(questionDTO.getQuestion())) {
            return new ApiResponse("This question allready exist", false);
        }

        Optional<Category> categoryOptional = categoryRepository.findById(questionDTO.getCategoryId());
        if (!categoryOptional.isPresent()) {
            return new ApiResponse("There is no category in this id", false);
        }

        Question question = new Question();
        question.setQuestion(questionDTO.getQuestion());
        question.setCategory(categoryOptional.get());
        questionRepository.save(question);
        return new ApiResponse("This question succesfully added", true);

    }

    public ApiResponse edit(Integer id, QuestionDTO questionDTO) {

        if (questionRepository.existsByQuestion(questionDTO.getQuestion())) {
            return new ApiResponse("This question allready exist", false);
        }

        Optional<Category> categoryOptional = categoryRepository.findById(questionDTO.getCategoryId());
        if (!categoryOptional.isPresent()) {
            return new ApiResponse("There is no category in this id", false);
        }
        Optional<Question> questionOptional = questionRepository.findById(id);
        if (!questionOptional.isPresent()){
            return new ApiResponse("There is no question in this id", false);
        }
        Question question = questionOptional.get();
        question.setQuestion(questionDTO.getQuestion());
        question.setCategory(categoryOptional.get());
        questionRepository.save(question);
        return new ApiResponse("This question succesfully edited", true);

    }

    public ApiResponse delet(Integer id) {
        Optional<Question> questionOptional = questionRepository.findById(id);
        if (!questionOptional.isPresent()) {
            return new ApiResponse("There is no question in this id", false);
        }
        questionRepository.deleteById(id);
        return new ApiResponse("This question succesfully deleted", true);
    }
}
