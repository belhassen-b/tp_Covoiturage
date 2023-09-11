package org.example.dao;

import org.example.model.Estimation;
import org.example.model.Reservation;
import org.example.model.User;
import org.example.utils.ConDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserDAO {
    private final Connection conDB;

    private PreparedStatement ps;

    private static final String ERROR_MESSAGE = "An exception occurred";

    public UserDAO() {
        this.conDB = ConDB.getConnection();
    }

    private void rsNext(ArrayList<User> users, ResultSet rs) throws SQLException {
        User user = new User();
        user.setUserId(rs.getLong("user_id"));
        user.setFirstName(rs.getString("firstname"));
        user.setLastName(rs.getString("lastname"));
        user.setEmail(rs.getString("email"));
        user.setPassword(rs.getString("password"));
        user.setPhone(rs.getString("phone"));
        user.setIsDriver(rs.getBoolean("is_driver"));
        user.setIsAdmin(rs.getBoolean("is_admin"));
        user.setReservations(rs.getInt("reservations"));
        user.setEstimations(rs.getInt("estimations"));
        users.add(user);

    }

    public void addUser(User user) {
        try {
            ps = conDB.prepareStatement("INSERT INTO user" + "(firstname, lastname, email, password, phone, is_driver, is_admin, reservations, estimations)VALUES (?,?,?,?,?,?,?,?,?)");
            ps.setString(1, user.getFirstName());
            ps.setString(2, user.getLastName());
            ps.setString(3, user.getEmail());
            ps.setString(4, user.getPassword());
            ps.setString(5, user.getPhone());
            ps.setBoolean(6, user.getIsDriver());
            ps.setBoolean(7, user.getIsAdmin());
            ps.setObject(8, user.getReservations());
            ps.setObject(9, user.getEstimations());
            ps.executeUpdate();
        } catch (SQLException e) {
            Logger logger = Logger.getLogger(UserDAO.class.getName());
            logger.log(Level.SEVERE, ERROR_MESSAGE, e);
        }
    }

    public void updateUser(User user) {
        try {
            ps = conDB.prepareStatement("UPDATE user SET firstname=?, lastname=?, email=?, password=?, phone=?, is_driver=?, is_admin=?, reservations=?, estimations=? " +
                    " WHERE user_id=?");
            ps.setString(1, user.getFirstName());
            ps.setString(2, user.getLastName());
            ps.setString(3, user.getEmail());
            ps.setString(4, user.getPassword());
            ps.setString(5, user.getPhone());
            ps.setBoolean(6, user.getIsDriver());
            ps.setBoolean(7, user.getIsAdmin());
            ps.setObject(8, user.getReservations());
            ps.setObject(9, user.getEstimations());
            ps.setLong(10, user.getUserId());
            ps.executeUpdate();
        } catch (SQLException e) {
            Logger logger = Logger.getLogger(UserDAO.class.getName());
            logger.log(Level.SEVERE, ERROR_MESSAGE, e);
        }
    }


    public void deleteUser(long userId) {
        try {
            ps = conDB.prepareStatement("DELETE FROM user WHERE user_id=?");
            ps.setLong(1, userId);

            ps.executeUpdate();
        } catch (SQLException e) {
            Logger logger = Logger.getLogger(UserDAO.class.getName());
            logger.log(Level.SEVERE, ERROR_MESSAGE, e);
        }
    }

    public List<User> getAllUsers() {
        ArrayList<User> users = new ArrayList<>();
        try {
            ps = conDB.prepareStatement("SELECT * FROM user");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                rsNext(users, rs);
            }
        } catch (SQLException e) {
            Logger logger = Logger.getLogger(UserDAO.class.getName());
            logger.log(Level.SEVERE, ERROR_MESSAGE, e);
        }
        return users;
    }

    public User getUserById(Long userId) {
        User user = null;
        try {
            ps = conDB.prepareStatement("SELECT * FROM user WHERE user_id = ?");
            ps.setLong(1, userId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String firstName = rs.getString("first_name");
                String lastName = rs.getString("last_name");
                String email = rs.getString("email");
                String password = rs.getString("password");
                String phone = rs.getString("phone");
                Boolean isDriver = rs.getBoolean("is_driver");
                Boolean isAdmin = rs.getBoolean("is_admin");
                List<Reservation> reservations = (List<Reservation>) rs.getObject("reservations");
                List<Estimation> estimations = (List<Estimation>) rs.getObject("estimations");
            }
        } catch (SQLException e) {
            Logger logger = Logger.getLogger(UserDAO.class.getName());
            logger.log(Level.SEVERE, ERROR_MESSAGE, e);
        }
        return user;
    }

    public boolean isDriver(long userId) {
        User user =  new User();

        try {
            ps = conDB.prepareStatement("SELECT * FROM user WHERE user_id = ?");
            ps.setLong(1, userId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                user = new User();
                user.setIsDriver(rs.getBoolean("is_driver"));
            }
        } catch (SQLException e) {
            Logger logger = Logger.getLogger(UserDAO.class.getName());
            logger.log(Level.SEVERE, ERROR_MESSAGE, e);
        }
        return user.getIsDriver();

    }

}

