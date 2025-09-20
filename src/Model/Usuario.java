package Model;

import java.util.List;

public class Usuario extends Default {
    private String cpf;
    private String cargo;
    private String login;
    private String senha;

    public Usuario() {
    }

    public Usuario(String nome, String cpf, String cargo, String login, String senha) {
        super(nome);
        this.cpf = cpf;
        this.cargo = cargo;
        this.login = login;
        this.senha = senha;
    }

    public void cadastro(Usuario u, List<Usuario> list){
        list.add(u);
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
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
}