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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

/**
 * GUI Controller - use to controller display the first layer of GUI with buttons for user to select.
 *
 */

public class ControllerGuiMain implements Initializable {
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
    private final String saveName = "Save_Data.json";
    private final TableView<Animal> table = new TableView<>();
    private final TableView<Shelter> shelterTable = new TableView<>();
    static ObservableList<Shelter> shelterData = FXCollections.observableArrayList();
    static ObservableList<Animal> animalData = FXCollections.observableArrayList();

    /**
     * Method used to initialize save data on start of program.
     *
     * @param arg0
     * @param arg1
     */

    public void initialize(URL arg0, ResourceBundle arg1) {
        shelterMap.addHashMap(ParseUtilities.loadJSON("Save_Data.json"));
        System.out.println("Importing Save Data...");
    }

    /**
     * Method used to import JSON file.
     *
     * @param e - once button is click, event occurs.
     */
    public void importing(ActionEvent e){
        String filename = "Project1_input.json";
        ParseUtilities.addIncomingJSON(filename, shelterMap);
        System.out.println("Importing JSON file...");
        FileUtilities.writeJSON(shelterMap, saveName);
    }

    /**
     * Method used to display new window for adding animal by user's input.
     * @param e - once button is click, event occurs.
     * @throws IOException
     */
    public void userInputAddingAnimal(ActionEvent e) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("user-input-adding-animal.fxml")));
        stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        FileUtilities.writeJSON(shelterMap, saveName);
    }

    /**
     * Method used to display new window for toggle receiving true/false.
     * @param e - once button is click, event occurs.
     * @throws IOException
     */
    public void enable(ActionEvent e) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("shelterList.fxml")));
        stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        FileUtilities.writeJSON(shelterMap, saveName);
    }

    /**
     * Method used to display new window for user to select which shelter to look at animals.
     * @param e - once button is click, event occurs.
     * @throws IOException
     */
    public void seeAnimal(ActionEvent e) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("selectShelterList3.fxml")));
        stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Method used to display a new window with all Animals and all Shelters.
     * @param e - once button is click, event occurs.
     */
    public void seeAnimalAllShelters(ActionEvent e) {
        Scene scene = new Scene(new Group());
        stage = new Stage();
        stage.setTitle("Animals in all Shelters");
        stage.setWidth(820);
        stage.setHeight(1000);

        shelterData.clear();
        animalData.clear();
        for (Shelter shelter : shelterMap.getShelters()) {
            shelterData.add(shelter);
            animalData.addAll(shelter.getAnimalList());
        }

        table.setItems(animalData);
        shelterTable.setItems(shelterData);

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

        shelterTable.getColumns().clear();
        shelterTable.getColumns().addAll(shelterID, shelterName);
        table.getColumns().clear();
        table.getColumns().addAll(animalType, animalName, animalID, animalWeight, weightUnit, receiptDate);
        table.setPrefWidth(800);
        table.setPrefHeight(600);
        table.setEditable(true);

        final VBox vbox = new VBox();
        vbox.setSpacing(5);
        vbox.setPadding(new Insets(10, 0, 0, 10));
        vbox.getChildren().addAll(shelterTable,table);

        ((Group) scene.getRoot()).getChildren().addAll(vbox);

        stage.setScene(scene);
        stage.show();

        stage.setOnCloseRequest(event -> table.getItems().clear());
    }

    /**
     * Method used to export file into a .json.
     * @param e - once button is click, event occurs.
     */
    public void export(ActionEvent e){
        String filename = "ProjectOutput.json";
        FileUtilities.writeJSON(shelterMap, filename);
        System.out.println("Exporting");
    }

    /**
     * Method used to import XML file.
     * @param e - once button is click, event occurs.
     */
    public void importXML(ActionEvent e){
        String filename = "sample.xml";
        ParseUtilities.parseIncomingXML(filename, shelterMap);
        System.out.println("Importing XML file...");
        FileUtilities.writeJSON(shelterMap, saveName);
    }

    /**
     * Method used to exit the GUI and save data to .json file.
     * @param e - once button is click, event occurs.
     */
    public void exit(ActionEvent e){
        stage = (Stage) scenePane.getScene().getWindow();
        System.out.println("Exiting");
        FileUtilities.writeJSON(shelterMap, saveName);
        stage.close();
    }

}


