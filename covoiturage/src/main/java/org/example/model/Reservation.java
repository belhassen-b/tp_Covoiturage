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

    private User userid;

    private Estimation estimationid;

    public Reservation(long reservationId) {
        this.reservationId = reservationId;
    }
}
