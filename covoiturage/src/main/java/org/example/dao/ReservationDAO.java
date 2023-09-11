package org.example.dao;

import org.example.model.Reservation;
import org.example.utils.ConDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ReservationDAO {

    private final Connection conDB;

    private PreparedStatement ps;

    private static final String ERROR_MESSAGE = "An exception occurred";

    public ReservationDAO() {
        this.conDB = ConDB.getConnection();
    }
    private void preparedStatement(Reservation reservation) throws SQLException {
        ps.setString(1, reservation.getDeparture());
        ps.setString(2, reservation.getArrival());
        ps.setDate(3, (Date) reservation.getDate());
        ps.setDouble(4, reservation.getPrice());
        ps.setLong(5, reservation.getUserid().getUserId());
        ps.setLong(6, reservation.getEstimationid().getEstimationId());
    }

    private void rsNext(ArrayList<Reservation> reservations, ResultSet rs) throws SQLException {
        long reservationId = rs.getLong("reservation_id");
        String departure = rs.getString("departure");
        String arrival = rs.getString("arrival");
        Date date = rs.getDate("date");
        Double price = rs.getDouble("price");
        long userId = rs.getLong("user_id");
        long estimationId = rs.getLong("estimation_id");

        Reservation reservation = new Reservation();
        reservation.setReservationId(rs.getLong("reservation_id"));
        reservation.setDeparture(rs.getString("departure"));
        reservation.setArrival(rs.getString("arrival"));
        reservation.setDate(rs.getDate("date"));
        reservation.setPrice(rs.getDouble("price"));
        reservation.setUserId(rs.getLong("user_id"));
        reservation.setEstimationId(rs.getLong("estimation_id"));
        reservations.add(reservation);
    }

    public void addReservation(Reservation reservation) {
        try {
            ps = conDB.prepareStatement("INSERT INTO reservation"
                    + "(departure, arrival, date, price, user_id, estimation_id)VALUES (?,?,?,?,?,?)");
            preparedStatement(reservation);
            ps.executeUpdate();
        } catch (SQLException e) {
            Logger logger = Logger.getLogger(ReservationDAO.class.getName());
            logger.log(Level.SEVERE, ERROR_MESSAGE, e);
        }
    }



    public void deleteReservation(Long reservationId) {
        try {
            ps = conDB.prepareStatement("DELETE FROM reservation WHERE reservation_id = ?");
            ps.setLong(1, reservationId);
            ps.executeUpdate();
        } catch (SQLException e) {
            Logger logger = Logger.getLogger(ReservationDAO.class.getName());
            logger.log(Level.SEVERE, ERROR_MESSAGE, e);
        }
    }

    public void updateReservation(Reservation reservation) {
        try {
            ps = conDB.prepareStatement("UPDATE reservation SET departure = ?, arrival = ?, date = ?, price = ?, user_id = ?, estimation_id = ? WHERE reservation_id = ?");
            preparedStatement(reservation);
            ps.setLong(7, reservation.getReservationId());
            ps.executeUpdate();
        } catch (SQLException e) {
            Logger logger = Logger.getLogger(ReservationDAO.class.getName());
            logger.log(Level.SEVERE, ERROR_MESSAGE, e);
        }
    }

    public List<Reservation> getAllReservations() {
        ArrayList<Reservation> reservations = new ArrayList<>();
        try {
            ps = conDB.prepareStatement("SELECT * FROM reservation");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                rsNext(reservations, rs);
            }
        } catch (SQLException e) {
            Logger logger = Logger.getLogger(ReservationDAO.class.getName());
            logger.log(Level.SEVERE, ERROR_MESSAGE, e);
        }
        return reservations;
    }

    public Reservation getReservationById(Long reservationId) {
        Reservation reservation = new Reservation();
        try {
            ps = conDB.prepareStatement("SELECT * FROM reservation WHERE reservation_id = ?");
            ps.setLong(1, reservationId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String departure = rs.getString("departure");
                String arrival = rs.getString("arrival");
                Date date = rs.getDate("date");
                Double price = rs.getDouble("price");
                long userId = rs.getLong("user_id");
                long estimationId = rs.getLong("estimation_id");
            }
        } catch (SQLException e) {
            Logger logger = Logger.getLogger(ReservationDAO.class.getName());
            logger.log(Level.SEVERE, ERROR_MESSAGE, e);
        }
        return reservation;
    }


    public int getReservationCount(long userId){
        int count = 0;
        try {
            ps = conDB.prepareStatement("SELECT COUNT(*) FROM reservation");
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                count = rs.getInt(1);
            }
        } catch (SQLException e) {
            Logger logger = Logger.getLogger(ReservationDAO.class.getName());
            logger.log(Level.SEVERE, ERROR_MESSAGE, e);
        }
        return count;
    }
    public List<Reservation> searchReservation(String search){
        ArrayList<Reservation> reservations = new ArrayList<>();
        try {
            ps= conDB.prepareStatement("SELECT * FROM reservation WHERE departure LIKE ? OR arrival LIKE ? OR date LIKE ? OR price LIKE ? OR user_id LIKE ? OR estimation_id LIKE ?");
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                rsNext(reservations, rs);
            }
        } catch (SQLException e) {
            Logger logger = Logger.getLogger(ReservationDAO.class.getName());
            logger.log(Level.SEVERE, ERROR_MESSAGE, e);
        }
        return reservations;
    }

    public int getReservationCountByUserId(long userId) {
        try {
            ps = conDB.prepareStatement("SELECT COUNT(*) FROM reservation WHERE user_id = ?");
            ps.setLong(1, userId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            Logger logger = Logger.getLogger(ReservationDAO.class.getName());
            logger.log(Level.SEVERE, ERROR_MESSAGE, e);
        }
        return getReservationCountByUserId(userId);
    }
}

