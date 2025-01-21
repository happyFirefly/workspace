package com.firefly.journalApp.repository;


import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.firefly.journalApp.entity.User;

public interface UserEntryRepository extends  MongoRepository<User, ObjectId>{
   User findByUserName(String userName);
}
