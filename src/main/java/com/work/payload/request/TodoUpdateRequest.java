package com.work.payload.request;

import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.Date;

public class TodoUpdateRequest {

    private Long todo_id;

    @Size(min = 3, max = 150)
    private String description;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private Date date_todo;

    private long status;

    public Long getTodo_id() {
        return todo_id;
    }

    public void setTodo_id(Long todo_id) {
        this.todo_id = todo_id;
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

    public long getStatus() {
        return status;
    }

    public void setStatus(long status) {
        this.status = status;
    }
}
