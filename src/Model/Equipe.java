package Model;

import Model.Usuario;

import java.util.ArrayList;
import java.util.List;

public class Equipe {
    private String nome;
    private List<Usuario> usuarios;

    public Equipe(String nome) {
        this.nome = nome;
        this.usuarios = new ArrayList<>();
    }
    public String getNome() {
        return nome;
    }
    public List<Usuario> getUsuarios() {
        return usuarios;
    }
    public boolean adicionarUsuario(Usuario usuario) {
        for (Usuario u : usuarios) {
            if (u.getCpf().equals(usuario.getCpf())) {
                return false;
            }
        }
        usuarios.add(usuario);
        return true;
    }
    public boolean removerUsuario(String cpf) {
        return usuarios.removeIf(u -> u.getCpf() .equals(cpf));
    }
}
