package org.example.atributos;

import lombok.Getter;
import org.example.classesSuportes.Dados;

@Getter
public enum Habilidade {
    // definicoes de habilidades !//
    BolaDeFogo("Bola-de-fogo",10, null, 2,"inimigo"),
    GolpeFendido("Golpe Fendido", 12,null,4,"inimigo"),
    DefesaDeFerro("Defesa de Ferro", 0,null,2,"usuario"),
    ContraAtaqueRapido("Contra-Ataque Rápido", 8,null,2,"inimigo"),
    VisaoDoEspírito("Visão do Espírito", 0,null,2,"ativa"),
    SombraIlusoria("Sombra Ilusória", 0,null,2,"usuario"),
    ExplorarFraquezas("Explorar Fraquezas", 0,"+ 1d6 de dano",2,"usuario");

    // Definição de atributos ! //

    private final String nome;
    private final Integer dano;
    private final String efeito;
    private final Integer custo;
    private final String alvo;

    // Construtor ! //
    Habilidade(String nome, Integer dano, String efeito,Integer custo,String alvo){
        this.nome = nome;
        this.dano = rollDamage(dano);
        this.efeito = efeito;
        this.custo = custo;
        this.alvo = alvo;

    }

    Dados dado = new Dados();
    public Integer efeito(){
        int efeito = 0;
        if(nome.equals(ExplorarFraquezas.getNome())){

            efeito = dado.getD6();
        }
        return efeito;
    }
    public Integer rollDamage(Integer face){
        Dados roll = new Dados();
        Integer dado = 0;
        switch (face){
            case 4 -> dado = roll.getD4();
            case 6 -> dado = roll.getD6();
            case 8 -> dado = roll.getD8();
            case 10 -> dado = roll.getD10();
            case 12 -> dado = roll.getD12();
        }
        return dado;
    }
}

