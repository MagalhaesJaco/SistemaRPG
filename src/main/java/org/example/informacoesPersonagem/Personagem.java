package org.example.informacoesPersonagem;

import lombok.*;
import org.example.model.*;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Personagem extends Status implements Calculos, Acoes {

    // === Atributos principais === //
    private String nome;
    private Raca raca;
    private ItemDeUso itemDeUso;
    private Classe classe;
    private ListaHabilidades habilidades;
    private Integer defence;

    @Setter(AccessLevel.PUBLIC)
    private Integer vidaMax = null;
    private int vidaAtual;

    private Integer manaMax = null;
    private Integer manaAtual;
    private final List<Integer> vidaPorNivel = new ArrayList<>();

    // === Construtor customizado === //
    public Personagem(Integer nivel, Integer forca, Integer agilidade, Integer vigor,
                      Integer intelecto, Integer presenca, String nome,
                      ItemDeUso itemDeUso, Raca raca, Classe classe,
                      ListaHabilidades habilidade) {
        setNome(nome);
        setRaca(raca);
        setClasse(classe);
        setItemDeUso(itemDeUso);
        setNivel(nivel);
        setHabilidades(habilidade);
        setForca(forca);
        setAgilidade(agilidade);
        setVigor(vigor);
        setIntelecto(intelecto);
        setPresenca(presenca);
        setVidaMax(calculoVidaMax());
        setManaMax(calculoManaMax());
        setVidaAtual(vidaMax);
        setManaAtual(manaMax);
        setDefence((vigor/2) + 10);
    }

    protected void setHabilidades(ListaHabilidades habilidade) {
        this.habilidades =  habilidade;
    }

    // === Dados de rolagem === //
    private final Dados random = new Dados();

    // === Cálculo de Vida === //
    @Override
    public Integer calculoVidaMax() {
        int vigor = getVigor() != null ? getVigor() : 0;
        int bonusClasse = classe.getVigorBonus();
        int bonusRaca = raca.getVigorBonus();
        int vidaAdicional = vidaPorNivel.stream().mapToInt(Integer::intValue).sum();

        int base = switch (classe) {
            case Mago, Feiticeiro -> 6;
            case Guerreiro, Paladino -> 10;
            case Arqueiro, Ladino, Druida -> 8;
            case Barbaro -> 12;
            case Selvagem -> 10;
            case Brutalmente -> 14;
            case Tanque -> 14;
        };

        vidaMax = vigor + bonusClasse + bonusRaca + base;
        atualizarVidaPorNivel();
        return vidaMax + vidaAdicional;
    }

    @Override
    public Integer calculoManaMax() {
        int intelecto = getIntelecto() != null ? getIntelecto() : 0;
        int bonusClasse = classe.getIntelectoBonus();
        int bonusRaca = raca.getIntelectoBonus();

        int base = switch (classe) {
            case Mago, Feiticeiro -> 6;
            case Guerreiro, Paladino -> 10;
            case Arqueiro, Ladino, Druida -> 8;
            case Barbaro -> 12;
            case Selvagem -> 10;
            case Brutalmente -> 12;
            case Tanque -> 12;
        };

        manaMax = intelecto + bonusClasse + bonusRaca + base;
        return manaMax;
    }

    public void atualizarVidaPorNivel() {
        int nivel = getNivel() != null ? getNivel() : 1;
        int rolagensNecessarias = nivel - 1;

        while (vidaPorNivel.size() < rolagensNecessarias) {
            int dado = switch (classe) {
                case Mago, Feiticeiro -> random.getD6();
                case Druida, Ladino, Arqueiro -> random.getD8();
                case Guerreiro, Paladino -> random.getD10();
                case Barbaro, Brutalmente, Tanque -> random.getD12();
                case Selvagem -> random.getD10();
            };
            vidaPorNivel.add(dado);
        }
    }

    // === Métodos auxiliares === //

    public void verFicha(Personagem personagem) {
        System.out.println("Você está usando o: " + personagem.getNome() + "\n");
        System.out.println("# Ficha " + personagem.getNome() + " #");
        System.out.println(" Vida: " + personagem.calculoVidaMax());
        System.out.println(" Raça: " + personagem.getRaca());
        System.out.println(" Está usando: " + personagem.getItemDeUso().getNome());
        System.out.println(" Classe: " + personagem.getClasse());

        System.out.println("# Status #");
        System.out.println(" Força: " + (getForca() + classe.getForcaBonus() + raca.getForcaBonus()));
        System.out.println(" Agilidade: " + (getAgilidade() + classe.getAgilidadeBonus() + raca.getAgilidadeBonus()));
        System.out.println(" Vigor: " + (getVigor() + classe.getVigorBonus() + raca.getVigorBonus()));
        System.out.println(" Intelecto: " + (getIntelecto() + classe.getIntelectoBonus() + raca.getIntelectoBonus()));
        System.out.println(" Presença: " + (getPresenca() + classe.getPresencaBonus() + raca.getPresencaBonus()) + "\n");
    }

    public int getForcaTotal() {
        return getForca() + classe.getForcaBonus() + raca.getForcaBonus();
    }

    public int getAgilidadeTotal() {
        return getAgilidade() + getClasse().getAgilidadeBonus() + raca.getAgilidadeBonus();
    }

    public int getVigorTotal() {
        return getVigor() + classe.getVigorBonus() + raca.getVigorBonus();
    }

    public int getIntelectoTotal() {
        return getIntelecto() + classe.getIntelectoBonus() + raca.getIntelectoBonus();
    }

    public int getPresencaTotal() {
        return getPresenca() + classe.getPresencaBonus() + raca.getPresencaBonus();
    }



    public String getResumoPersonagem() {
        if (vidaMax == null) calculoVidaMax();
        if (manaMax == null) calculoManaMax();

         // Aqui você pode usar um campo separado, se tiver controle de vida dinâmica


        String barraVida = gerarBarra(getVidaAtual(), getVidaMax(), 20);
        String barraMana = gerarBarra(manaAtual, getManaMax(), 20);


        return String.format("""
        \n===== Personagem =====
        Nome: %s
        vida: %d/%d %s
        Mana: %d/%d %s
        Classe: %s
        Raça: %s
        Usando item: %s
        ======================
        """,
                nome,
                getVidaAtual(), getVidaMax(), barraVida,
                manaAtual, getManaMax(), barraMana,
                classe, raca,
                itemDeUso != null ? itemDeUso.getNome() : "Nenhum"
        );
    }
    private String gerarBarra(int atual, int max, int tamanho) {
        int preenchido = (int) ((double) atual / max * tamanho);
        int vazio = tamanho - preenchido;
        return "[" + "█".repeat(preenchido) + "░".repeat(vazio) + "]";
    }

    // === Implementação de Ações === //
    @Override
    public void atacar(Personagem alvo) {
        Integer dano = itemDeUso.getDano();
        Integer acerto = random.getD20() + getForca();
        System.out.println("Você rolou: " + acerto + ", esta é a defesa: " + alvo.getDefence());
        if(acerto > alvo.getDefence()){
            alvo.setVidaAtual(alvo.getVidaAtual() - dano);
            System.out.println("Acertou: " + dano + " de dano no alvo!\n");
        }else{
            System.out.println("Errou!\n");
        }

    }

    @Override
    public Personagem combate() {
        return new ListaMonstros().monstroAleatorio();
    }
}
