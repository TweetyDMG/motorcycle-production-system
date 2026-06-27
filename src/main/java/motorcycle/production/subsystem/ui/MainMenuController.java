package motorcycle.production.subsystem.ui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainMenuController {
    private void openForm(String fxmlFile) throws IOException {
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
        Scene scene = new Scene(loader.load());
        scene.getStylesheets().add(getClass().getResource("/motorcycle/production/subsystem/styles.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void openClientForm() throws IOException {
        openForm("/motorcycle/production/subsystem/ClientForm.fxml");
    }

    @FXML
    private void openOrderForm() throws IOException {
        openForm("/motorcycle/production/subsystem/OrderForm.fxml");
    }

    @FXML
    private void openMotorcycleForm() throws IOException {
        openForm("/motorcycle/production/subsystem/MotorcycleForm.fxml");
    }

    @FXML
    private void openDeliveryForm() throws IOException {
        openForm("/motorcycle/production/subsystem/DeliveryForm.fxml");
    }

    @FXML
    private void openTestingForm() throws IOException {
        openForm("/motorcycle/production/subsystem/TestingForm.fxml");
    }

    @FXML
    private void openWorkerForm() throws IOException {
        openForm("/motorcycle/production/subsystem/WorkerForm.fxml");
    }

    @FXML
    private void openComponentForm() throws IOException {
        openForm("/motorcycle/production/subsystem/ComponentForm.fxml");
    }

    @FXML
    private void openMaterialForm() throws IOException {
        openForm("/motorcycle/production/subsystem/MaterialForm.fxml");
    }

    @FXML
    private void openSupplierForm() throws IOException {
        openForm("/motorcycle/production/subsystem/SupplierForm.fxml");
    }

    @FXML
    private void openManagerForm() throws IOException {
        openForm("/motorcycle/production/subsystem/ManagerForm.fxml");
    }
}
