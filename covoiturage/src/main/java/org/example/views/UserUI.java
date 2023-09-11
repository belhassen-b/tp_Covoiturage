package org.example.views;

import org.example.controller.UserController;
import org.example.dao.UserDAO;
import org.example.model.User;
import org.example.utils.BottomUserTableModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class UserUI extends JFrame {

    private final UserController userController = new UserController();

    private final BottomUserTableModel model;

    private JTextField txtId;

    private JTextField txtLastName;

    private JTextField txtFirstName;

    private JTextField txtEmail;

    private JTextField txtPassword;

    private JTextField txtPhone;

    private JTextField txtIsDriver;

    private JTextField txtReservations;

    private JTextField txtEstimations;


    private JRadioButton isAdmin;

    private JRadioButton isUser;
    private JTable table;


    public UserUI() {
        super();

        model = new BottomUserTableModel(new ArrayList<>());
        UserDAO userDAO = new UserDAO();
        initComponents();

    }

    private void initComponents() {

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Interface administrateur");
        setSize(1024, 900);
        setResizable(false);
        setLocationRelativeTo(null);

        table = new JTable();

        JPanel contentPanel = new JPanel(new BorderLayout());
        getContentPane().add(contentPanel, BorderLayout.CENTER);

        JPanel mainPanel = new JPanel(new BorderLayout());
        contentPanel.add(mainPanel, BorderLayout.CENTER);

        JPanel headerPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JPanel centerPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JPanel footerPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));

        JPanel leftPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        leftPanel.setPreferredSize(new Dimension(200, 400));
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        leftPanel.setFont(new Font("Arial", Font.PLAIN, 20));
        leftPanel.setBackground(Color.LIGHT_GRAY);
        leftPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1, true));

        JPanel rightPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        rightPanel.setPreferredSize(new Dimension(200, 400));
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
        rightPanel.setFont(new Font("Arial", Font.PLAIN, 40));
        rightPanel.setBackground(Color.LIGHT_GRAY);
        rightPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1, true));

        mainPanel.add(leftPanel, BorderLayout.WEST);
        mainPanel.add(rightPanel, BorderLayout.EAST);
        mainPanel.add(headerPanel, BorderLayout.NORTH);
        mainPanel.add(centerPanel, BorderLayout.CENTER);
        mainPanel.add(footerPanel, BorderLayout.SOUTH);

        headerPanel.setPreferredSize(new Dimension(1024, 300));
        headerPanel.setLayout(null);
        headerPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1, true));


        centerPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        centerPanel.setBounds(200, 0, 780, 40);
        centerPanel.setBackground(Color.LIGHT_GRAY);
        centerPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1, true));

        footerPanel.setBackground(Color.LIGHT_GRAY);
        footerPanel.setLayout(new BorderLayout());
        footerPanel.setBounds(0, 0, 1024, 550);

        JLabel lblId = new JLabel("Id");
        txtId = new JTextField();
        txtId.setEditable(false);
        txtId.setBackground(Color.LIGHT_GRAY);
        lblId.setBounds(50, 20, 100, 20);
        txtId.setBounds(150, 20, 150, 20);
        txtId.setColumns(20);
        headerPanel.add(lblId);
        headerPanel.add(txtId);


        JLabel lblName = new JLabel("Lastname");
        txtLastName = new JTextField();
        lblName.setBounds(50, 45, 100, 20);
        txtLastName.setBounds(150, 45, 150, 20);
        txtLastName.setColumns(20);
        headerPanel.add(lblName);
        headerPanel.add(txtLastName);

        JLabel lblfirstname = new JLabel("Firstname");
        txtFirstName = new JTextField(2);
        lblfirstname.setBounds(50, 70, 100, 20);
        txtFirstName.setBounds(150, 70, 150, 20);
        txtFirstName.setColumns(20);

        headerPanel.add(lblfirstname);
        headerPanel.add(txtFirstName);


        JLabel lblContactNo = new JLabel("Phone");
        txtPhone = new JTextField();
        lblContactNo.setBounds(50, 100, 100, 20);
        txtPhone.setBounds(150, 100, 150, 20);
        txtPhone.setColumns(20);
        headerPanel.add(lblContactNo);
        headerPanel.add(txtPhone);

        JLabel lblEmail = new JLabel("Email");
        txtEmail = new JTextField();
        lblEmail.setBounds(50, 130, 100, 20);
        txtEmail.setBounds(150, 130, 150, 20);
        txtEmail.setColumns(20);
        headerPanel.add(lblEmail);
        headerPanel.add(txtEmail);

        JLabel lblPassword = new JLabel("Password");
        txtPassword = new JTextField();
        lblPassword.setBounds(50, 160, 100, 20);
        txtPassword.setBounds(150, 160, 150, 20);
        txtPassword.setColumns(10);
        headerPanel.add(lblPassword);
        headerPanel.add(txtPassword);

        JLabel lblIsDriver = new JLabel("isDriver");
        txtIsDriver = new JTextField();
        lblIsDriver.setBounds(50, 190, 100, 20);
        txtIsDriver.setBounds(150, 190, 150, 20);
        txtIsDriver.setColumns(10);
        headerPanel.add(lblIsDriver);
        headerPanel.add(txtIsDriver);

        JLabel lblIsAdmin = new JLabel("isAdmin");
        isAdmin = new JRadioButton("Admin");
        isUser = new JRadioButton("User");
        lblIsAdmin.setBounds(50, 220, 100, 20);
        isAdmin.setBounds(150, 220, 150, 20);
        isUser.setBounds(150, 240, 150, 20);

        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(isAdmin);
        buttonGroup.add(isUser);
        headerPanel.add(lblIsAdmin);
        headerPanel.add(isAdmin);
        headerPanel.add(isUser);



        JLabel lblReservations = new JLabel("Reservations");
        txtReservations = new JTextField();
        lblReservations.setBounds(50, 270, 100, 20);
        txtReservations.setBounds(150, 270, 150, 20);
        txtReservations.setColumns(10);
        txtReservations.setHorizontalAlignment(JTextField.CENTER);
        txtReservations.setFont(new Font("Arial", Font.PLAIN, 40));
        leftPanel.add(lblReservations);
        leftPanel.add(txtReservations);

        JLabel lblEstimations = new JLabel("Estimations");
        txtEstimations = new JTextField();
        lblEstimations.setBounds(50, 300, 100, 20);
        txtEstimations.setColumns(10);
        txtEstimations.setHorizontalAlignment(JTextField.CENTER);
        txtEstimations.setFont(new Font("Arial", Font.PLAIN, 40));
        rightPanel.add(lblEstimations);
        rightPanel.add(txtEstimations);


        ImageIcon newIcon = new ImageIcon(Objects.requireNonNull(getClass().getResource("/images/new.png")));
        newIcon.setImage(newIcon.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));
        JButton newButton = new JButton("New", newIcon);

        centerPanel.add(newButton);


        ImageIcon saveIcon = new ImageIcon(Objects.requireNonNull(UserUI.class.getResource("/images/floppy.png")));
        saveIcon.setImage(saveIcon.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));
        JButton saveButton = new JButton("Save", saveIcon);
        centerPanel.add(saveButton);


        saveButton.addActionListener(e -> {
            User user = new User();
            user.setLastName(txtLastName.getText());
            user.setFirstName(txtFirstName.getText());
            user.setEmail(txtEmail.getText());
            user.setPassword(txtPassword.getText());
            user.setPhone(txtPhone.getText());
            user.setIsDriver(Boolean.parseBoolean(txtIsDriver.getText()));
            user.setIsAdmin(isAdmin.isSelected());
            user.setReservations(Integer.parseInt(txtReservations.getText()));
            user.setEstimations(Integer.parseInt(txtEstimations.getText()));
            userController.addUser(user);
            JOptionPane.showMessageDialog(null, "L'utilisateur a bien été ajouté !", "Success", JOptionPane.INFORMATION_MESSAGE);
            showUser(table);
        });

        ImageIcon updateIcon = new ImageIcon(Objects.requireNonNull(UserUI.class.getResource("/images/pen.png")));
        updateIcon.setImage(updateIcon.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));
        JButton updateButton = new JButton("Update", updateIcon);
        centerPanel.add(updateButton);

        ImageIcon deleteIcon = new ImageIcon(Objects.requireNonNull(UserUI.class.getResource("/images/delete.png")));
        deleteIcon.setImage(deleteIcon.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));
        JButton deleteButton = new JButton("Delete", deleteIcon);
        centerPanel.add(deleteButton);


        ImageIcon clearIcon = new ImageIcon(Objects.requireNonNull(UserUI.class.getResource("/images/clear.png")));
        clearIcon.setImage(clearIcon.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));
        JButton clearButton = new JButton("Clear", clearIcon);
        centerPanel.add(clearButton);

        ImageIcon printIcon = new ImageIcon(Objects.requireNonNull(UserUI.class.getResource("/images/printer.png")));
        printIcon.setImage(printIcon.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));
        JButton printButton = new JButton("Print", printIcon);
        centerPanel.add(printButton);

        JButton backButton = new JButton("Back");
        backButton.setPreferredSize(new Dimension(100, 30));
        backButton.setBackground(Color.RED);
        backButton.setForeground(Color.white);
        centerPanel.add(backButton);


        // button listener
        newButton.addActionListener(e -> {
            txtId.setText("");
            txtLastName.setText("");
            txtFirstName.setText("");
            txtEmail.setText("");
            txtPassword.setText("");
            txtPhone.setText("");
            txtIsDriver.setText("");
            txtReservations.setText("");
            txtEstimations.setText("");
            deleteButton.setEnabled(false);
            updateButton.setEnabled(false);
            printButton.setEnabled(false);
            clearButton.setEnabled(false);
            saveButton.setEnabled(true);
        });

        saveButton.addActionListener(e -> {
            User user = new User();
            user.setLastName(txtLastName.getText());
            user.setFirstName(txtFirstName.getText());
            user.setEmail(txtEmail.getText());
            user.setPassword(txtPassword.getText());
            user.setPhone(txtPhone.getText());
            user.setIsDriver(Boolean.parseBoolean(txtIsDriver.getText()));
            user.setIsAdmin(isAdmin.isSelected());
            user.setReservations(Integer.parseInt(txtReservations.getText()));
            user.setEstimations(Integer.parseInt(txtEstimations.getText()));
            userController.addUser(user);
            JOptionPane.showMessageDialog(null, "L'utilisateur a bien été ajouté !", "Success", JOptionPane.INFORMATION_MESSAGE);
            showUser(table);
        });

        updateButton.addActionListener(e -> {
            User user = new User();
            user.setUserId(Long.parseLong(txtId.getText()));
            user.setLastName(txtLastName.getText());
            user.setFirstName(txtFirstName.getText());
            user.setEmail(txtEmail.getText());
            user.setPassword(txtPassword.getText());
            user.setPhone(txtPhone.getText());
            user.setIsDriver(Boolean.parseBoolean(txtIsDriver.getText()));
            user.setIsAdmin(isAdmin.isSelected());
            user.setReservations(Integer.parseInt(txtReservations.getText()));
            user.setEstimations(Integer.parseInt(txtEstimations.getText()));
            userController.updateUser(user);
            JOptionPane.showMessageDialog(null, "L'employé a bien été mis à jour !", "Success", JOptionPane.INFORMATION_MESSAGE);
            showUser(table);
        });


        deleteButton.addActionListener(e -> {
            User user = new User();
            user.setUserId(Long.parseLong(txtId.getText()));
            userController.deleteUser(user.getUserId());
            JOptionPane.showMessageDialog(null, "L'utilisateur a bien été supprimé !", "Success", JOptionPane.INFORMATION_MESSAGE);
            showUser(table);
        });

        clearButton.addActionListener(e -> {
            newButton.doClick();
            deleteButton.setEnabled(false);
            updateButton.setEnabled(false);
            printButton.setEnabled(false);
            clearButton.setEnabled(false);
            saveButton.setEnabled(true);
        });


        printButton.addActionListener(e -> {
            try {
                table.print();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        backButton.addActionListener(e -> {
            dispose();
            new AdminUI();
        });


        table.setModel(model);
        table.setPreferredSize(new Dimension(1024, 400));
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                int selectedRow = table.getSelectedRow();
                if (selectedRow >= 0) {
                    txtId.setText(table.getValueAt(selectedRow, 0).toString());
                    txtFirstName.setText(table.getValueAt(selectedRow, 1).toString());
                    txtLastName.setText(table.getValueAt(selectedRow, 2).toString());
                    txtEmail.setText(table.getValueAt(selectedRow, 3).toString());
                    txtPassword.setText(table.getValueAt(selectedRow, 4).toString());
                    txtPhone.setText(table.getValueAt(selectedRow, 5).toString());
                    txtIsDriver.setText(table.getValueAt(selectedRow, 6).toString());
                    System.out.print("is admin :");
                    System.out.println(table.getValueAt(selectedRow, 7));

                    if (table.getValueAt(selectedRow, 7).toString().equals("true")) {
                        isAdmin.setSelected(true);
                    } else {
                        isUser.setSelected(true);
                    }
                    txtReservations.setText(table.getValueAt(selectedRow, 8).toString());
                    System.out.println(table.getValueAt(selectedRow, 8));
                    txtEstimations.setText(table.getValueAt(selectedRow, 9).toString());


                    deleteButton.setEnabled(true);
                    updateButton.setEnabled(true);
                    printButton.setEnabled(true);
                    clearButton.setEnabled(true);
                    saveButton.setEnabled(false);
                }

            }
        });
        JScrollPane jScrollPane = new JScrollPane(table);
        footerPanel.add(jScrollPane, BorderLayout.CENTER);
        showUser(table);


        setVisible(true);


    }


    public void showUser(JTable table) {
        UserController userController = new UserController();
        List<User> users = userController.getAllUsers();
        BottomUserTableModel model = new BottomUserTableModel(users);
        table.setModel(model);


    }

    public static void main(String[] args) {
        new UserUI();
    }
}
