module ShelterModules {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires json.simple;

    opens ShelterModules to javafx.fxml;
    exports ShelterModules to javafx.graphics;
}
