import Controller.EquipeController;
import Controller.ProjetoController;
import Controller.UsuarioController;
import Model.Equipe;
import Model.Projeto;
import Model.StatusProjeto;
import Model.Usuario;
import View.EquipeView;
import View.ProjetoView;
import View.UsuarioView;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        List<Usuario> bancoDadosLocal = new ArrayList<>();
        List<Equipe> bancoDadosLocalEquipe = new ArrayList<>();
        List<Projeto> bancoDadosLocalProjeto = new ArrayList<>();

        UsuarioView usuarioView = new UsuarioView();
        UsuarioController usuarioController = new UsuarioController(bancoDadosLocal, usuarioView);
        EquipeView equipeView = new EquipeView();
        EquipeController equipeController = new EquipeController(equipeView, bancoDadosLocalEquipe);
        ProjetoView projetoView = new ProjetoView();
        ProjetoController projetoController = new ProjetoController(projetoView, bancoDadosLocalProjeto);
        Scanner scanner = new Scanner(System.in);
        String opcao = "";
        String opcaoMenu = "";

        while (true) {
            System.out.println("\nMENU PRINCIPAL\n[1] Gerenciar Usuários\n[2] Gerenciar Projetos\n[3] Gerenciar Equipes\n[4] Sair\nSelecione uma opção: ");
            opcaoMenu = scanner.nextLine();
            switch (opcaoMenu) {
                case "1": //Usuarios Menu
                    opcao = ""; // <-- SOLUÇÃO: Reseta a variável aqui
                    while (!opcao.equals("0")) {
                        usuarioView.exibirMenu();
                        opcao = scanner.nextLine();
                        switch (opcao) {
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
                            case "0":
                                System.out.println("Retornando ao menu principal...");
                                break;
                            default:
                                System.out.println("Opção inválida. Tente novamente!");
                                break;
                        }
                    }
                    break;
                case "2": //Projetos Menu
                    opcao = ""; // <-- SOLUÇÃO: Reseta a variável aqui
                    while (!opcao.equals("0")) {
                        projetoView.exibirMenu();
                        opcao = scanner.nextLine();
                        switch (opcao) {
                            case "1":
                                projetoController.exibirProjetos();
                                break;
                            case "2":
                                System.out.print("Nome do projeto: ");
                                String nomeP = scanner.nextLine();
                                System.out.print("Data início (YYYY-MM-DD): ");
                                LocalDate inicio = LocalDate.parse(scanner.nextLine());
                                System.out.print("Data fim (YYYY-MM-DD): ");
                                LocalDate fim = LocalDate.parse(scanner.nextLine());
                                System.out.print("CPF do Responsável: ");
                                String cpfResp = scanner.nextLine();

                                Usuario resp = usuarioController.getUsuarios().stream()
                                        .filter(u -> u.getCpf().equals(cpfResp))
                                        .findFirst()
                                        .orElse(null);

                                if (resp != null) {
                                    projetoController.cadastrarProjeto(nomeP, inicio, fim, resp);
                                } else {
                                    ProjetoView.exibirMensagem("  Responsável não encontrado.");
                                }
                                break;
                            case "3":
                                System.out.print("Nome do projeto: ");
                                String nomeProjetoStatus = scanner.nextLine();
                                System.out.println("Escolha o novo status: ");
                                System.out.println("1 - PLANEJADO");
                                System.out.println("2 - EM_ANDAMENTO");
                                System.out.println("3 - CONCLUIDO");
                                String statusOpcao = scanner.nextLine();

                                StatusProjeto novoStatus = null;
                                switch (statusOpcao) {
                                    case "1":
                                        novoStatus = StatusProjeto.PLANEJADO;
                                        break;
                                    case "2":
                                        novoStatus = StatusProjeto.EM_ANDAMENTO;
                                        break;
                                    case "3":
                                        novoStatus = StatusProjeto.CONCLUIDO;
                                        break;
                                    default:
                                        ProjetoView.exibirMensagem("Opção de status inválida. ");
                                        break;
                                }
                                if (novoStatus != null) {
                                    projetoController.alterarStatusProjeto(nomeProjetoStatus, novoStatus);
                                }
                                break;

                            case "4":
                                System.out.print("Nome do projeto: ");
                                String nomeProjeto = scanner.nextLine();
                                System.out.print("Nome da equipe: ");
                                String equipeNome = scanner.nextLine();

                                Equipe equipe = equipeController.getEquipes().stream()
                                        .filter(e -> e.getNome().equalsIgnoreCase(equipeNome))
                                        .findFirst()
                                        .orElse(null);

                                if (equipe != null) {
                                    projetoController.adicionarEquipe(nomeProjeto, equipe);
                                } else {
                                    ProjetoView.exibirMensagem("  Equipe '" + equipeNome + "' não encontrada.");
                                }
                                break;
                            case "0":
                                System.out.println("Retornando ao menu principal...");
                                break;
                            default:
                                System.out.println("Opção inválida. Tente novamente.");
                                break;
                        }
                    }
                    break;
                case "3": //Equipe Menu
                    opcao = ""; // <-- SOLUÇÃO: Reseta a variável aqui
                    while (!opcao.equals("0")) {
                        equipeView.exibirMenu();
                        opcao = scanner.nextLine();
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
                                System.out.print("Nome da equipe para remover o usuário: ");
                                String nomeEquipeRemover = scanner.nextLine();
                                System.out.print("CPF do usuário a ser removido: ");
                                String cpfRemover = scanner.nextLine();
                                equipeController.removerUsuarioDaequipe(nomeEquipeRemover, cpfRemover);
                                break;
                            case "5":
                                equipeView.exibirDetalhesEquipes(bancoDadosLocalEquipe);
                                break;
                            case "0":
                                System.out.println("Retornando ao menu principal...");
                                break;
                            default:
                                System.out.println("Opção inválida. Tente novamente.");
                                break;
                        }
                    }
                    break;
                case "4":
                    System.out.println("Saindo do sistema...");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente!");
                    break;
            }
            if (opcaoMenu.equals("4")) {
                break;
            }
        }
        scanner.close();
        System.out.println("Programa Finalizado");
    }
}