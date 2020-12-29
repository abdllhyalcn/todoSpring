package com.work.payload.response;

import com.work.models.Status;

import java.time.LocalDate;
import java.util.Date;

public class TodoResponse {
    private Long id;
    private Long user_id;
    private String description;
    private Date date_todo;
    private Long status;

    public TodoResponse(Long id, Long user_id, String description, Date date_todo, Long status) {
        this.id = id;
        this.user_id = user_id;
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

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
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

    public Long getStatus() {
        return status;
    }

    public void setStatus(Long status) {
        this.status = status;
    }
}
