package Model;

public class Default {
    private String nome;

    public Default() {
    }

    public Default(String nome) {
        try {
            if (nome == null || nome.trim().isEmpty()) {
                throw new IllegalArgumentException("Nome não pode ser vazio");
            }
            this.nome = nome;
        } catch (IllegalArgumentException e) {
            System.err.println("Erro de validação ao criar objeto Default: " + e.getMessage());
            throw e;
        } catch (Exception e) {
            System.err.println("Erro inesperado ao criar objeto Default: " + e.getMessage());
            throw e;
        }
    }

    public void cadastro(){
        try {
            // Implementação do método cadastro pode ser adicionada aqui
            System.out.println("Método cadastro executado");
        } catch (Exception e) {
            System.err.println("Erro no método cadastro: " + e.getMessage());
        }
    }

    public void visualizarTodos(){
        try {
            // Implementação do método visualizarTodos pode ser adicionada aqui
            System.out.println("Método visualizarTodos executado");
        } catch (Exception e) {
            System.err.println("Erro no método visualizarTodos: " + e.getMessage());
        }
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        try {
            if (nome == null || nome.trim().isEmpty()) {
                throw new IllegalArgumentException("Nome não pode ser vazio");
            }
            this.nome = nome;
        } catch (IllegalArgumentException e) {
            System.err.println("Erro de validação ao definir nome: " + e.getMessage());
            throw e;
        } catch (Exception e) {
            System.err.println("Erro inesperado ao definir nome: " + e.getMessage());
            throw e;
        }
    }

    @Override
    public String toString() {
        try {
            return String.format("Default{nome='%s'}", nome != null ? nome : "N/A");
        } catch (Exception e) {
            return "Default{erro ao exibir dados: " + e.getMessage() + "}";
        }
    }
}