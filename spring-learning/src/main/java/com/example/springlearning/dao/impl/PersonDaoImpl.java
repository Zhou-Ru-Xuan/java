package com.example.springlearning.dao.impl;

import com.example.springlearning.dao.PersonDao;
import com.example.springlearning.entity.Person;
import org.springframework.stereotype.Repository;

@Repository
public class PersonDaoImpl implements PersonDao {
    @Override
    public Person getPersonById(Long id) {
        Person person = new Person();
        person.setId(id);
        return person;
    }
}
