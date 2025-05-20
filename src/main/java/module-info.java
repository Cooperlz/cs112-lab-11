module cs112.ud3 {
    requires javafx.controls;
    requires javafx.fxml;

    opens cs112.ud3 to javafx.fxml;
    exports cs112.ud3;
    exports cs112.ud3.controllers;
    exports cs112.ud3.models; //added manually
    opens cs112.ud3.controllers to javafx.fxml;
    opens cs112.ud3.models to javafx.fxml; //added manually
}