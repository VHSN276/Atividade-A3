import Controller.UsuarioController;
import Model.Usuario;
import View.UsuarioView;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        List<Usuario> bancoDadosLocal = new ArrayList<>();

        UsuarioView usuarioView = new UsuarioView();
        UsuarioController usuarioController = new UsuarioController(bancoDadosLocal, usuarioView);

        Scanner scanner = new Scanner(System.in);
        String opcao = "";
        String opcaoMenu = "";



        while(true){
            System.out.println("MENU\n[1]Usuarios\n[2]Projetos\n[3]Equipes\n[4]Sair\nSelecione uma opção: ");
            opcaoMenu = scanner.nextLine();
            switch (opcaoMenu){
                case "1":
                    while(!opcao.equals("4")){
                        usuarioView.exibirMenu();
                        opcao = scanner.nextLine();

                        switch (opcao){
                            case "1": //Exibir usuarios cadastrados
                                usuarioController.listarUsuarios();
                                break;
                            case "2": //Cadastro de usuario
                                System.out.println("Nome: ");
                                String nome = scanner.nextLine();
                                System.out.println("CPF: ");
                                String cpf = scanner.nextLine();
                                System.out.println("Cargo: ");
                                String cargo = scanner.nextLine();
                                System.out.println("Login: ");
                                String login = scanner.nextLine();
                                System.out.println("Senha: ");
                                String senha = scanner.nextLine();
                                usuarioController.cadastrarUsuario(nome, cpf, cargo, login, senha);
                        }
                    }
                    break;
                case "2":

                    break;
                case "3":

                    break;
                case "4":
                    System.out.println("Saindo do sistema");
                    break;
                default:
                    System.out.println("Opção invalida. Tente novammente");
                    break;
            }
            if(opcaoMenu.equals("4")){
                break;
            }
        }
        scanner.close();
        System.out.println("Programa Finalizado");


    }
}