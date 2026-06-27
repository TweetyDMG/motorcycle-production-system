package motorcycle.production.subsystem.handler;

import javafx.scene.control.Alert;
import motorcycle.production.subsystem.model.Motorcycle;
import motorcycle.production.subsystem.service.MotorcycleService;

public class MotorcycleHandler {
    private final MotorcycleService motorcycleService;

    public MotorcycleHandler(MotorcycleService motorcycleService) {
        this.motorcycleService = motorcycleService;
    }

    public void handleCreateMotorcycle(String model, String[] componentIds) {
        try {
            motorcycleService.createMotorcycle(model, componentIds);
            showInfoAlert("Успех", "Мотоцикл успешно создан");
        } catch (IllegalArgumentException e) {
            showErrorAlert("Ошибка", e.getMessage());
        }
    }

    public Motorcycle handleGetMotorcycle(String motorcycleId) {
        try {
            return motorcycleService.getMotorcycleById(motorcycleId);
        } catch (IllegalArgumentException e) {
            showErrorAlert("Ошибка", e.getMessage());
            return null;
        }
    }

    public void handleAssembleMotorcycle(String motorcycleId) {
        try {
            motorcycleService.assembleMotorcycle(motorcycleId);
            showInfoAlert("Успех", "Мотоцикл успешно собран");
        } catch (IllegalArgumentException | IllegalStateException e) {
            showErrorAlert("Ошибка", e.getMessage());
        }
    }

    public void handleTestMotorcycle(String motorcycleId) {
        try {
            motorcycleService.testMotorcycle(motorcycleId);
            showInfoAlert("Успех", "Мотоцикл успешно протестирован");
        } catch (IllegalArgumentException e) {
            showErrorAlert("Ошибка", e.getMessage());
        }
    }

    public void handleDeleteMotorcycle(String motorcycleId) {
        try {
            motorcycleService.deleteMotorcycle(motorcycleId);
            showInfoAlert("Успех", "Мотоцикл успешно удален");
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