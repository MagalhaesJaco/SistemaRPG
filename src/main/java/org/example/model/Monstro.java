package org.example.model;

import lombok.*;
import org.example.informacoesPersonagem.*;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Monstro extends Personagem {

    @Getter
    private String nome;
    private Raca raca;
    private ItemDeUso itemDeUso;
    private ListaHabilidades habilidade;
    private Classe classe;
    private Status status;
    @Setter
    private Integer vidaMax = 0;
    private Integer manaMax = 0;
    private final List<Integer> vidaPorNivel = new ArrayList<>();

    public Monstro() {
    }
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

}


