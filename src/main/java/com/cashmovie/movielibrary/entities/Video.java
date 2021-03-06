package com.cashmovie.movielibrary.entities;

import com.sun.javafx.beans.IDProperty;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "videos")
public class Video {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    Long id;

    @Column(name = "title")
    String title;

    @Column(name = "path")
    String filePath;

    @Column(name = "author")
    String author;

    @Column(name = "date_uploaded")
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    Date date;

    @Column(name = "description")
    String description;

    public Video(){

    }

    public Video(String title, String filePath, String author, String description){
        this.title = title;
        this.filePath = filePath;
        this.author = author;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
