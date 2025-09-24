package Controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ConcurrentModificationException;
import Model.Equipe;
import Model.Projeto;
import Model.StatusProjeto;
import Model.Usuario;
import View.ProjetoView;

public class ProjetoController {
    private List<Projeto> projetos;
    private ProjetoView view;

    public ProjetoController(ProjetoView view, List<Projeto> listaProjetos) {
        try {
            if (view == null) {
                throw new IllegalArgumentException("ProjetoView não pode ser nula");
            }
            if (listaProjetos == null) {
                throw new IllegalArgumentException("Lista de projetos não pode ser nula");
            }
            this.view = view;
            this.projetos = listaProjetos;
        } catch (Exception e) {
            System.err.println("Erro ao inicializar ProjetoController: " + e.getMessage());
            throw e;
        }
    }

    public void cadastrarProjeto(String nome, LocalDate inicio, LocalDate fim, Usuario responsavel) {
        try {
            // Validações de entrada
            if (nome == null || nome.trim().isEmpty()) {
                throw new IllegalArgumentException("Nome do projeto não pode ser vazio");
            }
            if (inicio == null) {
                throw new IllegalArgumentException("Data de início não pode ser nula");
            }
            if (fim == null) {
                throw new IllegalArgumentException("Data de fim não pode ser nula");
            }
            if (responsavel == null) {
                throw new IllegalArgumentException("Responsável não pode ser nulo");
            }

            try {
                if (responsavel.getCargo() == null) {
                    throw new IllegalArgumentException("Cargo do responsável não pode ser nulo");
                }

                if (!responsavel.getCargo().equalsIgnoreCase("Admin") &&
                        !responsavel.getCargo().equalsIgnoreCase("Gerente")) {
                    ProjetoView.exibirMensagem("Apenas Admin ou Gerente podem ser responsáveis por projetos.");
                    return;
                }
            } catch (NullPointerException e) {
                System.err.println("Erro: dados do responsável são nulos: " + e.getMessage());
                return;
            }

            try {
                if (fim.isBefore(inicio)) {
                    ProjetoView.exibirMensagem("Data final deve ser depois da data inicial.");
                    return;
                }
            } catch (Exception e) {
                System.err.println("Erro ao comparar datas: " + e.getMessage());
                return;
            }

            try {
                projetos.add(new Projeto(nome.trim(), inicio, fim, responsavel));
                ProjetoView.exibirMensagem("Projeto '" + nome + "' cadastrado com sucesso!");
            } catch (Exception e) {
                System.err.println("Erro ao adicionar projeto à lista: " + e.getMessage());
            }

        } catch (IllegalArgumentException e) {
            System.err.println("Erro de validação: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Erro inesperado ao cadastrar projeto: " + e.getMessage());
        }
    }

    public void adicionarEquipe(String nomeProjeto, Equipe equipe) {
        try {
            if (nomeProjeto == null || nomeProjeto.trim().isEmpty()) {
                throw new IllegalArgumentException("Nome do projeto não pode ser vazio");
            }
            if (equipe == null) {
                throw new IllegalArgumentException("Equipe não pode ser nula");
            }

            try {
                for (Projeto p : projetos) {
                    if (p != null && p.getNome() != null && p.getNome().equalsIgnoreCase(nomeProjeto.trim())) {
                        try {
                            boolean adicionada = p.adicionarEquipe(equipe);
                            if (adicionada) {
                                ProjetoView.exibirMensagem("Projeto adicionado à Equipe!");
                            } else {
                                ProjetoView.exibirMensagem("Projeto já está nesta Equipe.");
                            }
                        } catch (Exception e) {
                            System.err.println("Erro ao adicionar equipe ao projeto: " + e.getMessage());
                        }
                        return;
                    }
                }
            } catch (ConcurrentModificationException e) {
                System.err.println("Erro de concorrência ao buscar projeto: " + e.getMessage());
                return;
            } catch (NullPointerException e) {
                System.err.println("Erro: projeto nulo encontrado na lista: " + e.getMessage());
                return;
            }

            ProjetoView.exibirMensagem("Projeto não encontrado.");
        } catch (IllegalArgumentException e) {
            System.err.println("Erro de validação: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Erro inesperado ao adicionar equipe: " + e.getMessage());
        }
    }

    public void alterarStatusProjeto(String nomeProjeto, StatusProjeto novoStatus) {
        try {
            if (nomeProjeto == null || nomeProjeto.trim().isEmpty()) {
                throw new IllegalArgumentException("Nome do projeto não pode ser vazio");
            }
            if (novoStatus == null) {
                throw new IllegalArgumentException("Novo status não pode ser nulo");
            }

            try {
                for (Projeto p : projetos) {
                    if (p != null && p.getNome() != null && p.getNome().equalsIgnoreCase(nomeProjeto.trim())) {
                        try {
                            p.alterarStatus(novoStatus);
                            ProjetoView.exibirMensagem("Status do projeto " + nomeProjeto + " alterado para " + novoStatus.toString());
                        } catch (Exception e) {
                            System.err.println("Erro ao alterar status do projeto: " + e.getMessage());
                        }
                        return;
                    }
                }
            } catch (ConcurrentModificationException e) {
                System.err.println("Erro de concorrência ao buscar projeto: " + e.getMessage());
                return;
            } catch (NullPointerException e) {
                System.err.println("Erro: projeto nulo encontrado na lista: " + e.getMessage());
                return;
            }

            ProjetoView.exibirMensagem("Projeto não encontrado.");
        } catch (IllegalArgumentException e) {
            System.err.println("Erro de validação: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Erro inesperado ao alterar status: " + e.getMessage());
        }
    }

    public void exibirProjetos() {
        try {
            if (projetos == null) {
                ProjetoView.exibirMensagem("Lista de projetos não inicializada.");
                return;
            }

            if (projetos.isEmpty()) {
                ProjetoView.exibirMensagem("Nenhum projeto cadastrado.");
            } else {
                try {
                    view.exibirProjetos(projetos);
                } catch (Exception e) {
                    System.err.println("Erro ao exibir projetos na view: " + e.getMessage());
                }
            }
        } catch (Exception e) {
            System.err.println("Erro inesperado ao exibir projetos: " + e.getMessage());
        }
    }
}