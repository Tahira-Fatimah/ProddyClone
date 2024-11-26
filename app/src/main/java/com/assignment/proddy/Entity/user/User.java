package com.assignment.proddy.Entity.user;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.util.UUID;

//import javax.validation.constraints.Email;
//import javax.validation.constraints.NotBlank;

@Entity(tableName = "user", indices = {@Index(value = "userEmail", unique = true)})
public class User implements Serializable {

    @PrimaryKey
    @NonNull private UUID userId;

//    @NotBlank(message = "Name cannot be blank")
    private String userName;

//    @Email(message = "Email should be valid")
    private String userEmail;

    private String userPassword;

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }


    public void setUserId(@NonNull UUID userId) {
        this.userId = userId;
    }

    @NonNull
    public UUID getUserId() {
        return userId;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

    public User(@NonNull UUID userId, String userName, String userEmail, String userPassword) {
        this.userName = userName;
        this.userEmail = userEmail;
        this.userPassword = userPassword;
        this.userId = userId;
    }
}
