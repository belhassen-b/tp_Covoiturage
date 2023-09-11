package org.example.controller;

import org.example.dao.ReservationDAO;
import org.example.model.Reservation;

public class ReservationController {

    private final ReservationDAO reservationDAO = new ReservationDAO();

    public void addReservation(Reservation reservation){
        reservationDAO.addReservation(reservation);
    }

    public void deleteReservation(long reservationId){
        reservationDAO.deleteReservation(reservationId);
    }

    public void getReservationById(long reservationId) {
        reservationDAO.getReservationById(reservationId);
    }

    public void updateReservation(Reservation reservation) {
        reservationDAO.updateReservation(reservation);
    }

    public void getAllReservations() {
        reservationDAO.getAllReservations();
    }
    
    public void searchReservation(long reservationId) {
        reservationDAO.searchReservation(String.valueOf(reservationId));
    }
    


}
