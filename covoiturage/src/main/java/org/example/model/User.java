package org.example.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    private Long userId;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String phone;
    private Boolean isDriver = false;
    private Boolean isAdmin;
    private int reservations;
    private int estimations;


    public User(long userId) {
        this.userId = userId;
    }
}
