package motorcycle.production.subsystem.handler;

import javafx.scene.control.Alert;
import motorcycle.production.subsystem.model.Material;
import motorcycle.production.subsystem.service.MaterialService;

public class MaterialHandler {
    private final MaterialService materialService;

    public MaterialHandler(MaterialService materialService) {
        this.materialService = materialService;
    }

    public void handleCreateMaterial(String name, int quantity) {
        try {
            materialService.createMaterial(name, quantity);
            showInfoAlert("Успех", "Материал успешно создан");
        } catch (IllegalArgumentException e) {
            showErrorAlert("Ошибка", e.getMessage());
        }
    }

    public Material handleGetMaterial(String materialId) {
        try {
            return materialService.getMaterialById(materialId);
        } catch (IllegalArgumentException e) {
            showErrorAlert("Ошибка", e.getMessage());
            return null;
        }
    }

    public void handleUpdateMaterial(String materialId, String name, int quantity) {
        try {
            Material material = materialService.getMaterialById(materialId);
            material.setName(name);
            material.setQuantity(quantity);
            materialService.updateMaterial(material);
            showInfoAlert("Успех", "Материал успешно обновлен");
        } catch (IllegalArgumentException e) {
            showErrorAlert("Ошибка", e.getMessage());
        }
    }

    public void handleDeleteMaterial(String materialId) {
        try {
            materialService.deleteMaterial(materialId);
            showInfoAlert("Успех", "Материал успешно удален");
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