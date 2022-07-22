module ShelterModules {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires json.simple;
    requires org.json;
    requires java.xml;
    requires junit;

    opens ShelterModules to javafx.fxml, javafx.base;
    exports ShelterModules to javafx.graphics, junit;

}
