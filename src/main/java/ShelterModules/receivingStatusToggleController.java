package ShelterModules;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

import static ShelterModules.guiMainController.shelterMap;

/**
 * Controller that controls the window to changing the receiving of a shelter once shelter is selected by user.
 */

public class receivingStatusToggleController implements Initializable {
    @FXML
    private Label myLabel3;
    @FXML
    private ChoiceBox<Shelter> shelterChoiceBox;
    @FXML
    private Button enterButton;
    private final Shelter[] shelterList = shelterMap.getShelters().toArray(new Shelter[0]);

    public void initialize(URL arg0, ResourceBundle arg1) {
        shelterChoiceBox.getItems().addAll(shelterList);
    }

    /**
     * Method used to toggle/display the receiving status of selected shelter.
     * @param e - once button is click, event occurs.
     * @throws IOException
     */
    public void onEnter(ActionEvent e) throws IOException {

        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("gui-user-menu.fxml")));
        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

        if(!shelterChoiceBox.getValue().isReceiving()){
            shelterMap.getShelter(shelterChoiceBox.getValue().getShelterID()).setReceiving(true);
            scene = new Scene(new Group());
            stage = new Stage();
            stage.setTitle("Receiving Status");
            stage.setWidth(400);
            stage.setHeight(80);

            Label myLabel = new Label("Shelter :" + shelterChoiceBox.getValue() + " receiving status changed to " +
                                    shelterMap.getShelter(shelterChoiceBox.getValue().getShelterID()).isReceiving());
            final VBox vbox = new VBox();
            vbox.setSpacing(5);
            vbox.setPadding(new Insets(10, 0, 0, 10));
            vbox.getChildren().addAll(myLabel);

            ((Group) scene.getRoot()).getChildren().addAll(vbox);

        }
        else{
            shelterMap.getShelter(shelterChoiceBox.getValue().getShelterID()).setReceiving(false);
            scene = new Scene(new Group());
            stage = new Stage();
            stage.setTitle("Receiving Status");
            stage.setWidth(400);
            stage.setHeight(80);

            Label myLabel = new Label("Shelter " + shelterChoiceBox.getValue() + " receiving status changed to " +
                                        shelterMap.getShelter(shelterChoiceBox.getValue().getShelterID()).isReceiving());
            final VBox vbox = new VBox();
            vbox.setSpacing(5);
            vbox.setPadding(new Insets(10, 0, 0, 10));
            vbox.getChildren().addAll(myLabel);

            ((Group) scene.getRoot()).getChildren().addAll(vbox);
        }

        stage.setScene(scene);
        stage.show();

        FileUtilities.writeJSON(shelterMap, "Save_Data.json");
    }
}
