package org.example.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Reservation {
    private Long reservationId;
    private String departure;
    private String arrival;
    private Date date;
    private Double price;

    private long userId;

    private long estimationId;

    public Reservation(long reservationId) {
        this.reservationId = reservationId;
    }

    public Reservation(String departure, String arrival, Date date, Double price, long userId, Estimation estimation) {
        this.departure = departure;
        this.arrival = arrival;
        this.date = date;
        this.price = price;
        this.userId = userId;
        this.estimationId = estimationId;
    }


    public void setDate(Date date) {
        this.date = date;
    }

    public void setEstimation(long estimationId) {
        this.estimationId = estimationId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    }
