package motorcycle.production.subsystem.ui;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import motorcycle.production.subsystem.handler.ClientHandler;
import motorcycle.production.subsystem.model.Client;
import motorcycle.production.subsystem.util.ServiceFactory;

public class ClientFormController1 {
    @FXML
    private TextField customerIdField;
    @FXML
    private TextField nameField;
    @FXML
    private TextField contactInfoField;

    private ClientHandler clientController;

    @FXML
    private void initialize() {
        clientController = new ClientHandler(ServiceFactory.clientService());
    }

    @FXML
    private void handleCreateClient() {
        clientController.handleCreateClient(nameField.getText(), contactInfoField.getText());
        clearFields();
    }

    @FXML
    private void handleFindClient() {
        Client client = clientController.handleGetClient(customerIdField.getText());
        if (client != null) {
            nameField.setText(client.getName());
            contactInfoField.setText(client.getContactInfo());
        }
    }

    @FXML
    private void handleUpdateClient() {
        clientController.handleUpdateClient(customerIdField.getText(), nameField.getText(), contactInfoField.getText());
        clearFields();
    }

    @FXML
    private void handleDeleteClient() {
        clientController.handleDeleteClient(customerIdField.getText());
        clearFields();
    }

    private void clearFields() {
        customerIdField.clear();
        nameField.clear();
        contactInfoField.clear();
    }
}