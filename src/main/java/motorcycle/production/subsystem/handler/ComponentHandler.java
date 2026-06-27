package motorcycle.production.subsystem.handler;

import javafx.scene.control.Alert;
import motorcycle.production.subsystem.model.Component;
import motorcycle.production.subsystem.service.ComponentService;

public class ComponentHandler {
    private final ComponentService componentService;

    public ComponentHandler(ComponentService componentService) {
        this.componentService = componentService;
    }

    public void handleCreateComponent(String name, String materialId) {
        try {
            componentService.createComponent(name, materialId);
            showInfoAlert("Успех", "Компонент успешно создан");
        } catch (IllegalArgumentException e) {
            showErrorAlert("Ошибка", e.getMessage());
        }
    }

    public Component handleGetComponent(String componentId) {
        try {
            return componentService.getComponentById(componentId);
        } catch (IllegalArgumentException e) {
            showErrorAlert("Ошибка", e.getMessage());
            return null;
        }
    }

    public void handleManufactureComponent(String componentId) {
        try {
            componentService.manufactureComponent(componentId);
            showInfoAlert("Успех", "Компонент успешно изготовлен");
        } catch (IllegalArgumentException | IllegalStateException e) {
            showErrorAlert("Ошибка", e.getMessage());
        }
    }

    public void handleUpdateComponent(String componentId, String name, String materialId) {
        try {
            componentService.updateComponent(componentId, name, materialId);
            showInfoAlert("Успех", "Компонент успешно обновлен");
        } catch (IllegalArgumentException e) {
            showErrorAlert("Ошибка", e.getMessage());
        }
    }

    public void handleDeleteComponent(String componentId) {
        try {
            componentService.deleteComponent(componentId);
            showInfoAlert("Успех", "Компонент успешно удален");
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