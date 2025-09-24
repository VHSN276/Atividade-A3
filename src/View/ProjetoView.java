package View;

import java.util.List;
import java.util.stream.Collectors;

import Model.Equipe;
import Model.Projeto;


public class ProjetoView {

    public void exibirMenu() {
        try {
            System.out.println("\n--- Menu de Projetos ---");
            System.out.println("[1] Listar Projetos");
            System.out.println("[2] Cadastrar Projeto");
            System.out.println("[3] Alterar Status");
            System.out.println("[4] Vincular Equipe");
            System.out.println("[0] Voltar");
        } catch (Exception e) {
            System.out.println("Erro ao exibir menu de projetos: " + e.getMessage());
        }
    }

    public void exibirProjetos(List<Projeto> projetos) {
        try {
            System.out.println("\n--- Lista de Projetos ---");

            if (projetos == null || projetos.isEmpty()) {
                System.out.println("Nenhum projeto cadastrado.");
                return;
            }

            for (Projeto projeto : projetos) {
                if (projeto != null) {
                    // Exibe as informações básicas do projeto
                    System.out.println("Nome: " + (projeto.getNome() != null ? projeto.getNome() : "N/A"));
                    System.out.println("Responsável: " + (projeto.getResponsavel() != null && projeto.getResponsavel().getNome() != null ? projeto.getResponsavel().getNome() : "Não definido"));
                    System.out.println("Data de Início: " + (projeto.getDataInicio() != null ? projeto.getDataInicio() : "N/A"));
                    System.out.println("Data de Fim: " + (projeto.getDataFim() != null ? projeto.getDataFim() : "N/A"));
                    System.out.println("Status: " + (projeto.getStatus() != null ? projeto.getStatus() : "Não definido"));

                    // Exibe as equipes vinculadas ao projeto
                    try {
                        if (projeto.getEquipes() == null || projeto.getEquipes().isEmpty()) {
                            System.out.println("Equipes: Nenhuma equipe vinculada.");
                        } else {
                            String nomesEquipes = projeto.getEquipes().stream()
                                    .filter(equipe -> equipe != null && equipe.getNome() != null)
                                    .map(Equipe::getNome)
                                    .collect(Collectors.joining(", "));
                            System.out.println("Equipes: " + (nomesEquipes.isEmpty() ? "Nenhuma equipe válida" : nomesEquipes));
                        }
                    } catch (Exception e) {
                        System.out.println("Equipes: Erro ao carregar equipes - " + e.getMessage());
                    }
                    System.out.println("-----------------------------");
                }
            }
        } catch (NullPointerException e) {
            System.out.println("Erro: Referência nula ao exibir projetos - " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Erro ao exibir projetos: " + e.getMessage());
        }
    }

    public static void exibirMensagem(String msg) {
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

    public void ProjetoView(String s) {
        try {
            // Método vazio - implementação futura se necessário
        } catch (Exception e) {
            System.out.println("Erro no método ProjetoView: " + e.getMessage());
        }
    }
}