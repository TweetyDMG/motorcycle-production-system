package motorcycle.production.subsystem.handler;

import javafx.scene.control.Alert;
import motorcycle.production.subsystem.model.Manager;
import motorcycle.production.subsystem.service.ManagerService;

public class ManagerHandler {
    private final ManagerService managerService;

    public ManagerHandler(ManagerService managerService) {
        this.managerService = managerService;
    }

    public void handleCreateManager(String name, String contactInfo) {
        try {
            managerService.createManager(name, contactInfo);
            showInfoAlert("Успех", "Менеджер успешно создан");
        } catch (IllegalArgumentException e) {
            showErrorAlert("Ошибка", e.getMessage());
        }
    }

    public Manager handleGetManager(String managerId) {
        try {
            return managerService.getManagerById(managerId);
        } catch (IllegalArgumentException e) {
            showErrorAlert("Ошибка", e.getMessage());
            return null;
        }
    }

    public void handleManageOrder(String managerId, String orderId, String status) {
        try {
            managerService.manageOrder(managerId, orderId, status);
            showInfoAlert("Успех", "Заказ успешно обновлен");
        } catch (IllegalArgumentException e) {
            showErrorAlert("Ошибка", e.getMessage());
        }
    }

    public void handleManageSupplier(String managerId, String supplierId) {
        try {
            managerService.manageSupplier(managerId, supplierId);
            showInfoAlert("Успех", "Поставщик успешно обновлен");
        } catch (IllegalArgumentException e) {
            showErrorAlert("Ошибка", e.getMessage());
        }
    }

    public void handleDeleteManager(String managerId) {
        try {
            managerService.deleteManager(managerId);
            showInfoAlert("Успех", "Менеджер успешно удален");
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