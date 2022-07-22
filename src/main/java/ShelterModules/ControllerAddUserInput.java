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
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

import static ShelterModules.ControllerGuiMain.shelterMap;

/**
 * Controller class that controls the window of user's input and adding new animal into selected shelter.
 */
public class ControllerAddUserInput implements Initializable {
    @FXML
    private ChoiceBox<Shelter> shelterChoiceBox;
    @FXML
    private ChoiceBox<String> typeChoiceBox;
    @FXML
    private TextField nameTextField;
    @FXML
    private TextField idTextField;
    @FXML
    private TextField weightTextField;
    @FXML
    private TextField weightUnitTextField;
    @FXML
    private TextField receiptTextField;
    private final String[] animalType = {"Dog", "Cat", "Bird", "Rabbit"};
    private final Shelter[] shelterList = shelterMap.getShelters().toArray(new Shelter[0]);

    public void initialize(URL arg0, ResourceBundle arg1) {
        shelterChoiceBox.getItems().addAll(shelterList);
        typeChoiceBox.getItems().addAll(animalType);
    }

    /**
     * Method used to add animal based on user's input once animal attributes are inputted.
     * @param e - once button is click, event occurs.
     * @throws IOException
     */
    public void enter(ActionEvent e) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("gui-user-menu.fxml")));
        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

        Shelter shelter = shelterChoiceBox.getSelectionModel().selectedItemProperty().getValue();
        String animal_Type = String.valueOf(typeChoiceBox.getSelectionModel().selectedItemProperty().getValue());
        String animal_ID = idTextField.getText();
        String animal_Name = nameTextField.getText();
        double animal_weight = Double.parseDouble(weightTextField.getText());
        String weight_unit = weightUnitTextField.getText();
        long receipt_date = Long.parseLong(receiptTextField.getText());

        scene = new Scene(new Group());
        stage = new Stage();
        stage.setTitle("Add Animal Status");
        stage.setWidth(400);
        stage.setHeight(80);
        Label myLabel = new Label();

        if (shelterChoiceBox.getValue().isReceiving()) {
            Animal newAnimal = new Animal(animal_Type, animal_Name, animal_ID, animal_weight, weight_unit, receipt_date);
            Shelter.addUserCreatedAnimal(newAnimal, shelter.getShelterID(), shelterMap);

            if (!shelter.getAnimalList().contains(newAnimal)){
                stage.setTitle("Add Animal Status");
                myLabel = new Label("Animal: " + newAnimal.getAnimal_Name() + " was successfully added!");
            } else {
                stage.setTitle("Add Animal Status");

                myLabel = new Label("Animal: " + newAnimal.getAnimal_Name() + " was not successfully added!");
            }
        }
        else{
            stage.setTitle("Receiving is False");
            myLabel = new Label("Can not add animal due to receiving being false!");
        }
        final VBox vbox = new VBox();
        vbox.setSpacing(5);
        vbox.setPadding(new Insets(10, 0, 0, 10));
        vbox.getChildren().addAll(myLabel);

        ((Group) scene.getRoot()).getChildren().addAll(vbox);

        stage.setScene(scene);
        stage.show();

        FileUtilities.writeJSON(shelterMap, "Save_Data.json");
    }

}
