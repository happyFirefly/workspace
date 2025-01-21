package com.firefly.journalApp.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.firefly.journalApp.entity.JournalEntity;
import com.firefly.journalApp.service.JournalEntryService;

@RestController
@RequestMapping("/journal")
public class JourneyEntryController {

    @Autowired
    public JournalEntryService journalEntryService;
    @GetMapping
    public ResponseEntity<?> getAll()
    {
        List<JournalEntity> all = journalEntryService.getAll();
        if(!all.isEmpty() && all != null)
        {
            return new ResponseEntity<>(all,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<JournalEntity> createEntry(@RequestBody JournalEntity myEntry){
        try{
            myEntry.setDate(LocalDateTime.now());
            journalEntryService.saveEntry(myEntry);
            return new ResponseEntity<>(myEntry,HttpStatus.CREATED);
        }
        catch(Exception e)
        {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping("/id/{myId}")    
    public ResponseEntity<JournalEntity> getJournalEntitybyId(@PathVariable ObjectId myId)
    {
        //return journalEntryService.findById(myId).orElse(null);
        Optional<JournalEntity> journalEntry = journalEntryService.findById(myId);
        if(journalEntry.isPresent())
        {
            return new ResponseEntity<>(journalEntry.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/id/{myId}")
    public ResponseEntity<?> updateJournalEntity(@PathVariable ObjectId myId, @RequestBody JournalEntity newEntity)
    {
        JournalEntity old_entry = journalEntryService.findById(myId).orElse(null);
        if(old_entry != null)
        {
            old_entry.setDate(LocalDateTime.now());
            old_entry.setTitle(newEntity.getTitle()!= null && !newEntity.getTitle().equals("")? newEntity.getTitle() : old_entry.getTitle());
            old_entry.setContent(newEntity.getContent()!= null && !newEntity.getContent().equals("")? newEntity.getContent() : old_entry.getContent());
            journalEntryService.saveEntry(old_entry);
            return new ResponseEntity<>(old_entry, HttpStatus.OK);
        }

        
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    } 

    @DeleteMapping("/id/{myId}")
    public ResponseEntity<?> deleteJournalEntity(@PathVariable ObjectId myId)
    {
        journalEntryService.deleteID(myId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }  

}

// FULLY qualified name 

//  ResponseEntity<?> wild card pattern , anything can be passed

//lombok is a popular library in the java ecosystem, often used in spring boot application, aims to reduce the boilerplate code that
// that developers have to write such as getters setters constructorts and more

// LOMBOK achieves this by genearting this code automatically during compilation based on annotations 
// you add to your java class