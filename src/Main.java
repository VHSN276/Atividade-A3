import Controller.EquipeController;
import Controller.UsuarioController;
import Model.Equipe;
import Model.Usuario;
import View.EquipeView;
import View.UsuarioView;

import java.sql.SQLOutput;
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
        EquipeView equipeView = new EquipeView();
        EquipeController   equipeController = new EquipeController(equipeView);
        Scanner scanner = new Scanner(System.in);
        String opcao = "";
        String opcaoMenu = "";



        while(true){
            System.out.println("MENU\n[1]Usuarios\n[2]Projetos\n[3]Equipes\n[4]Sair\nSelecione uma opção: ");
            opcaoMenu = scanner.nextLine();
            switch (opcaoMenu){
                case "1": //Usuarios Menu
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
                                break;
                            case "3": //Exclusão de Usuario
                                System.out.println("Digite o CPF do usuario que deseja excluir: ");
                                String deleteCpf = scanner.nextLine();
                                usuarioController.excluirUsuario(deleteCpf);
                                break;
                            case "4":
                                break;
                            default:
                                System.out.println("Opção inválida. Tente novamente!");
                                break;
                        }
                    }
                    break;
                case "2": //Projetos Menu


                    break;
                case "3"://Equipe Menu
                    while(!opcao.equals("0")){
                        usuarioView.exibirMenu();
                        opcao = scanner.nextLine();
                    }
                    switch (opcao) {
                        case "1":
                            equipeController.listarEquipes();
                            break;
                        case "2":
                            System.out.print("Nome da equipe: ");
                            String nomeE = scanner.nextLine();
                            equipeController.cadastrarEquipe(nomeE);
                            break;
                        case "3":
                            System.out.print("Nome da equipe: ");
                            String nomeEquipe = scanner.nextLine();
                            System.out.print("CPF do usuário: ");
                            String cpfUsuario = scanner.nextLine();

                            Usuario usuarioEncontrado = usuarioController.getUsuarios().stream()
                                    .filter(u -> u.getCpf().equals(cpfUsuario))
                                    .findFirst()
                                    .orElse(null);

                            if (usuarioEncontrado != null) {
                                equipeController.adicionarUsuario(nomeEquipe, usuarioEncontrado);
                            } else {
                                equipeView.exibirMensagem(" Usuário não encontrado.");
                            }
                            break;
                        case "4":
                            System.out.print("Nome da equipe: ");
                            String nomeEquipeRemover = scanner.nextLine();
                            String cpfRemover = scanner.nextLine();
                            equipeController.removerUsuarioDaequipe(nomeEquipeRemover, cpfRemover);
                            break;
                    }

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