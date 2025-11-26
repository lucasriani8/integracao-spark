package com.ti2cc.app;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

// A classe DAO (Data Access Object) é responsável por toda a comunicação com o banco de dados.
// Ela encapsula os comandos SQL para manipulação da tabela 'usuario'.
public class UsuarioDAO extends DAO {

    public UsuarioDAO() {
        super(); // Chama o construtor da classe pai (DAO) para conectar ao banco
    }

    // Método para inserir um novo usuário no banco de dados
    public boolean insert(Usuario usuario) {
        boolean status = false;
        try {
            String sql = "INSERT INTO usuario (login, senha, sexo) VALUES (?, ?, ?)";
            PreparedStatement st = conexao.prepareStatement(sql);
            st.setString(1, usuario.getLogin());
            st.setString(2, usuario.getSenha());
            st.setString(3, String.valueOf(usuario.getSexo())); // setString para CHAR
            st.executeUpdate();
            st.close();
            status = true;
        } catch (SQLException u) {
            throw new RuntimeException(u);
        }
        return status;
    }

    // Método para buscar um usuário pelo seu código (chave primária)
    public Usuario get(int codigo) {
        Usuario usuario = null;
        try {
            String sql = "SELECT * FROM usuario WHERE codigo = ?";
            PreparedStatement st = conexao.prepareStatement(sql);
            st.setInt(1, codigo);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                usuario = new Usuario(
                    rs.getInt("codigo"),
                    rs.getString("login"),
                    rs.getString("senha"),
                    rs.getString("sexo").charAt(0)
                );
            }
            st.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return usuario;
    }

    // Método para listar todos os usuários da tabela
    public List<Usuario> getAll() {
        List<Usuario> usuarios = new ArrayList<>();
        try {
            Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            String sql = "SELECT * FROM usuario ORDER BY codigo";
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                Usuario u = new Usuario(
                    rs.getInt("codigo"),
                    rs.getString("login"),
                    rs.getString("senha"),
                    rs.getString("sexo").charAt(0)
                );
                usuarios.add(u);
            }
            st.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return usuarios;
    }

    // Método para atualizar os dados de um usuário
    public boolean update(Usuario usuario) {
        boolean status = false;
        try {
            String sql = "UPDATE usuario SET login = ?, senha = ?, sexo = ? WHERE codigo = ?";
            PreparedStatement st = conexao.prepareStatement(sql);
            st.setString(1, usuario.getLogin());
            st.setString(2, usuario.getSenha());
            st.setString(3, String.valueOf(usuario.getSexo()));
            st.setInt(4, usuario.getCodigo());
            st.executeUpdate();
            st.close();
            status = true;
        } catch (SQLException u) {
            throw new RuntimeException(u);
        }
        return status;
    }

    // Método para excluir um usuário pelo seu código
    public boolean delete(int codigo) {
        boolean status = false;
        try {
            String sql = "DELETE FROM usuario WHERE codigo = ?";
            PreparedStatement st = conexao.prepareStatement(sql);
            st.setInt(1, codigo);
            st.executeUpdate();
            st.close();
            status = true;
        } catch (SQLException u) {
            throw new RuntimeException(u);
        }
        return status;
    }
}
