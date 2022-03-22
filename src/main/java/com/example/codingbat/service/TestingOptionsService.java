package com.example.codingbat.service;

import com.example.codingbat.dto.ApiResponse;
import com.example.codingbat.dto.QuestionDTO;
import com.example.codingbat.dto.TestingOptionsDTO;
import com.example.codingbat.entity.Category;
import com.example.codingbat.entity.Question;
import com.example.codingbat.entity.TestingOptions;
import com.example.codingbat.repository.CategoryRepository;
import com.example.codingbat.repository.QuestionRepository;
import com.example.codingbat.repository.TestingOptionsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TestingOptionsService {

    final QuestionRepository questionRepository;
    final TestingOptionsRepository testingOptionsRepository;

    public ApiResponse findById(Integer id) {
        Optional<TestingOptions> byId = testingOptionsRepository.findById(id);
        if (byId.isPresent()) {
            return new ApiResponse("Mana", true, byId.get());
        }
        return new ApiResponse("The testingOptions in this id does not exist", false);
    }

    public ApiResponse getAll() {
        List<TestingOptions> all = testingOptionsRepository.findAll();
        if (all.size() == 0) {
            return new ApiResponse("The question list is Empty", false);
        }
        return new ApiResponse("Mana", true, all);
    }

    public ApiResponse add(TestingOptionsDTO testingOptionsDTO) {
        Optional<Question> questionOptional = questionRepository.findById(testingOptionsDTO.getQuestionId());
        if (!questionOptional.isPresent()) {
            return new ApiResponse("There is no category in this id", false);
        }

        TestingOptions testingOptions = new TestingOptions();
        testingOptions.setQuestion(questionOptional.get());
        testingOptions.setInput(testingOptionsDTO.getInput());
        testingOptions.setOutput(testingOptions.getOutput());
        testingOptionsRepository.save(testingOptions);
        return new ApiResponse("This testingOptions succesfully added", true);

    }

    public ApiResponse edit(Integer id, TestingOptionsDTO testingOptionsDTO) {
        Optional<Question> questionOptional = questionRepository.findById(testingOptionsDTO.getQuestionId());
        if (!questionOptional.isPresent()) {
            return new ApiResponse("There is no category in this id", false);
        }
        Optional<TestingOptions> testingOptionsOptional = testingOptionsRepository.findById(id);
        if (!testingOptionsOptional.isPresent()) {
            return new ApiResponse("The testingOptions in this id does not exist", false);
        }
        TestingOptions testingOptions = testingOptionsOptional.get();
        testingOptions.setQuestion(questionOptional.get());
        testingOptions.setInput(testingOptionsDTO.getInput());
        testingOptions.setOutput(testingOptions.getOutput());
        testingOptionsRepository.save(testingOptions);
        return new ApiResponse("This testingOptions succesfully edited", true);

    }

    public ApiResponse delet(Integer id) {
        Optional<TestingOptions> byId = testingOptionsRepository.findById(id);
        if (!byId.isPresent()) {
            return new ApiResponse("The testingOptions in this id does not exist", false);
        }
        testingOptionsRepository.deleteById(id);
        return new ApiResponse("This question succesfully deleted", true);
    }
}
