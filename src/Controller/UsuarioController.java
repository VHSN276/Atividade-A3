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

    
    public void  cadastrarUsuario(String nome, String cpf, String cargo, String login, String senha){
        boolean flag_cpf_cadastrado = false;
        for (Usuario u : listaDeUsuarios){
            if (cpf.equals(u.getCpf())){
                System.out.println("Usuário com esse CPF já cadastrado");
                flag_cpf_cadastrado = true;
            }
        }
        if(flag_cpf_cadastrado == false){
            Usuario usuario = new Usuario(nome, cpf, cargo, login, senha);
            listaDeUsuarios.add(usuario);
            usuarioView.exibirMensagem("Usuario "+usuario.getNome()+" cadastrado com sucesso!");
        }
    }

    public void excluirUsuario(String cpf) {
        int flag_founded_user = 0;

        // 1. Crie um Iterator para a sua lista
        Iterator<Usuario> iterator = listaDeUsuarios.iterator();

        // 2. Use um loop 'while' com o iterator
        while (iterator.hasNext()) {
            Usuario usuario = iterator.next();
            if (usuario.getCpf().equals(cpf)) {
                usuarioView.exibirMensagem("Usuario " + usuario.getNome() + " excluido com sucesso!");
                iterator.remove();
                flag_founded_user = 1;
                break;
            }
        }

        if (flag_founded_user == 0) {
            System.out.println("Usuário não encontrado!");
        }
    }

    public void listarUsuarios(){
        usuarioView.exibirUsuarios(listaDeUsuarios);
    }
    public List<Usuario> getUsuarios() {
        return listaDeUsuarios;
    }
}

