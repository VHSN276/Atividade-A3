package Model;

import java.util.List;

public class Usuario extends Default {
    private String cpf;
    private String cargo;
    private String login;
    private String senha;

    public Usuario() {
    }

    public Usuario(String nome, String cpf, String cargo, String login, String senha) {
        try {
            super.setNome(nome);
            this.setCpf(cpf);
            this.setCargo(cargo);
            this.setLogin(login);
            this.setSenha(senha);
        } catch (Exception e) {
            System.err.println("Erro ao criar usuário: " + e.getMessage());
            throw new RuntimeException("Erro ao criar usuário", e);
        }
    }

    public void cadastro(Usuario u, List<Usuario> list){
        try {
            if (u == null) {
                throw new IllegalArgumentException("Usuário não pode ser nulo");
            }
            if (list == null) {
                throw new IllegalArgumentException("Lista não pode ser nula");
            }
            list.add(u);
        } catch (IllegalArgumentException e) {
            System.err.println("Erro de validação no cadastro: " + e.getMessage());
            throw e;
        } catch (Exception e) {
            System.err.println("Erro inesperado no cadastro: " + e.getMessage());
            throw new RuntimeException("Erro no cadastro", e);
        }
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        try {
            if (cpf == null || cpf.trim().isEmpty()) {
                throw new IllegalArgumentException("CPF não pode ser vazio");
            }
            // Validação básica de formato de CPF
            String cpfLimpo = cpf.replaceAll("[.-]", "");
            if (!cpfLimpo.matches("\\d{11}")) {
                throw new IllegalArgumentException("CPF deve conter 11 dígitos numéricos");
            }
            this.cpf = cpf.trim();
        } catch (IllegalArgumentException e) {
            System.err.println("Erro de validação ao definir CPF: " + e.getMessage());
            throw e;
        } catch (Exception e) {
            System.err.println("Erro inesperado ao definir CPF: " + e.getMessage());
            throw new RuntimeException("Erro ao definir CPF", e);
        }
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        try {
            if (cargo == null || cargo.trim().isEmpty()) {
                throw new IllegalArgumentException("Cargo não pode ser vazio");
            }
            this.cargo = cargo.trim();
        } catch (IllegalArgumentException e) {
            System.err.println("Erro de validação ao definir cargo: " + e.getMessage());
            throw e;
        } catch (Exception e) {
            System.err.println("Erro inesperado ao definir cargo: " + e.getMessage());
            throw new RuntimeException("Erro ao definir cargo", e);
        }
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        try {
            if (login == null || login.trim().isEmpty()) {
                throw new IllegalArgumentException("Login não pode ser vazio");
            }
            if (login.trim().length() < 3) {
                throw new IllegalArgumentException("Login deve ter pelo menos 3 caracteres");
            }
            this.login = login.trim();
        } catch (IllegalArgumentException e) {
            System.err.println("Erro de validação ao definir login: " + e.getMessage());
            throw e;
        } catch (Exception e) {
            System.err.println("Erro inesperado ao definir login: " + e.getMessage());
            throw new RuntimeException("Erro ao definir login", e);
        }
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        try {
            if (senha == null || senha.trim().isEmpty()) {
                throw new IllegalArgumentException("Senha não pode ser vazia");
            }
            if (senha.trim().length() < 4) {
                throw new IllegalArgumentException("Senha deve ter pelo menos 4 caracteres");
            }
            this.senha = senha.trim();
        } catch (IllegalArgumentException e) {
            System.err.println("Erro de validação ao definir senha: " + e.getMessage());
            throw e;
        } catch (Exception e) {
            System.err.println("Erro inesperado ao definir senha: " + e.getMessage());
            throw new RuntimeException("Erro ao definir senha", e);
        }
    }

    @Override
    public String toString() {
        try {
            return String.format("Usuario{nome='%s', cpf='%s', cargo='%s', login='%s'}",
                    getNome() != null ? getNome() : "N/A",
                    cpf != null ? cpf : "N/A",
                    cargo != null ? cargo : "N/A",
                    login != null ? login : "N/A");
        } catch (Exception e) {
            return "Usuario{erro ao exibir dados: " + e.getMessage() + "}";
        }
    }

    @Override
    public boolean equals(Object obj) {
        try {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;

            Usuario usuario = (Usuario) obj;
            return cpf != null && cpf.equals(usuario.cpf);
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public int hashCode() {
        try {
            return cpf != null ? cpf.hashCode() : 0;
        } catch (Exception e) {
            return 0;
        }
    }
}