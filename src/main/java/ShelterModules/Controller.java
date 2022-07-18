package ShelterModules;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.Window;
import org.controlsfx.control.action.Action;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class Controller {
    @FXML
    private AnchorPane scenePane;
    @FXML
    private Label myLabel;
    @FXML
    private Button importButton;
    @FXML
    private Button addingIncomingButton;
    @FXML
    private Button enableButton;
    @FXML
    private Button disableButton;
    @FXML
    private Button seeAnimalButton;
    @FXML
    private Button seeAnimalAllSheltersButton;
    @FXML
    private Button exportButton;
    @FXML
    private Button exitButton;

    Stage stage;
    String selected;
    static final ShelterList shelterMap = new ShelterList();
    private final TableView<Animal> table = new TableView<>();

    public void importing(ActionEvent e){
        String filename = "Project1_input.json";
        ParseUtilities.addIncomingJSON(filename, shelterMap);
        System.out.println("Importing JSON file...");
    }
    public void userInputAddingAnimal(ActionEvent e) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("user-input-adding-animal.fxml")));
        stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void enable(ActionEvent e) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("shelterList.fxml")));
        stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        System.out.println("Enable receiving...");
    }
    public void disable(ActionEvent e) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("selectShelterList2.fxml")));
        stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        System.out.println("Disable receiving...");
    }
    public void seeAnimal(ActionEvent e) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("selectShelterList3.fxml")));
        stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void seeAnimalAllShelters(ActionEvent e) {
        Scene scene = new Scene(new Group());
        stage = new Stage();
        stage.setTitle("Animals in all Shelters");
        stage.setWidth(820);
        stage.setHeight(1000);

        for (Shelter shelter : shelterMap.getShelters()) {
            String shelter_id = shelter.getShelterID();
            String shelter_name = shelter.getShelterName();

            for(Animal animal : shelter.getAnimalList()){
                String animal_type = animal.getAnimal_Type();
                String animal_name = animal.getAnimal_Name();
                String animal_id = animal.getAnimal_ID();
                double animal_weight = animal.getAnimal_weight();
                String weight_unit = animal.getWeight_unit();
                long receipt_date = animal.getReceipt_date();

                System.out.println(shelter_id + " " + shelter_name + " " + animal_type +  " " + animal_name +
                        " " + animal_id + " " + animal_weight + " " + weight_unit + " " + receipt_date);
                ObservableList<Shelter> shelterData = FXCollections.observableArrayList(
                        new Shelter(shelter_id, shelter_name)
                );

                ObservableList<Animal> animalData = FXCollections.observableArrayList(
                        new Animal(animal_type, animal_name, animal_id, animal_weight, weight_unit, receipt_date)
                );
//                table.setItems(shelterData);
                table.setItems(animalData);
                System.out.println(animalData);
            }
        }

        TableColumn<Shelter, String> shelterID = new TableColumn<>("Shelter ID");
        shelterID.setPrefWidth(100);
        shelterID.setCellValueFactory(new PropertyValueFactory<>("shelterID"));
        TableColumn<Shelter, String> shelterName = new TableColumn<>("Shelter Name");
        shelterName.setPrefWidth(100);
        shelterName.setCellValueFactory(new PropertyValueFactory<>("shelterName"));
        TableColumn<Animal, String> animalType = new TableColumn<>("Animal Type");
        animalType.setPrefWidth(100);
        animalType.setCellValueFactory(new PropertyValueFactory<>("animal_Type"));
        TableColumn<Animal, String> animalName = new TableColumn<>("Animal Name");
        animalName.setPrefWidth(100);
        animalName.setCellValueFactory(new PropertyValueFactory<>("animal_Name"));
        TableColumn<Animal, String> animalID = new TableColumn<>("Animal ID");
        animalID.setPrefWidth(100);
        animalID.setCellValueFactory(new PropertyValueFactory<>("animal_ID"));
        TableColumn<Animal, String> animalWeight = new TableColumn<>("Animal Weight");
        animalWeight.setPrefWidth(100);
        animalWeight.setCellValueFactory(new PropertyValueFactory<>("animal_weight"));
        TableColumn<Animal, String> weightUnit = new TableColumn<>("Weight Unit");
        weightUnit.setPrefWidth(100);
        weightUnit.setCellValueFactory(new PropertyValueFactory<>("weight_unit"));
        TableColumn<Animal, String> receiptDate = new TableColumn<>("Receipt Date");
        receiptDate.setPrefWidth(105);
        receiptDate.setCellValueFactory(new PropertyValueFactory<>("receipt_date"));


        table.getColumns().addAll(animalType, animalName, animalID, animalWeight, weightUnit, receiptDate);
        table.setPrefWidth(800);
        table.setPrefHeight(900);
        table.setEditable(true);

        final VBox vbox = new VBox();
        vbox.setSpacing(5);
        vbox.setPadding(new Insets(10, 0, 0, 10));
        vbox.getChildren().addAll(table);

        ((Group) scene.getRoot()).getChildren().addAll(vbox);

        stage.setScene(scene);
        stage.show();
    }
    public void export(ActionEvent e){
        String filename = "ProjectOutput.json";
        FileUtilities.writeJSON(shelterMap, filename);
        System.out.println("Exporting");
    }

    public void importXML(ActionEvent e){
        String filename = "sample.xml";
        ParseUtilities.parseIncomingXML(filename, shelterMap);
        System.out.println("Importing XML file...");
    }
    public void exit(ActionEvent e){
        stage = (Stage) scenePane.getScene().getWindow();
        System.out.println("Exiting");
        stage.close();
    }
}


