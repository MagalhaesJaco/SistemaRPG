package org.example.informacoesPersonagem;

import lombok.*;
import org.example.model.Calculos;
import org.example.model.Dados;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Personagem extends Status implements Calculos  {

    // Definiçoes de atributos ! //

    // Gets e sets ! //
    @Getter
    private String nome;
    private Raca raca;
    private ItemDeUso itemDeUso;
    @Setter
    private ListaHabilidades habilidade;
    @Getter
    private Classe classe;
    private Status status;
    private Integer vidaMax = 0;
    private Integer manaMax = 0;
    private final List<Integer> vidaPorNivel = new ArrayList<>();

    // Construtores ! //

    public Personagem (Integer nivel, Integer forca, Integer agilidade, Integer vigor, Integer intelecto, Integer presenca, String nome, ItemDeUso itemDeUso, Raca raca, Classe classe, ListaHabilidades habilidade) {
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

    public void setHabilidades(ListaHabilidades habilidade) {
    }

    // Criação dos dados de rolagem ! //
    Dados random = new Dados();

    public ListaHabilidades getHabilidades() {
        return habilidade;
    }

    // Calculo de vida maxima e melhorias por nivel ! //

    public Integer calculoVidaMax() {
        int vigor = getVigor() != null ? getVigor() : 0;
        int vidaAdicional = vidaPorNivel.stream().mapToInt(Integer::intValue).sum();
        int bonusClasse = getClasse().getVigorBonus();
        int bonusRaca = getRaca().getVigorBonus();

        switch(getClasse()){
            case Mago, Feiticeiro ->{
                vidaMax = (vigor + bonusClasse) + bonusRaca + 6;
            }
            case Guerreiro, Paladino -> {
                vidaMax = (vigor + bonusClasse) + bonusRaca + 10;
            }
            case Arqueiro, Ladino, Druida -> {
                vidaMax = (vigor + bonusClasse) + bonusRaca + 8;
            }
            case Barbaro -> {
                vidaMax = (vigor + bonusClasse) + bonusRaca + 12;
            }
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
            case Barbaro -> {while (vidaPorNivel.size() < (nivel - 1)) {
                vidaPorNivel.add(random.getD12());
            }}
        }
    }

    public Integer calculoManaMax() {
        int intelecto = getIntelecto() != null ? getIntelecto() : 0;
        int bonusClasse = getClasse().getIntelectoBonus();
        int bonusRaca = getRaca().getIntelectoBonus();

        switch(getClasse()){
            case Mago, Feiticeiro ->{
                manaMax = (intelecto + bonusClasse) + bonusRaca + 6;
            }
            case Guerreiro, Paladino -> {
                manaMax = (intelecto + bonusClasse) + bonusRaca + 10;
            }
            case Arqueiro, Ladino, Druida -> {
                manaMax = (intelecto + bonusClasse) + bonusRaca + 8;
            }
            case Barbaro -> {
                manaMax = (intelecto + bonusClasse) + bonusRaca + 12;
            }
        }

        atualizarVidaPorNivel();
        return manaMax + bonusRaca ;
    }

    // Metodos auxilires ! //

    public void verFicha(Personagem personagem){
        System.out.println("Você está usando o: " + personagem.getNome() + "\n");
        System.out.println("# Ficha " + personagem.getNome() + " #");
        System.out.println(" Vida: " + personagem.calculoVidaMax());
        System.out.println(" Raça: " + personagem.getRaca());
        System.out.println(" Está usando: " + personagem.getItemDeUso().getNome());
        System.out.println(" Classe: " + personagem.getClasse());
        System.out.println("# Status #");
        System.out.println(" Força: " + (personagem.getForca() + personagem.getClasse().getForcaBonus() + personagem.getRaca().getForcaBonus()));
        System.out.println(" Agilidade: " + (personagem.getAgilidade() + personagem.getClasse().getAgilidadeBonus() + personagem.getRaca().getAgilidadeBonus()));
        System.out.println(" Vigor: " + (personagem.getVigor() + personagem.getClasse().getVigorBonus() + personagem.getRaca().getVigorBonus()));
        System.out.println(" Intelecto: " + (personagem.getIntelecto() + personagem.getClasse().getIntelectoBonus() + personagem.getRaca().getIntelectoBonus()));
        System.out.println(" Presença: " + (personagem.getPresenca() + personagem.getClasse().getPresencaBonus() + personagem.getRaca().getPresencaBonus()));
    }
}

