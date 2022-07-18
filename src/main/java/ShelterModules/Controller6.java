package ShelterModules;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

import static ShelterModules.Controller.shelterMap;

public class Controller6 implements Initializable {
    @FXML
    private Label label4;
    @FXML
    private Button enterButton;
    @FXML
    private ChoiceBox<Shelter> shelterListChoiceBox;

    private final Shelter[] shelterList = shelterMap.getShelters().toArray(new Shelter[0]);

    public void initialize(URL arg0, ResourceBundle arg1) {
        shelterListChoiceBox.getItems().addAll(shelterList);
    }

    public void onEnter(ActionEvent e) throws IOException {
        shelterListChoiceBox.getValue().changeReceiving(false);
        System.out.println(shelterListChoiceBox.getValue().isReceiving());

        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("gui-user-menu.fxml")));
        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
