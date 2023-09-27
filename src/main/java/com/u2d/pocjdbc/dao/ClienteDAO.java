package com.u2d.pocjdbc.dao;

import com.u2d.pocjdbc.model.Cliente;

import java.sql.SQLException;
import java.util.List;

public interface ClienteDAO {
    List<Cliente> findAll() throws SQLException;

    Cliente save(Cliente cliente) throws SQLException;

    Cliente findById(Long id) throws SQLException;
}
