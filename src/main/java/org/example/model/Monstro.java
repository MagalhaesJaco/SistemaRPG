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
        setNivel(10);
        setForca(forca);
        setAgilidade(agilidade);
        setVigor(vigor);
        setIntelecto(intelecto);
        setPresenca(presenca);
        setVidaMax(calculoVidaMax());
        setVidaAtual(vidaMax);

    }

    public String getResumoPersonagem() {
        if (vidaMax == null) calculoVidaMax();
        if (manaMax == null) calculoManaMax();

        // Aqui você pode usar um campo separado, se tiver controle de vida dinâmica


        String barraVida = gerarBarra(getVidaAtual(), getVidaMax(), 20);

        return String.format("""
                        ===== Personagem =====
                        Nome: %s
                        vida: %d/%d %s
                        Raça: %s
                        Usando item: %s
                        ======================
                        """,
                nome,
                getVidaAtual(), getVidaMax(), barraVida,
                raca,
                itemDeUso != null ? itemDeUso.getNome() : "Nenhum"
        );
    }

    private String gerarBarra(int atual, int max, int tamanho) {
        int preenchido = (int) ((double) atual / max * tamanho);
        int vazio = tamanho - preenchido;
        return "[" + "█".repeat(preenchido) + "░".repeat(vazio) + "]";
    }

    public Integer calculoVidaMax() {
        int vigor = getVigor() != null ? getVigor() : 0;
        int bonusRaca = raca.getVigorBonus();


        vidaMax = (vigor + bonusRaca) * 3;

        return vidaMax;
    }
}


