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
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.InputMismatchException;

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

        try {
            while (true) {
                try {
                    System.out.println("\nMENU PRINCIPAL\n[1] Gerenciar Usuários\n[2] Gerenciar Projetos\n[3] Gerenciar Equipes\n[4] Sair\nSelecione uma opção: ");
                    opcaoMenu = scanner.nextLine();

                    switch (opcaoMenu) {
                        case "1": //Usuarios Menu
                            opcao = ""; // <-- SOLUÇÃO: Reseta a variável aqui
                            while (!opcao.equals("0")) {
                                try {
                                    usuarioView.exibirMenu();
                                    opcao = scanner.nextLine();
                                    switch (opcao) {
                                        case "1": //Exibir usuarios cadastrados
                                            usuarioController.listarUsuarios();
                                            break;
                                        case "2": //Cadastro de usuario
                                            try {
                                                System.out.println("Nome: ");
                                                String nome = scanner.nextLine();
                                                if (nome == null || nome.trim().isEmpty()) {
                                                    System.out.println("Erro: Nome não pode estar vazio!");
                                                    break;
                                                }

                                                System.out.println("CPF: ");
                                                String cpf = scanner.nextLine();
                                                if (cpf == null || cpf.trim().isEmpty()) {
                                                    System.out.println("Erro: CPF não pode estar vazio!");
                                                    break;
                                                }

                                                System.out.println("Cargo (Admin/Gerente/Comum): ");
                                                String cargo = scanner.nextLine();
                                                if (cargo == null || cargo.trim().isEmpty()) {
                                                    System.out.println("Erro: Cargo não pode estar vazio!");
                                                    break;
                                                }

                                                System.out.println("Login: ");
                                                String login = scanner.nextLine();
                                                if (login == null || login.trim().isEmpty()) {
                                                    System.out.println("Erro: Login não pode estar vazio!");
                                                    break;
                                                }

                                                System.out.println("Senha: ");
                                                String senha = scanner.nextLine();
                                                if (senha == null || senha.trim().isEmpty()) {
                                                    System.out.println("Erro: Senha não pode estar vazia!");
                                                    break;
                                                }

                                                usuarioController.cadastrarUsuario(nome, cpf, cargo, login, senha);
                                            } catch (Exception e) {
                                                System.out.println("Erro ao cadastrar usuário: " + e.getMessage());
                                            }
                                            break;
                                        case "3": //Exclusão de Usuario
                                            try {
                                                System.out.println("Digite o CPF do usuario que deseja excluir: ");
                                                String deleteCpf = scanner.nextLine();
                                                if (deleteCpf == null || deleteCpf.trim().isEmpty()) {
                                                    System.out.println("Erro: CPF não pode estar vazio!");
                                                    break;
                                                }
                                                usuarioController.excluirUsuario(deleteCpf);
                                            } catch (Exception e) {
                                                System.out.println("Erro ao excluir usuário: " + e.getMessage());
                                            }
                                            break;
                                        case "0":
                                            System.out.println("Retornando ao menu principal...");
                                            break;
                                        default:
                                            System.out.println("Opção inválida. Tente novamente!");
                                            break;
                                    }
                                } catch (Exception e) {
                                    System.out.println("Erro no menu de usuários: " + e.getMessage());
                                }
                            }
                            break;
                        case "2": //Projetos Menu
                            opcao = "";
                            while (!opcao.equals("0")) {
                                try {
                                    projetoView.exibirMenu();
                                    opcao = scanner.nextLine();
                                    switch (opcao) {
                                        case "1":
                                            try {
                                                projetoController.exibirProjetos();
                                            } catch (Exception e) {
                                                System.out.println("Erro ao exibir projetos: " + e.getMessage());
                                            }
                                            break;
                                        case "2":
                                            try {
                                                System.out.print("Nome do projeto: ");
                                                String nomeP = scanner.nextLine();
                                                if (nomeP == null || nomeP.trim().isEmpty()) {
                                                    System.out.println("Erro: Nome do projeto não pode estar vazio!");
                                                    break;
                                                }

                                                System.out.print("Data início (YYYY-MM-DD): ");
                                                String dataInicioStr = scanner.nextLine();
                                                LocalDate inicio;
                                                try {
                                                    inicio = LocalDate.parse(dataInicioStr);
                                                } catch (DateTimeParseException e) {
                                                    System.out.println("Erro: Formato de data inválido! Use YYYY-MM-DD");
                                                    break;
                                                }

                                                System.out.print("Data fim (YYYY-MM-DD): ");
                                                String dataFimStr = scanner.nextLine();
                                                LocalDate fim;
                                                try {
                                                    fim = LocalDate.parse(dataFimStr);
                                                } catch (DateTimeParseException e) {
                                                    System.out.println("Erro: Formato de data inválido! Use YYYY-MM-DD");
                                                    break;
                                                }

                                                System.out.print("CPF do Responsável: ");
                                                String cpfResp = scanner.nextLine();
                                                if (cpfResp == null || cpfResp.trim().isEmpty()) {
                                                    System.out.println("Erro: CPF do responsável não pode estar vazio!");
                                                    break;
                                                }

                                                Usuario resp = usuarioController.getUsuarios().stream()
                                                        .filter(u -> u.getCpf().equals(cpfResp))
                                                        .findFirst()
                                                        .orElse(null);

                                                if (resp != null) {
                                                    projetoController.cadastrarProjeto(nomeP, inicio, fim, resp);
                                                } else {
                                                    ProjetoView.exibirMensagem("  Responsável não encontrado.");
                                                }
                                            } catch (Exception e) {
                                                System.out.println("Erro ao cadastrar projeto: " + e.getMessage());
                                            }
                                            break;
                                        case "3":
                                            try {
                                                System.out.print("Nome do projeto: ");
                                                String nomeProjetoStatus = scanner.nextLine();
                                                if (nomeProjetoStatus == null || nomeProjetoStatus.trim().isEmpty()) {
                                                    System.out.println("Erro: Nome do projeto não pode estar vazio!");
                                                    break;
                                                }

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
                                            } catch (Exception e) {
                                                System.out.println("Erro ao alterar status do projeto: " + e.getMessage());
                                            }
                                            break;

                                        case "4":
                                            try {
                                                System.out.print("Nome do projeto: ");
                                                String nomeProjeto = scanner.nextLine();
                                                if (nomeProjeto == null || nomeProjeto.trim().isEmpty()) {
                                                    System.out.println("Erro: Nome do projeto não pode estar vazio!");
                                                    break;
                                                }

                                                System.out.print("Nome da equipe: ");
                                                String equipeNome = scanner.nextLine();
                                                if (equipeNome == null || equipeNome.trim().isEmpty()) {
                                                    System.out.println("Erro: Nome da equipe não pode estar vazio!");
                                                    break;
                                                }

                                                Equipe equipe = equipeController.getEquipes().stream()
                                                        .filter(e -> e.getNome().equalsIgnoreCase(equipeNome))
                                                        .findFirst()
                                                        .orElse(null);

                                                if (equipe != null) {
                                                    projetoController.adicionarEquipe(nomeProjeto, equipe);
                                                } else {
                                                    ProjetoView.exibirMensagem("  Equipe '" + equipeNome + "' não encontrada.");
                                                }
                                            } catch (Exception e) {
                                                System.out.println("Erro ao adicionar equipe ao projeto: " + e.getMessage());
                                            }
                                            break;
                                        case "0":
                                            System.out.println("Retornando ao menu principal...");
                                            break;
                                        default:
                                            System.out.println("Opção inválida. Tente novamente.");
                                            break;
                                    }
                                } catch (Exception e) {
                                    System.out.println("Erro no menu de projetos: " + e.getMessage());
                                }
                            }
                            break;
                        case "3": //Equipe Menu
                            opcao = "";
                            while (!opcao.equals("0")) {
                                try {
                                    equipeView.exibirMenu();
                                    opcao = scanner.nextLine();
                                    switch (opcao) {
                                        case "1":
                                            try {
                                                equipeController.listarEquipes();
                                            } catch (Exception e) {
                                                System.out.println("Erro ao listar equipes: " + e.getMessage());
                                            }
                                            break;
                                        case "2":
                                            try {
                                                System.out.print("Nome da equipe: ");
                                                String nomeE = scanner.nextLine();
                                                if (nomeE == null || nomeE.trim().isEmpty()) {
                                                    System.out.println("Erro: Nome da equipe não pode estar vazio!");
                                                    break;
                                                }
                                                equipeController.cadastrarEquipe(nomeE);
                                            } catch (Exception e) {
                                                System.out.println("Erro ao cadastrar equipe: " + e.getMessage());
                                            }
                                            break;
                                        case "3":
                                            try {
                                                System.out.print("Nome da equipe: ");
                                                String nomeEquipe = scanner.nextLine();
                                                if (nomeEquipe == null || nomeEquipe.trim().isEmpty()) {
                                                    System.out.println("Erro: Nome da equipe não pode estar vazio!");
                                                    break;
                                                }

                                                System.out.print("CPF do usuário: ");
                                                String cpfUsuario = scanner.nextLine();
                                                if (cpfUsuario == null || cpfUsuario.trim().isEmpty()) {
                                                    System.out.println("Erro: CPF do usuário não pode estar vazio!");
                                                    break;
                                                }

                                                Usuario usuarioEncontrado = usuarioController.getUsuarios().stream()
                                                        .filter(u -> u.getCpf().equals(cpfUsuario))
                                                        .findFirst()
                                                        .orElse(null);

                                                if (usuarioEncontrado != null) {
                                                    equipeController.adicionarUsuario(nomeEquipe, usuarioEncontrado);
                                                } else {
                                                    equipeView.exibirMensagem(" Usuário não encontrado.");
                                                }
                                            } catch (Exception e) {
                                                System.out.println("Erro ao adicionar usuário à equipe: " + e.getMessage());
                                            }
                                            break;
                                        case "4":
                                            try {
                                                System.out.print("Nome da equipe para remover o usuário: ");
                                                String nomeEquipeRemover = scanner.nextLine();
                                                if (nomeEquipeRemover == null || nomeEquipeRemover.trim().isEmpty()) {
                                                    System.out.println("Erro: Nome da equipe não pode estar vazio!");
                                                    break;
                                                }

                                                System.out.print("CPF do usuário a ser removido: ");
                                                String cpfRemover = scanner.nextLine();
                                                if (cpfRemover == null || cpfRemover.trim().isEmpty()) {
                                                    System.out.println("Erro: CPF do usuário não pode estar vazio!");
                                                    break;
                                                }
                                                equipeController.removerUsuarioDaequipe(nomeEquipeRemover, cpfRemover);
                                            } catch (Exception e) {
                                                System.out.println("Erro ao remover usuário da equipe: " + e.getMessage());
                                            }
                                            break;
                                        case "5":
                                            try {
                                                equipeView.exibirDetalhesEquipes(bancoDadosLocalEquipe);
                                            } catch (Exception e) {
                                                System.out.println("Erro ao exibir detalhes das equipes: " + e.getMessage());
                                            }
                                            break;
                                        case "0":
                                            System.out.println("Retornando ao menu principal...");
                                            break;
                                        default:
                                            System.out.println("Opção inválida. Tente novamente.");
                                            break;
                                    }
                                } catch (Exception e) {
                                    System.out.println("Erro no menu de equipes: " + e.getMessage());
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
                } catch (Exception e) {
                    System.out.println("Erro no menu principal: " + e.getMessage());
                    System.out.println("Tente novamente.");
                }
            }
        } catch (Exception e) {
            System.out.println("Erro crítico no sistema: " + e.getMessage());
        } finally {
            try {
                scanner.close();
            } catch (Exception e) {
                System.out.println("Erro ao fechar scanner: " + e.getMessage());
            }
            System.out.println("Programa Finalizado");
        }
    }
}