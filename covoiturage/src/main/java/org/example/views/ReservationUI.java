package org.example.views;


import com.toedter.calendar.JDateChooser;
import org.example.controller.ReservationController;
import org.example.dao.ReservationDAO;
import org.example.model.Reservation;
import org.example.utils.RightReservationTableModel;

import javax.swing.*;
import java.sql.Date;
import java.util.List;

public class ReservationUI extends JFrame {
    final ReservationDAO reservationDAO = new ReservationDAO();

    final ReservationController reservationController = new ReservationController();

    private final RightReservationTableModel model;

    public ReservationUI() {
        super();
        model = new RightReservationTableModel(reservationDAO.getAllReservations());
        initComponents();
    }

    public void initComponents(){

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Reservations");
        setSize(1024, 768);
        setResizable(false);
        setLocationRelativeTo(null);

        JPanel panel1 = new JPanel();
        panel1.setLayout(null);


        JLabel reservationIdLabel = new JLabel("Reservation ID");
        JLabel departureLabel = new JLabel("Departure");
        JLabel arrivalLabel = new JLabel("Arrival");
        JLabel dateLabel = new JLabel("Date");
        JDateChooser dateChooser = new JDateChooser();
        JLabel priceLabel = new JLabel("Price");
        JLabel userIdLabel = new JLabel("User ID");
        JLabel estimationIdLabel = new JLabel("Estimation ID");

        reservationIdLabel.setBounds(20, 20, 100, 25);
        departureLabel.setBounds(20, 50, 100, 25);
        arrivalLabel.setBounds(20, 80, 100, 25);
        dateLabel.setBounds(20, 110, 100, 25);
        priceLabel.setBounds(20, 140, 100, 25);
        userIdLabel.setBounds(20, 170, 100, 25);
        estimationIdLabel.setBounds(20, 200, 100, 25);

        panel1.add(reservationIdLabel);
        panel1.add(departureLabel);
        panel1.add(arrivalLabel);
        panel1.add(dateLabel);
        panel1.add(dateChooser);
        panel1.add(priceLabel);
        panel1.add(userIdLabel);
        panel1.add(estimationIdLabel);


        JTextField reservationIdTxtField = new JTextField();
        JTextField departureTxtField = new JTextField();
        JTextField arrivalTxtField = new JTextField();
        JTextField dateTxtField = new JTextField();
        JTextField priceTxtField = new JTextField();
        JTextField userIdTxtField = new JTextField();
        JTextField estimationIdTxtField = new JTextField();

        JButton addReservationButton = new JButton("Add");
        JButton updateReservationButton = new JButton("Update");
        JButton deleteReservationButton = new JButton("Delete");
        JButton searchReservationButton = new JButton("Search");
        JTable reservationTable = new JTable();
        JScrollPane reservationScrollPane = new JScrollPane();




        JButton backButton = new JButton("Back");

        reservationIdTxtField.setBounds(230, 20, 200, 25);
        reservationIdTxtField.setEditable(false);
        reservationIdTxtField.setBackground(new java.awt.Color(204, 204, 204));
        departureTxtField.setBounds(230, 50, 200, 25);
        arrivalTxtField.setBounds(230, 80, 200, 25);
        dateChooser.setBounds(230, 110, 200, 25);

//        dateTxtField.setBounds(230, 110, 200, 25);
        priceTxtField.setBounds(230, 140, 200, 25);
        userIdTxtField.setBounds(230, 170, 200, 25);
        estimationIdTxtField.setBounds(230, 200, 200, 25);

        addReservationButton.setBounds(20, 400, 200, 25);
        updateReservationButton.setBounds(230, 400, 200, 25);
        deleteReservationButton.setBounds(20, 450, 200, 25);
        searchReservationButton.setBounds(230, 450, 200, 25);

        reservationScrollPane.setBounds(450, 30, 550, 700);
        reservationScrollPane.setViewportView(reservationTable);

        backButton.setBounds(20, 500, 410, 25);

        panel1.add(reservationIdTxtField);
        panel1.add(departureTxtField);
        panel1.add(arrivalTxtField);
        panel1.add(dateTxtField);
        panel1.add(priceTxtField);
        panel1.add(userIdTxtField);
        panel1.add(estimationIdTxtField);

        panel1.add(addReservationButton);
        panel1.add(updateReservationButton);
        panel1.add(deleteReservationButton);
        panel1.add(searchReservationButton);

        panel1.add(reservationScrollPane);

        panel1.add(backButton);

        setContentPane(panel1);

        reservationTable.setModel(model);
        reservationTable.setAutoCreateRowSorter(true);
        reservationTable.setPreferredSize(new java.awt.Dimension(550, 700));
        reservationScrollPane.setViewportView(reservationTable);
        reservationTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                super.mouseClicked(evt);
                int selectedRow = reservationTable.getSelectedRow();
                if (selectedRow >= 0) {
                    reservationIdTxtField.setText(reservationTable.getValueAt(selectedRow, 0).toString());
                    departureTxtField.setText(reservationTable.getValueAt(selectedRow, 1).toString());
                    arrivalTxtField.setText(reservationTable.getValueAt(selectedRow, 2).toString());
                    dateChooser.setDate((Date) reservationTable.getValueAt(selectedRow, 3));
                    priceTxtField.setText(reservationTable.getValueAt(selectedRow, 4).toString());
                    userIdTxtField.setText(String.valueOf(reservationTable.getValueAt(selectedRow, 5)));
                    estimationIdTxtField.setText(String.valueOf(reservationTable.getValueAt(selectedRow, 6)));
                }


                }
            });

        showReservations(reservationTable);
        setVisible(true);




        backButton.addActionListener(e -> {
            new AdminUI();
            dispose();
        });

        // button listener
