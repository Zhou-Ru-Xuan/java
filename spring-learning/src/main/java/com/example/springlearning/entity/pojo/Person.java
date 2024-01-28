package com.example.springlearning.entity.pojo;

import com.example.springlearning.model.School;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import java.util.List;

@Component
@NoArgsConstructor
@AllArgsConstructor
@ToString
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

    private String dName;

    private boolean vPoiInPromiseBlackList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public List<Integer> getNumberList() {
        return numberList;
    }

    public void setNumberList(List<Integer> numberList) {
        this.numberList = numberList;
    }

    public Integer[] getNumberList2() {
        return numberList2;
    }

    public void setNumberList2(Integer[] numberList2) {
        this.numberList2 = numberList2;
    }

    public School getSchool() {
        return school;
    }

    public void setSchool(School school) {
        this.school = school;
    }

    public String getdName() {
        return dName;
    }

    public void setdName(String dName) {
        this.dName = dName;
    }

    public boolean isvPoiInPromiseBlackList() {
        return vPoiInPromiseBlackList;
    }

    public void setvPoiInPromiseBlackList(boolean vPoiInPromiseBlackList) {
        this.vPoiInPromiseBlackList = vPoiInPromiseBlackList;
    }
}
