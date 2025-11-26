package com.ti2cc.app;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ProdutoDAO extends DAO {

    public ProdutoDAO() {
        super(); // O construtor da classe pai é chamado, mas não conecta mais.
    }

    public boolean inserir(Produto produto) {
        boolean status = false;
        try {
            conectar(); // ABRE a conexão
            String sql = "INSERT INTO produto (nome, preco, quantidade) VALUES (?, ?, ?)";
            PreparedStatement st = conexao.prepareStatement(sql);
            st.setString(1, produto.getNome());
            st.setFloat(2, produto.getPreco());
            st.setInt(3, produto.getQuantidade());
            st.executeUpdate();
            st.close();
            status = true;
        } catch (SQLException u) {
            throw new RuntimeException(u);
        } finally {
            fechar(); // FECHA a conexão, aconteça o que acontecer (sucesso ou erro)
        }
        return status;
    }

    public List<Produto> getAll() {
        List<Produto> produtos = new ArrayList<>();
        try {
            conectar(); // ABRE a conexão
            Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            String sql = "SELECT * FROM produto ORDER BY id";
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                Produto p = new Produto(
                    rs.getInt("id"),
                    rs.getString("nome"),
                    rs.getFloat("preco"),
                    rs.getInt("quantidade")
                );
                produtos.add(p);
            }
            st.close();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        } finally {
            fechar(); // FECHA a conexão, aconteça o que acontecer
        }
        return produtos;
    }
}