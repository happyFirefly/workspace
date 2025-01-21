package com.firefly.journalApp.service;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.firefly.journalApp.entity.User;
import com.firefly.journalApp.repository.UserEntryRepository;

@Component
public class UserEntryService {

    @Autowired
    private UserEntryRepository userEntryRepository;

    public void saveEntry(User user)
    {
        userEntryRepository.save(user);
    }

    public List<User> getAll()
    {
        return userEntryRepository.findAll();
    }

    public Optional<User> findById(ObjectId id)
    {
        return userEntryRepository.findById(id);
    }

    public void deleteID(ObjectId myId)
    {
        userEntryRepository.deleteById(myId);
    }

    public User findbyUserName(String userName)
    {
        return userEntryRepository.findByUserName(userName);
    }
}



