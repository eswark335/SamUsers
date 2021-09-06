package com.sampana.users.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.sampana.users.entity.Address;

@Repository
public interface AddressRepo extends MongoRepository<Address, String> {

}
