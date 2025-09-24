package View;

import java.util.List;
import java.util.stream.Collectors;

import Model.Equipe;
import Model.Projeto;


public class ProjetoView {

    public void exibirMenu() {
        System.out.println("\n--- Menu de Projetos ---");
        System.out.println("[1] Listar Projetos");
        System.out.println("[2] Cadastrar Projeto");
        System.out.println("[3] Alterar Status");
        System.out.println("[4] Vincular Equipe");
        System.out.println("[0] Voltar");
    }

    public void exibirProjetos(List<Projeto> projetos) {
        System.out.println("\n--- Lista de Projetos ---");

        if (projetos == null || projetos.isEmpty()) {
            System.out.println("Nenhum projeto cadastrado.");
            return;
        }

        for (Projeto projeto : projetos) {
            // Exibe as informações básicas do projeto
            System.out.println("Nome: " + projeto.getNome());
            System.out.println("Responsável: " + (projeto.getResponsavel() != null ? projeto.getResponsavel().getNome() : "Não definido")); // Supondo que Usuario tem getNome()
            System.out.println("Data de Início: " + projeto.getDataInicio());
            System.out.println("Data de Fim: " + projeto.getDataFim());
            System.out.println("Status: " + (projeto.getStatus() != null ? projeto.getStatus() : "Não definido"));

            // Exibe as equipes vinculadas ao projeto
            if (projeto.getEquipes().isEmpty()) {
                System.out.println("Equipes: Nenhuma equipe vinculada.");
            } else {
                String nomesEquipes = projeto.getEquipes().stream()
                        .map(Equipe::getNome)
                        .collect(Collectors.joining(", "));
                System.out.println("Equipes: " + nomesEquipes);
            }
            System.out.println("-----------------------------");
        }
    }

    public static void exibirMensagem(String msg) {
        System.out.println(msg);
    }

    public void ProjetoView(String s) {
    }


}