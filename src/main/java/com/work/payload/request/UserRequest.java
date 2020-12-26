package com.work.payload.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class UserRequest {

    private Long user_id;

    @NotBlank
    @Size(max = 20)
    private String new_username;

    @NotBlank
    @Size(max = 120)
    private String new_password;

    @NotBlank
    @Size(max = 50)
    @Email
    private String new_email;

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public String getNew_username() {
        return new_username;
    }

    public void setNew_username(String new_username) {
        this.new_username = new_username;
    }

    public String getNew_password() {
        return new_password;
    }

    public void setNew_password(String new_password) {
        this.new_password = new_password;
    }

    public String getNew_email() {
        return new_email;
    }

    public void setNew_email(String new_email) {
        this.new_email = new_email;
    }
}
