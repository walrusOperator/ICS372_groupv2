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
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

import static ShelterModules.Controller.shelterMap;

public class Controller2 implements Initializable {
    @FXML
    private AnchorPane scene2;
    @FXML
    private Label label2;
    @FXML
    private Label shelterLabel;
    @FXML
    private ChoiceBox<Shelter> shelterChoiceBox;
    @FXML
    private Label typeLabel;
    @FXML
    private ChoiceBox<String> typeChoiceBox;
    @FXML
    private Label nameLabel;
    @FXML
    private TextField nameTextField;
    @FXML
    private Label idLabel;
    @FXML
    private TextField idTextField;
    @FXML
    private Label weightLabel;
    @FXML
    private TextField weightTextField;
    @FXML
    private Label weightUnitLabel;
    @FXML
    private TextField weightUnitTextField;
    @FXML
    private Label receiptLabel;
    @FXML
    private TextField receiptTextField;
    @FXML
    private Button enterButton;
    @FXML
    private Button exitButton;
    private final String[] animalType = {"Dog", "Cat", "Bird", "Rabbit"};
    private final Shelter[] shelterList = shelterMap.getShelters().toArray(new Shelter[0]);

    public void initialize(URL arg0, ResourceBundle arg1){
        shelterChoiceBox.getItems().addAll(shelterList);
        typeChoiceBox.getItems().addAll(animalType);
    }
    public void enter(ActionEvent e){
        String animal_ID = idTextField.getText();
        String animal_Name = nameTextField.getText();
        double animal_weight = Double.parseDouble(weightTextField.getText());
        String animal_unit = weightUnitTextField.getText();
        long receipt_date = Long.parseLong(receiptTextField.getText());

        if (shelterChoiceBox.getValue().isReceiving()) {
            Animal newAnimal = new Animal(typeChoiceBox.getValue(),animal_Name,animal_ID,animal_weight,animal_unit,receipt_date);
            Shelter.addUserCreatedAnimal(newAnimal, String.valueOf(shelterChoiceBox.getValue()), shelterMap);
        }
    }

    public void exit(ActionEvent e) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("gui-user-menu.fxml")));
        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }





}
