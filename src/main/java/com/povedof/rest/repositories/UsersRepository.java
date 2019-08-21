package com.povedof.rest.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.povedof.rest.models.Users;

public interface UsersRepository extends MongoRepository<Users, String> {
  Users findByUsername(String username);
}
