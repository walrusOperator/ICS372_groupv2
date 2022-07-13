package ShelterModules;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.controlsfx.control.action.Action;

import java.io.File;


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
        String filename = "Project1_input.json";
        shelterMap.addIncomingJSON(filename);
    }
    public void addingIncoming(ActionEvent e){
        System.out.println("Adding Animal");
        shelterMap.showShelters();
        selected = UserInterface.shelterSelection();
        if(ShelterList.shelterSearch(selected, shelterMap)) {
            Animal newAnimal = Animal.createNewAnimal(shelterMap);
            if(newAnimal != null) {
                Shelter.addUserCreatedAnimal(newAnimal, selected, shelterMap);
            }
        }
    }
    public void enable(ActionEvent e){
        System.out.println("Enable receiving");
    }
    public void disable(ActionEvent e){
        System.out.println("Disable receiving");
    }
    public void seeAnimal(ActionEvent e){
        System.out.println("Seeing animal from a shelter");
    }
    public void seeAnimalAllShelters(ActionEvent e){
        System.out.println("Seeing animals from all shelters");
    }
    public void export(ActionEvent e){
        System.out.println("Exporting");
    }
    public void exit(ActionEvent e){
        stage = (Stage) scenePane.getScene().getWindow();
        System.out.println("Exiting");
        stage.close();
    }
    
}


