package motorcycle.production.subsystem.ui;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import motorcycle.production.subsystem.model.Order;
import motorcycle.production.subsystem.model.User;
import motorcycle.production.subsystem.service.OrderService;
import motorcycle.production.subsystem.service.ManagerService;
import motorcycle.production.subsystem.util.ServiceFactory;

public class ManagerDashboardController {
    @FXML private TextField orderIdField;
    @FXML private TextField statusField;
    @FXML private TableView<Order> ordersTable;
    @FXML private TableColumn<Order, String> orderIdColumn;
    @FXML private TableColumn<Order, String> clientIdColumn;
    @FXML private TableColumn<Order, String> motorcycleIdColumn;
    @FXML private TableColumn<Order, String> statusColumn;

    private ManagerService managerService;
    private OrderService orderService;
    private User user;

    public void setUser(User user) {
        this.user = user;
        initialize();
    }

    private void initialize() {
        orderService = ServiceFactory.orderService();
        managerService = ServiceFactory.managerService();
        orderIdColumn.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue().getOrderID()));
        clientIdColumn.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue().getCustomerID()));
        motorcycleIdColumn.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue().getMotorcycleID()));
        statusColumn.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue().getStatus()));
        loadOrders();
    }

    @FXML
    private void handleUpdateOrderStatus() {
        try {
            managerService.manageOrder(user.getUserId(), orderIdField.getText(), statusField.getText());
            loadOrders();
            showInfoAlert("Успех", "Статус заказа обновлён");
        } catch (IllegalArgumentException e) {
            showErrorAlert("Ошибка", e.getMessage());
        }
    }

    private void loadOrders() {
        ordersTable.setItems(FXCollections.observableArrayList(orderService.getAllOrders()));
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