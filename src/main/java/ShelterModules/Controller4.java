package ShelterModules;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;
import static ShelterModules.Controller.shelterMap;

public class Controller4 {
    @FXML
    private TableView<Animal> table;
    @FXML
    private TableColumn<Animal, String> animalIDColumn;
    @FXML
    private TableColumn<Animal, String> animalTypeColumn;
    @FXML
    private TableColumn<Animal, String> animalNameColumn;
    @FXML
    private TableColumn<Animal, String> animalWeightColumn;
    @FXML
    private TableColumn<Animal, String> receiveDateColumn;
    @FXML
    private Button exitButton;

    public ObservableList<Animal> getAnimal(){
        ObservableList<Animal> animals = FXCollections.observableArrayList();
        animals.add(shelterMap.);
    }

    public void exit(ActionEvent e) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("gui-user-menu.fxml")));
        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}
