package org.example.personagem;

import lombok.*;
import org.example.atributos.*;
import org.example.classesSuportes.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class Personagem extends Status implements Calculos {

    // === Atributos principais === //
    private Status status;
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
    public Personagem(Status status,String nome, ItemDeUso itemDeUso, Raca raca, Classe classe, ListaHabilidades habilidade) throws SQLException {
        super();
        setStatus(status);
        setStatusTotal(getStatusTotal());
        setNome(nome);
        setRaca(raca);
        setClasse(classe);
        setItemDeUso(itemDeUso);

        setHabilidades(habilidade);


        setVidaMax(calculoVidaMax());
        setManaMax(calculoManaMax());
        setVidaAtual(vidaMax);
        setManaAtual(manaMax);
    }

    public Personagem() throws SQLException {
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

        int base = switch (classe.getNome()) {
            case "Mago","Feiticeiro" -> 6;
            case "Guerreiro", "Paladino", "Selvagem" -> 10;
            case "Arqueiro", "Ladino", "Druida" -> 8;
            case "Barbaro" -> 12;
            case "Brutalmente", "Tanque" -> 14;
            default -> throw new IllegalStateException("Unexpected value: " + classe.getNome());
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

        int base = switch (classe.getNome()) {
            case "Mago","Feiticeiro" -> 6;
            case "Guerreiro", "Paladino", "Selvagem" -> 10;
            case "Arqueiro", "Ladino", "Druida" -> 8;
            case "Barbaro" -> 12;
            case "Brutalmente", "Tanque" -> 14;
            default -> throw new IllegalStateException("Unexpected value: " + classe.getNome());
        };

        manaMax = intelecto + bonusClasse + bonusRaca + base;
        return manaMax;
    }

    public void atualizarVidaPorNivel() {
        int nivel = getNivel() != null ? getNivel() : 1;
        int rolagensNecessarias = nivel - 1;

        while (vidaPorNivel.size() < rolagensNecessarias) {
            int dado = switch (classe.getNome()) {
                case "Mago", "Feiticeiro" -> random.getD6();
                case "Druida", "Ladino", "Arqueiro" -> random.getD8();
                case "Guerreiro", "Paladino" -> random.getD10();
                case "Barbaro", "Brutalmente", "Tanque" -> random.getD12();
                case "Selvagem" -> random.getD10();
                default -> throw new IllegalStateException("Unexpected value: " + classe.getNome());
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


        String barraVida = gerarBarra(getVidaAtual(), getVidaMax(), (getVidaMax()*2));
        String barraMana = gerarBarra(manaAtual, getManaMax(), (getManaMax()*2));


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
                classe.getNome(), raca.getNome(),
                itemDeUso != null ? itemDeUso.getNome() : "Nenhum"
        );
    }
    private String gerarBarra(int atual, int max, int tamanho) {
        int preenchido = (int) ((double) atual / max * tamanho);
        int vazio = tamanho - preenchido;
        return "[" + "█".repeat(preenchido) + "░".repeat(vazio) + "]";
    }
}
