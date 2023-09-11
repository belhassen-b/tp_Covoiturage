package org.example.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Estimation {

    private Long estimationId;
    private String comment;
    private Double rating;
    private Reservation reservation;
    private User user;


    public Estimation(long estimationId) {
        this.estimationId = estimationId;
    }

    public void setReservationId(long reservationId) {
        this.reservation = new Reservation(reservationId);
    }

    public void setUser(long userId) {
        this.user = new User(userId);
    }
}
