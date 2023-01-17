package view;

import controller.MainController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainWindowApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainWindowApplication.class.getResource("Main.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 625, 500);
        stage.setTitle("Grupo De Amigos");
        MainController mainController = fxmlLoader.getController();
        mainController.getNameGroup();
        mainController.setColegasInTable();
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}