package org.example.monstro;

import lombok.*;
import org.example.atributos.ItemDeUso;
import org.example.atributos.ListaHabilidades;
import org.example.atributos.Raca;
import org.example.atributos.Status;
import org.example.atributos.Classe;
import org.example.personagem.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Data
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

    public Monstro() throws SQLException {
    }

    public Monstro(String nome, ItemDeUso itemDeUso, Raca raca) throws SQLException {
        setNome(nome);
        setRaca(raca);
        setItemDeUso(itemDeUso);
        setNivel(10);
        setVidaMax(calculoVidaMax());
        setVidaAtual(vidaMax);
        setDefence((2) + 10);

    }

    public String getResumoPersonagem() {
        if (vidaMax == null) calculoVidaMax();
        if (manaMax == null) calculoManaMax();

        // Aqui você pode usar um campo separado, se tiver controle de vida dinâmica


        String barraVida = gerarBarra(getVidaAtual(), getVidaMax(), 20);

        return String.format("""
                        ===== Monstro =====
                        Nome: %s
                        vida: %d/%d %s
                        Raça: %s
                        Usando item: %s
                        ======================
                        """,
                nome,
                getVidaAtual(), getVidaMax(), barraVida,
                raca.getNome(),
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

    public int getForcaTotalMonstro() {
        return getForca() + raca.getForcaBonus();
    }

    public int getAgilidadeTotalMonstro() {
        return getAgilidade() + raca.getAgilidadeBonus();
    }

    public int getVigorTotalMonstro() {
        return getVigor() + raca.getVigorBonus();
    }

    public int getIntelectoTotalMonstro() {
        return getIntelecto() + raca.getIntelectoBonus();
    }

    public int getPresencaTotalMonstro() {
        return getPresenca() + raca.getPresencaBonus();
    }


    }


