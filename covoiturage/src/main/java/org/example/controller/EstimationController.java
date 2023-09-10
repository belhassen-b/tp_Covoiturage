package org.example.controller;

import org.example.dao.EstimationDAO;
import org.example.model.Estimation;

import javax.swing.table.TableModel;

public class EstimationController {

    private EstimationDAO estimationDAO = new EstimationDAO();

    public void addEstimation(Estimation estimation) {
        estimationDAO.addEstimation(estimation);
    }

    public void deleteEstimation(long estimationId) {
        estimationDAO.deleteEstimation(estimationId);
    }

    public void getEstimationById(long estimationId) {
        estimationDAO.getEstimationById(estimationId);
    }

    public void updateEstimation(Estimation estimation) {
        estimationDAO.updateEstimation(estimation);
    }

    public void getAllEstimations() {
        estimationDAO.getAllEstimations();
    }

}
