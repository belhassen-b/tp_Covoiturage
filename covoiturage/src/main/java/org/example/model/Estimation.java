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


}
