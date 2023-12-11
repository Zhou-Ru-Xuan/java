package com.example.springlearning.entity;

import com.example.springlearning.model.School;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import java.util.List;

@Component
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Person {
    @NotNull(message = "id cannot be null")
    private Long id;
    private String name;
    private Integer age;

    @Value("${numberList}")
    private List<Integer> numberList;

    @Value("${numberList}")
    private Integer[] numberList2;

    private School school;
}
