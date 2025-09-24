package View;

import java.util.List;
import Model.Equipe;
import Model.Usuario;

public class EquipeView {

    // Menu principal da área de equipes
    public void exibirMenu() {
        try {
            System.out.println("\n--- Menu de Equipes ---");
            System.out.println("[1] Listar Equipes");
            System.out.println("[2] Cadastrar Equipe");
            System.out.println("[3] Adicionar Usuário a uma Equipe");
            System.out.println("[4] Remover Usuário de uma Equipe");
            System.out.println("[5] Listar Equipes com Membros");
            System.out.println("[0] Voltar");
            System.out.print("Escolha uma opção: ");
        } catch (Exception e) {
            System.out.println("Erro ao exibir menu de equipes: " + e.getMessage());
        }
    }

    // Lista simples de equipes
    public void exibirEquipes(List<Equipe> equipes) {
        try {
            System.out.println("\n--- Lista de Equipes ---");
            if (equipes == null || equipes.isEmpty()) {
                System.out.println("Nenhuma equipe cadastrada.");
                return;
            }

            int i = 1;
            for (Equipe e : equipes) {
                if (e != null && e.getNome() != null) {
                    System.out.println("[" + (i++) + "] Equipe: " + e.getNome());
                }
            }
        } catch (Exception e) {
            System.out.println("Erro ao exibir equipes: " + e.getMessage());
        }
    }

    //Lista das equipes com os membros
    public void exibirDetalhesEquipes(List<Equipe> equipes) {
        try {
            System.out.println("\n--- Detalhes das Equipes e Seus Membros ---");
            if (equipes == null || equipes.isEmpty()) {
                System.out.println("Nenhuma equipe para mostrar.");
                return;
            }

            for (Equipe equipe : equipes) {
                if (equipe != null && equipe.getNome() != null) {
                    System.out.println("\nEquipe: " + equipe.getNome());

                    try {
                        List<Usuario> membros = equipe.getUsuarios();

                        if (membros == null || membros.isEmpty()) {
                            System.out.println("  (Esta equipe ainda não possui membros)");
                        } else {
                            System.out.println("  Membros:");
                            for (Usuario membro : membros) {
                                if (membro != null && membro.getNome() != null) {
                                    System.out.println("  - " + membro.getNome());
                                }
                            }
                        }
                    } catch (Exception e) {
                        System.out.println("  Erro ao carregar membros da equipe: " + e.getMessage());
                    }
                }
            }
            System.out.println("---------------------------------------------");
        } catch (Exception e) {
            System.out.println("Erro ao exibir detalhes das equipes: " + e.getMessage());
        }
    }

    // Mostra uma equipe com seus membros
    public void exibirEquipeComMembros(Equipe equipe, List<Usuario> membros) {
        try {
            if (equipe == null) {
                System.out.println("Equipe inválida.");
                return;
            }

            System.out.println("\nEquipe: " + (equipe.getNome() != null ? equipe.getNome() : "Nome não definido"));

            if (membros == null || membros.isEmpty()) {
                System.out.println("(sem membros)");
                return;
            }

            System.out.println("Membros:");
            for (Usuario u : membros) {
                if (u != null) {
                    // imprime o objeto direto
                    System.out.println(" - " + (u.getNome() != null ? u.getNome() : "Nome não definido"));
                }
            }
        } catch (Exception e) {
            System.out.println("Erro ao exibir equipe com membros: " + e.getMessage());
        }
    }

    public void exibirMensagem(String msg) {
        try {
            if (msg != null) {
                System.out.println(msg);
            } else {
                System.out.println("Mensagem nula recebida.");
            }
        } catch (Exception e) {
            System.out.println("Erro ao exibir mensagem: " + e.getMessage());
        }
    }
}