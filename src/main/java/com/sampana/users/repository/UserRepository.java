package com.sampana.users.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.sampana.users.entity.User;

@Repository
public interface UserRepository extends MongoRepository<User, String> {

}
