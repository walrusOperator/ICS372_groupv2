module com.example.ics372_groupv2 {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;

    opens com.example.ics372_groupv2 to javafx.fxml;
    exports com.example.ics372_groupv2;
}