package Controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List; // Importe apenas a List
import Model.Equipe;
import Model.Projeto;
import Model.StatusProjeto;
import Model.Usuario;
import View.ProjetoView;

public class ProjetoController {
    private List<Projeto> projetos;
    private ProjetoView view;

    public ProjetoController(ProjetoView view, List<Projeto> listaProjetos) {
        this.view = view;
        this.projetos = listaProjetos;
    }

    public void cadastrarProjeto(String nome, LocalDate inicio, LocalDate fim, Usuario responsavel) {
        if (!responsavel.getCargo().equalsIgnoreCase("Admin") &&
                !responsavel.getCargo().equalsIgnoreCase("Gerente")) {
            ProjetoView.exibirMensagem(" Apenas Admin ou Gerente podem ser responsáveis por projetos.");
            return;
        }
        if (fim.isBefore(inicio)) {
            ProjetoView.exibirMensagem(" Data final deve ser depois da data inicial.");
            return;
        }
        projetos.add(new Projeto(nome, inicio, fim, responsavel));
        ProjetoView.exibirMensagem(" Projeto '" + nome + "' cadastrado com sucesso!");
    }

    public void adicionarEquipe(String nomeProjeto, Equipe equipe) {
        for (Projeto p : projetos) {
            if (p.getNome().equalsIgnoreCase(nomeProjeto)) {
                boolean adicionada = p.adicionarEquipe(equipe);
                if (adicionada) {
                    ProjetoView.exibirMensagem(" Projeto adicionado à Equipe!");
                } else {
                    ProjetoView.exibirMensagem(" Projeto já está nesta Equipe.");
                }
                return;
            }
        }
        ProjetoView.exibirMensagem(" Projeto não encontrado.");
    }

    public void alterarStatusProjeto(String nomeProjeto, StatusProjeto novoStatus) {
        for (Projeto p : projetos) {
            if (p.getNome().equalsIgnoreCase(nomeProjeto)) {
                p.alterarStatus(novoStatus);
                ProjetoView.exibirMensagem("Status do projeto" + nomeProjeto + " alterado para " + novoStatus.toString());
                return;
            }
        }
        ProjetoView.exibirMensagem(" Projeto não encontrado.");
    }


    public void exibirProjetos() {
        if (projetos.isEmpty()) {
            ProjetoView.exibirMensagem("Nenhum projeto cadastrado.");
        } else {
            view.exibirProjetos(projetos);
        }
    }
}