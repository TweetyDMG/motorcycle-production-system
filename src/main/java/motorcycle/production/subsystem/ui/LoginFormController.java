package motorcycle.production.subsystem.ui;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import motorcycle.production.subsystem.model.User;
import motorcycle.production.subsystem.service.UserService;
import motorcycle.production.subsystem.util.ServiceFactory;

import java.io.IOException;
import java.util.logging.Logger;

public class LoginFormController {

    private static final Logger LOG = Logger.getLogger(LoginFormController.class.getName());

    @FXML private TextField loginField;
    @FXML private PasswordField passwordField;
    @FXML private ChoiceBox<String> roleChoiceBox;
    @FXML private ChoiceBox<String> subroleChoiceBox;
    @FXML private Label subroleLabel;

    private final UserService userService = ServiceFactory.userService();

    @FXML
    private void initialize() {
        roleChoiceBox.setItems(FXCollections.observableArrayList("Клиент", "Менеджер", "Работник", "Логист"));
        subroleChoiceBox.setItems(FXCollections.observableArrayList("Производственный", "Сборщик", "Тестировщик"));
        roleChoiceBox.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
            boolean isWorker = "Работник".equals(newVal);
            subroleLabel.setVisible(isWorker);
            subroleChoiceBox.setVisible(isWorker);
        });
    }

    @FXML
    private void handleLogin() {
        try {
            User user = userService.loginUser(loginField.getText(), passwordField.getText());
            openRoleWindow(user);
        } catch (IllegalArgumentException e) {
            showErrorAlert("Ошибка входа", e.getMessage());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    private void handleRegister() {
        String role = roleChoiceBox.getValue();
        String subrole = "Работник".equals(role) ? subroleChoiceBox.getValue() : null;
        try {
            userService.registerUser(loginField.getText(), passwordField.getText(), role, subrole);
            showInfoAlert("Успех", "Пользователь зарегистрирован");
        } catch (IllegalArgumentException e) {
            showErrorAlert("Ошибка регистрации", e.getMessage());
        }
    }

    private void openRoleWindow(User user) throws IOException {
        Stage stage = new Stage();
        String fxmlFile;
        switch (user.getRole()) {
            case "Клиент": fxmlFile = "ClientDashboard.fxml"; break;
            case "Менеджер": fxmlFile = "ManagerDashboard.fxml"; break;
            case "Работник":
                switch (user.getSubrole()) {
                    case "Производственный": fxmlFile = "ProductionWorkerDashboard.fxml"; break;
                    case "Сборщик": fxmlFile = "AssemblerDashboard.fxml"; break;
                    case "Тестировщик": fxmlFile = "TesterDashboard.fxml"; break;
                    default: throw new IllegalArgumentException("Неверная подроль работника");
                }
                break;
            case "Логист": fxmlFile = "LogisticianDashboard.fxml"; break;
            default: throw new IllegalArgumentException("Неверная роль");
        }
        LOG.info("Loading FXML: /motorcycle/production/subsystem/ui/" + fxmlFile);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/motorcycle/production/subsystem/" + fxmlFile));
        if (loader.getLocation() == null) {
            throw new IllegalStateException("FXML file not found: /motorcycle/production/subsystem/" + fxmlFile);
        }
        Scene scene = new Scene(loader.load(), 800, 600);
        scene.getStylesheets().add(getClass().getResource("/motorcycle/production/subsystem/styles.css").toExternalForm());

        // Передача объекта User в контроллер
        Object controller = loader.getController();
        if (controller instanceof ClientDashboardController) {
            ((ClientDashboardController) controller).setUser(user);
        } else if (controller instanceof ManagerDashboardController) {
            ((ManagerDashboardController) controller).setUser(user);
        } else if (controller instanceof ProductionWorkerDashboardController) {
            ((ProductionWorkerDashboardController) controller).setUser(user);
        } else if (controller instanceof AssemblerDashboardController) {
            ((AssemblerDashboardController) controller).setUser(user);
        } else if (controller instanceof TesterDashboardController) {
            ((TesterDashboardController) controller).setUser(user);
        } else if (controller instanceof LogisticianDashboardController) {
            ((LogisticianDashboardController) controller).setUser(user);
        }

        stage.setScene(scene);
        stage.show();
        loginField.getScene().getWindow().hide();
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