package com.test.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class JpaDataTest {

    @Autowired
    private PersonRepository personRepository;

    @Test
    public void should_find_all_persons(){
        createFakePersons();

        Iterable<Person> people = personRepository.findAll();

        int nOfPersons = 4;
        assertThat(people).hasSize(nOfPersons);
    }

    @Test
    public void should_find_less_persons(){
        createFakePersons();

        Iterable<Person> people = personRepository.findAll();

        int nOfPersons = 3;
        assertThat(people).hasSizeGreaterThan(nOfPersons);
    }

    @Test
    public void should_find_person_with_specific_name(){
        createFakePersons();

        Person person = personRepository.findPersonByName("Max");

        assertThat(person.getName()).isEqualTo("Max");
        assertThat(person.getAge()).isEqualTo(21);
    }

    @Test
    public void find_persons_under_age_of_70(){
        createFakePersons();
        Iterable<Person> people = personRepository.findPersonByAgeIsLessThan(70);

        int nOfPersons = 3;

        assertThat(people).hasSize(nOfPersons);
    }

    public void createFakePersons(){
        Person person = new Person("Hans",18);
        Person person2 = new Person("Kees", 100);
        Person person3 = new Person("Sjaak", 25);
        Person person4 = new Person("Max", 21);

        personRepository.save(person);
        personRepository.save(person2);
        personRepository.save(person3);
        personRepository.save(person4);
    }
}
