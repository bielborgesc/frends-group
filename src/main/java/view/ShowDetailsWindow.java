package view;

import controller.ShowDetailsController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Amigo;
import model.Colega;
import model.enums.Sexo;

public class ShowDetailsWindow {
    public void showAndWait(Colega colega){
        try{
            FXMLLoader loader = new FXMLLoader();
            Pane graph = loader.load(getClass().getResource("showDetails.fxml").openStream());
            Scene scene = new Scene(graph, 320, 580);
            ShowDetailsController showDetailsController = loader.getController();

            if(colega != null){
                showDetailsController.setData(colega);
            }

            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Detalhes Colega");
            stage.setResizable(false);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();

        } catch (Exception e){
            e.printStackTrace();
        }

    }

    public void showAndWait(Amigo amigo){
        try{
            FXMLLoader loader = new FXMLLoader();
            Pane graph = loader.load(getClass().getResource("showDetails.fxml").openStream());
            Scene scene = new Scene(graph, 320, 580);
            ShowDetailsController showDetailsController = loader.getController();

            if(amigo != null){
                showDetailsController.setData(amigo);
            }

            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Detalhes Amigo");
            stage.setResizable(false);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();

        } catch (Exception e){
            e.printStackTrace();
        }

    }

    public void showAndWait(Sexo sexo){
        try{
            FXMLLoader loader = new FXMLLoader();
            Pane graph = loader.load(getClass().getResource("showDetails.fxml").openStream());
            Scene scene = new Scene(graph, 320, 580);
            ShowDetailsController showDetailsController = loader.getController();

            showDetailsController.setData(sexo);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Adicionar");
            stage.setResizable(false);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();

        } catch (Exception e){
            e.printStackTrace();
        }
    }


}