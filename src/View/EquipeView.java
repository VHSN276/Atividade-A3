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
            System.out.println("[0] Voltar");
            System.out.print("Escolha uma opção: ");
        } catch (Exception e) {
            System.err.println("Erro ao exibir menu de equipes: " + e.getMessage());
        }
    }

    // Lista simples de equipes
    public void exibirEquipes(List<Equipe> equipes) {
        try {
            System.out.println("\n--- Lista de Equipes ---");

            if (equipes == null) {
                System.out.println("Lista de equipes não inicializada.");
                return;
            }

            if (equipes.isEmpty()) {
                System.out.println("Nenhuma equipe cadastrada.");
                return;
            }

            int i = 1;
            for (Equipe e : equipes) {
                try {
                    if (e == null) {
                        System.out.println("[" + i + "] Equipe inválida (dados nulos)");
                    } else {
                        System.out.println("[" + i + "] Equipe: " + e.toString());
                    }
                    i++;
                } catch (Exception ex) {
                    System.err.println("Erro ao exibir equipe " + i + ": " + ex.getMessage());
                    i++;
                }
            }
        } catch (NullPointerException e) {
            System.err.println("Erro: referência nula ao exibir equipes: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Erro inesperado ao exibir equipes: " + e.getMessage());
        }
    }

    // Mostra uma equipe com seus membros
    public void exibirEquipeComMembros(Equipe equipe, List<Usuario> membros) {
        try {
            if (equipe == null) {
                System.out.println("Equipe inválida.");
                return;
            }

            System.out.println("\nEquipe: " + equipe.toString());

            if (membros == null) {
                System.out.println("Lista de membros não inicializada");
                return;
            }

            if (membros.isEmpty()) {
                System.out.println("(sem membros)");
                return;
            }

            System.out.println("Membros:");
            int index = 1;
            for (Usuario u : membros) {
                try {
                    if (u == null) {
                        System.out.println(" - [" + index + "] Membro inválido (dados nulos)");
                    } else {
                        System.out.println(" - [" + index + "] " + u.toString());
                    }
                    index++;
                } catch (Exception e) {
                    System.err.println("Erro ao exibir membro " + index + ": " + e.getMessage());
                    index++;
                }
            }
        } catch (NullPointerException e) {
            System.err.println("Erro: referência nula ao exibir equipe com membros: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Erro inesperado ao exibir equipe com membros: " + e.getMessage());
        }
    }

    public void exibirMensagem(String msg) {
        try {
            if (msg == null) {
                System.out.println("Mensagem não informada");
            } else {
                System.out.println(msg);
            }
        } catch (Exception e) {
            System.err.println("Erro ao exibir mensagem: " + e.getMessage());
        }
    }
}