package com.povedof.rest;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.bson.types.ObjectId;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.povedof.rest.controllers.PetsController;
import com.povedof.rest.models.Pets;
import com.povedof.rest.repositories.PetsRepository;

@WebMvcTest(controllers = PetsController.class, secure = false)
@RunWith(SpringRunner.class)
public class PetsControllerTest {
	
    private static final ObjectId OBJECT_ID_1 = new ObjectId();
    private static final ObjectId OBJECT_ID_2 = new ObjectId();
	
	@MockBean
	PetsRepository petsRepository;
	
    @Autowired
    MockMvc mockMvc;

    @Before
    public void setUp() {
    	Pets pet = Pets.builder().breed("breed").name("name").species("species")._id(OBJECT_ID_1).build();
    	
        given(this.petsRepository.findBy_id(OBJECT_ID_1)).willReturn(pet);

        given(this.petsRepository.findBy_id(OBJECT_ID_2)).willReturn(null);
    }

    @Test
    public void getByIdTest() throws Exception {

        this.mockMvc
            .perform(
                get(RestSpringBootMongoTutorialApplicationTests.PETS_RESOURCE + "{id}/", OBJECT_ID_1)
                    .accept(MediaType.APPLICATION_JSON)
            )
           .andExpect(status().isOk())
           .andExpect(jsonPath("$.name").value("name"));

        verify(this.petsRepository, times(1)).findBy_id(any(ObjectId.class));
        verifyNoMoreInteractions(this.petsRepository);
    }
    
    @Test
    public void getByIdTestNotFound() throws Exception {

    	MvcResult mvcResult = this.mockMvc
            .perform(
                    get(RestSpringBootMongoTutorialApplicationTests.PETS_RESOURCE + "{id}/", OBJECT_ID_2)
                    .accept(MediaType.APPLICATION_JSON)
            )
            .andExpect(status().isOk())
            .andReturn();

    	assert(mvcResult.getResponse().getContentAsString().isEmpty());
    	
        verify(this.petsRepository, times(1)).findBy_id(any(ObjectId.class));
        verifyNoMoreInteractions(this.petsRepository);
    }

}
