module com.example.treedatastructure {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;

    exports com.example.treedatastructure;
    opens com.example.treedatastructure to javafx.fxml;
}