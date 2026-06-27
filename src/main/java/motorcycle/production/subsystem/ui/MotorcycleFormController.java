package motorcycle.production.subsystem.ui;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import motorcycle.production.subsystem.handler.MotorcycleHandler;
import motorcycle.production.subsystem.model.Component;
import motorcycle.production.subsystem.model.Motorcycle;
import motorcycle.production.subsystem.util.ServiceFactory;

public class MotorcycleFormController {
    @FXML
    private TextField motorcycleIdField;
    @FXML
    private TextField modelField;
    @FXML
    private TextField componentIdsField;

    private MotorcycleHandler motorcycleController;

    @FXML
    private void initialize() {
        motorcycleController = new MotorcycleHandler(ServiceFactory.motorcycleService());
    }

    @FXML
    private void handleCreateMotorcycle() {
        String[] componentIds = componentIdsField.getText().split(",");
        motorcycleController.handleCreateMotorcycle(modelField.getText(), componentIds);
        clearFields();
    }

    @FXML
    private void handleFindMotorcycle() {
        Motorcycle motorcycle = motorcycleController.handleGetMotorcycle(motorcycleIdField.getText());
        if (motorcycle != null) {
            modelField.setText(motorcycle.getModel());
            Component[] components = motorcycle.getComponents();
            // Преобразуем Component[] в строку ID
            String[] componentIds = new String[components.length];
            for (int i = 0; i < components.length; i++) {
                componentIds[i] = components[i].getComponentID();
            }
        }
    }

    @FXML
    private void handleAssembleMotorcycle() {
        motorcycleController.handleAssembleMotorcycle(motorcycleIdField.getText());
        clearFields();
    }

    @FXML
    private void handleTestMotorcycle() {
        motorcycleController.handleTestMotorcycle(motorcycleIdField.getText());
        clearFields();
    }

    @FXML
    private void handleDeleteMotorcycle() {
        motorcycleController.handleDeleteMotorcycle(motorcycleIdField.getText());
        clearFields();
    }

    private void clearFields() {
        motorcycleIdField.clear();
        modelField.clear();
        componentIdsField.clear();
    }
}