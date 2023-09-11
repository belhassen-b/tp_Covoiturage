package org.example.controller;

import org.example.dao.ReservationDAO;
import org.example.dao.UserDAO;
import org.example.model.User;

import java.util.List;

public class UserController {
    private final UserDAO userDAO = new UserDAO();
    private final ReservationDAO reservationDAO = new ReservationDAO();

    public void addUser(User user) {
        userDAO.addUser(user);
    }

    public void deleteUser(long userId){
        userDAO.deleteUser(userId);
    }

    public long getUserById(long userId) {
          userDAO.getUserById(userId);
        return userId;
    }

    public void updateUser(User user) {
        userDAO.updateUser(user);
    }


    public List<User> getAllUsers() {
        userDAO.getAllUsers();
        return userDAO.getAllUsers();
    }



}
