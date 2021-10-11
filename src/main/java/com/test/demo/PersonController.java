package com.test.demo;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/person")
public class PersonController {

    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping("")
    public String getHello(){
        return "Hello World";
    }

    @GetMapping("/person")
    public Person getPerson(){
       Person person = new Person();
       person.setAge(21);
       person.setName("Max");

       return person;
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerPerson(@RequestBody Person person){
        personService.createPerson(person);
        return ResponseEntity.ok("Person is created");
    }
}
