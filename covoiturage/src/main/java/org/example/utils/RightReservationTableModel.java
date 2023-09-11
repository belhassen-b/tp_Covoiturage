package org.example.utils;

import org.example.model.Reservation;

import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;
import java.util.List;

public class RightReservationTableModel extends AbstractTableModel implements TableModel {
private final List<Reservation> data;

private final String[] columnNames = {"Id", "Departure", "Arrival", "Date", "Price", "UserId", "EstimationId"};


public RightReservationTableModel(List<Reservation> reservationsList) {
        this.data = reservationsList;
        }

@Override
public int getRowCount() {
        return data.size();
        }

@Override
public int getColumnCount() {
        return columnNames.length;
        }

@Override
public Object getValueAt(int rowIndex, int columnIndex) {
        Reservation reservation = data.get(rowIndex);
        return switch (columnIndex) {
        case 0 -> reservation.getReservationId();
        case 1 -> reservation.getDeparture();
        case 2 -> reservation.getArrival();
        case 3 -> reservation.getDate();
        case 4 -> reservation.getPrice();
        case 5 -> reservation.getUserid();
        case 6 -> reservation.getEstimationid();
        default -> null;
        };
        }

@Override
public String getColumnName(int column) {
        return columnNames[column];
        }



}
