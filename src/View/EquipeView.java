package View;

import java.util.List;
import Model.Equipe;
import Model.Usuario;

public class EquipeView {

    // Menu principal da área de equipes
    public void exibirMenu() {
        System.out.println("\n--- Menu de Equipes ---");
        System.out.println("[1] Listar Equipes");
        System.out.println("[2] Cadastrar Equipe");
        System.out.println("[3] Adicionar Usuário a uma Equipe");
        System.out.println("[4] Remover Usuário de uma Equipe");
        System.out.println("[5] Listar Equipes com Membros");
        System.out.println("[0] Voltar");
        System.out.print("Escolha uma opção: ");
    }

    // Lista simples de equipes
    public void exibirEquipes(List<Equipe> equipes) {
        System.out.println("\n--- Lista de Equipes ---");
        if (equipes == null || equipes.isEmpty()) {
            System.out.println("Nenhuma equipe cadastrada.");
            return;
        }

        int i = 1;
        for (Equipe e : equipes) {
            System.out.println("[" + (i++) + "] Equipe: " + e.getNome());
        }
    }
    //Lista das equipes com os membros
    public void exibirDetalhesEquipes(List<Equipe> equipes) {
        System.out.println("\n--- Detalhes das Equipes e Seus Membros ---");
        if (equipes == null || equipes.isEmpty()) {
            System.out.println("Nenhuma equipe para mostrar.");
            return;
        }

        for (Equipe equipe : equipes) {
            System.out.println("\nEquipe: " + equipe.getNome());
            List<Usuario> membros = equipe.getUsuarios();

            if (membros == null || membros.isEmpty()) {
                System.out.println("  (Esta equipe ainda não possui membros)");
            } else {
                System.out.println("  Membros:");
                for (Usuario membro : membros) {
                    System.out.println("  - " + membro.getNome());
                }
            }
        }
        System.out.println("---------------------------------------------");
    }

    // Mostra uma equipe com seus membros
    public void exibirEquipeComMembros(Equipe equipe, List<Usuario> membros) {
        if (equipe == null) {
            System.out.println("Equipe inválida.");
            return;
        }

        System.out.println("\nEquipe: " + equipe);

        if (membros == null || membros.isEmpty()) {
            System.out.println("(sem membros)");
            return;
        }

        System.out.println("Membros:");
        for (Usuario u : membros) {
            // imprime o objeto direto
            System.out.println(" - " + u);
        }
    }

    public void exibirMensagem(String msg) {
        System.out.println(msg);
    }
}