addReservationButton.addActionListener(e -> {
    Reservation reservation = new Reservation();
    reservation.setDeparture(departureTxtField.getText());
    reservation.setArrival(arrivalTxtField.getText());
    reservation.setDate((Date) dateChooser.getDate());
    reservation.setPrice(Double.valueOf(priceTxtField.getText()));
    reservation.setUserId(Long.parseLong(userIdTxtField.getText()));
    reservation.setEstimationId(Long.parseLong(estimationIdTxtField.getText()));

    reservationController.addReservation(reservation);
    JOptionPane.showMessageDialog(null, "Reservation added successfully!");
    dispose();
    new ReservationUI();

});

        updateReservationButton.addActionListener(e -> {
            String reservationId = reservationIdTxtField.getText();
            String departure = departureTxtField.getText();
            String arrival = arrivalTxtField.getText();
            String date = dateTxtField.getText();
            String price = priceTxtField.getText();
            String userId = userIdTxtField.getText();
            String estimationId = estimationIdTxtField.getText();

//            reservationDAO.updateReservation(reservationId, departure, arrival, date, price, userId, estimationId);
            JOptionPane.showMessageDialog(null, "Reservation updated successfully!");
            dispose();
            new ReservationUI();
        });

        deleteReservationButton.addActionListener(e -> {
            String reservationId = reservationIdTxtField.getText();
//            reservationDAO.deleteReservation(reservationId);
            JOptionPane.showMessageDialog(null, "Reservation deleted successfully!");
            dispose();
            new ReservationUI();
        });

        searchReservationButton.addActionListener(e -> {
            String reservationId = reservationIdTxtField.getText();
            reservationController.searchReservation(Long.parseLong(reservationId));
            JOptionPane.showMessageDialog(null, "Reservation searched successfully!");
            dispose();
            new ReservationUI();
        }
        );





    }

    private void showReservations(JTable reservationTable) {
        ReservationController reservationController = new ReservationController();
        List<Reservation> reservations = reservationDAO.getAllReservations();
        RightReservationTableModel model = new RightReservationTableModel(reservations);
        reservationTable.setModel(model);
    }


    public static void main(String[] args) {
        new ReservationUI();
    }


}
