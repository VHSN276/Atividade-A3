package Controller;
import java.util.Iterator;
import java.util.ConcurrentModificationException;
import Model.Usuario;
import View.UsuarioView;

import java.util.List;

public class UsuarioController {
    private List<Usuario> listaDeUsuarios;
    private UsuarioView usuarioView;

    public UsuarioController(List<Usuario> listaDeUsuarios, UsuarioView usuarioView) {
        try {
            if (listaDeUsuarios == null) {
                throw new IllegalArgumentException("Lista de usuários não pode ser nula");
            }
            if (usuarioView == null) {
                throw new IllegalArgumentException("UsuarioView não pode ser nula");
            }
            this.listaDeUsuarios = listaDeUsuarios;
            this.usuarioView = usuarioView;
        } catch (Exception e) {
            System.err.println("Erro ao inicializar UsuarioController: " + e.getMessage());
            throw e;
        }
    }

    public void cadastrarUsuario(String nome, String cpf, String cargo, String login, String senha){
        try {
            // Validações de entrada
            if (nome == null || nome.trim().isEmpty()) {
                throw new IllegalArgumentException("Nome não pode ser vazio");
            }
            if (cpf == null || cpf.trim().isEmpty()) {
                throw new IllegalArgumentException("CPF não pode ser vazio");
            }
            if (cargo == null || cargo.trim().isEmpty()) {
                throw new IllegalArgumentException("Cargo não pode ser vazio");
            }
            if (login == null || login.trim().isEmpty()) {
                throw new IllegalArgumentException("Login não pode ser vazio");
            }
            if (senha == null || senha.trim().isEmpty()) {
                throw new IllegalArgumentException("Senha não pode ser vazia");
            }

            boolean flag_cpf_cadastrado = false;
            try {
                for (Usuario u : listaDeUsuarios){
                    if (u != null && cpf.equals(u.getCpf())){
                        System.out.println("Usuário com esse CPF já cadastrado");
                        flag_cpf_cadastrado = true;
                        break;
                    }
                }
            } catch (ConcurrentModificationException e) {
                System.err.println("Erro de concorrência ao verificar CPF: " + e.getMessage());
                return;
            } catch (NullPointerException e) {
                System.err.println("Erro: usuário nulo encontrado na lista: " + e.getMessage());
                return;
            }

            if(flag_cpf_cadastrado == false){
                try {
                    Usuario usuario = new Usuario(nome.trim(), cpf.trim(), cargo.trim(), login.trim(), senha.trim());
                    listaDeUsuarios.add(usuario);
                    usuarioView.exibirMensagem("Usuario "+usuario.getNome()+" cadastrado com sucesso!");
                } catch (Exception e) {
                    System.err.println("Erro ao criar ou adicionar usuário: " + e.getMessage());
                }
            }
        } catch (IllegalArgumentException e) {
            System.err.println("Erro de validação: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Erro inesperado ao cadastrar usuário: " + e.getMessage());
        }
    }

    public void excluirUsuario(String cpf) {
        try {
            if (cpf == null || cpf.trim().isEmpty()) {
                throw new IllegalArgumentException("CPF não pode ser vazio");
            }

            int flag_founded_user = 0;

            try {
                // 1. Crie um Iterator para a sua lista
                Iterator<Usuario> iterator = listaDeUsuarios.iterator();

                // 2. Use um loop 'while' com o iterator
                while (iterator.hasNext()) {
                    Usuario usuario = iterator.next();
                    if (usuario != null && usuario.getCpf() != null && usuario.getCpf().equals(cpf.trim())) {
                        usuarioView.exibirMensagem("Usuario " + usuario.getNome() + " excluido com sucesso!");
                        iterator.remove();
                        flag_founded_user = 1;
                        break;
                    }
                }
            } catch (ConcurrentModificationException e) {
                System.err.println("Erro de concorrência ao excluir usuário: " + e.getMessage());
                return;
            } catch (NullPointerException e) {
                System.err.println("Erro: dados nulos encontrados: " + e.getMessage());
                return;
            } catch (Exception e) {
                System.err.println("Erro inesperado durante a exclusão: " + e.getMessage());
                return;
            }

            if (flag_founded_user == 0) {
                System.out.println("Usuário não encontrado!");
            }
        } catch (IllegalArgumentException e) {
            System.err.println("Erro de validação: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Erro inesperado ao excluir usuário: " + e.getMessage());
        }
    }

    public void listarUsuarios(){
        try {
            if (listaDeUsuarios == null) {
                System.err.println("Lista de usuários não inicializada");
                return;
            }
            usuarioView.exibirUsuarios(listaDeUsuarios);
        } catch (NullPointerException e) {
            System.err.println("Erro: referência nula ao listar usuários: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Erro inesperado ao listar usuários: " + e.getMessage());
        }
    }

    public List<Usuario> getUsuarios() {
        try {
            return listaDeUsuarios;
        } catch (Exception e) {
            System.err.println("Erro ao obter lista de usuários: " + e.getMessage());
            return null;
        }
    }
}