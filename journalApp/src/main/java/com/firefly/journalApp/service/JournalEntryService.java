package com.firefly.journalApp.service;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.firefly.journalApp.entity.JournalEntity;
import com.firefly.journalApp.repository.JournalEntryRepository;

@Component
public class JournalEntryService {

    @Autowired
    private JournalEntryRepository journalEntryRepository;

    public void saveEntry(JournalEntity entity)
    {
        journalEntryRepository.save(entity);
    }

    public List<JournalEntity> getAll()
    {
        return journalEntryRepository.findAll();
    }

    public Optional<JournalEntity> findById(ObjectId id)
    {
        return journalEntryRepository.findById(id);
    }

    public void deleteID(ObjectId myId)
    {
        journalEntryRepository.deleteById(myId);
    }
}
