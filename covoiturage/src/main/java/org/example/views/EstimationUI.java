package org.example.views;

import org.example.controller.EstimationController;
import org.example.controller.UserController;
import org.example.dao.EstimationDAO;
import org.example.dao.ReservationDAO;
import org.example.model.Estimation;
import org.example.model.Reservation;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class EstimationUI extends JFrame {

    private final UserController user = new UserController();

    private final ReservationDAO reservation = new ReservationDAO();
    private final EstimationDAO estimation = new EstimationDAO();

    private final EstimationController estimationController = new EstimationController();

    private JTable estimationTable;

    private JTextField searchTextField;

    public EstimationUI() {

        super("Estimations");
        initComponents();

    }

    private void initComponents() {

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 400);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        // Create and add the search panel to the top (North)
        JPanel searchPanel = new JPanel();
        searchPanel.setLayout(new FlowLayout());

        JLabel searchLabel = new JLabel("Search by User ID:");
        searchPanel.add(searchLabel);

        searchTextField = new JTextField(10); // Adjust the size as needed
        searchPanel.add(searchTextField);

        JButton searchButton = new JButton("Search");

        searchPanel.add(searchButton);

        // Create and add the table panel to the center
        JPanel tablePanel = new JPanel();
        tablePanel.setLayout(new BorderLayout());

        estimationTable = new JTable();
        estimationTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        estimationTable.setRowHeight(30);
        estimationTable.getTableHeader().setReorderingAllowed(false);

        JScrollPane scrollPane = new JScrollPane(estimationTable);
        scrollPane.setPreferredSize(new Dimension(700, 300));

        tablePanel.add(scrollPane, BorderLayout.CENTER);

        // Create and add the buttons panel to the bottom
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new FlowLayout());

        JButton addEstimationButton = new JButton("Add");
        addEstimationButton.setBackground(Color.GREEN);
        addEstimationButton.setForeground(Color.BLACK);
        addEstimationButton.setPreferredSize(new Dimension(100, 40));

        JButton updateEstimationButton = new JButton("Update");
        updateEstimationButton.setBackground(Color.ORANGE);
        updateEstimationButton.setForeground(Color.BLACK);
        updateEstimationButton.setPreferredSize(new Dimension(100, 40));

        JButton deleteEstimationButton = new JButton("Delete");
        deleteEstimationButton.setBackground(Color.RED);
        deleteEstimationButton.setForeground(Color.BLACK);
        deleteEstimationButton.setPreferredSize(new Dimension(100, 40));

        JButton backBtn = new JButton("Back");
        backBtn.setBackground(Color.RED);
        backBtn.setForeground(Color.WHITE);
        backBtn.setPreferredSize(new Dimension(100, 40));

        buttonsPanel.add(addEstimationButton);
        buttonsPanel.add(updateEstimationButton);
        buttonsPanel.add(deleteEstimationButton);
        buttonsPanel.add(backBtn);

        mainPanel.add(searchPanel, BorderLayout.NORTH);
        mainPanel.add(tablePanel, BorderLayout.CENTER);
        mainPanel.add(buttonsPanel, BorderLayout.SOUTH);

        setContentPane(mainPanel);

        DefaultTableModel tableModel = new DefaultTableModel(
                new Object[][]{
                        {"", "", "", "", ""},
                },
                new String[]{
                        "Id", "Comment", "Rating", "Reservation", "User"
                }
        );
        estimationTable = new JTable(tableModel);


        searchButton.addActionListener(e -> {
            System.out.println("Search button clicked");
            addDataToTable();
        });


        addEstimationButton.addActionListener(e -> {
            Estimation estimation = new Estimation();

            estimation.setComment((String) estimationTable.getValueAt(estimationTable.getSelectedRow(), 1));
            estimation.setRating((double) estimationTable.getValueAt(estimationTable.getSelectedRow(), 2));
            long reservationId = (long) estimationTable.getValueAt(estimationTable.getSelectedRow(), 3);
            Reservation reservation = new Reservation(reservationId);
            estimation.setReservation(reservation);
            long userId = (long) estimationTable.getValueAt(estimationTable.getSelectedRow(), 4);
            estimation.setUser(user.getUserById(userId));


            estimationController.addEstimation(estimation);


        });

        updateEstimationButton.addActionListener(e -> {
            Estimation estimation = new Estimation();

            estimation.setEstimationId((long) estimationTable.getValueAt(estimationTable.getSelectedRow(), 0));
            estimation.setComment((String) estimationTable.getValueAt(estimationTable.getSelectedRow(), 1));
            estimation.setRating((double) estimationTable.getValueAt(estimationTable.getSelectedRow(), 2));
            Reservation reservation = new Reservation();
            reservation.setReservationId((long) estimationTable.getValueAt(estimationTable.getSelectedRow(), 3));
            estimation.setReservation(reservation);
            estimation.setUser(user.getUserById((long) estimationTable.getValueAt(estimationTable.getSelectedRow(), 4)));

            estimationController.updateEstimation(estimation);


        });

        deleteEstimationButton.addActionListener(e -> {
            int selectedRow = estimationTable.getSelectedRow();
            if (selectedRow != -1) {
                long id = (long) estimationTable.getValueAt(selectedRow, 0);
                int option = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this estimation?", "Delete", JOptionPane.YES_NO_OPTION);
                if (option == JOptionPane.YES_OPTION) {
                    estimation.deleteEstimation(id);
                    estimation.getEstimationTableModel();
                }
            } else {
                JOptionPane.showMessageDialog(null, "Please select a row to delete");
            }
        });

        backBtn.addActionListener(e -> {
            new AdminUI();
            dispose();
        });

        setVisible(true);


    }

    private void addDataToTable() {
        String userIdText = searchTextField.getText();
        long userId;

        try {
            userId = Long.parseLong(userIdText);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid User ID");
            return;
        }

        // Use the EstimationDAO to fetch data from MongoDB based on the user ID
        List<Estimation> estimations = EstimationDAO.getEstimationsByUserId(userId);

        // Create a DefaultTableModel and populate the JTable as shown in your existing code
        // ...
    }




    public static void main(String[] args) {
        SwingUtilities.invokeLater(EstimationUI::new);
    }

}
