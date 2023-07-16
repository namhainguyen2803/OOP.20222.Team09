module com.example.treedatastructure {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires java.desktop;

//    exports com.example.treedatastructure;
//    opens com.example.treedatastructure to javafx.fxml;
//    exports src.screen;
//    opens src.screen to javafx.fxml;
    exports src.test;
    opens src.test to javafx.fxml;
//    exports com.example.treedatastructure.treedatastructure;
//    opens com.example.treedatastructure.treedatastructure to javafx.fxml;
    exports src.treedatastructure;
    opens src.treedatastructure to javafx.fxml;
    exports src.screen.controller;
    opens src.screen.controller to javafx.fxml;
    exports src.screen.controller.operation;
    opens src.screen.controller.operation to javafx.fxml;
    exports src.exception;
    opens src.exception to javafx.fxml;
}