package Controller;

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

    public void  cadastrarUsuario(String nome, String cpf, String cargo, String login, String senha){
        Usuario usuario = new Usuario(nome, cpf, cargo, login, senha);
        listaDeUsuarios.add(usuario);
        usuarioView.exibirMensagem("Usuario "+usuario.getNome()+" cadastrado com sucesso!");
    }

    public void listarUsuarios(){
        usuarioView.exibirUsuarios(listaDeUsuarios);
    }
}
