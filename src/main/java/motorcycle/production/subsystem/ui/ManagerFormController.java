package motorcycle.production.subsystem.ui;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import motorcycle.production.subsystem.handler.ManagerHandler;
import motorcycle.production.subsystem.model.Manager;
import motorcycle.production.subsystem.util.ServiceFactory;

public class ManagerFormController {
    @FXML
    private TextField managerIdField;
    @FXML
    private TextField nameField;
    @FXML
    private TextField contactInfoField;
    @FXML
    private TextField orderIdField;
    @FXML
    private TextField orderStatusField;
    @FXML
    private TextField supplierIdField;

    private ManagerHandler managerController;

    @FXML
    private void initialize() {
        managerController = new ManagerHandler(ServiceFactory.managerService());
    }

    @FXML
    private void handleCreateManager() {
        managerController.handleCreateManager(nameField.getText(), contactInfoField.getText());
        clearFields();
    }

    @FXML
    private void handleFindManager() {
        Manager manager = managerController.handleGetManager(managerIdField.getText());
        if (manager != null) {
            nameField.setText(manager.getName());
            contactInfoField.setText(manager.getContactInfo());
        }
    }

    @FXML
    private void handleManageOrder() {
        managerController.handleManageOrder(managerIdField.getText(), orderIdField.getText(), orderStatusField.getText());
        clearFields();
    }

    @FXML
    private void handleManageSupplier() {
        managerController.handleManageSupplier(managerIdField.getText(), supplierIdField.getText());
        clearFields();
    }

    @FXML
    private void handleDeleteManager() {
        managerController.handleDeleteManager(managerIdField.getText());
        clearFields();
    }

    private void clearFields() {
        managerIdField.clear();
        nameField.clear();
        contactInfoField.clear();
        orderIdField.clear();
        orderStatusField.clear();
        supplierIdField.clear();
    }
}