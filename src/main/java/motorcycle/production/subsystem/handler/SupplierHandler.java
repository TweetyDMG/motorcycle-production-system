package motorcycle.production.subsystem.handler;

import javafx.scene.control.Alert;
import motorcycle.production.subsystem.model.Supplier;
import motorcycle.production.subsystem.service.SupplierService;

public class SupplierHandler {
    private final SupplierService supplierService;

    public SupplierHandler(SupplierService supplierService) {
        this.supplierService = supplierService;
    }

    public void handleCreateSupplier(String materialId, String name, String contactInfo) {
        try {
            supplierService.createSupplier(materialId, name, contactInfo);
            showInfoAlert("Успех", "Поставщик успешно создан");
        } catch (IllegalArgumentException e) {
            showErrorAlert("Ошибка", e.getMessage());
        }
    }

    public Supplier handleGetSupplier(String supplierId) {
        try {
            return supplierService.getSupplierById(supplierId);
        } catch (IllegalArgumentException e) {
            showErrorAlert("Ошибка", e.getMessage());
            return null;
        }
    }

    public void handleSupplyMaterial(String supplierId, String materialId, int quantity) {
        try {
            supplierService.supplyMaterial(supplierId, materialId, quantity);
            showInfoAlert("Успех", "Материал успешно поставлен");
        } catch (IllegalArgumentException | IllegalStateException e) {
            showErrorAlert("Ошибка", e.getMessage());
        }
    }

    public void handleDeleteSupplier(String supplierId) {
        try {
            supplierService.deleteSupplier(supplierId);
            showInfoAlert("Успех", "Поставщик успешно удален");
        } catch (IllegalArgumentException e) {
            showErrorAlert("Ошибка", e.getMessage());
        }
    }

    private void showInfoAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void showErrorAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}