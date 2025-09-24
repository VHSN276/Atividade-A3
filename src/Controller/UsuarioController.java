package Controller;
import java.util.Iterator;
import Model.Usuario;
import View.UsuarioView;

import java.util.List;

public class UsuarioController {
    private List<Usuario> listaDeUsuarios;
    private UsuarioView usuarioView;

    public UsuarioController(List<Usuario> listaDeUsuarios, UsuarioView usuarioView) {
        this.listaDeUsuarios = listaDeUsuarios;
        this.usuarioView = usuarioView;
    }


    public void cadastrarUsuario(String nome, String cpf, String cargo, String login, String senha){
        try {
            // Validações básicas
            if (nome == null || nome.trim().isEmpty()) {
                usuarioView.exibirMensagem("Erro: Nome não pode estar vazio!");
                return;
            }
            if (cpf == null || cpf.trim().isEmpty()) {
                usuarioView.exibirMensagem("Erro: CPF não pode estar vazio!");
                return;
            }
            if (cargo == null || cargo.trim().isEmpty()) {
                usuarioView.exibirMensagem("Erro: Cargo não pode estar vazio!");
                return;
            }
            if (login == null || login.trim().isEmpty()) {
                usuarioView.exibirMensagem("Erro: Login não pode estar vazio!");
                return;
            }
            if (senha == null || senha.trim().isEmpty()) {
                usuarioView.exibirMensagem("Erro: Senha não pode estar vazia!");
                return;
            }

            boolean flag_cpf_cadastrado = false;
            for (Usuario u : listaDeUsuarios){
                if (cpf.equals(u.getCpf())){
                    System.out.println("Usuário com esse CPF já cadastrado");
                    flag_cpf_cadastrado = true;
                    break;
                }
            }
            if(flag_cpf_cadastrado == false){
                Usuario usuario = new Usuario(nome, cpf, cargo, login, senha);
                listaDeUsuarios.add(usuario);
                usuarioView.exibirMensagem("Usuario "+usuario.getNome()+" cadastrado com sucesso!");
            }
        } catch (NullPointerException e) {
            usuarioView.exibirMensagem("Erro: Dados nulos fornecidos - " + e.getMessage());
        } catch (Exception e) {
            usuarioView.exibirMensagem("Erro ao cadastrar usuário: " + e.getMessage());
        }
    }

    public void excluirUsuario(String cpf) {
        try {
            if (cpf == null || cpf.trim().isEmpty()) {
                usuarioView.exibirMensagem("Erro: CPF não pode estar vazio!");
                return;
            }

            if (listaDeUsuarios == null || listaDeUsuarios.isEmpty()) {
                usuarioView.exibirMensagem("Erro: Lista de usuários está vazia!");
                return;
            }

            int flag_founded_user = 0;

            // 1. Crie um Iterator para a sua lista
            Iterator<Usuario> iterator = listaDeUsuarios.iterator();

            // 2. Use um loop 'while' com o iterator
            while (iterator.hasNext()) {
                Usuario usuario = iterator.next();
                if (usuario != null && usuario.getCpf() != null && usuario.getCpf().equals(cpf)) {
                    usuarioView.exibirMensagem("Usuario " + usuario.getNome() + " excluido com sucesso!");
                    iterator.remove();
                    flag_founded_user = 1;
                    break;
                }
            }

            if (flag_founded_user == 0) {
                System.out.println("Usuário não encontrado!");
            }
        } catch (NullPointerException e) {
            usuarioView.exibirMensagem("Erro: Referência nula encontrada - " + e.getMessage());
        } catch (Exception e) {
            usuarioView.exibirMensagem("Erro ao excluir usuário: " + e.getMessage());
        }
    }

    public void listarUsuarios(){
        try {
            if (listaDeUsuarios == null) {
                usuarioView.exibirMensagem("Erro: Lista de usuários não foi inicializada!");
                return;
            }
            usuarioView.exibirUsuarios(listaDeUsuarios);
        } catch (Exception e) {
            usuarioView.exibirMensagem("Erro ao listar usuários: " + e.getMessage());
        }
    }

    public List<Usuario> getUsuarios() {
        try {
            return listaDeUsuarios;
        } catch (Exception e) {
            System.out.println("Erro ao obter lista de usuários: " + e.getMessage());
            return null;
        }
    }
}