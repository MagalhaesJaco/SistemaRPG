package org.example.informaçoesPersonagem;

import org.example.model.Calculos;
import org.example.model.Dados;

import java.util.ArrayList;
import java.util.List;

public class Personagens extends Status implements Calculos  {
    private String nome;
    private Raca raca;
    private ItemDeUso itemDeUso;
    private ListaHabilidades habilidade;
    private Classe classe;
    private Status status;
    private Integer vidaMax = 0;
    private final List<Integer> vidaPorNivel = new ArrayList<>();


    public Personagens(){};
    public Personagens(Integer nivel, Integer forca, Integer agilidade, Integer vigor, Integer intelecto, Integer presenca, String nome, ItemDeUso itemDeUso, Raca raca, Classe classe, ListaHabilidades habilidade) {
        setNome(nome);
        setRaca(raca);
        setHabilidades(habilidade);
        setClasse(classe);
        setItemDeUso(itemDeUso);
        setNivel(nivel);

        setForca(forca);
        setAgilidade(agilidade);
        setVigor(vigor);
        setIntelecto(intelecto);
        setPresenca(presenca);

    }

    Dados random = new Dados();


    public String getNome() {
        return nome;
    }

    public ListaHabilidades getHabilidades() {
        return habilidade;
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
        int vigor = getVigor() != null ? getVigor() : 0;
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
        int nivel = getNivel() != null ? getNivel() : 1;
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

    public void verFicha(Personagens personagens){
        System.out.println("Você está usando o: " + personagens.getNome() + "\n");
        System.out.println("# Ficha " + personagens.getNome() + " #");
        System.out.println(" Vida: " + personagens.calculoVidaMax());
        System.out.println(" Raça: " + personagens.getRaca());
        System.out.println(" Está usando: " + personagens.getItemDeUso().getNome());
        System.out.println(" Classe: " + personagens.getClasse());
        System.out.println("# Status #");
        System.out.println(" Força: " + (personagens.getForca() + personagens.getClasse().getForcaBonus() + personagens.getRaca().getForcaBonus()));
        System.out.println(" Agilidade: " + (personagens.getAgilidade() + personagens.getClasse().getAgilidadeBonus() + personagens.getRaca().getAgilidadeBonus()));
        System.out.println(" Vigor: " + (personagens.getVigor() + personagens.getClasse().getVigorBonus() + personagens.getRaca().getVigorBonus()));
        System.out.println(" Intelecto: " + (personagens.getIntelecto() + personagens.getClasse().getIntelectoBonus() + personagens.getRaca().getIntelectoBonus()));
        System.out.println(" Presença: " + (personagens.getPresenca() + personagens.getClasse().getPresencaBonus() + personagens.getRaca().getPresencaBonus()));
    }
}

