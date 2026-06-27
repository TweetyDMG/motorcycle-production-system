package motorcycle.production.subsystem.handler;

import javafx.scene.control.Alert;
import motorcycle.production.subsystem.model.Delivery;
import motorcycle.production.subsystem.service.DeliveryService;

public class DeliveryHandler {
    private final DeliveryService deliveryService;

    public DeliveryHandler(DeliveryService deliveryService) {
        this.deliveryService = deliveryService;
    }

    public void handleCreateDelivery(String motorcycleId, String customerId, String destination) {
        try {
            deliveryService.createDelivery(motorcycleId, customerId, destination);
            showInfoAlert("Успех", "Доставка успешно создана");
        } catch (IllegalArgumentException e) {
            showErrorAlert("Ошибка", e.getMessage());
        }
    }

    public Delivery handleGetDelivery(String deliveryId) {
        try {
            return deliveryService.getDeliveryById(deliveryId);
        } catch (IllegalArgumentException e) {
            showErrorAlert("Ошибка", e.getMessage());
            return null;
        }
    }

    public void handleDeliverMotorcycle(String deliveryId) {
        try {
            deliveryService.deliverMotorcycle(deliveryId);
            showInfoAlert("Успех", "Мотоцикл успешно доставлен");
        } catch (IllegalArgumentException e) {
            showErrorAlert("Ошибка", e.getMessage());
        }
    }

    public void handleDeleteDelivery(String deliveryId) {
        try {
            deliveryService.deleteDelivery(deliveryId);
            showInfoAlert("Успех", "Доставка успешно удалена");
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