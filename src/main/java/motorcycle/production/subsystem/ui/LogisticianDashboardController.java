package motorcycle.production.subsystem.ui;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import motorcycle.production.subsystem.model.Delivery;
import motorcycle.production.subsystem.model.User;
import motorcycle.production.subsystem.service.DeliveryService;
import motorcycle.production.subsystem.util.ServiceFactory;

public class LogisticianDashboardController {
    @FXML private TextField motorcycleIdField;
    @FXML private TextField customerIdField;
    @FXML private TextField destinationField;
    @FXML private TableView<Delivery> deliveriesTable;
    @FXML private TableColumn<Delivery, String> deliveryIdColumn;
    @FXML private TableColumn<Delivery, String> motorcycleIdColumn;
    @FXML private TableColumn<Delivery, String> customerIdColumn;
    @FXML private TableColumn<Delivery, String> destinationColumn;

    private DeliveryService deliveryService;
    private User user;

    public void setUser(User user) {
        this.user = user;
        initialize();
    }

    private void initialize() {
        deliveryService = ServiceFactory.deliveryService();
        deliveryIdColumn.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue().getDeliveryID()));
        motorcycleIdColumn.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue().getMotorcycleID()));
        customerIdColumn.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue().getCustomerID()));
        destinationColumn.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue().getDestination()));
        loadDeliveries();
    }

    @FXML
    private void handleCreateDelivery() {
        try {
            deliveryService.createDelivery(motorcycleIdField.getText(), customerIdField.getText(), destinationField.getText());
            loadDeliveries();
            showInfoAlert("Успех", "Доставка создана");
        } catch (IllegalArgumentException e) {
            showErrorAlert("Ошибка", e.getMessage());
        }
    }

    @FXML
    private void handleDeliverMotorcycle() {
        Delivery selected = deliveriesTable.getSelectionModel().getSelectedItem();
        if (selected == null) {
            showErrorAlert("Ошибка", "Выберите доставку");
            return;
        }
        try {
            deliveryService.deliverMotorcycle(selected.getDeliveryID());
            loadDeliveries();
            showInfoAlert("Успех", "Мотоцикл доставлен");
        } catch (IllegalArgumentException e) {
            showErrorAlert("Ошибка", e.getMessage());
        }
    }

    private void loadDeliveries() {
        deliveriesTable.setItems(FXCollections.observableArrayList(deliveryService.getAllDeliveries()));
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