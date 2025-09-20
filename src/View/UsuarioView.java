package View;

import Model.Usuario;

import java.util.List;

public class UsuarioView {
    public void exibirMenu(){
        System.out.println("\nMenu\n[1]Exibir Usuários\n[2]Cadastrar um novo Usuário\n[3]Excluir um Usuário\n[4]Voltar\nEscolha uma opção: ");
    }
    public void exibirUsuarios(List<Usuario> usuarios){
        System.out.println("Lista de Usuários");
        if(usuarios.isEmpty()){
            System.out.println("Nenhum usuário cadastrado");
        }
        else{
            int index = 1;
            for (Usuario usuario : usuarios){
                System.out.println("==========");
                System.out.println("["+index+"] Usuario");
                System.out.println(String.format("Nome: %s\nCPF: %s\nCargo: %s\nLogin: %s\nSenha: %s", usuario.getNome(), usuario.getCpf(), usuario.getCargo(), usuario.getLogin(), usuario.getSenha()));
                System.out.println("==========");
            }
        }
    }

    public void exibirMensagem(String mensagem){
        System.out.println(mensagem);
    }
}
