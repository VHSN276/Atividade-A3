package Model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Projeto extends Default {
    private Long id;
    private LocalDate dataInicio;
    private LocalDate dataFim;
    private Usuario responsavel;
    private StatusProjeto status;
    private List<Equipe> equipes;

    public Projeto() {
        try {
            this.equipes = new ArrayList<>();
            this.status = StatusProjeto.PLANEJADO;
        } catch (Exception e) {
            System.err.println("Erro ao inicializar projeto: " + e.getMessage());
        }
    }

    public Projeto(String nome, LocalDate dataInicio, LocalDate dataFim, Usuario responsavel) {
        try {
            if (nome == null || nome.trim().isEmpty()) {
                throw new IllegalArgumentException("Nome do projeto não pode ser vazio");
            }
            if (dataInicio == null) {
                throw new IllegalArgumentException("Data de início não pode ser nula");
            }
            if (dataFim == null) {
                throw new IllegalArgumentException("Data de fim não pode ser nula");
            }
            if (responsavel == null) {
                throw new IllegalArgumentException("Responsável não pode ser nulo");
            }
            if (dataFim.isBefore(dataInicio)) {
                throw new IllegalArgumentException("Data de fim deve ser posterior à data de início");
            }

            super.setNome(nome.trim());
            this.dataInicio = dataInicio;
            this.dataFim = dataFim;
            this.responsavel = responsavel;
            this.status = StatusProjeto.PLANEJADO;
            this.equipes = new ArrayList<>();
        } catch (IllegalArgumentException e) {
            System.err.println("Erro de validação ao criar projeto: " + e.getMessage());
            throw e;
        } catch (Exception e) {
            System.err.println("Erro inesperado ao criar projeto: " + e.getMessage());
            throw new RuntimeException("Erro ao criar projeto", e);
        }
    }

    // Getters e Setters com tratamento de exceções
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        try {
            this.id = id;
        } catch (Exception e) {
            System.err.println("Erro ao definir ID: " + e.getMessage());
        }
    }

    public LocalDate getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(LocalDate dataInicio) {
        try {
            if (dataInicio == null) {
                throw new IllegalArgumentException("Data de início não pode ser nula");
            }
            if (dataFim != null && dataInicio.isAfter(dataFim)) {
                throw new IllegalArgumentException("Data de início deve ser anterior à data de fim");
            }
            this.dataInicio = dataInicio;
        } catch (IllegalArgumentException e) {
            System.err.println("Erro de validação ao definir data de início: " + e.getMessage());
            throw e;
        } catch (Exception e) {
            System.err.println("Erro inesperado ao definir data de início: " + e.getMessage());
            throw new RuntimeException("Erro ao definir data de início", e);
        }
    }

    public LocalDate getDataFim() {
        return dataFim;
    }

    public void setDataFim(LocalDate dataFim) {
        try {
            if (dataFim == null) {
                throw new IllegalArgumentException("Data de fim não pode ser nula");
            }
            if (dataInicio != null && dataFim.isBefore(dataInicio)) {
                throw new IllegalArgumentException("Data de fim deve ser posterior à data de início");
            }
            this.dataFim = dataFim;
        } catch (IllegalArgumentException e) {
            System.err.println("Erro de validação ao definir data de fim: " + e.getMessage());
            throw e;
        } catch (Exception e) {
            System.err.println("Erro inesperado ao definir data de fim: " + e.getMessage());
            throw new RuntimeException("Erro ao definir data de fim", e);
        }
    }

    public Usuario getResponsavel() {
        return responsavel;
    }

    public void setResponsavel(Usuario responsavel) {
        try {
            if (responsavel == null) {
                throw new IllegalArgumentException("Responsável não pode ser nulo");
            }
            this.responsavel = responsavel;
        } catch (IllegalArgumentException e) {
            System.err.println("Erro de validação ao definir responsável: " + e.getMessage());
            throw e;
        } catch (Exception e) {
            System.err.println("Erro inesperado ao definir responsável: " + e.getMessage());
            throw new RuntimeException("Erro ao definir responsável", e);
        }
    }

    public StatusProjeto getStatus() {
        return status;
    }

    public void alterarStatus(StatusProjeto novoStatus) {
        try {
            if (novoStatus == null) {
                throw new IllegalArgumentException("Status não pode ser nulo");
            }
            this.status = novoStatus;
        } catch (IllegalArgumentException e) {
            System.err.println("Erro de validação ao alterar status: " + e.getMessage());
            throw e;
        } catch (Exception e) {
            System.err.println("Erro inesperado ao alterar status: " + e.getMessage());
            throw new RuntimeException("Erro ao alterar status", e);
        }
    }

    public List<Equipe> getEquipes() {
        try {
            return equipes != null ? new ArrayList<>(equipes) : new ArrayList<>();
        } catch (Exception e) {
            System.err.println("Erro ao obter lista de equipes: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    public boolean adicionarEquipe(Equipe equipe) {
        try {
            if (equipe == null) {
                throw new IllegalArgumentException("Equipe não pode ser nula");
            }
            if (equipes == null) {
                equipes = new ArrayList<>();
            }
            if (equipes.contains(equipe)) {
                return false; // Equipe já está no projeto
            }
            equipes.add(equipe);
            return true;
        } catch (IllegalArgumentException e) {
            System.err.println("Erro de validação ao adicionar equipe: " + e.getMessage());
            return false;
        } catch (Exception e) {
            System.err.println("Erro inesperado ao adicionar equipe: " + e.getMessage());
            return false;
        }
    }

    public boolean removerEquipe(Equipe equipe) {
        try {
            if (equipe == null) {
                throw new IllegalArgumentException("Equipe não pode ser nula");
            }
            if (equipes == null) {
                return false;
            }
            return equipes.remove(equipe);
        } catch (IllegalArgumentException e) {
            System.err.println("Erro de validação ao remover equipe: " + e.getMessage());
            return false;
        } catch (Exception e) {
            System.err.println("Erro inesperado ao remover equipe: " + e.getMessage());
            return false;
        }
    }

    @Override
    public String toString() {
        try {
            return String.format("Projeto{nome='%s', status='%s', responsavel='%s', dataInicio='%s', dataFim='%s'}",
                    getNome() != null ? getNome() : "N/A",
                    status != null ? status.toString() : "N/A",
                    responsavel != null && responsavel.getNome() != null ? responsavel.getNome() : "N/A",
                    dataInicio != null ? dataInicio.toString() : "N/A",
                    dataFim != null ? dataFim.toString() : "N/A");
        } catch (Exception e) {
            return "Projeto{erro ao exibir dados: " + e.getMessage() + "}";
        }
    }

    @Override
    public boolean equals(Object obj) {
        try {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;

            Projeto projeto = (Projeto) obj;

            if (id != null && projeto.id != null) {
                return id.equals(projeto.id);
            }

            return getNome() != null && getNome().equals(projeto.getNome());
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public int hashCode() {
        try {
            if (id != null) {
                return id.hashCode();
            }
            return getNome() != null ? getNome().hashCode() : 0;
        } catch (Exception e) {
            return 0;
        }
    }
}