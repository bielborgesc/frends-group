package database.dao;

import database.utils.ConnectionFactory;
import model.Amigo;
import model.enums.Sexo;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class AmigoDAO implements GenericDAO<Amigo, Integer> {
    @Override
    public void save(Amigo amigo) {
        Objects.requireNonNull(amigo, "Entilty must not be null!");
        final String sql = "INSERT INTO amigo(nome, dataNascimento, sexo, grupoid, enderecoid, coisaPreferida) VALUES(?, ?, ?, ?, ?, ?)";
        try (PreparedStatement statement = ConnectionFactory.createPreparedStatemment(sql)){
            statement.setString(1, amigo.getNome());
            statement.setDate(2, new Date(amigo.getDataNascimento().getTime()));
            statement.setString(3, amigo.getSexo().toString());
            statement.setInt(4, amigo.getGrupoId());
            statement.setInt(5, amigo.getEndereco().getId());
            statement.setString(6, amigo.getCoisaPreferida());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Amigo amigo) {
        Objects.requireNonNull(amigo, "Entilty must not be null!");
        final String sql = "UPDATE amigo SET nome = ?,  dataNascimento = ?, sexo = ?, grupoid = ?, enderecoid = ?, coisaPreferida = ? WHERE id = ?";
        try (PreparedStatement statement = ConnectionFactory.createPreparedStatemment(sql)){
            statement.setString(1, amigo.getNome());
            statement.setDate(2, new Date(amigo.getDataNascimento().getTime()));
            statement.setString(3, amigo.getSexo().toString());
            statement.setInt(4, amigo.getGrupoId());
            statement.setInt(5, amigo.getEndereco().getId());
            statement.setString(6, amigo.getCoisaPreferida());
            statement.setInt(7, amigo.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Integer id) {
        Objects.requireNonNull(id, "Id must not be null!");
        final String sql = "DELETE FROM amigo WHERE id = ?";
        try (PreparedStatement statement = ConnectionFactory.createPreparedStatemment(sql)){
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Amigo load(Integer id) {
        EnderecoDAO enderecoDAO = new EnderecoDAO();
        Objects.requireNonNull(id, "Id must not be null!");
        final String sql = "SELECT * FROM amigo WHERE id = ?";
        Amigo amigo = null;
        try (PreparedStatement statement = ConnectionFactory.createPreparedStatemment(sql)){
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            if(rs.next()){
                amigo = new Amigo(
                        rs.getString("nome"),
                        rs.getDate("dataNascimento"),
                        enderecoDAO.load(rs.getInt("enderecoid")),
                        Sexo.valueOf(rs.getString("sexo")),
                        rs.getString("coisaPreferida"),
                        rs.getInt("grupoid")
                );
                amigo.setId(rs.getInt("id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return amigo;
    }

    @Override
    public List<Amigo> loadAll() {
        final String sql = "SELECT * FROM amigo";
        ArrayList<Amigo> list = new ArrayList<>();
        try (PreparedStatement statement = ConnectionFactory.createPreparedStatemment(sql)){
            ResultSet rs = statement.executeQuery();
            EnderecoDAO enderecoDAO = new EnderecoDAO();
            while (rs.next()){
                list.add(new Amigo(
                        rs.getString("nome"),
                        rs.getDate("dataNascimento"),
                        enderecoDAO.load(rs.getInt("enderecoid")),
                        Sexo.valueOf(rs.getString("sexo")),
                        rs.getString("coisaPreferida"),
                        rs.getInt("grupoid"))
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}





