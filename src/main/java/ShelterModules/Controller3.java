package ShelterModules;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller3 implements Initializable {
    @FXML
    private TableView displayTable;
    @FXML
    private TableColumn<Shelter, String> shelterIDColumn;
    @FXML
    private TableColumn<Shelter, String> shelterNameColumn;
    @FXML
    private TableColumn<Shelter, String> receivingStatusColumn;
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

    @Override
    public void initialize(URL url, ResourceBundle resource){
        shelterIDColumn.setCellValueFactory(new PropertyValueFactory<>("shelterID"));
        shelterNameColumn.setCellValueFactory(new PropertyValueFactory<>("shelterName"));
    }
    ObservableList<Shelter> shelterList = FXCollections.observableArrayList(
            new Shelter()
    );

}
