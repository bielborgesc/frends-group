package database.utils;

import database.dao.AmigoDAO;
import database.dao.ColegaDAO;
import database.dao.EnderecoDAO;
import database.dao.GrupoDAO;
import model.Amigo;
import model.Colega;
import model.Endereco;
import model.Grupo;
import model.enums.Sexo;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

public class DatabseBuilder {
    public static void main(String[] args) {
        clear();
        build();
        insert();
    }

    private static void insert(){
        Endereco endereco1 = new Endereco("Joaquim Lopes", "Santa Marta", "São Caetano", "s/n");
        Endereco endereco2 = new Endereco("Maria Lopes", "Santa Marta", "São Caetano", "525");
        Endereco endereco3 = new Endereco("Joaquim Algusto", "Ribeiro", "Aguas rasa", "1575");
        Endereco endereco4 = new Endereco("Odete Santoro", "Pratinho", "Norteless", "252");
        EnderecoDAO enderecoDAO = new EnderecoDAO();
        enderecoDAO.save(endereco1);
        enderecoDAO.save(endereco2);
        enderecoDAO.save(endereco3);
        enderecoDAO.save(endereco4);

        Grupo grupo = new Grupo("SemCamisa");
        GrupoDAO grupoDAO = new GrupoDAO();
        grupoDAO.save(grupo);

        Colega colega1 = new Colega("Michael", new Date(), endereco1, Sexo.MASCULINO, 1);
        Colega colega2 = new Colega("Nicole", new Date(), endereco2, Sexo.FEMININO, 1);
        ColegaDAO colegaDAO = new ColegaDAO();
        colegaDAO.save(colega1);
        colegaDAO.save(colega2);

        Amigo amigo1 = new Amigo("Leandro", new Date(), endereco3, Sexo.MASCULINO, "Bola de futebol", 1);
        Amigo amigo2 = new Amigo("Kamila", new Date(), endereco4, Sexo.FEMININO, "Boneca", 1);
        AmigoDAO amigoDAO = new AmigoDAO();
        amigoDAO.save(amigo1);
        amigoDAO.save(amigo2);
    }

    private static void clear() {
        System.out.println("Cleaning up...");
        try {
            Files.deleteIfExists(Paths.get("grupoDeAmigos.db"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void build() {
        try (Statement stmt = ConnectionFactory.createStatement()){
            stmt.addBatch("""
                CREATE TABLE colega (
                    id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
                    nome TEXT NOT NULL,
                    dataNascimento DATE,
                    sexo TEXT NOT NULL,
                    grupoid INTEGER NOT NULL,
                    enderecoid INTEGER NOT NULL,
                    CONSTRAINT grupoid FOREIGN KEY(grupoid) REFERENCES grupo(id),
                    CONSTRAINT enderecoid FOREIGN KEY(enderecoid) REFERENCES endereco(id)            
                );
                """
            );
            stmt.addBatch("""
                CREATE TABLE amigo (
                    id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
                    nome TEXT NOT NULL,
                    dataNascimento DATE,
                    sexo TEXT NOT NULL,
                    coisaPreferida TEXT,
                    grupoid INTEGER NOT NULL,
                    enderecoid INTEGER NOT NULL,
                    CONSTRAINT grupoid FOREIGN KEY(grupoid) REFERENCES grupo(id),
                    CONSTRAINT enderecoid FOREIGN KEY(enderecoid) REFERENCES endereco(id)  
                );
                """
            );
            stmt.addBatch("""
                CREATE TABLE endereco (
                    id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
                    rua TEXT,
                    bairro TEXT,
                    cidade TEXT,
                    numero TEXT
                );
                """
            );
            stmt.addBatch("""
                CREATE TABLE grupo (
                    id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
                    nome TEXT NOT NULL
                );
                """
            );
            stmt.executeBatch();
            System.out.println("Database has been created ...");
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

}
