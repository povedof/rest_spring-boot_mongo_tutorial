package com.povedof.rest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;


@RunWith(SpringRunner.class)
@SpringBootTest
public class RestSpringBootMongoTutorialApplicationTests {

    private MockMvc mockMvc;
    static final String PETS_RESOURCE = "/pets/";

    @Autowired
    WebApplicationContext applicationContext;


    @Before
    public void setup() {
        this.mockMvc = webAppContextSetup(this.applicationContext)
            .apply(springSecurity())
            .build();
    }

    @Test
    @WithMockUser(username = "user1", password="pwd")
    public void getAllPetsTest() throws Exception {
        this.mockMvc
            .perform(
                get(PETS_RESOURCE)
                    .accept(MediaType.APPLICATION_JSON)
            )
            .andExpect(status().isOk());
    }
	
    @Test
    @WithMockUser(username = "user1", password="pwd")
    public void postPetTest() throws Exception {

        this.mockMvc
            .perform(
                post(PETS_RESOURCE)
                    .content("{\"name\" : \"Liam2\", \"species\" : \"cat\", \"breed\" : \"tabby\"}")
                    .contentType(MediaType.APPLICATION_JSON)
            )
            .andExpect(status().isOk());

    }
	
}
