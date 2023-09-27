package com.u2d.pocjdbc.dao.impl;

import com.u2d.pocjdbc.conn.Conexao;
import com.u2d.pocjdbc.dao.ClienteDAO;
import com.u2d.pocjdbc.model.Cliente;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAOImpl implements ClienteDAO {
    @Override
    public List<Cliente> findAll() throws SQLException {
            ResultSet resultSet = executeQuery("SELECT * FROM CLIENT");
            List<Cliente> list = new ArrayList<>();
            while (resultSet.next()){
                Cliente cliente = Cliente.builder()
                        .id(resultSet.getLong("id"))
                        .name(resultSet.getString("name"))
                        .build();
                list.add(cliente);
            }
            return list;
    }

    @Override
    public Cliente save(Cliente cliente) throws SQLException {
        Connection con = null;
        try {
            con = Conexao.getConexao();
            PreparedStatement statement = con.prepareStatement("INSERT INTO CLIENT (NAME) VALUES (?)", Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, cliente.getName());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            if(resultSet.next()) {
                long last_inserted_id = resultSet.getLong(1);
                return findById(last_inserted_id);
            }
        } catch (SQLException e) {
            con.rollback();
            throw new RuntimeException(e);
        } finally {
            con.close();
        }
        return null;
    }

    @Override
    public Cliente findById(Long id) throws SQLException {
        ResultSet resultSet = executeQuery("SELECT * FROM CLIENT WHERE ID = "+id);
        if (resultSet.next()){
            return Cliente.builder()
                    .id(resultSet.getLong("id"))
                    .name(resultSet.getString("name"))
                    .build();
        }
        return null;
    }

    private ResultSet executeQuery(String query) throws SQLException {
        Connection connection = null;
        try {
            connection = Conexao.getConexao();
            return connection.createStatement().executeQuery(query);
        } catch (SQLException e) {
            connection.rollback();
            throw new RuntimeException(e);
        } finally {
            connection.close();
        }
    }
}
