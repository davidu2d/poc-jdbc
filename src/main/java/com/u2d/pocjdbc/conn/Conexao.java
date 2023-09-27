package com.u2d.pocjdbc.conn;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
    public static Connection getConexao() throws SQLException {
        try {
            System.out.println("Estabelecendo conexão em Banco Postgresql...");
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/u2d", "postgres", "415782");
            System.out.println("Conexão estabelecida!!");
            return connection;
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
