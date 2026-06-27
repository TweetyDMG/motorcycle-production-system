package motorcycle.production.subsystem.handler;

import javafx.scene.control.Alert;
import motorcycle.production.subsystem.model.Order;
import motorcycle.production.subsystem.service.OrderService;

public class OrderHandler {
    private final OrderService orderService;

    public OrderHandler(OrderService orderService) {
        this.orderService = orderService;
    }

    public void handleCreateOrder(String customerId, String motorcycleId, String orderDate) {
        try {
            orderService.createOrder(customerId, motorcycleId, orderDate);
            showInfoAlert("Успех", "Заказ успешно создан");
        } catch (IllegalArgumentException e) {
            showErrorAlert("Ошибка", e.getMessage());
        }
    }

    public Order handleGetOrder(String orderId) {
        try {
            return orderService.getOrderById(orderId);
        } catch (IllegalArgumentException e) {
            showErrorAlert("Ошибка", e.getMessage());
            return null;
        }
    }

    public void handleUpdateOrderStatus(String orderId, String status) {
        try {
            orderService.updateOrderStatus(orderId, status);
            showInfoAlert("Успех", "Статус заказа успешно обновлен");
        } catch (IllegalArgumentException e) {
            showErrorAlert("Ошибка", e.getMessage());
        }
    }

    public void handleDeleteOrder(String orderId) {
        try {
            orderService.deleteOrder(orderId);
            showInfoAlert("Успех", "Заказ успешно удален");
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