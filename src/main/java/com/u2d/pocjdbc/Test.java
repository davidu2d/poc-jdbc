package com.u2d.pocjdbc;

import com.u2d.pocjdbc.dao.ClienteDAO;
import com.u2d.pocjdbc.dao.impl.ClienteDAOImpl;
import com.u2d.pocjdbc.model.Cliente;

import java.sql.SQLException;
import java.util.List;

public class Test {
    public static void main(String[] args) throws SQLException {
        ClienteDAO dao = new ClienteDAOImpl();

        Cliente cliente = Cliente.builder()
                .name("David")
                .build();
        dao.save(cliente);
        System.out.println(cliente);

        List<Cliente> clientes = dao.findAll();
        System.out.println(clientes);

        Cliente last = dao.findById(3L);
        System.out.println(last);
    }
}
