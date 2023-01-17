package database.dao;

import database.utils.ConnectionFactory;
import model.Grupo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class GrupoDAO implements GenericDAO<Grupo, Integer>{
    @Override
    public void save(Grupo grupo) {
        Objects.requireNonNull(grupo, "Entilty must not be null!");
        final String sql = "INSERT INTO grupo(nome) VALUES(?)";
        try (PreparedStatement statement = ConnectionFactory.createPreparedStatemment(sql)){
            statement.setString(1, grupo.getNome());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Grupo grupo) {
        Objects.requireNonNull(grupo, "Entilty must not be null!");
        final String sql = "UPDATE grupo SET nome = ? WHERE id = ?";
        try (PreparedStatement statement = ConnectionFactory.createPreparedStatemment(sql)){
            statement.setString(1, grupo.getNome());
            statement.setInt(2, grupo.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Integer id) {
        Objects.requireNonNull(id, "Id must not be null!");
        final String sql = "DELETE FROM grupo WHERE id = ?";
        try (PreparedStatement statement = ConnectionFactory.createPreparedStatemment(sql)){
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Grupo load(Integer id) {
        Objects.requireNonNull(id, "Id must not be null!");
        final String sql = "SELECT * FROM grupo WHERE id = ?";
        Grupo grupo = null;
        try (PreparedStatement statement = ConnectionFactory.createPreparedStatemment(sql)){
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            if(rs.next()){
                grupo = new Grupo(rs.getInt("id"),rs.getString("nome"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return grupo;
    }

    @Override
    public List<Grupo> loadAll() {
        final String sql = "SELECT * FROM grupo";
        ArrayList<Grupo> list = new ArrayList<>();
        try (PreparedStatement statement = ConnectionFactory.createPreparedStatemment(sql)){
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                list.add(new Grupo(rs.getInt("id"),rs.getString("nome")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
