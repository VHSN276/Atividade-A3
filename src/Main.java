import Controller.UsuarioController;
import Model.Usuario;
import View.UsuarioView;

import java.util.ArrayList;
import java.util.InputMismatchException;
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

        try {
            while(true){
                try {
                    System.out.println("MENU\n[1]Usuarios\n[2]Projetos\n[3]Equipes\n[4]Sair\nSelecione uma opção: ");
                    opcaoMenu = scanner.nextLine();

                    switch (opcaoMenu){
                        case "1": //Usuarios Menu
                            opcao = ""; // Reset opcao para o submenu
                            while(!opcao.equals("4")){
                                try {
                                    usuarioView.exibirMenu();
                                    opcao = scanner.nextLine();

                                    switch (opcao){
                                        case "1": //Exibir usuarios cadastrados
                                            try {
                                                usuarioController.listarUsuarios();
                                            } catch (Exception e) {
                                                System.err.println("Erro ao listar usuários: " + e.getMessage());
                                            }
                                            break;
                                        case "2": //Cadastro de usuario
                                            try {
                                                System.out.println("Nome: ");
                                                String nome = scanner.nextLine();
                                                if (nome == null || nome.trim().isEmpty()) {
                                                    throw new IllegalArgumentException("Nome não pode ser vazio");
                                                }

                                                System.out.println("CPF: ");
                                                String cpf = scanner.nextLine();
                                                if (cpf == null || cpf.trim().isEmpty()) {
                                                    throw new IllegalArgumentException("CPF não pode ser vazio");
                                                }

                                                System.out.println("Cargo: ");
                                                String cargo = scanner.nextLine();
                                                if (cargo == null || cargo.trim().isEmpty()) {
                                                    throw new IllegalArgumentException("Cargo não pode ser vazio");
                                                }

                                                System.out.println("Login: ");
                                                String login = scanner.nextLine();
                                                if (login == null || login.trim().isEmpty()) {
                                                    throw new IllegalArgumentException("Login não pode ser vazio");
                                                }

                                                System.out.println("Senha: ");
                                                String senha = scanner.nextLine();
                                                if (senha == null || senha.trim().isEmpty()) {
                                                    throw new IllegalArgumentException("Senha não pode ser vazia");
                                                }

                                                usuarioController.cadastrarUsuario(nome, cpf, cargo, login, senha);
                                            } catch (IllegalArgumentException e) {
                                                System.err.println("Erro de validação: " + e.getMessage());
                                            } catch (Exception e) {
                                                System.err.println("Erro ao cadastrar usuário: " + e.getMessage());
                                            }
                                            break;
                                        case "3": //Exclusão de Usuario
                                            try {
                                                System.out.println("Digite o CPF do usuario que deseja excluir: ");
                                                String deleteCpf = scanner.nextLine();
                                                if (deleteCpf == null || deleteCpf.trim().isEmpty()) {
                                                    throw new IllegalArgumentException("CPF não pode ser vazio");
                                                }
                                                usuarioController.excluirUsuario(deleteCpf);
                                            } catch (IllegalArgumentException e) {
                                                System.err.println("Erro de validação: " + e.getMessage());
                                            } catch (Exception e) {
                                                System.err.println("Erro ao excluir usuário: " + e.getMessage());
                                            }
                                            break;
                                        case "4":
                                            break;
                                        default:
                                            System.out.println("Opção inválida. Tente novamente!");
                                            break;
                                    }
                                } catch (InputMismatchException e) {
                                    System.err.println("Entrada inválida. Por favor, digite uma opção válida.");
                                    scanner.nextLine(); // Limpa o buffer
                                } catch (Exception e) {
                                    System.err.println("Erro inesperado no menu de usuários: " + e.getMessage());
                                }
                            }
                            break;
                        case "2": //Projetos Menu
                            System.out.println("Menu de Projetos ainda não implementado.");
                            break;
                        case "3"://Equipe Menu
                            System.out.println("Menu de Equipes ainda não implementado.");
                            break;
                        case "4":
                            System.out.println("Saindo do sistema");
                            break;
                        default:
                            System.out.println("Opção inválida. Tente novamente");
                            break;
                    }
                    if(opcaoMenu.equals("4")){
                        break;
                    }
                } catch (InputMismatchException e) {
                    System.err.println("Entrada inválida. Por favor, digite uma opção válida.");
                    scanner.nextLine(); // Limpa o buffer
                } catch (Exception e) {
                    System.err.println("Erro inesperado no menu principal: " + e.getMessage());
                }
            }
        } catch (Exception e) {
            System.err.println("Erro crítico na aplicação: " + e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                scanner.close();
            } catch (Exception e) {
                System.err.println("Erro ao fechar o scanner: " + e.getMessage());
            }
            System.out.println("Programa Finalizado");
        }
    }
}