package com.work.payload.request;

import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.Date;

public class TodoRequest {

    @Size(min = 3, max = 150)
    private String description;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private Date date_todo;

    private long status;

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
