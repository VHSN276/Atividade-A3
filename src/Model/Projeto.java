package Model;


import Model.Equipe;
import Model.Usuario;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Projeto {
    private String nome;
    private LocalDate dataInicio;
    private LocalDate dataFim;
    private Usuario responsavel;
    private List<Equipe> equipes;
    private StatusProjeto status;

    public Projeto(String nome, LocalDate dataInicio, LocalDate dataFim, Usuario responsavel){
        this.nome = nome;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.responsavel = responsavel;
        this.equipes = new ArrayList<>();

    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDate getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(LocalDate dataInicio) {
        this.dataInicio = dataInicio;
    }

    public LocalDate getDataFim() {
        return dataFim;
    }

    public void setDataFim(LocalDate dataFim) {
        this.dataFim = dataFim;
    }

    public Usuario getResponsavel() {
        return responsavel;
    }

    public void setResponsavel(Usuario responsavel) {
        this.responsavel = responsavel;
    }

    public List<Equipe> getEquipes() {
        return equipes;
    }

    public void setEquipes(List<Equipe> equipes) {
        this.equipes = equipes;
    }

    public StatusProjeto getStatus() {
        return status;
    }

    public void setStatus(StatusProjeto status) {
        this.status = status;
    }

    public void alterarStatus(StatusProjeto novoStatus){
        if (novoStatus != null) {
            this.status = novoStatus;
        }
    }


    public boolean adicionarEquipe(Equipe equipe) {
        return equipes.add(equipe);
    }

}
