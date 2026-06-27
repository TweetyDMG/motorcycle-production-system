package motorcycle.production.subsystem.ui;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import motorcycle.production.subsystem.handler.MaterialHandler;
import motorcycle.production.subsystem.model.Material;
import motorcycle.production.subsystem.util.ServiceFactory;

public class MaterialFormController {
    @FXML
    private TextField materialIdField;
    @FXML
    private TextField nameField;
    @FXML
    private TextField quantityField;

    private MaterialHandler materialController;

    @FXML
    private void initialize() {
        materialController = new MaterialHandler(ServiceFactory.materialService());
    }

    @FXML
    private void handleCreateMaterial() {
        materialController.handleCreateMaterial(nameField.getText(), Integer.parseInt(quantityField.getText()));
        clearFields();
    }

    @FXML
    private void handleFindMaterial() {
        Material material = materialController.handleGetMaterial(materialIdField.getText());
        if (material != null) {
            nameField.setText(material.getName());
            quantityField.setText(String.valueOf(material.getQuantity()));
        }
    }

    @FXML
    private void handleUpdateMaterial() {
        materialController.handleUpdateMaterial(materialIdField.getText(), nameField.getText(), Integer.parseInt(quantityField.getText()));
        clearFields();
    }

    @FXML
    private void handleDeleteMaterial() {
        materialController.handleDeleteMaterial(materialIdField.getText());
        clearFields();
    }

    private void clearFields() {
        materialIdField.clear();
        nameField.clear();
        quantityField.clear();
    }
}