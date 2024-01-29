package com.example.springlearning.entity.pojo;

import lombok.*;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;

@Component
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
public class Person {
    @NotNull(message = "id cannot be null")
    private Long id;
    private String name;
    private Integer age;
    private School school;
}
