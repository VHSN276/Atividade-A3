package Controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import Model.Equipe;
import Model.Projeto;
import Model.StatusProjeto;
import Model.Usuario;
import View.ProjetoView;

public class ProjetoController {
    private static Deque<Projeto> Projetos;
    private static List<Projeto> projetos;
    private ProjetoView view;

    public ProjetoController(ProjetoView view) {
        this.view = view;
        this.projetos = new ArrayList<>();
    }

    public static void exibirProjetos() {
    }

    public static void cadastrarProjeto(String nome, LocalDate inicio, LocalDate fim, Usuario responsavel) {
        if (!responsavel.getCargo().equalsIgnoreCase("Admin") &&
                !responsavel.getCargo().equalsIgnoreCase("Gerente")) {
            ProjetoView.exibirMensagem(" Apenas Admin ou Gerente podem ser responsáveis por projetos.");
            return;
        }
        if (fim.isBefore(inicio)) {
            ProjetoView.exibirMensagem(" Data final deve ser depois da data inicial.");
            return;
        }
        Projetos.add(new Projeto(nome, inicio, fim, responsavel));
        ProjetoView.exibirMensagem(" Projeto '" + nome + "' cadastrado com sucesso!");
    }
    public static void adicionarEquipe(String nomeProjeto, Equipe equipe) {
        for (Projeto p : projetos) {
            if (p.getNome().equalsIgnoreCase(nomeProjeto)) {
                boolean adicionada = p.adicionarEquipe(equipe);
                if (adicionada) {
                    ProjetoView.exibirMensagem(" Equipe adicionada ao projeto!");
                } else {
                    ProjetoView.exibirMensagem(" Equipe já está neste projeto.");
                }
                return;
            }
        }
        ProjetoView.exibirMensagem(" Projeto não encontrado.");
    }
    public void listarProjetos() {
        view.exibirProjetos(projetos);
    }

    public static void alterarStatusProjeto(String nomeProjeto, StatusProjeto novoStatus) {
        for (Projeto p : projetos) {
            if (p.getNome().equalsIgnoreCase(nomeProjeto)) {
                p.alterarStatus(novoStatus);
                ProjetoView.exibirMensagem("Status do projeto" + nomeProjeto + " alterado para " + novoStatus.toString());
                return;
            }
        }
    }

}
