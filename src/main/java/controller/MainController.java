package controller;

import database.dao.AmigoDAO;
import database.dao.ColegaDAO;
import database.dao.EnderecoDAO;
import database.dao.GrupoDAO;
import javafx.beans.property.SimpleSetProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.fxml.FXML;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Window;
import model.Amigo;
import model.Colega;
import model.Endereco;
import model.Grupo;
import model.enums.Sexo;
import view.ShowDetailsWindow;

import java.util.Date;

public class MainController {
    @FXML
    private Button btnRemover;
    @FXML
    private Button btnAdd;
    @FXML
    private Button btnEditar;
    @FXML
    private Button btnDetalhes;
    @FXML
    private Button btnAlterarNome;
    @FXML
    private TableColumn<Colega, String> columCoisaFav;
    @FXML
    private TableColumn<Colega, String> columDetalhes;
    @FXML
    private TableColumn<Colega, String> columNivelAmizade;
    @FXML
    private TableColumn<Colega, String> columNome;
    @FXML
    private TableColumn<Colega, String> columSexo;
    @FXML
    private TextField txtNomeGrupo;
    @FXML
    private TableView <String> table;

    public void getNameGroup(){
        GrupoDAO grupoDAO = new GrupoDAO();
        txtNomeGrupo.setText(grupoDAO.load(1).getNome());
    }

    public void updateNameGroup(){
        GrupoDAO grupoDAO = new GrupoDAO();
        String newName = txtNomeGrupo.getCharacters().toString();
        Grupo grupo = new Grupo(newName);
        grupo.setId(1);
        grupoDAO.update(grupo);
    }

    public void setColegasInTable(){
//        AmigoDAO amigoDAO = new AmigoDAO();
//        ColegaDAO colegaDAO = new ColegaDAO();
//        for (Amigo amigo : amigoDAO.loadAll()) {
//            columCoisaFav.setText(amigo.getCoisaPreferida());
//            columDetalhes.setText(amigo.meEmprestaDinheiro(1000.00));
//            columNome.setText(amigo.getNome());
//            columNivelAmizade.setText("Amigo");
//            columSexo.setText(amigo.getSexo().toString());
//        }
//        for (Colega colega : colegaDAO.loadAll()) {
//            columCoisaFav.setText("Desconhecido");
//            columDetalhes.setText(colega.meEmprestaDinheiro(1000.00));
//            columNome.setText(colega.getNome());
//            columNivelAmizade.setText("Colega");
//            columSexo.setText(colega.getSexo().toString());
//        }
    }

    public void add(ActionEvent actionEvent){
        ShowDetailsWindow view = new ShowDetailsWindow();
        Sexo sexo = Sexo.FEMININO;
        view.showAndWait(sexo);
    }

    public void edit(ActionEvent actionEvent){
        ShowDetailsWindow view = new ShowDetailsWindow();
        Endereco endereco = new Endereco("Rua tal", "Trabalhop", " não sie", "125");
        Colega colega = new Colega("Bartolomeu", new Date(), endereco, Sexo.MASCULINO);
        view.showAndWait(colega);
    }

    public void remove(ActionEvent actionEvent){

    }

    public void detalhes(ActionEvent actionEvent) {

    }

}