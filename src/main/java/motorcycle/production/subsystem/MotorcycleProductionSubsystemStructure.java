package motorcycle.production.subsystem;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.logging.Logger;

public class MotorcycleProductionSubsystemStructure extends Application {

    private static final Logger LOG = Logger.getLogger(MotorcycleProductionSubsystemStructure.class.getName());

    @Override
    public void start(Stage stage) throws IOException {
        LOG.info("Initializing Motorcycle Production Subsystem...");
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/motorcycle/production/subsystem/LoginForm.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        scene.getStylesheets().add(getClass().getResource("/motorcycle/production/subsystem/styles.css").toExternalForm());
        stage.setTitle("Подсистема производства мотоциклов");
        stage.setScene(scene);
        stage.show();
        LOG.info("Motorcycle Production Subsystem UI started successfully.");
    }

    public static void main(String[] args) {
        launch(args);
    }
}