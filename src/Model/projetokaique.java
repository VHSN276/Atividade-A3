package Model;

public class projetokaique {
    private Long id;
    private String nome;
    private String cargo;
    private String cpf;
    private String login;
    private String senha;

    public projetokaique() {}

    public projetokaique(String nome, String cargo, String cpf, String login, String senha) {
        this.nome = nome;
        this.cargo = cargo;
        this.cpf = cpf;
        this.login = login;
        this.senha = senha;
    }

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getCargo() { return cargo; }
    public void setCargo(String cargo) { this.cargo = cargo; }

    public String getCpf() { return cpf; }
    public void setCpf(String cpf) { this.cpf = cpf; }

    public String getLogin() { return login; }
    public void setLogin(String login) { this.login = login; }

    public String getSenha() { return senha; }
    public void setSenha(String senha) { this.senha = senha; }
}
