package database.dao;

import database.utils.ConnectionFactory;
import model.Endereco;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class EnderecoDAO implements GenericDAO<Endereco,Integer>{
    @Override
    public void save(Endereco endereco) {
        Objects.requireNonNull(endereco, "Entilty must not be null!");
        final String sql = "INSERT INTO endereco(rua, bairro, cidade, numero) VALUES(?, ?, ?, ?)";
        try (PreparedStatement statement = ConnectionFactory.createPreparedStatemment(sql)){
            statement.setString(1, endereco.getRua());
            statement.setString(2, endereco.getBairro());
            statement.setString(3, endereco.getCidade());
            statement.setString(4, endereco.getNumero());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Endereco endereco) {
        Objects.requireNonNull(endereco, "Entilty must not be null!");
        final String sql = "UPDATE endereco SET rua = ?, bairro = ?, cidade = ?, numero = ? WHERE id = ?";
        try (PreparedStatement statement = ConnectionFactory.createPreparedStatemment(sql)){
            statement.setString(1, endereco.getRua());
            statement.setString(2, endereco.getBairro());
            statement.setString(3, endereco.getCidade());
            statement.setString(4, endereco.getNumero());
            statement.setInt(5, endereco.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Integer id) {
        Objects.requireNonNull(id, "Id must not be null!");
        final String sql = "DELETE FROM endereco WHERE id = ?";
        try (PreparedStatement statement = ConnectionFactory.createPreparedStatemment(sql)){
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Endereco load(Integer id) {
        Objects.requireNonNull(id, "Id must not be null!");
        final String sql = "SELECT * FROM endereco WHERE id = ?";
        Endereco endereco = null;
        try (PreparedStatement statement = ConnectionFactory.createPreparedStatemment(sql)){
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            if(rs.next()){
                endereco = new Endereco(rs.getInt("id"),
                        rs.getString("rua"), rs.getString("bairro"),
                        rs.getString("cidade"), rs.getString("numero"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return endereco;
    }

    @Override
    public List<Endereco> loadAll() {
        final String sql = "SELECT * FROM endereco";
        ArrayList<Endereco> list = new ArrayList<>();
        try (PreparedStatement statement = ConnectionFactory.createPreparedStatemment(sql)){
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                list.add(new Endereco(rs.getInt("id"),rs.getString("rua"), rs.getString("bairro"),
                        rs.getString("cidade"), rs.getString("numero")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
