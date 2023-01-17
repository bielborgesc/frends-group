package database.dao;

import database.utils.ConnectionFactory;
import model.Colega;
import model.Grupo;
import model.enums.Sexo;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ColegaDAO implements GenericDAO<Colega, Integer>{
    @Override
    public void save(Colega colega) {
        Objects.requireNonNull(colega, "Entilty must not be null!");
        final String sql = "INSERT INTO colega(nome, dataNascimento, sexo, grupoid, enderecoid) VALUES(?, ?, ?, ?, ?)";
        try (PreparedStatement statement = ConnectionFactory.createPreparedStatemment(sql)){
            statement.setString(1, colega.getNome());
            statement.setDate(2, new Date(colega.getDataNascimento().getTime()));
            statement.setString(3, colega.getSexo().toString());
            statement.setInt(4, colega.getGrupoId());
            statement.setInt(5, colega.getEndereco().getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Colega colega) {
        Objects.requireNonNull(colega, "Entilty must not be null!");
        final String sql = "UPDATE colega SET nome = ?,  dataNascimento = ?, sexo = ?, grupoid = ?, enderecoid = ? WHERE id = ?";
        try (PreparedStatement statement = ConnectionFactory.createPreparedStatemment(sql)){
            statement.setString(1, colega.getNome());
            statement.setDate(2, new Date(colega.getDataNascimento().getTime()));
            statement.setString(3, colega.getSexo().toString());
            statement.setInt(4, colega.getGrupoId());
            statement.setInt(5, colega.getEndereco().getId());
            statement.setInt(6, colega.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Integer id) {
        Objects.requireNonNull(id, "Id must not be null!");
        final String sql = "DELETE FROM colega WHERE id = ?";
        try (PreparedStatement statement = ConnectionFactory.createPreparedStatemment(sql)){
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Colega load(Integer id) {
        EnderecoDAO enderecoDAO = new EnderecoDAO();
        Objects.requireNonNull(id, "Id must not be null!");
        final String sql = "SELECT * FROM colega WHERE id = ?";
        Colega colega = null;
        try (PreparedStatement statement = ConnectionFactory.createPreparedStatemment(sql)){
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            if(rs.next()){
                colega = new Colega(
                        rs.getString("nome"),
                        rs.getDate("dataNascimento"),
                        enderecoDAO.load(rs.getInt("enderecoid")),
                        Sexo.valueOf(rs.getString("sexo")),
                        rs.getInt("grupoid")
                );
                colega.setId(rs.getInt("id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return colega;
    }

    @Override
    public List<Colega> loadAll() {
        final String sql = "SELECT * FROM colega";
        ArrayList<Colega> list = new ArrayList<>();
        try (PreparedStatement statement = ConnectionFactory.createPreparedStatemment(sql)){
            ResultSet rs = statement.executeQuery();
            EnderecoDAO enderecoDAO = new EnderecoDAO();
            while (rs.next()){
                list.add(new Colega(
                        rs.getString("nome"),
                        rs.getDate("dataNascimento"),
                        enderecoDAO.load(rs.getInt("enderecoid")),
                        Sexo.valueOf(rs.getString("sexo")),
                        rs.getInt("grupoid"))
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
