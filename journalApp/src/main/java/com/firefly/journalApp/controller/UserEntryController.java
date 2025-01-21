package com.firefly.journalApp.controller;

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

import com.firefly.journalApp.entity.User;
import com.firefly.journalApp.service.UserEntryService;

@RestController
@RequestMapping("/user") 
public class UserEntryController {
    @Autowired
    public UserEntryService userEntryService;
    @GetMapping
    public ResponseEntity<?> getAll()
    {
        List<User> all = userEntryService.getAll();
        if(!all.isEmpty() && all != null)
        {
            return new ResponseEntity<>(all,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<User> createEntry(@RequestBody User myEntry){
        try{
            userEntryService.saveEntry(myEntry);
            return new ResponseEntity<>(myEntry,HttpStatus.CREATED);
        }
        catch(Exception e)
        {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping("/userid/{myId}")    
    public ResponseEntity<User> getUserEntrybyId(@PathVariable ObjectId myId)
    {
        //return journalEntryService.findById(myId).orElse(null);
        Optional<User> userEntry = userEntryService.findById(myId);
        if(userEntry.isPresent())
        {
            return new ResponseEntity<>(userEntry.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/userid/{username}")
    public ResponseEntity<?> updateUser(@PathVariable String username, @RequestBody User user)
    {
        User old_entry = userEntryService.findbyUserName(username);
        if(old_entry != null)
        {
            old_entry.setUserName(user.getUserName());
            old_entry.setPassWord(user.getPassWord());
            userEntryService.saveEntry(old_entry);
            return new ResponseEntity<>(old_entry, HttpStatus.OK);
        }        
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    } 


    @DeleteMapping("/userid/{myId}")
    public ResponseEntity<?> deleteUser(@PathVariable ObjectId myId)
    {
        userEntryService.deleteID(myId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }  

}
