package ShelterModules;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.Window;
import org.controlsfx.control.action.Action;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


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
    private static final ShelterList shelterMap = new ShelterList();

    public void importing(ActionEvent e){
        System.out.println("importing");
        String filename = "Project1_input.json";
        shelterMap.addIncomingJSON(filename);
    }
    public void userInputAddingAnimal(ActionEvent e) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("user-input-adding-animal.fxml"));
        stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }
    public void enable(ActionEvent e) throws IOException {
        System.out.println("Enable receiving");
        Parent root = FXMLLoader.load(getClass().getResource("shelterList.fxml"));
        stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        shelterMap.getShelter(selected).changeReceiving(true);
    }
    public void disable(ActionEvent e) throws IOException {
        System.out.println("Disable receiving");
        Parent root = FXMLLoader.load(getClass().getResource("shelterList.fxml"));
        stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        shelterMap.getShelter(selected).changeReceiving(false);
    }
    public void seeAnimal(ActionEvent e) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("animal-in-shelter.fxml"));
        stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void seeAnimalAllShelters(ActionEvent e) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("animals-and-shelters.fxml"));
        stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }
    public void export(ActionEvent e){
        System.out.println("Exporting");
        String filename = "ProjectOutput.json";
        Utilities.writeJSON(shelterMap, filename);
    }
    public void exit(ActionEvent e){
        stage = (Stage) scenePane.getScene().getWindow();
        System.out.println("Exiting");
        stage.close();
    }
}


