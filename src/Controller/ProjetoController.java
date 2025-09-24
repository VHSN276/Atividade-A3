package Controller;

import Model.Projeto;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProjetoController {
    private final List<Projeto> projetos = new ArrayList<>();
    private Long contador = 1L;

    // Método para listar todos os projetos
    public List<Projeto> listar() {
        return projetos;
    }

    // Método para criar um novo projeto
    public Projeto criar(Projeto projeto) {
        projeto.setId(contador++);
        projetos.add(projeto);
        return projeto;
    }

    // Método para buscar um projeto pelo ID
    public Optional<Projeto> buscarPorId(Long id) {
        return projetos.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst();
    }

    // Método para atualizar um projeto existente
    public Optional<Projeto> atualizar(Long id, Projeto atualizado) {
        Optional<Projeto> projetoExistente = buscarPorId(id);
        if (projetoExistente.isPresent()) {
            Projeto projeto = projetoExistente.get();
            projeto.setNome(atualizado.getNome());
            projeto.setCargo(atualizado.getCargo());
            projeto.setCpf(atualizado.getCpf());
            projeto.setLogin(atualizado.getLogin());
            projeto.setSenha(atualizado.getSenha());
            return Optional.of(projeto);
        }
        return Optional.empty();
    }

    // Método para remover um projeto pelo ID
    public boolean remover(Long id) {
        return projetos.removeIf(p -> p.getId().equals(id));
    }
}
