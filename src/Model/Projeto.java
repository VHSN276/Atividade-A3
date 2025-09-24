package Model;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;
import jakarta.persistence.*;
import java.util.*;

@SpringBootApplication
public class ProjetoApplication {
    public static void main(String[] args) {
        SpringApplication.run(ProjetoApplication.class, args);
    }
}

// Classe de modelo
@Entity
public class Projeto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String cargo;
    private String cpf;
    private String login;
    private String senha;

    public Projeto() {}

    public Projeto(String nome, String cargo, String cpf, String login, String senha) {
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

// Classe de Controller (com simulação de banco de dados)
@RestController
@RequestMapping("/projetos")
public class ProjetoController {

    private final List<Projeto> projetos = new ArrayList<>();
    private Long contador = 1L;

    @GetMapping
    public List<Projeto> listar() {
        return projetos;
    }

    @PostMapping
    public Projeto criar(@RequestBody Projeto projeto) {
        projeto.setId(contador++);
        projetos.add(projeto);
        return projeto;
    }

    @GetMapping("/{id}")
    public Projeto buscarPorId(@PathVariable Long id) {
        return projetos.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Projeto não encontrado"));
    }

    @PutMapping("/{id}")
    public Projeto atualizar(@PathVariable Long id, @RequestBody Projeto atualizado) {
        Projeto projeto = buscarPorId(id);
        projeto.setNome(atualizado.getNome());
        projeto.setCargo(atualizado.getCargo());
        projeto.setCpf(atualizado.getCpf());
        projeto.setLogin(atualizado.getLogin());
        projeto.setSenha(atualizado.getSenha());
        return projeto;
    }

    @DeleteMapping("/{id}")
    public void remover(@PathVariable Long id) {
        projetos.removeIf(p -> p.getId().equals(id));
    }
}
