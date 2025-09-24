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
        for (Equipe e : equipes) {
            if (e.getNome().equalsIgnoreCase(nome)) {
                view.exibirMensagem(" Já existe uma equipe com esse nome.");
            }
        }
        equipes.add(new Equipe(nome));
        view.exibirMensagem(" Equipe cadastrada com sucesso!");
    }
    public void adicionarUsuario(String nomeEquipe, Usuario usuario){
        for (Equipe e : equipes) {
            if (e.getNome().equalsIgnoreCase(nomeEquipe)){
                boolean adicionado = e.adicionarUsuario(usuario);
                if(adicionado) {
                } else {
                    view.exibirMensagem(" Usuário já existe na equipe.");
                }
                return;
            }

        }
        view.exibirMensagem(" Equipe não encontrada.");
    }

    public void listarEquipes() {
        view.exibirEquipes(equipes);
    }

    public void listarEquipeComMembros() {
        if (equipes.isEmpty()) {
            view.exibirMensagem("Nenhuma equipe cadastrada.");
            return;
        }
        // Supõe que a sua EquipeView tem um método para exibir os detalhes da equipe
        // Se não tiver, você precisará criar um ou adaptar a lógica abaixo.
        view.exibirDetalhesEquipes(equipes);
    }

    public List<Equipe> getEquipes(){
        return equipes;
    }
    public void removerUsuarioDaequipe(String nomeEquipe,String nomeUsuario) {
        for (Equipe e : equipes) {
            if (e.getNome().equalsIgnoreCase(nomeEquipe)){
                boolean removed = e.removerUsuario(nomeUsuario);
                if(removed) {
                    view.exibirMensagem("Usuário " + nomeUsuario + " removido da equipe " + nomeEquipe);
                } else {
                    view.exibirMensagem("Usuário não encontrado na equipe ");
                }
                return;
            }
        }
    }
}
