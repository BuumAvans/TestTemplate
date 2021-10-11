package com.test.demo;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class HttpPostRequestTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired PersonService personService;

    @Test
    public void Post_Should_Post_A_Person() throws Exception{
        Person person = new Person("Henk",40);

        mockMvc.perform(post("/person/register").content(asJsonString(person))
                .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
    }

    @Test
    public void Post_Person_Is_Not_Allowed() throws Exception{
        Person person = new Person();
        person.setName("Hans");

        mockMvc.perform(post("/person/").content(asJsonString(person))
                .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isMethodNotAllowed());
    }

    public static String asJsonString(Person person){
        try {
            return new ObjectMapper().writeValueAsString(person);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}
