package com.povedof.rest;

import static org.junit.Assert.assertEquals;

import org.bson.types.ObjectId;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.povedof.rest.models.Pets;
import com.povedof.rest.repositories.PetsRepository;

@RunWith(SpringRunner.class)
@DataMongoTest
public class PetsRepositoryTest {
	
	@Autowired
	private PetsRepository petsRepository;
	
    @Test
    public void mapping() {
    	ObjectId object_Id_1 = new ObjectId();
    	String breedStr = "breed";
    	String nameStr = "name";
    	String speciesStr = "species";
    	
    	Pets pet = Pets.builder().breed(breedStr).name(nameStr).species(speciesStr)._id(object_Id_1).build();
        this.petsRepository.save(pet);
        Pets petRetrieved = this.petsRepository.findBy_id(object_Id_1);
        
        assertEquals(breedStr, petRetrieved.getBreed());
        assertEquals(nameStr, petRetrieved.getName());
        assertEquals(speciesStr, petRetrieved.getSpecies());
        
        this.petsRepository.delete(pet);
    }

}
