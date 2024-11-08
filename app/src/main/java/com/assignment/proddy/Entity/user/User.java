package com.assignment.proddy.Entity.user;

import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

//import javax.validation.constraints.Email;
//import javax.validation.constraints.NotBlank;

@Entity(tableName = "user", indices = {@Index(value = "email", unique = true)})
public class User {

    @PrimaryKey(autoGenerate = true)
    private int id;

//    @NotBlank(message = "Name cannot be blank")
    private String name;

//    @Email(message = "Email should be valid")
    private String email;

    private String password;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }
}
