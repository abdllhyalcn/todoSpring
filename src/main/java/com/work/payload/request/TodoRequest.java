package com.work.payload.request;

import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;

public class TodoRequest {

    private long user_id;

    @Size(min = 3, max = 150)
    private String description;

    @FutureOrPresent(message = "Date input is invalid because past.")
    @DateTimeFormat(pattern="yyyy-mm-dd")
    private Date date_todo;

    private long status;

    public long getUser_id() {
        return user_id;
    }

    public void setUser_id(long user_id) {
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

    public long getStatus() {
        return status;
    }

    public void setStatus(long status) {
        this.status = status;
    }
}
