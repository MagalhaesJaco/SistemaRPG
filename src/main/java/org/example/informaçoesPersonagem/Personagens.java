package org.example.informaçoesPersonagem;

import org.example.model.Calculos;
import org.example.model.Dados;

public class Personagens implements Calculos {
    private String nome;
    private Raca raca;
    private ItemDeUso itemDeUso;
    private ListaHabilidades habilidade;
    private Classe classe;
    private Status status;


    public Personagens( String nome, ItemDeUso itemDeUso, Raca raca, Classe classe, ListaHabilidades habilidade, Status status) {
        setStatus(status);
        setNome(nome);
        setRaca(raca);
        setHabilidades(habilidade);

        setClasse(classe);
        setItemDeUso(itemDeUso);
    }
    Dados random = new Dados();


    public String getNome() {
        return nome;
    }

    public ListaHabilidades getHabilidades() {
        return habilidade;
    }

    public Status getStatus() {
        return status;
    }

    public Classe getClasse() {
        return classe;
    }

    public Raca getRaca() {
        return raca;
    }


    public ItemDeUso getItemDeUso() {
        return itemDeUso;
    }

    public Integer calculoVidaMax(){
        int vigor = getStatus().getVigor() != null ? getStatus().getVigor() : 0;

        Integer vidaMax = 0;
        if(classe == Classe.Mago){
            if(getStatus().getNivel() == 1){
                vidaMax = vigor + 6;
            }
        }
        return  vidaMax;
    }
    public void setClasse(Classe classe) {
        this.classe = classe;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setRaca(Raca raca) {
        this.raca = raca;
    }

    public void setHabilidades(ListaHabilidades habilidades) {
        this.habilidade = habilidades;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void setItemDeUso(ItemDeUso itemDeUso) {
        this.itemDeUso = itemDeUso;

    }
}