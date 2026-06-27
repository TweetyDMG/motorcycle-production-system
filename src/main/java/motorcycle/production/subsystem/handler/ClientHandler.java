package motorcycle.production.subsystem.handler;

import javafx.scene.control.Alert;
import motorcycle.production.subsystem.model.Client;
import motorcycle.production.subsystem.service.ClientService;

public class ClientHandler {
    private final ClientService clientService;

    public ClientHandler(ClientService clientService) {
        this.clientService = clientService;
    }

    public void handleCreateClient(String name, String contactInfo) {
        try {
            clientService.createClient(name, contactInfo);
            showInfoAlert("Успех", "Клиент успешно создан");
        } catch (IllegalArgumentException e) {
            showErrorAlert("Ошибка", e.getMessage());
        }
    }

    public Client handleGetClient(String customerId) {
        try {
            return clientService.getClientById(customerId);
        } catch (IllegalArgumentException e) {
            showErrorAlert("Ошибка", e.getMessage());
            return null;
        }
    }

    public void handleUpdateClient(String customerId, String name, String contactInfo) {
        try {
            clientService.updateClient(customerId, name, contactInfo);
            showInfoAlert("Успех", "Клиент успешно обновлен");
        } catch (IllegalArgumentException e) {
            showErrorAlert("Ошибка", e.getMessage());
        }
    }

    public void handleDeleteClient(String customerId) {
        try {
            clientService.deleteClient(customerId);
            showInfoAlert("Успех", "Клиент успешно удален");
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