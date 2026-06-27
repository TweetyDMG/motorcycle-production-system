module motorcycle.production.subsystem.mispisapp {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens motorcycle.production.subsystem.ui to javafx.fxml;
    opens motorcycle.production.subsystem to javafx.fxml;
    exports motorcycle.production.subsystem;
    exports motorcycle.production.subsystem.ui;
}