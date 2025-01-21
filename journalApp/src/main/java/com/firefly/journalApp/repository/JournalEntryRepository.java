package com.firefly.journalApp.repository;


import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.firefly.journalApp.entity.JournalEntity;

public interface JournalEntryRepository extends  MongoRepository<JournalEntity, ObjectId> {

}


// FLOW OF CODE
// controller -->service---> repository