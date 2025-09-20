package View;

import java.util.List;
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
        if (projetos == null || projetos.isEmpty()) {
            System.out.println("Nenhum projeto cadastrado.");
        } else {
            for (Projeto projeto : projetos) {
                System.out.println(" - " + projeto);
            }
        }
    }

    public void exibirMensagem(String msg) {
        System.out.println(msg);
    }
}