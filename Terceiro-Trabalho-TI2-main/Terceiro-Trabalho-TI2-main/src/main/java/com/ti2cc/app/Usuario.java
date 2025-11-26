package com.ti2cc.app;

// Esta classe (POJO - Plain Old Java Object) representa um usuário do nosso banco de dados.
// Cada atributo da classe corresponde a uma coluna da tabela 'usuario'.
public class Usuario {
    private int codigo;
    private String login;
    private String senha;
    private char sexo;

    // Construtor padrão (vazio)
    public Usuario() {
        this.codigo = -1;
        this.login = "";
        this.senha = "";
        this.sexo = '*';
    }

    // Construtor com parâmetros para facilitar a criação de objetos
    public Usuario(int codigo, String login, String senha, char sexo) {
        this.codigo = codigo;
        this.login = login;
        this.senha = senha;
        this.sexo = sexo;
    }

    // Métodos Getters e Setters para acessar e modificar os atributos privados
    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public char getSexo() {
        return sexo;
    }

    public void setSexo(char sexo) {
        this.sexo = sexo;
    }

    // Método toString para facilitar a exibição dos dados do objeto
    @Override
    public String toString() {
        return "Usuario [codigo=" + codigo + ", login=" + login + ", senha=" + senha + ", sexo=" + sexo + "]";
    }
}
