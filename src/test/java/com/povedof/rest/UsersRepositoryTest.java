package com.povedof.rest;

import static org.junit.Assert.assertEquals;

import org.bson.types.ObjectId;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.povedof.rest.models.Users;
import com.povedof.rest.repositories.UsersRepository;

@RunWith(SpringRunner.class)
@DataMongoTest
public class UsersRepositoryTest {
	
	@Autowired
	private UsersRepository usersRepository;
	
    @Test
    public void mapping() {
    	ObjectId object_Id_1 = new ObjectId();
    	String userNameStr = "userTest";
    	String passwordStr = "password";
    	
    	Users user = Users.builder().username(userNameStr).password(passwordStr)._id(object_Id_1).build();
        this.usersRepository.save(user);
        Users userRetrieved = this.usersRepository.findByUsername(userNameStr);
        
        assertEquals(object_Id_1.toHexString(), userRetrieved.get_id());
        assertEquals(passwordStr, userRetrieved.getPassword());
        
        this.usersRepository.delete(user);
    }

}
