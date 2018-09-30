package com.soda.nse.batch.repository;

import com.soda.nse.batch.model.Users;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface UserRepository extends MongoRepository<Users, Integer> {


}
