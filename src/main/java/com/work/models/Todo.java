package com.work.models;


import javax.persistence.*;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@Table(	name = "todos")
public class Todo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Size(max = 150)
    @NotBlank
    private String description;

    @FutureOrPresent(message = "Date input is invalid because past.")
    private Date date_todo;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name="status", nullable = false)
    private Status status;

    public Todo(){}

    public Todo(User user, @Size(max = 150) @NotBlank String description, @NotBlank Date date_todo, Status status) {
        this.user = user;
        this.description = description;
        this.date_todo = date_todo;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDate_todo() {
        return date_todo;
    }

    public void setDate_todo(Date date_todo) {
        this.date_todo = date_todo;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
