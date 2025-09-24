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
        return usuarios.removeIf(u -> u.getCpf().equals(cpf));
    }

    // Método adicional para compatibilidade com EquipeController
    public boolean removerUsuario(String nomeUsuario, boolean porNome) {
        if (porNome) {
            return usuarios.removeIf(u -> u.getNome().equalsIgnoreCase(nomeUsuario));
        } else {
            return removerUsuario(nomeUsuario);
        }
    }

    // Método para obter membros (alias para getUsuarios)
    public List<Usuario> getMembros() {
        return getUsuarios();
    }

    @Override
    public String toString() {
        return String.format("Equipe{nome='%s', membros=%d}", nome, usuarios.size());
    }
}