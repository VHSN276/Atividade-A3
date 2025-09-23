package Model;

import java.util.Date;

public class Projeto extends Default {
    private Long id;
    private String descricao;
    private String status;
    private Date dataInicio;
    private Date dataFim;
    private String responsavel;

    public Projeto() {
        super();
    }

    public Projeto(String nome, String descricao, String responsavel) {
        try {
            if (nome == null || nome.trim().isEmpty()) {
                throw new IllegalArgumentException("Nome do projeto não pode estar vazio");
            }
            if (descricao == null || descricao.trim().isEmpty()) {
                throw new IllegalArgumentException("Descrição do projeto não pode estar vazia");
            }
            if (responsavel == null || responsavel.trim().isEmpty()) {
                throw new IllegalArgumentException("Responsável do projeto não pode estar vazio");
            }

            super.setNome(nome.trim());
            this.descricao = descricao.trim();
            this.responsavel = responsavel.trim();
            this.status = "Em Planejamento";
            this.dataInicio = new Date();

        } catch (IllegalArgumentException e) {
            System.err.println("Erro de validação ao criar projeto: " + e.getMessage());
            throw e;
        } catch (Exception e) {
            System.err.println("Erro inesperado ao criar projeto: " + e.getMessage());
            throw new RuntimeException("Erro ao criar projeto", e);
        }
    }

    public Projeto(String nome, String descricao, String responsavel, String status) {
        try {
            if (nome == null || nome.trim().isEmpty()) {
                throw new IllegalArgumentException("Nome do projeto não pode estar vazio");
            }
            if (descricao == null || descricao.trim().isEmpty()) {
                throw new IllegalArgumentException("Descrição do projeto não pode estar vazia");
            }
            if (responsavel == null || responsavel.trim().isEmpty()) {
                throw new IllegalArgumentException("Responsável do projeto não pode estar vazio");
            }
            if (status == null || status.trim().isEmpty()) {
                throw new IllegalArgumentException("Status do projeto não pode estar vazio");
            }

            super.setNome(nome.trim());
            this.descricao = descricao.trim();
            this.responsavel = responsavel.trim();
            this.status = status.trim();
            this.dataInicio = new Date();

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
            System.err.println("Erro ao definir ID do projeto: " + e.getMessage());
            throw new RuntimeException("Erro ao definir ID", e);
        }
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        try {
            if (descricao == null || descricao.trim().isEmpty()) {
                throw new IllegalArgumentException("Descrição não pode estar vazia");
            }
            this.descricao = descricao.trim();
        } catch (IllegalArgumentException e) {
            System.err.println("Erro de validação ao definir descrição: " + e.getMessage());
            throw e;
        } catch (Exception e) {
            System.err.println("Erro inesperado ao definir descrição: " + e.getMessage());
            throw new RuntimeException("Erro ao definir descrição", e);
        }
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        try {
            if (status == null || status.trim().isEmpty()) {
                throw new IllegalArgumentException("Status não pode estar vazio");
            }

            // Validação de status válidos
            String statusUpper = status.trim().toUpperCase();
            if (!statusUpper.equals("EM PLANEJAMENTO") &&
                    !statusUpper.equals("EM ANDAMENTO") &&
                    !statusUpper.equals("CONCLUIDO") &&
                    !statusUpper.equals("CANCELADO")) {
                throw new IllegalArgumentException("Status inválido. Use: Em Planejamento, Em Andamento, Concluido ou Cancelado");
            }

            this.status = status.trim();
        } catch (IllegalArgumentException e) {
            System.err.println("Erro de validação ao definir status: " + e.getMessage());
            throw e;
        } catch (Exception e) {
            System.err.println("Erro inesperado ao definir status: " + e.getMessage());
            throw new RuntimeException("Erro ao definir status", e);
        }
    }

    public Date getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(Date dataInicio) {
        try {
            if (dataInicio == null) {
                throw new IllegalArgumentException("Data de início não pode ser nula");
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

    public Date getDataFim() {
        return dataFim;
    }

    public void setDataFim(Date dataFim) {
        try {
            if (dataFim != null && dataInicio != null && dataFim.before(dataInicio)) {
                throw new IllegalArgumentException("Data de fim não pode ser anterior à data de início");
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

    public String getResponsavel() {
        return responsavel;
    }

    public void setResponsavel(String responsavel) {
        try {
            if (responsavel == null || responsavel.trim().isEmpty()) {
                throw new IllegalArgumentException("Responsável não pode estar vazio");
            }
            this.responsavel = responsavel.trim();
        } catch (IllegalArgumentException e) {
            System.err.println("Erro de validação ao definir responsável: " + e.getMessage());
            throw e;
        } catch (Exception e) {
            System.err.println("Erro inesperado ao definir responsável: " + e.getMessage());
            throw new RuntimeException("Erro ao definir responsável", e);
        }
    }

    /**
     * Método para validar se o projeto está em estado válido
     */
    public boolean isValido() {
        try {
            return this.getNome() != null && !this.getNome().trim().isEmpty() &&
                    this.descricao != null && !this.descricao.trim().isEmpty() &&
                    this.responsavel != null && !this.responsavel.trim().isEmpty() &&
                    this.status != null && !this.status.trim().isEmpty();
        } catch (Exception e) {
            System.err.println("Erro ao validar projeto: " + e.getMessage());
            return false;
        }
    }

    /**
     * Método para finalizar projeto
     */
    public void finalizar() {
        try {
            this.status = "Concluido";
            this.dataFim = new Date();
        } catch (Exception e) {
            System.err.println("Erro ao finalizar projeto: " + e.getMessage());
            throw new RuntimeException("Erro ao finalizar projeto", e);
        }
    }

    /**
     * Método para cancelar projeto
     */
    public void cancelar() {
        try {
            this.status = "Cancelado";
            this.dataFim = new Date();
        } catch (Exception e) {
            System.err.println("Erro ao cancelar projeto: " + e.getMessage());
            throw new RuntimeException("Erro ao cancelar projeto", e);
        }
    }

    @Override
    public String toString() {
        try {
            return String.format("Projeto{id=%d, nome='%s', descricao='%s', status='%s', responsavel='%s'}",
                    id != null ? id : 0,
                    getNome() != null ? getNome() : "N/A",
                    descricao != null ? descricao : "N/A",
                    status != null ? status : "N/A",
                    responsavel != null ? responsavel : "N/A");
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

            // Compara por ID se disponível, senão por nome
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