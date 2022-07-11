package ShelterModules;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.controlsfx.control.action.Action;

//import static ShelterModules.Main.shelterMap;

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

    public void importing(ActionEvent e){
        System.out.println("Importing");
//        shelterMap.addIncoming();
    }
    public void addingIncoming(ActionEvent e){
        System.out.println("Adding Animal");
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


