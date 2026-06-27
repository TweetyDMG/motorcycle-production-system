package motorcycle.production.subsystem.ui;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import motorcycle.production.subsystem.model.User;
import motorcycle.production.subsystem.service.TestingService;
import motorcycle.production.subsystem.util.ServiceFactory;

public class TesterDashboardController {
    @FXML private TextField testIdField;
    @FXML private TextField resultField;
    @FXML private TextField issuesField;
    private TestingService testingService;
    private User user;

    public void setUser(User user) {
        this.user = user;
        initialize();
    }

    private void initialize() {
        testingService = ServiceFactory.testingService();
    }

    @FXML
    private void handlePerformTest() {
        try {
            testingService.performTest(testIdField.getText(), resultField.getText(), issuesField.getText());
            showInfoAlert("Успех", "Тест выполнен");
        } catch (IllegalArgumentException e) {
            showErrorAlert("Ошибка", e.getMessage());
        }
    }

    @FXML
    private void handleFixIssues() {
        try {
            testingService.fixIssues(testIdField.getText());
            showInfoAlert("Успех", "Проблемы устранены");
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