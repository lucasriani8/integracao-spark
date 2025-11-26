package com.ti2cc.app;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DAO {
    protected Connection conexao;

    public DAO() {
        conexao = null; // O construtor agora apenas inicializa a variável.
    }

    public boolean conectar() {
        String driver = "org.postgresql.Driver";
        String url = "jdbc:postgresql://localhost:5432/Teste";
        String usuario = "ti2cc";
        String senha = "ti@cc";
        boolean status = false;

        try {
            Class.forName(driver);
            conexao = DriverManager.getConnection(url, usuario, senha);
            status = (conexao != null);
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println("Erro ao conectar ao banco de dados: " + e.getMessage());
        }
        return status;
    }

    public void fechar() {
        try {
            if (conexao != null) {
                conexao.close();
                conexao = null; // Garante que a conexão seja reiniciada na próxima vez
            }
        } catch (SQLException e) {
            System.err.println("Erro ao fechar a conexão: " + e.getMessage());
        }
    }
}