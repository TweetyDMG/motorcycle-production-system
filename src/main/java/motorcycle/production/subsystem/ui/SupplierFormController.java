package motorcycle.production.subsystem.ui;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import motorcycle.production.subsystem.handler.SupplierHandler;
import motorcycle.production.subsystem.model.Supplier;
import motorcycle.production.subsystem.util.ServiceFactory;

public class SupplierFormController {
    @FXML
    private TextField supplierIdField;
    @FXML
    private TextField materialIdField;
    @FXML
    private TextField nameField;
    @FXML
    private TextField contactInfoField;
    @FXML
    private TextField quantityField;

    private SupplierHandler supplierController;

    @FXML
    private void initialize() {
        supplierController = new SupplierHandler(ServiceFactory.supplierService());
    }

    @FXML
    private void handleCreateSupplier() {
        supplierController.handleCreateSupplier(materialIdField.getText(), nameField.getText(), contactInfoField.getText());
        clearFields();
    }

    @FXML
    private void handleFindSupplier() {
        Supplier supplier = supplierController.handleGetSupplier(supplierIdField.getText());
        if (supplier != null) {
            materialIdField.setText(supplier.getMaterialID());
            nameField.setText(supplier.getName());
            contactInfoField.setText(supplier.getContactInfo());
        }
    }

    @FXML
    private void handleSupplyMaterial() {
        supplierController.handleSupplyMaterial(supplierIdField.getText(), materialIdField.getText(), Integer.parseInt(quantityField.getText()));
        clearFields();
    }

    @FXML
    private void handleDeleteSupplier() {
        supplierController.handleDeleteSupplier(supplierIdField.getText());
        clearFields();
    }

    private void clearFields() {
        supplierIdField.clear();
        materialIdField.clear();
        nameField.clear();
        contactInfoField.clear();
        quantityField.clear();
    }
}