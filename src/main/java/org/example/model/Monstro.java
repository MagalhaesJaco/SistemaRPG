package org.example.model;

import lombok.EqualsAndHashCode;
import org.example.informacoesPersonagem.*;

import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
public class Monstro extends Personagem{


    private String nome;
    private Raca raca;
    private ItemDeUso itemDeUso;
    private ListaHabilidades habilidade;
    private Classe classe;
    private Status status;
    private Integer vidaMax = 0;
    private Integer manaMax = 0;
    private final List<Integer> vidaPorNivel = new ArrayList<>();

    // Exemplo de criação de 10 monstros com esse construtor:

    Monstro lobo = new Monstro(
            1, 5, 4, 3, 1, 2,
            "Lobo",
            ItemDeUso.GarraDeFera,
            Raca.Fera,
            new ListaHabilidades()
    );

    Monstro esqueleto = new Monstro(
            2, 4, 3, 2, 2, 1,
            "Esqueleto",
            ItemDeUso.OSSADA,
            Raca.MortoVivo,
            new ListaHabilidades()
    );

    Monstro goblin = new Monstro(
            1, 3, 5, 2, 2, 2,
            "Goblin",
            ItemDeUso.FACA_RUSTICA,
            Raca.Humanoide,
            new ListaHabilidades()
    );

    Monstro ogro = new Monstro(
            3, 8, 2, 6, 1, 1,
            "Ogro",
            ItemDeUso.PEDRA_GRANDE,
            Raca.Gigante,
            new ListaHabilidades()
    );

    Monstro aranhaGigante = new Monstro(
            2, 4, 6, 3, 2, 1,
            "Aranha Gigante",
            ItemDeUso.VENENO_DE_ARANHA,
            Raca.Insetoide,
            new ListaHabilidades()
    );

    Monstro zumbi = new Monstro(
            1, 5, 2, 5, 1, 1,
            "Zumbi",
            ItemDeUso.CARNE_PODRE,
            Raca.MortoVivo,
            new ListaHabilidades()
    );

    Monstro dragaoFilhote = new Monstro(
            5, 9, 5, 6, 4, 5,
            "Dragão Filhote",
            ItemDeUso.ESCAMA_DE_DRAGAO,
            Raca.Draconico,
            new ListaHabilidades()
    );

    Monstro serpente = new Monstro(
            2, 3, 7, 2, 3, 2,
            "Serpente",
            ItemDeUso.VENENO_CONCENTRADO,
            Raca.Reptil,
            new ListaHabilidades()
    );

    Monstro golemDePedra = new Monstro(
            4, 10, 1, 8, 1, 1,
            "Golem de Pedra",
            ItemDeUso.FRAGMENTO_DE_PEDRA,
            Raca.Construto,
            new ListaHabilidades()
    );

    Monstro espectro = new Monstro(
            3, 2, 8, 2, 7, 5,
            "Espectro",
            ItemDeUso.ESSENCIA_SOMBRIA,
            Raca.Espirito,
            new ListaHabilidades()
    );


    public Monstro(){};
    public Monstro(Integer nivel, Integer forca, Integer agilidade, Integer vigor, Integer intelecto, Integer presenca, String nome, ItemDeUso itemDeUso, Raca raca, ListaHabilidades habilidade) {
        setNome(nome);
        setRaca(raca);
        setHabilidades(habilidade);
        setItemDeUso(itemDeUso);
        setNivel(nivel);
        setForca(forca);
        setAgilidade(agilidade);
        setVigor(vigor);
        setIntelecto(intelecto);
        setPresenca(presenca);

    }

};

