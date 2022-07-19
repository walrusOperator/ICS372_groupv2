package ShelterModules;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

/**
 * GUI class that used to initialize the GUI window.
 */

public class GUI extends Application {

    /**
     * Main method that launch the GUI once program start.
     * @param args
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Method used to display the gui-user-menu on start of program.
     * @param stage - display of gui-user-menu
     * @throws IOException
     */
    @Override
    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("gui-user-menu.fxml")));
        stage.setTitle("User Menu");
        stage.setScene(new Scene(root));
        stage.show();

    }
}
