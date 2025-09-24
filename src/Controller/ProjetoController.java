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
        try {
            // Validações básicas
            if (nome == null || nome.trim().isEmpty()) {
                ProjetoView.exibirMensagem("Erro: Nome do projeto não pode estar vazio.");
                return;
            }
            if (inicio == null) {
                ProjetoView.exibirMensagem("Erro: Data de início não pode ser nula.");
                return;
            }
            if (fim == null) {
                ProjetoView.exibirMensagem("Erro: Data de fim não pode ser nula.");
                return;
            }
            if (responsavel == null) {
                ProjetoView.exibirMensagem("Erro: Responsável não pode ser nulo.");
                return;
            }

            if (!responsavel.getCargo().equalsIgnoreCase("Admin") &&
                    !responsavel.getCargo().equalsIgnoreCase("Gerente")) {
                ProjetoView.exibirMensagem(" Apenas Admin ou Gerente podem ser responsáveis por projetos.");
                return;
            }
            if (fim.isBefore(inicio)) {
                ProjetoView.exibirMensagem(" Data final deve ser depois da data inicial.");
                return;
            }

            // Verificar se já existe projeto com o mesmo nome
            for (Projeto p : projetos) {
                if (p != null && p.getNome() != null && p.getNome().equalsIgnoreCase(nome)) {
                    ProjetoView.exibirMensagem("Erro: Já existe um projeto com esse nome.");
                    return;
                }
            }

            projetos.add(new Projeto(nome, inicio, fim, responsavel));
            ProjetoView.exibirMensagem(" Projeto '" + nome + "' cadastrado com sucesso!");
        } catch (NullPointerException e) {
            ProjetoView.exibirMensagem("Erro: Referência nula encontrada - " + e.getMessage());
        } catch (Exception e) {
            ProjetoView.exibirMensagem("Erro ao cadastrar projeto: " + e.getMessage());
        }
    }

    public void adicionarEquipe(String nomeProjeto, Equipe equipe) {
        try {
            if (nomeProjeto == null || nomeProjeto.trim().isEmpty()) {
                ProjetoView.exibirMensagem("Erro: Nome do projeto não pode estar vazio.");
                return;
            }
            if (equipe == null) {
                ProjetoView.exibirMensagem("Erro: Equipe não pode ser nula.");
                return;
            }
            if (projetos == null || projetos.isEmpty()) {
                ProjetoView.exibirMensagem("Erro: Nenhum projeto cadastrado.");
                return;
            }

            for (Projeto p : projetos) {
                if (p != null && p.getNome() != null && p.getNome().equalsIgnoreCase(nomeProjeto)) {
                    boolean adicionada = p.adicionarEquipe(equipe);
                    if (adicionada) {
                        ProjetoView.exibirMensagem(" Equipe adicionada ao Projeto!");
                    } else {
                        ProjetoView.exibirMensagem(" Equipe já está neste Projeto.");
                    }
                    return;
                }
            }
            ProjetoView.exibirMensagem(" Projeto não encontrado.");
        } catch (NullPointerException e) {
            ProjetoView.exibirMensagem("Erro: Referência nula encontrada - " + e.getMessage());
        } catch (Exception e) {
            ProjetoView.exibirMensagem("Erro ao adicionar equipe ao projeto: " + e.getMessage());
        }
    }

    public void alterarStatusProjeto(String nomeProjeto, StatusProjeto novoStatus) {
        try {
            if (nomeProjeto == null || nomeProjeto.trim().isEmpty()) {
                ProjetoView.exibirMensagem("Erro: Nome do projeto não pode estar vazio.");
                return;
            }
            if (novoStatus == null) {
                ProjetoView.exibirMensagem("Erro: Status não pode ser nulo.");
                return;
            }
            if (projetos == null || projetos.isEmpty()) {
                ProjetoView.exibirMensagem("Erro: Nenhum projeto cadastrado.");
                return;
            }

            for (Projeto p : projetos) {
                if (p != null && p.getNome() != null && p.getNome().equalsIgnoreCase(nomeProjeto)) {
                    p.alterarStatus(novoStatus);
                    ProjetoView.exibirMensagem("Status do projeto " + nomeProjeto + " alterado para " + novoStatus.toString());
                    return;
                }
            }
            ProjetoView.exibirMensagem(" Projeto não encontrado.");
        } catch (NullPointerException e) {
            ProjetoView.exibirMensagem("Erro: Referência nula encontrada - " + e.getMessage());
        } catch (Exception e) {
            ProjetoView.exibirMensagem("Erro ao alterar status do projeto: " + e.getMessage());
        }
    }


    public void exibirProjetos() {
        try {
            if (projetos == null) {
                ProjetoView.exibirMensagem("Erro: Lista de projetos não foi inicializada.");
                return;
            }
            if (projetos.isEmpty()) {
                ProjetoView.exibirMensagem("Nenhum projeto cadastrado.");
            } else {
                view.exibirProjetos(projetos);
            }
        } catch (Exception e) {
            ProjetoView.exibirMensagem("Erro ao exibir projetos: " + e.getMessage());
        }
    }
}