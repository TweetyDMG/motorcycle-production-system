package motorcycle.production.subsystem.ui;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import motorcycle.production.subsystem.handler.DeliveryHandler;
import motorcycle.production.subsystem.model.Delivery;
import motorcycle.production.subsystem.util.ServiceFactory;

public class DeliveryFormController {
    @FXML
    private TextField deliveryIdField;
    @FXML
    private TextField motorcycleIdField;
    @FXML
    private TextField customerIdField;
    @FXML
    private TextField destinationField;

    private DeliveryHandler deliveryController;

    @FXML
    private void initialize() {
        deliveryController = new DeliveryHandler(ServiceFactory.deliveryService());
    }

    @FXML
    private void handleCreateDelivery() {
        deliveryController.handleCreateDelivery(motorcycleIdField.getText(), customerIdField.getText(), destinationField.getText());
        clearFields();
    }

    @FXML
    private void handleFindDelivery() {
        Delivery delivery = deliveryController.handleGetDelivery(deliveryIdField.getText());
        if (delivery != null) {
            motorcycleIdField.setText(delivery.getMotorcycleID());
            customerIdField.setText(delivery.getCustomerID());
            destinationField.setText(delivery.getDestination());
        }
    }

    @FXML
    private void handleDeliverMotorcycle() {
        deliveryController.handleDeliverMotorcycle(deliveryIdField.getText());
        clearFields();
    }

    @FXML
    private void handleDeleteDelivery() {
        deliveryController.handleDeleteDelivery(deliveryIdField.getText());
        clearFields();
    }

    private void clearFields() {
        deliveryIdField.clear();
        motorcycleIdField.clear();
        customerIdField.clear();
        destinationField.clear();
    }
}