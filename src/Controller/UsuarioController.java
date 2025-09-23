package Controller;

import Model.Usuario;
import View.UsuarioView;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
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
            System.out.println("Erro ao inicializar UsuarioController: " + e.getMessage());
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
                // Verificação de CPF duplicado com tratamento para ConcurrentModificationException
                for (Usuario u : new ArrayList<>(listaDeUsuarios)){
                    if (u != null && cpf.equals(u.getCpf())){
                        System.out.println("Usuário com esse CPF já cadastrado");
                        flag_cpf_cadastrado = true;
                        break;
                    }
                }
            } catch (ConcurrentModificationException e) {
                System.out.println("Erro de concorrência ao verificar CPF: " + e.getMessage());
                return;
            } catch (NullPointerException e) {
                System.out.println("Erro: usuário nulo encontrado na lista: " + e.getMessage());
                return;
            }

            if (!flag_cpf_cadastrado) {
                try {
                    Usuario usuario = new Usuario(nome, cpf, cargo, login, senha);
                    listaDeUsuarios.add(usuario);
                    usuarioView.exibirMensagem("Usuario " + usuario.getNome() + " cadastrado com sucesso!");
                } catch (Exception e) {
                    System.out.println("Erro ao criar ou adicionar usuário: " + e.getMessage());
                }
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Erro de validação: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Erro inesperado ao cadastrar usuário: " + e.getMessage());
        }
    }

    public void excluirUsuario(String cpf){
        try {
            if (cpf == null || cpf.trim().isEmpty()) {
                throw new IllegalArgumentException("CPF não pode ser vazio");
            }

            boolean flag_founded_user = false;
            Usuario usuarioParaRemover = null;

            try {
                // Busca o usuário sem modificar a lista durante a iteração
                for (Usuario usuario : new ArrayList<>(listaDeUsuarios)) {
                    if (usuario != null && usuario.getCpf() != null && usuario.getCpf().equals(cpf)) {
                        usuarioParaRemover = usuario;
                        flag_founded_user = true;
                        break;
                    }
                }

                // Remove o usuário fora do loop de iteração
                if (flag_founded_user && usuarioParaRemover != null) {
                    listaDeUsuarios.remove(usuarioParaRemover);
                    usuarioView.exibirMensagem("Usuario " + usuarioParaRemover.getNome() + " excluido com sucesso!");
                } else {
                    System.out.println("Usuário não encontrado!");
                }
            } catch (ConcurrentModificationException e) {
                System.out.println("Erro de concorrência ao excluir usuário: " + e.getMessage());
            } catch (NullPointerException e) {
                System.out.println("Erro: dados nulos encontrados: " + e.getMessage());
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Erro de validação: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Erro inesperado ao excluir usuário: " + e.getMessage());
        }
    }

    public void listarUsuarios(){
        try {
            if (listaDeUsuarios == null) {
                System.out.println("Lista de usuários não inicializada");
                return;
            }
            usuarioView.exibirUsuarios(listaDeUsuarios);
        } catch (NullPointerException e) {
            System.out.println("Erro: referência nula ao listar usuários: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Erro inesperado ao listar usuários: " + e.getMessage());
        }
    }
}