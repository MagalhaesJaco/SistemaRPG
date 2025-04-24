package org.example.informaçoesPersonagem;

import org.example.model.Calculos;
import org.example.model.Dados;

import java.util.ArrayList;
import java.util.List;

public class Personagens implements Calculos {
    private String nome;
    private Raca raca;
    private ItemDeUso itemDeUso;
    private ListaHabilidades habilidade;
    private Classe classe;
    private Status status;
    private Integer vidaMax = 0;
    private final List<Integer> vidaPorNivel = new ArrayList<>();


    public Personagens(String nome, ItemDeUso itemDeUso, Raca raca, Classe classe, ListaHabilidades habilidade, Status status) {
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

    public Integer calculoVidaMax() {
        int vigor = getStatus().getVigor() != null ? getStatus().getVigor() : 0;
        int vidaAdicional = vidaPorNivel.stream().mapToInt(Integer::intValue).sum();
        int bonusClasse = getClasse().getVigorBonus();
        int bonusRaca = getRaca().getVigorBonus();

        if (getClasse() == Classe.Mago) {
            vidaMax = (vigor + bonusClasse) + bonusRaca + 6;
        } else if (getClasse() == Classe.Guerreiro) {
            vidaMax = (vigor + bonusClasse) + bonusRaca + 10;
        } else if (getClasse() == Classe.Arqueiro) {
            vidaMax = (vigor + bonusClasse) + bonusRaca + 8;
        } else if (getClasse() == Classe.Ladino) {
            vidaMax = (vigor + bonusClasse) + bonusRaca + 8;
        } else if (getClasse() == Classe.Paladino) {
            vidaMax = (vigor + bonusClasse) + bonusRaca + 10;
        } else if (getClasse() == Classe.Druida) {
            vidaMax = (vigor + bonusClasse) + bonusRaca + 8;
        } else if (getClasse() == Classe.Bárbaro){
            vidaMax = (vigor + bonusClasse) + bonusRaca + 12;
        } else if (getClasse() == Classe.Feiticeiro){
            vidaMax = (vigor + bonusClasse) + bonusRaca + 6;
        }
        atualizarVidaPorNivel();
        return vidaMax + bonusRaca + vidaAdicional;
    }

    public void atualizarVidaPorNivel() {
        int nivel = getStatus().getNivel() != null ? getStatus().getNivel() : 1;
        switch (classe) {
            case Mago, Feiticeiro -> {
                while (vidaPorNivel.size() < (nivel - 1)) {
                    vidaPorNivel.add(random.getD6());
                }
            }
            case Druida, Ladino, Arqueiro -> {
                while (vidaPorNivel.size() < (nivel - 1)) {
                    vidaPorNivel.add(random.getD8());
                }
            }
            case Guerreiro, Paladino -> {
                while (vidaPorNivel.size() < (nivel - 1)) {
                    vidaPorNivel.add(random.getD10());
                }
            }
            case Bárbaro -> {while (vidaPorNivel.size() < (nivel - 1)) {
                vidaPorNivel.add(random.getD12());
            }}
        }
    }
}

