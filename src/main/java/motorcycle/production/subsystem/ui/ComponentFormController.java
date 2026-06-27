package motorcycle.production.subsystem.ui;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import motorcycle.production.subsystem.handler.ComponentHandler;
import motorcycle.production.subsystem.model.Component;
import motorcycle.production.subsystem.util.ServiceFactory;

public class ComponentFormController {
    @FXML
    private TextField componentIdField;
    @FXML
    private TextField nameField;
    @FXML
    private TextField materialIdField;

    private ComponentHandler componentController;

    @FXML
    private void initialize() {
        componentController = new ComponentHandler(ServiceFactory.componentService());
    }

    @FXML
    private void handleCreateComponent() {
        componentController.handleCreateComponent(nameField.getText(), materialIdField.getText());
        clearFields();
    }

    @FXML
    private void handleFindComponent() {
        Component component = componentController.handleGetComponent(componentIdField.getText());
        if (component != null) {
            nameField.setText(component.getName());
            materialIdField.setText(component.getMaterialID());
        }
    }

    @FXML
    private void handleManufactureComponent() {
        componentController.handleManufactureComponent(componentIdField.getText());
        clearFields();
    }

    @FXML
    private void handleUpdateComponent() {
        componentController.handleUpdateComponent(componentIdField.getText(), nameField.getText(), materialIdField.getText());
        clearFields();
    }

    @FXML
    private void handleDeleteComponent() {
        componentController.handleDeleteComponent(componentIdField.getText());
        clearFields();
    }

    private void clearFields() {
        componentIdField.clear();
        nameField.clear();
        materialIdField.clear();
    }
}