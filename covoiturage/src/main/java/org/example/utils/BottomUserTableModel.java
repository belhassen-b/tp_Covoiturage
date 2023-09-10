package org.example.utils;

import lombok.Data;
import org.example.model.User;

import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;
import java.util.List;


@Data
public class BottomUserTableModel extends AbstractTableModel implements TableModel {
    private final List<User> data;

    private final String[] columnNames = {"Id", "LastName", "FirstName", "Email", "Password", "Phone", "isDriver", "isAdmin", "Reservations", "Estimations"};

    public BottomUserTableModel(List<User> usersList) {
        this.data = usersList;
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
        User user = data.get(rowIndex);
        return switch (columnIndex) {
            case 0 -> user.getUserId();
            case 1 -> user.getLastName();
            case 2 -> user.getFirstName();
            case 3 -> user.getEmail();
            case 4 -> user.getPassword();
            case 5 -> user.getPhone();
            case 6 -> user.getIsDriver();
            case 7 -> user.getIsAdmin();
            case 8 -> user.getReservations();
            case 9 -> user.getEstimations();
            default -> null;
        };
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }


    public void addRow(Object[] objects) {

        User user = new User();
        user.setUserId((Long) objects[0]);
        user.setLastName((String) objects[1]);
        user.setFirstName((String) objects[2]);
        user.setEmail((String) objects[3]);
        user.setPassword((String) objects[4]);
        user.setPhone((String) objects[5]);
        user.setIsDriver((Boolean) objects[6]);
        user.setIsAdmin((Boolean) objects[7]);
        user.setReservations((Integer) objects[8]);
        user.setEstimations((Integer) objects[9]);
        data.add(user);

        fireTableRowsInserted(data.size() - 1, data.size() - 1);
    }

    public void removeRow(int row) {
        data.remove(row);
        fireTableRowsDeleted(row, row);
    }

}
