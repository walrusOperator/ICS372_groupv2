package ShelterModules;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class Controller3 {
    @FXML
    private TableView<Shelter> displayTable;
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

    public void exit(ActionEvent e) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("gui-user-menu.fxml")));
        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
