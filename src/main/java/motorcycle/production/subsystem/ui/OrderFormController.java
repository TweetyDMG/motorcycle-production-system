package motorcycle.production.subsystem.ui;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import motorcycle.production.subsystem.handler.OrderHandler;
import motorcycle.production.subsystem.model.Order;
import motorcycle.production.subsystem.util.ServiceFactory;

public class OrderFormController {
    @FXML
    private TextField orderIdField;
    @FXML
    private TextField customerIdField;
    @FXML
    private TextField motorcycleIdField;
    @FXML
    private TextField orderDateField;
    @FXML
    private TextField statusField;

    private OrderHandler orderController;

    @FXML
    private void initialize() {
        orderController = new OrderHandler(ServiceFactory.orderService());
    }

    @FXML
    private void handleCreateOrder() {
        orderController.handleCreateOrder(customerIdField.getText(), motorcycleIdField.getText(), orderDateField.getText());
        clearFields();
    }

    @FXML
    private void handleFindOrder() {
        Order order = orderController.handleGetOrder(orderIdField.getText());
        if (order != null) {
            customerIdField.setText(order.getCustomerID());
            motorcycleIdField.setText(order.getMotorcycleID());
            orderDateField.setText(order.getOrderDate());
            statusField.setText(order.getStatus());
        }
    }

    @FXML
    private void handleUpdateStatus() {
        orderController.handleUpdateOrderStatus(orderIdField.getText(), statusField.getText());
        clearFields();
    }

    @FXML
    private void handleDeleteOrder() {
        orderController.handleDeleteOrder(orderIdField.getText());
        clearFields();
    }

    private void clearFields() {
        orderIdField.clear();
        customerIdField.clear();
        motorcycleIdField.clear();
        orderDateField.clear();
        statusField.clear();
    }
}