package motorcycle.production.subsystem.ui;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import motorcycle.production.subsystem.model.User;
import motorcycle.production.subsystem.service.ComponentService;
import motorcycle.production.subsystem.util.ServiceFactory;

public class ProductionWorkerDashboardController {
    @FXML private TextField componentIdField;
    private ComponentService componentService;
    private User user;

    public void setUser(User user) {
        this.user = user;
        initialize();
    }

    private void initialize() {
        componentService = ServiceFactory.componentService();
    }

    @FXML
    private void handleManufactureComponent() {
        try {
            componentService.manufactureComponent(componentIdField.getText());
            showInfoAlert("Успех", "Компонент изготовлен");
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