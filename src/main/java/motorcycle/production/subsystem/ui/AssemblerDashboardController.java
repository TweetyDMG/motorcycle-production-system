package motorcycle.production.subsystem.ui;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import motorcycle.production.subsystem.model.User;
import motorcycle.production.subsystem.service.MotorcycleService;
import motorcycle.production.subsystem.util.ServiceFactory;

public class AssemblerDashboardController {
    @FXML private TextField motorcycleIdField;
    private MotorcycleService motorcycleService;
    private User user;

    public void setUser(User user) {
        this.user = user;
        initialize();
    }

    private void initialize() {
        motorcycleService = ServiceFactory.motorcycleService();
    }

    @FXML
    private void handleAssembleMotorcycle() {
        try {
            motorcycleService.assembleMotorcycle(motorcycleIdField.getText());
            showInfoAlert("Успех", "Мотоцикл собран");
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