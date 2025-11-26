package com.ti2cc.app;

import static spark.Spark.*;
import java.util.List;

public class Aplicacao {

    private static ProdutoDAO produtoDAO = new ProdutoDAO();

    public static void main(String[] args) {
        
        port(6789);
        
        before((request, response) -> {
            request.raw().setCharacterEncoding("UTF-8");
        });

        staticFiles.location("/public");

        staticFiles.location("/public");

        post("/produto/inserir", (request, response) -> {
            String nome = request.queryParams("nome");
            float preco = Float.parseFloat(request.queryParams("preco"));
            int quantidade = Integer.parseInt(request.queryParams("quantidade"));
            
            Produto produto = new Produto(-1, nome, preco, quantidade);
            produtoDAO.inserir(produto);
            
            response.redirect("/produto/listar");
            return null;
        });

        get("/produto/listar", (request, response) -> {
            response.type("text/html");
            
            List<Produto> produtos = produtoDAO.getAll();
            
            StringBuilder html = new StringBuilder();
            html.append("<html><head><title>Lista de Produtos</title></head><body>");
            html.append("<h1>Produtos Cadastrados</h1>");
            html.append("<table border='1'><tr><th>ID</th><th>Nome</th><th>Preço</th><th>Quantidade</th></tr>");
            
            for (Produto p : produtos) {
                html.append("<tr>");
                html.append("<td>").append(p.getId()).append("</td>");
                html.append("<td>").append(p.getNome()).append("</td>");
                html.append("<td>").append(p.getPreco()).append("</td>");
                html.append("<td>").append(p.getQuantidade()).append("</td>");
                html.append("</tr>");
            }
            
            html.append("</table>");
            html.append("<br><a href='/form.html'>Voltar para o formulário</a>");
            html.append("</body></html>");
            
            return html.toString();
        });
    }
}