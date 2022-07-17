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
import java.util.ResourceBundle;

public class Controller2 implements Initializable {

    private static final ShelterList shelterMap = new ShelterList();
    @FXML
    private AnchorPane scene2;
    @FXML
    private Label label2;
    @FXML
    private Label shelterLabel;
    @FXML
    private ChoiceBox shelterChoiceBox;
    @FXML
    private Label typeLabel;
    @FXML
    private ChoiceBox typeChoiceBox;
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
    private Label receiptLabel;
    @FXML
    private TextField receiptTextField;
    @FXML
    private Button enterButton;
    private String[] animalType = {"Dog", "Cat", "Bird", "Rabbit"};
    private String[] shelterList = {String.valueOf(shelterMap.getShelters())};
    private String shelter;
    private String animal_ID;
    private String animal_Type;
    private String animal_Name;
    private double animal_weight;
    private String animal_unit;
    private long receipt_date;

    public void initialize(URL arg0, ResourceBundle arg1){
        typeChoiceBox.getItems().addAll(animalType);
        shelterChoiceBox.getItems().addAll(shelterList);
    }

    public void enter(ActionEvent e) throws IOException {
        shelter = shelterChoiceBox.getId();
        animal_Type = typeChoiceBox.getId();
        animal_ID = idTextField.getText();
        animal_Name = nameTextField.getText();
        animal_weight = Double.parseDouble(weightTextField.getText());
        receipt_date = Long.parseLong(receiptTextField.getText());

        Animal newAnimal = new Animal(animal_Type,animal_ID, animal_Name, animal_weight, animal_unit, receipt_date);
        if (newAnimal != null) {
            Shelter.addUserCreatedAnimal(newAnimal, shelter, shelterMap);
        }

        Parent root = FXMLLoader.load(getClass().getResource("gui-user-menu.fxml"));
        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }






}
