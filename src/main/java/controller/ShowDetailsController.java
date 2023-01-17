package controller;

import database.dao.AmigoDAO;
import database.dao.ColegaDAO;
import database.dao.EnderecoDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import model.Amigo;
import model.Colega;
import model.Endereco;
import model.enums.Sexo;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ShowDetailsController {
    @FXML
    private TextField TxtBairro;
    @FXML
    private ChoiceBox<Sexo> choiceSsexo;
    private List<Sexo> listSex = new ArrayList<>();
    private ObservableList<Sexo> obsSex;
    @FXML
    private DatePicker dateNasc;
    @FXML
    private TextField txtCidade;
    @FXML
    private TextField txtFav;
    @FXML
    private TextField txtNome;
    @FXML
    private TextField txtNum;
    @FXML
    private TextField txtRua;
    @FXML
    private RadioButton rdoAmigo;
    @FXML
    private RadioButton rdoColega;
    @FXML
    private ToggleGroup grupo;

    public void setData(Colega colega) {
        TxtBairro.setText(colega.getEndereco().getBairro());

        listSex.add(Sexo.MASCULINO);
        listSex.add(Sexo.FEMININO);
        obsSex = FXCollections.observableArrayList(listSex);
        choiceSsexo.setItems(obsSex);
        choiceSsexo.setValue(colega.getSexo());

        LocalDate localDate = colega.getDataNascimento().toInstant().atZone( ZoneId.systemDefault() ).toLocalDate();
        dateNasc.setValue(localDate);

        txtCidade.setText(colega.getEndereco().getCidade());

        txtFav.setText("Não Disponivel");
        txtFav.setEditable(false);

        txtNome.setText(colega.getNome());
        txtNum.setText(colega.getEndereço().getNumero());
        txtRua.setText(colega.getEndereço().getRua());

        rdoColega.fire();

    }

    public void setData(Amigo amigo) {
        TxtBairro.setText(amigo.getEndereco().getBairro());

        listSex.add(Sexo.MASCULINO);
        listSex.add(Sexo.FEMININO);
        obsSex = FXCollections.observableArrayList(listSex);
        choiceSsexo.setItems(obsSex);

        LocalDate localDate = amigo.getDataNascimento().toInstant().atZone( ZoneId.systemDefault() ).toLocalDate();
        dateNasc.setValue(localDate);

        txtCidade.setText(amigo.getEndereco().getCidade());

        txtFav.setEditable(true);
        txtFav.setText(amigo.getCoisaPreferida());

        txtNome.setText(amigo.getNome());
        txtNum.setText(amigo.getEndereço().getNumero());
        txtRua.setText(amigo.getEndereço().getRua());

        rdoAmigo.fire();

    }

    public void setData(Sexo sexo) {

        listSex.add(Sexo.MASCULINO);
        listSex.add(Sexo.FEMININO);
        obsSex = FXCollections.observableArrayList(listSex);
        choiceSsexo.setItems(obsSex);
        choiceSsexo.setValue(sexo);
        rdoAmigo.fire();
    }

    public void handleClickButtonSave() {
        String rua = txtRua.getCharacters().toString();
        String numero = txtNum.getCharacters().toString();
        String bairro = TxtBairro.getCharacters().toString();
        String cidade = txtCidade.getCharacters().toString();
        String nome = txtNome.getCharacters().toString();
        Sexo sexo = Sexo.valueOf(choiceSsexo.toString());
        DatePicker date = dateNasc;
        Endereco endereco = new Endereco(rua, bairro, cidade, numero);
        EnderecoDAO enderecoDAO = new EnderecoDAO();
        enderecoDAO.save(endereco);
        if(rdoColega.isSelected()){
            Colega colega = new Colega(nome, new Date(), endereco, Sexo.MASCULINO);
            ColegaDAO colegaDAO = new ColegaDAO();
            colegaDAO.save(colega);
        }else{
            String coisaFavorita = txtFav.getCharacters().toString();
            Amigo amigo = new Amigo(nome, new Date(), endereco, Sexo.FEMININO, coisaFavorita);
            AmigoDAO amigoDAO = new AmigoDAO();
            amigoDAO.save(amigo);
        }

    }
}
