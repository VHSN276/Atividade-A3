package Controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import Model.Equipe;
import Model.Usuario;
import View.EquipeView;

public class EquipeController {
    private List<Equipe> equipes;
    private EquipeView view;

    public EquipeController(EquipeView view, List<Equipe> listaEquipes) {
        this.view = view;
        this.equipes = listaEquipes;
    }

    public void cadastrarEquipe(String nome) {
        try {
            if (nome == null || nome.trim().isEmpty()) {
                view.exibirMensagem("Erro: Nome da equipe não pode estar vazio.");
                return;
            }
            if (equipes == null) {
                view.exibirMensagem("Erro: Lista de equipes não foi inicializada.");
                return;
            }

            // Verificar se já existe equipe com o mesmo nome
            for (Equipe e : equipes) {
                if (e != null && e.getNome() != null && e.getNome().equalsIgnoreCase(nome)) {
                    view.exibirMensagem(" Já existe uma equipe com esse nome.");
                    return;
                }
            }
            equipes.add(new Equipe(nome));
            view.exibirMensagem(" Equipe cadastrada com sucesso!");
        } catch (NullPointerException e) {
            view.exibirMensagem("Erro: Referência nula encontrada - " + e.getMessage());
        } catch (Exception e) {
            view.exibirMensagem("Erro ao cadastrar equipe: " + e.getMessage());
        }
    }

    public void adicionarUsuario(String nomeEquipe, Usuario usuario){
        try {
            if (nomeEquipe == null || nomeEquipe.trim().isEmpty()) {
                view.exibirMensagem("Erro: Nome da equipe não pode estar vazio.");
                return;
            }
            if (usuario == null) {
                view.exibirMensagem("Erro: Usuário não pode ser nulo.");
                return;
            }
            if (equipes == null || equipes.isEmpty()) {
                view.exibirMensagem("Erro: Nenhuma equipe cadastrada.");
                return;
            }

            for (Equipe e : equipes) {
                if (e != null && e.getNome() != null && e.getNome().equalsIgnoreCase(nomeEquipe)){
                    boolean adicionado = e.adicionarUsuario(usuario);
                    if(adicionado) {
                        view.exibirMensagem("Usuário " + usuario.getNome() + " adicionado à equipe " + nomeEquipe + " com sucesso!");
                    } else {
                        view.exibirMensagem(" Usuário já existe na equipe.");
                    }
                    return;
                }
            }
            view.exibirMensagem(" Equipe não encontrada.");
        } catch (NullPointerException e) {
            view.exibirMensagem("Erro: Referência nula encontrada - " + e.getMessage());
        } catch (Exception e) {
            view.exibirMensagem("Erro ao adicionar usuário à equipe: " + e.getMessage());
        }
    }

    public void listarEquipes() {
        try {
            if (equipes == null) {
                view.exibirMensagem("Erro: Lista de equipes não foi inicializada.");
                return;
            }
            view.exibirEquipes(equipes);
        } catch (Exception e) {
            view.exibirMensagem("Erro ao listar equipes: " + e.getMessage());
        }
    }

    public void listarEquipeComMembros() {
        try {
            if (equipes == null) {
                view.exibirMensagem("Erro: Lista de equipes não foi inicializada.");
                return;
            }
            if (equipes.isEmpty()) {
                view.exibirMensagem("Nenhuma equipe cadastrada.");
                return;
            }
            // Supõe que a sua EquipeView tem um método para exibir os detalhes da equipe
            // Se não tiver, você precisará criar um ou adaptar a lógica abaixo.
            view.exibirDetalhesEquipes(equipes);
        } catch (Exception e) {
            view.exibirMensagem("Erro ao listar equipes com membros: " + e.getMessage());
        }
    }

    public List<Equipe> getEquipes(){
        try {
            return equipes;
        } catch (Exception e) {
            System.out.println("Erro ao obter lista de equipes: " + e.getMessage());
            return null;
        }
    }

    public void removerUsuarioDaequipe(String nomeEquipe, String cpfUsuario) {
        try {
            if (nomeEquipe == null || nomeEquipe.trim().isEmpty()) {
                view.exibirMensagem("Erro: Nome da equipe não pode estar vazio.");
                return;
            }
            if (cpfUsuario == null || cpfUsuario.trim().isEmpty()) {
                view.exibirMensagem("Erro: CPF do usuário não pode estar vazio.");
                return;
            }
            if (equipes == null || equipes.isEmpty()) {
                view.exibirMensagem("Erro: Nenhuma equipe cadastrada.");
                return;
            }

            for (Equipe e : equipes) {
                if (e != null && e.getNome() != null && e.getNome().equalsIgnoreCase(nomeEquipe)){
                    boolean removed = e.removerUsuario(cpfUsuario);
                    if(removed) {
                        view.exibirMensagem("Usuário com CPF " + cpfUsuario + " removido da equipe " + nomeEquipe);
                    } else {
                        view.exibirMensagem("Usuário não encontrado na equipe ");
                    }
                    return;
                }
            }
            view.exibirMensagem("Equipe não encontrada.");
        } catch (NullPointerException e) {
            view.exibirMensagem("Erro: Referência nula encontrada - " + e.getMessage());
        } catch (Exception e) {
            view.exibirMensagem("Erro ao remover usuário da equipe: " + e.getMessage());
        }
    }
}