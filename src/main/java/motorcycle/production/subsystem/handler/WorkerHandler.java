package motorcycle.production.subsystem.handler;

import javafx.scene.control.Alert;
import motorcycle.production.subsystem.model.Worker;
import motorcycle.production.subsystem.service.WorkerService;

public class WorkerHandler {
    private final WorkerService workerService;

    public WorkerHandler(WorkerService workerService) {
        this.workerService = workerService;
    }

    public void handleCreateWorker(String name, String specialization) {
        try {
            workerService.createWorker(name, specialization);
            showInfoAlert("Успех", "Работник успешно создан");
        } catch (IllegalArgumentException e) {
            showErrorAlert("Ошибка", e.getMessage());
        }
    }

    public Worker handleGetWorker(String workerId) {
        try {
            return workerService.getWorkerById(workerId);
        } catch (IllegalArgumentException e) {
            showErrorAlert("Ошибка", e.getMessage());
            return null;
        }
    }

    public void handleUpdateWorker(String workerId, String name, String specialization) {
        try {
            workerService.updateWorker(workerId, name, specialization);
            showInfoAlert("Успех", "Работник успешно обновлен");
        } catch (IllegalArgumentException e) {
            showErrorAlert("Ошибка", e.getMessage());
        }
    }

    public void handleDeleteWorker(String workerId) {
        try {
            workerService.deleteWorker(workerId);
            showInfoAlert("Успех", "Работник успешно удален");
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