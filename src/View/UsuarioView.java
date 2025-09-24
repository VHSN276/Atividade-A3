package View;

import Model.Usuario;

import java.util.List;

public class UsuarioView {
    public void exibirMenu(){
        try {
            System.out.println("\nMenu\n[1]Exibir Usuários\n[2]Cadastrar um novo Usuário\n[3]Excluir um Usuário\n[4]Voltar\nEscolha uma opção: ");
        } catch (Exception e) {
            System.err.println("Erro ao exibir menu: " + e.getMessage());
        }
    }

    public void exibirUsuarios(List<Usuario> usuarios){
        try {
            System.out.println("Lista de Usuários");

            if (usuarios == null) {
                System.out.println("Lista de usuários não inicializada");
                return;
            }

            if(usuarios.isEmpty()){
                System.out.println("Nenhum usuário cadastrado");
            } else {
                int index = 1;
                for (Usuario usuario : usuarios){
                    try {
                        if (usuario == null) {
                            System.out.println("==========");
                            System.out.println("[" + index + "] Usuario inválido (dados nulos)");
                            System.out.println("==========");
                        } else {
                            System.out.println("==========");
                            System.out.println("["+index+"] Usuario");

                            // Tratamento para campos que podem ser nulos
                            String nome = usuario.getNome() != null ? usuario.getNome() : "Nome não informado";
                            String cpf = usuario.getCpf() != null ? usuario.getCpf() : "CPF não informado";
                            String cargo = usuario.getCargo() != null ? usuario.getCargo() : "Cargo não informado";
                            String login = usuario.getLogin() != null ? usuario.getLogin() : "Login não informado";
                            String senha = usuario.getSenha() != null ? "****" : "Senha não definida"; // Mascarar senha por segurança

                            System.out.println(String.format("Nome: %s\nCPF: %s\nCargo: %s\nLogin: %s\nSenha: %s",
                                    nome, cpf, cargo, login, senha));
                            System.out.println("==========");
                        }
                        index++;
                    } catch (Exception e) {
                        System.err.println("Erro ao exibir usuário " + index + ": " + e.getMessage());
                        index++;
                    }
                }
            }
        } catch (java.util.ConcurrentModificationException e) {
            System.err.println("Erro: Lista foi modificada durante a exibição. Tente novamente.");
        } catch (Exception e) {
            System.err.println("Erro inesperado ao exibir usuários: " + e.getMessage());
        }
    }

    public void exibirMensagem(String mensagem){
        try {
            if (mensagem == null) {
                System.out.println("(mensagem nula)");
                return;
            }
            System.out.println(mensagem);
        } catch (Exception e) {
            System.err.println("Erro ao exibir mensagem: " + e.getMessage());
        }
    }
}