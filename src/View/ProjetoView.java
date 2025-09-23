package View;

import java.util.List;
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
            System.err.println("Erro ao exibir menu de projetos: " + e.getMessage());
        }
    }

    public void exibirProjetos(List<Projeto> projetos) {
        try {
            if (projetos == null) {
                System.out.println("Lista de projetos não inicializada.");
                return;
            }

            if (projetos.isEmpty()) {
                System.out.println("Nenhum projeto cadastrado.");
            } else {
                System.out.println("\n--- Lista de Projetos ---");
                int index = 1;
                for (Projeto projeto : projetos) {
                    try {
                        if (projeto == null) {
                            System.out.println("[" + index + "] Projeto inválido (dados nulos)");
                        } else {
                            System.out.println("[" + index + "] " + projeto.toString());
                        }
                        index++;
                    } catch (Exception e) {
                        System.err.println("Erro ao exibir projeto " + index + ": " + e.getMessage());
                        index++;
                    }
                }
            }
        } catch (NullPointerException e) {
            System.err.println("Erro: referência nula ao exibir projetos: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Erro inesperado ao exibir projetos: " + e.getMessage());
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