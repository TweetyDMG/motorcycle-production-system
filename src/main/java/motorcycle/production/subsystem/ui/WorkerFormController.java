package motorcycle.production.subsystem.ui;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import motorcycle.production.subsystem.handler.WorkerHandler;
import motorcycle.production.subsystem.model.Worker;
import motorcycle.production.subsystem.util.ServiceFactory;

public class WorkerFormController {
    @FXML
    private TextField workerIdField;
    @FXML
    private TextField nameField;
    @FXML
    private TextField specializationField;

    private WorkerHandler workerController;

    @FXML
    private void initialize() {
        workerController = new WorkerHandler(ServiceFactory.workerService());
    }

    @FXML
    private void handleCreateWorker() {
        workerController.handleCreateWorker(nameField.getText(), specializationField.getText());
        clearFields();
    }

    @FXML
    private void handleFindWorker() {
        Worker worker = workerController.handleGetWorker(workerIdField.getText());
        if (worker != null) {
            nameField.setText(worker.getName());
            specializationField.setText(worker.getSpecialization());
        }
    }

    @FXML
    private void handleUpdateWorker() {
        workerController.handleUpdateWorker(workerIdField.getText(), nameField.getText(), specializationField.getText());
        clearFields();
    }

    @FXML
    private void handleDeleteWorker() {
        workerController.handleDeleteWorker(workerIdField.getText());
        clearFields();
    }

    private void clearFields() {
        workerIdField.clear();
        nameField.clear();
        specializationField.clear();
    }
}