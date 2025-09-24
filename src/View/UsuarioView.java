package View;

import Model.Usuario;

import java.util.List;

public class UsuarioView {
    public void exibirMenu(){
        try {
            System.out.println("\nMenu\n[1]Exibir Usuários\n[2]Cadastrar um novo Usuário\n[3]Excluir um Usuário\n[0]Voltar\nEscolha uma opção: ");
        } catch (Exception e) {
            System.out.println("Erro ao exibir menu: " + e.getMessage());
        }
    }

    public void exibirUsuarios(List<Usuario> usuarios) {
        try {
            System.out.println("\n--- Lista de Usuários ---");

            if (usuarios == null || usuarios.isEmpty()) {
                System.out.println("Nenhum usuário cadastrado.");
                return;
            }

            // Itera sobre cada usuário na lista para exibir seus detalhes
            for (Usuario usuario : usuarios) {
                if (usuario != null) {
                    System.out.println("Nome: " + (usuario.getNome() != null ? usuario.getNome() : "N/A"));
                    System.out.println("CPF: " + (usuario.getCpf() != null ? usuario.getCpf() : "N/A"));
                    System.out.println("Cargo: " + (usuario.getCargo() != null ? usuario.getCargo() : "N/A"));
                    System.out.println("Login: " + (usuario.getLogin() != null ? usuario.getLogin() : "N/A"));
                    // ⚠️ A senha NUNCA deve ser exibida por motivos de segurança.
                    System.out.println("-----------------------------");
                }
            }
        } catch (NullPointerException e) {
            System.out.println("Erro: Referência nula ao exibir usuários - " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Erro ao exibir usuários: " + e.getMessage());
        }
    }

    public void exibirMensagem(String mensagem){
        try {
            if (mensagem != null) {
                System.out.println(mensagem);
            } else {
                System.out.println("Mensagem nula recebida.");
            }
        } catch (Exception e) {
            System.out.println("Erro ao exibir mensagem: " + e.getMessage());
        }
    }
}