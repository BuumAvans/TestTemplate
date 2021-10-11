package com.test.demo;

import org.springframework.data.jpa.repository.JpaRepository;



public interface PersonRepository extends JpaRepository<Person, Long> {
    Person findPersonByName(String name);
    Iterable<Person> findPersonByAgeIsLessThan(int age);
}
