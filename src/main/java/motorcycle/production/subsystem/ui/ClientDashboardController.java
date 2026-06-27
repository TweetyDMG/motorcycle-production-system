package motorcycle.production.subsystem.ui;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import motorcycle.production.subsystem.model.Order;
import motorcycle.production.subsystem.model.User;
import motorcycle.production.subsystem.service.OrderService;
import motorcycle.production.subsystem.service.ClientService;
import motorcycle.production.subsystem.util.ServiceFactory;

import java.time.LocalDate;

public class ClientDashboardController {
    @FXML private ChoiceBox<String> motorcycleModelChoiceBox;
    @FXML private TableView<Order> ordersTable;
    @FXML private TableColumn<Order, String> orderIdColumn;
    @FXML private TableColumn<Order, String> motorcycleIdColumn;
    @FXML private TableColumn<Order, String> statusColumn;

    private OrderService orderService;
    private ClientService clientService;
    private User user;

    public void setUser(User user) {
        this.user = user;
        initialize();
    }

    private void initialize() {
        clientService = ServiceFactory.clientService();
        orderService = ServiceFactory.orderService();
        orderIdColumn.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue().getOrderID()));
        motorcycleIdColumn.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue().getMotorcycleID()));
        statusColumn.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue().getStatus()));
        loadOrders();
    }

    @FXML
    private void handleCreateOrder() {
        try {
            if (user == null) {
                throw new IllegalStateException("Пользователь не инициализирован");
            }
            String selectedModel = motorcycleModelChoiceBox.getValue();
            if (selectedModel == null) {
                throw new IllegalArgumentException("Выберите модель мотоцикла");
            }
            orderService.createOrder(user.getUserId(), selectedModel, LocalDate.now().toString());
            loadOrders();
            showInfoAlert("Успех", "Заказ создан");
        } catch (IllegalArgumentException | IllegalStateException e) {
            showErrorAlert("Ошибка", e.getMessage());
        }
    }

    @FXML
    private void handleUpdateContact() {
        showInfoAlert("Успех", "Контактная информация обновлена (заглушка)");
    }

    private void loadOrders() {
        if (user != null) {
            ordersTable.setItems(FXCollections.observableArrayList(orderService.getOrdersByClient(user.getUserId())));
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