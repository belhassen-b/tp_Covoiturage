package org.example.controller;

import org.example.dao.ReservationDAO;
import org.example.dao.UserDAO;
import org.example.model.User;

import java.util.List;

public class UserController {
    private UserDAO userDAO = new UserDAO();
    private ReservationDAO reservationDAO = new ReservationDAO();

    public void addUser(User user) {
        userDAO.addUser(user);
    }

    public void deleteUser(long userId){
        userDAO.deleteUser(userId);
    }

    public User getUserById(long userId) {
        return userDAO.getUserById(userId);
    }

    public void updateUser(User user) {
        userDAO.updateUser(user);
    }


    public List<User> getAllUsers() {
        userDAO.getAllUsers();
        return userDAO.getAllUsers();
    }

    public boolean isDriver(long userId) {
        return userDAO.isDriver(userId);
    }

    public void getReservations(long userId) {
        reservationDAO.getReservationCount(userId);
    }
}
