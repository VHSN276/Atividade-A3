package Model;

import java.util.ArrayList;
import java.util.List;

public class Equipe extends Default {
    private String descricao;
    private List<Usuario> membros;
    private int maxMembros;

    public Equipe() {
        try {
            this.membros = new ArrayList<>();
            this.maxMembros = 10; // Valor padrão
        } catch (Exception e) {
            System.err.println("Erro ao inicializar equipe: " + e.getMessage());
        }
    }

    public Equipe(String nome, String descricao) {
        try {
            if (nome == null || nome.trim().isEmpty()) {
                throw new IllegalArgumentException("Nome da equipe não pode ser vazio");
            }
            if (descricao == null || descricao.trim().isEmpty()) {
                throw new IllegalArgumentException("Descrição da equipe não pode ser vazia");
            }

            super.setNome(nome);
            this.descricao = descricao;
            this.membros = new ArrayList<>();
            this.maxMembros = 10; // Valor padrão
        } catch (IllegalArgumentException e) {
            System.err.println("Erro de validação ao criar equipe: " + e.getMessage());
            throw e;
        } catch (Exception e) {
            System.err.println("Erro inesperado ao criar equipe: " + e.getMessage());
            throw e;
        }
    }

    public Equipe(String nome, String descricao, int maxMembros) {
        try {
            if (nome == null || nome.trim().isEmpty()) {
                throw new IllegalArgumentException("Nome da equipe não pode ser vazio");
            }
            if (descricao == null || descricao.trim().isEmpty()) {
                throw new IllegalArgumentException("Descrição da equipe não pode ser vazia");
            }
            if (maxMembros <= 0) {
                throw new IllegalArgumentException("Número máximo de membros deve ser maior que zero");
            }

            super.setNome(nome);
            this.descricao = descricao;
            this.membros = new ArrayList<>();
            this.maxMembros = maxMembros;
        } catch (IllegalArgumentException e) {
            System.err.println("Erro de validação ao criar equipe: " + e.getMessage());
            throw e;
        } catch (Exception e) {
            System.err.println("Erro inesperado ao criar equipe: " + e.getMessage());
            throw e;
        }
    }

    public boolean adicionarMembro(Usuario usuario) {
        try {
            if (usuario == null) {
                throw new IllegalArgumentException("Usuário não pode ser nulo");
            }
            if (membros == null) {
                membros = new ArrayList<>();
            }
            if (membros.size() >= maxMembros) {
                System.out.println("Equipe já atingiu o número máximo de membros (" + maxMembros + ")");
                return false;
            }
            if (membros.contains(usuario)) {
                System.out.println("Usuário já é membro desta equipe");
                return false;
            }

            membros.add(usuario);
            System.out.println("Usuário " + usuario.getNome() + " adicionado à equipe " + getNome());
            return true;
        } catch (IllegalArgumentException e) {
            System.err.println("Erro de validação ao adicionar membro: " + e.getMessage());
            return false;
        } catch (Exception e) {
            System.err.println("Erro inesperado ao adicionar membro: " + e.getMessage());
            return false;
        }
    }

    public boolean removerMembro(Usuario usuario) {
        try {
            if (usuario == null) {
                throw new IllegalArgumentException("Usuário não pode ser nulo");
            }
            if (membros == null) {
                System.out.println("Lista de membros não inicializada");
                return false;
            }

            boolean removido = membros.remove(usuario);
            if (removido) {
                System.out.println("Usuário " + usuario.getNome() + " removido da equipe " + getNome());
            } else {
                System.out.println("Usuário não encontrado na equipe");
            }
            return removido;
        } catch (IllegalArgumentException e) {
            System.err.println("Erro de validação ao remover membro: " + e.getMessage());
            return false;
        } catch (Exception e) {
            System.err.println("Erro inesperado ao remover membro: " + e.getMessage());
            return false;
        }
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        try {
            if (descricao == null || descricao.trim().isEmpty()) {
                throw new IllegalArgumentException("Descrição não pode ser vazia");
            }
            this.descricao = descricao;
        } catch (IllegalArgumentException e) {
            System.err.println("Erro de validação ao definir descrição: " + e.getMessage());
            throw e;
        }
    }

    public List<Usuario> getMembros() {
        try {
            if (membros == null) {
                return new ArrayList<>();
            }
            return new ArrayList<>(membros); // Retorna uma cópia para evitar modificações externas
        } catch (Exception e) {
            System.err.println("Erro ao obter lista de membros: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    public int getMaxMembros() {
        return maxMembros;
    }

    public void setMaxMembros(int maxMembros) {
        try {
            if (maxMembros <= 0) {
                throw new IllegalArgumentException("Número máximo de membros deve ser maior que zero");
            }
            if (membros != null && membros.size() > maxMembros) {
                throw new IllegalArgumentException("Não é possível definir um limite menor que o número atual de membros (" + membros.size() + ")");
            }
            this.maxMembros = maxMembros;
        } catch (IllegalArgumentException e) {
            System.err.println("Erro de validação ao definir número máximo de membros: " + e.getMessage());
            throw e;
        }
    }

    public int getNumeroMembros() {
        try {
            return membros != null ? membros.size() : 0;
        } catch (Exception e) {
            System.err.println("Erro ao obter número de membros: " + e.getMessage());
            return 0;
        }
    }

    @Override
    public String toString() {
        try {
            return String.format("Equipe{nome='%s', descricao='%s', membros=%d/%d}",
                    getNome() != null ? getNome() : "N/A",
                    descricao != null ? descricao : "N/A",
                    getNumeroMembros(),
                    maxMembros);
        } catch (Exception e) {
            return "Equipe{erro ao exibir dados: " + e.getMessage() + "}";
        }
    }
}