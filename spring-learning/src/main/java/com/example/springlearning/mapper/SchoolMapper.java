package com.example.springlearning.mapper;

import com.example.springlearning.entity.pojo.School;

public interface SchoolMapper {
    void insertSchool(School school);

    void updateSchool(School school);

    void deleteSchoolById(Long id);

    School selectSchoolById(Long id);
}
