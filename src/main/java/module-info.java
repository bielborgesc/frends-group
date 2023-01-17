module grupodeamigos {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens view to javafx.fxml;
    exports view;
    exports controller;
    opens controller to javafx.fxml;
}