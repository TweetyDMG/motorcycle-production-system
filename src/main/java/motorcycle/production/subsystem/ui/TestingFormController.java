package motorcycle.production.subsystem.ui;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import motorcycle.production.subsystem.handler.TestingHandler;
import motorcycle.production.subsystem.model.Testing;
import motorcycle.production.subsystem.util.ServiceFactory;

public class TestingFormController {
    @FXML
    private TextField testIdField;
    @FXML
    private TextField motorcycleIdField;
    @FXML
    private TextField resultField;
    @FXML
    private TextField issuesField;

    private TestingHandler testingController;

    @FXML
    private void initialize() {
        testingController = new TestingHandler(ServiceFactory.testingService());
    }

    @FXML
    private void handleCreateTest() {
        testingController.handleCreateTest(motorcycleIdField.getText());
        clearFields();
    }

    @FXML
    private void handleFindTest() {
        Testing testing = testingController.handleGetTest(testIdField.getText());
        if (testing != null) {
            motorcycleIdField.setText(testing.getMotorcycleID());
            resultField.setText(testing.getResult());
            issuesField.setText(testing.getIssues());
        }
    }

    @FXML
    private void handlePerformTest() {
        testingController.handlePerformTest(testIdField.getText(), resultField.getText(), issuesField.getText());
        clearFields();
    }

    @FXML
    private void handleFixIssues() {
        testingController.handleFixIssues(testIdField.getText());
        clearFields();
    }

    @FXML
    private void handleDeleteTest() {
        testingController.handleDeleteTest(testIdField.getText());
        clearFields();
    }

    private void clearFields() {
        testIdField.clear();
        motorcycleIdField.clear();
        resultField.clear();
        issuesField.clear();
    }
}