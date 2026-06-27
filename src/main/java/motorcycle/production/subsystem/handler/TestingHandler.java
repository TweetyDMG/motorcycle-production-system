package motorcycle.production.subsystem.handler;

import javafx.scene.control.Alert;
import motorcycle.production.subsystem.model.Testing;
import motorcycle.production.subsystem.service.TestingService;

public class TestingHandler {
    private final TestingService testingService;

    public TestingHandler(TestingService testingService) {
        this.testingService = testingService;
    }

    public void handleCreateTest(String motorcycleId) {
        try {
            testingService.createTest(motorcycleId);
            showInfoAlert("Успех", "Тест успешно создан");
        } catch (IllegalArgumentException e) {
            showErrorAlert("Ошибка", e.getMessage());
        }
    }

    public Testing handleGetTest(String testId) {
        try {
            return testingService.getTestById(testId);
        } catch (IllegalArgumentException e) {
            showErrorAlert("Ошибка", e.getMessage());
            return null;
        }
    }

    public void handlePerformTest(String testId, String result, String issues) {
        try {
            testingService.performTest(testId, result, issues);
            showInfoAlert("Успех", "Тест успешно выполнен");
        } catch (IllegalArgumentException e) {
            showErrorAlert("Ошибка", e.getMessage());
        }
    }

    public void handleFixIssues(String testId) {
        try {
            testingService.fixIssues(testId);
            showInfoAlert("Успех", "Проблемы успешно устранены");
        } catch (IllegalArgumentException e) {
            showErrorAlert("Ошибка", e.getMessage());
        }
    }

    public void handleDeleteTest(String testId) {
        try {
            testingService.deleteTest(testId);
            showInfoAlert("Успех", "Тест успешно удален");
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