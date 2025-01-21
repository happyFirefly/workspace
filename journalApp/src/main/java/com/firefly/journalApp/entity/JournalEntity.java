package com.firefly.journalApp.entity;

import java.time.LocalDateTime;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.mongodb.lang.NonNull;

import lombok.Data;

// POJO : plain old java object
@Document(collection="journal_entries")
// @Getter
// @Setter
// @NoArgsConstructor
// @AllArgsConstructor
// @ToString
// @EqualsAndHashCode
// @Builder
//REplacemnet for all the above in the below
@Data
public class JournalEntity {

    @Id
    private ObjectId id;
    @NonNull
    private String title;

    private String content;

    private LocalDateTime date;
    

    // public void setDate(LocalDateTime date) {
    //     this.date = date;
    // }

    // public LocalDateTime getDate() {
    //     return date;
    // }

    // public ObjectId getId() {
    //     return id;
    // }

    // public String getTitle() {
    //     return title;
    // }

    // public String getContent() {
    //     return content;
    // }

    // public void setId(ObjectId id) {
    //     this.id = id;
    // }

    // public void setTitle(String title) {
    //     this.title = title;
    // }

    // public void setContent(String content) {
    //     this.content = content;
    // }

    
}
