package com.example.eventapp.entity;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Event {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    private LocalDateTime timestamp = LocalDateTime.now();
    @ManyToOne
    private User creator;
    public Long getId() { return id; }
    public String getTitle() { return title; }
    public void setTitle(String t) { title = t; }
    public String getDescription() { return description; }
    public void setDescription(String d) { description = d; }
    public LocalDateTime getTimestamp() { return timestamp; }
    public User getCreator() { return creator; }
    public void setCreator(User u) { creator = u; }
}
