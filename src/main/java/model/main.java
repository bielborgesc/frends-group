package model;

import database.dao.AmigoDAO;
import database.dao.ColegaDAO;
import database.dao.EnderecoDAO;
import database.dao.GrupoDAO;
import model.enums.Sexo;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

public class main {
    public static void main(String[] args) throws ParseException {
        EnderecoDAO enderecoDAO = new EnderecoDAO();
        AmigoDAO amigoDAO = new AmigoDAO();
        GrupoDAO grupoDAO = new GrupoDAO();

        Endereco endereco = new Endereco("Lago dos anjos", "Caraucaria", "Sao Paula", "225");
        Date data = new Date();
        Endereco endereco1 = enderecoDAO.load(1);
        Grupo grupo1 = grupoDAO.load(1);
        Amigo amigo = amigoDAO.load(2);
        List list = amigoDAO.loadAll();
        System.out.println(list);
//        amigoDAO.update(amigo);
//        amigoDAO.save(amigo);




    }
}
