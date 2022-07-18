package ShelterModules;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

import static ShelterModules.Controller.shelterMap;

public class Controller3 implements Initializable {
    @FXML
    private Label label5;
    @FXML
    private ChoiceBox<Shelter> shelterChoiceBox;
    @FXML
    private Button enterButton;

    private final Shelter[] shelterList = shelterMap.getShelters().toArray(new Shelter[0]);

    public void initialize(URL arg0, ResourceBundle arg1) {
        shelterChoiceBox.getItems().addAll(shelterList);
    }

    private final TableView<Animal> table = new TableView<>();

    String animal_type = null;
    String animal_name = null;
    String animal_id = null;
    double animal_weight = 0;
    String weight_unit = null;
    long receipt_date = 0;

    public void enter(ActionEvent e) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("gui-user-menu.fxml")));
        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

        scene = new Scene(new Group());
        stage = new Stage();
        stage.setTitle("Animals in Shelter");
        stage.setWidth(600);
        stage.setHeight(800);

        Label label = new Label("Shelter: " + shelterChoiceBox.getValue());
        label.setFont(new Font("Arial", 20));

        for (Animal animal : shelterChoiceBox.getValue().getAnimalList()) {
            animal_type = animal.getAnimal_Type();
            animal_name = animal.getAnimal_Name();
            animal_id = animal.getAnimal_ID();
            animal_weight = animal.getAnimal_weight();
            weight_unit = animal.getWeight_unit();
            receipt_date = animal.getReceipt_date();

            System.out.println(animal_type + " " + animal_name + " " + animal_id + " " + animal_weight + " " + weight_unit + " " + receipt_date);
            ObservableList<Animal> animalData = FXCollections.observableArrayList(
                    new Animal(animal_type, animal_name, animal_id, animal_weight, weight_unit, receipt_date)
            );
            table.setItems(animalData);
            System.out.println(animalData);
        }

        TableColumn<Animal, String> animalType = new TableColumn<>("Animal Type");
        animalType.setPrefWidth(95);
        animalType.setCellValueFactory(new PropertyValueFactory<>("animal_Type"));
        TableColumn<Animal, String> animalName = new TableColumn<>("Animal Name");
        animalName.setPrefWidth(95);
        animalName.setCellValueFactory(new PropertyValueFactory<>("animal_Name"));
        TableColumn<Animal, String> animalID = new TableColumn<>("Animal ID");
        animalID.setPrefWidth(95);
        animalID.setCellValueFactory(new PropertyValueFactory<>("animal_ID"));
        TableColumn<Animal, String> animalWeight = new TableColumn<>("Animal Weight");
        animalWeight.setPrefWidth(95);
        animalWeight.setCellValueFactory(new PropertyValueFactory<>("animal_weight"));
        TableColumn<Animal, String> weightUnit = new TableColumn<>("Weight Unit");
        weightUnit.setPrefWidth(95);
        weightUnit.setCellValueFactory(new PropertyValueFactory<>("weight_unit"));
        TableColumn<Animal, String> receiptDate = new TableColumn<>("Receipt Date");
        receiptDate.setPrefWidth(105);
        receiptDate.setCellValueFactory(new PropertyValueFactory<>("receipt_date"));

        table.getColumns().addAll(animalType, animalName, animalID, animalWeight, weightUnit, receiptDate);
        table.setPrefWidth(580);
        table.setPrefHeight(700);
        table.setEditable(true);

        final VBox vbox = new VBox();
        vbox.setSpacing(5);
        vbox.setPadding(new Insets(10, 0, 0, 10));
        vbox.getChildren().addAll(label, table);

        ((Group) scene.getRoot()).getChildren().addAll(vbox);

        stage.setScene(scene);
        stage.show();
    }
}
