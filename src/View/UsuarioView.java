package View;

import Model.Usuario;

import java.util.List;

public class UsuarioView {
    public void exibirMenu(){
        System.out.println("\nMenu\n[1]Exibir Usuários\n[2]Cadastrar um novo Usuário\n[3]Excluir um Usuário\n[0]Voltar\nEscolha uma opção: ");
    }
    public void exibirUsuarios(List<Usuario> usuarios) {
        System.out.println("\n--- Lista de Usuários ---");

        if (usuarios == null || usuarios.isEmpty()) {
            System.out.println("Nenhum usuário cadastrado.");
        }

        // Itera sobre cada usuário na lista para exibir seus detalhes
        for (Usuario usuario : usuarios) {
            System.out.println("Nome: " + usuario.getNome());
            System.out.println("CPF: " + usuario.getCpf());
            System.out.println("Cargo: " + usuario.getCargo());
            System.out.println("Login: " + usuario.getLogin());
            // ⚠️ A senha NUNCA deve ser exibida por motivos de segurança.
            System.out.println("-----------------------------");
        }
    }

    public void exibirMensagem(String mensagem){
        System.out.println(mensagem);
    }
}
