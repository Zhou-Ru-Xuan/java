package com.example.springlearning.dao;

import com.example.springlearning.entity.pojo.Person;

public interface PersonDao {
    Person getPersonById(Long id);
}
