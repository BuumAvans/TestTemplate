package com.test.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class HttpRequestTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private MockMvc mockMvc;

    /*
    Get Request test driven development TestRestTemplate for expecting a String
     */
    @Test
    public void requestShouldReturnHelloWorld() throws Exception{
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/person",
                   String.class).contains("Hello world"));
    }

    /*
    Get Request test driven development MVC example with String
     */
    @Test
    public void requestShouldReturnHelloWorldMockMvc() throws Exception {
        this.mockMvc.perform(get("/person")).andDo(print())
                             .andExpect(status().isOk())
                             .andExpect(content().string("Hello World"));
    }

    @Test
    public void matchPerson() throws Exception {
        Person person = new Person();
        person.setName("Max");
        person.setAge(21);

        Person person1 = this.restTemplate.getForObject("http://localhost:" + port + "/person/person",
                Person.class);

        assertThat(person1.getName()).isEqualTo(person.getName());
        assertThat(person1.getAge()).isEqualTo(person.getAge());
    }

    @Test
    public void doesNotMatchPerson() throws Exception {

        Person person = new Person();
        person.setName("Henk");
        person.setAge(55);

        Person person1 = this.restTemplate.getForObject("http://localhost:" + port + "/person/person",
                Person.class);

        assertThat(person1.getName()).isNotEqualTo(person.getName());
        assertThat(person1.getAge()).isNotEqualTo(person.getAge());
    }
    
}
