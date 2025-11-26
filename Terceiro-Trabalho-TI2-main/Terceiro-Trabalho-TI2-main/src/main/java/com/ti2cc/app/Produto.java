package com.ti2cc.app;

public class Produto {
    private int id;
    private String nome;
    private float preco;
    private int quantidade;

    public Produto() {
        this.id = -1;
        this.nome = "";
        this.preco = 0.00f;
        this.quantidade = 0;
    }

    public Produto(int id, String nome, float preco, int quantidade) {
        this.id = id;
        this.nome = nome;
        this.preco = preco;
        this.quantidade = quantidade;
    }

    // GETTERS E SETTERS - ESTA É A PARTE QUE ESTÁ FALTANDO
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public float getPreco() {
        return preco;
    }

    public void setPreco(float preco) {
        this.preco = preco;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
    // FIM DOS GETTERS E SETTERS

    @Override
    public String toString() {
        return "Produto [id=" + id + ", nome=" + nome + ", preco=" + preco + ", quantidade=" + quantidade + "]";
    }
}