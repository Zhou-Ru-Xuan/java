package com.example.springlearning.dao;

import com.example.springlearning.entity.Person;

public interface PersonDao {
    Person getPersonById(Long id);
}
