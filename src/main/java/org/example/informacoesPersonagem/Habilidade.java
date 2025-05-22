package org.example.informacoesPersonagem;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.example.model.Dados;

@Getter
public enum Habilidade {
    // definicoes de habilidades !//
    BolaDeFogo("Bola-de-fogo",10, null, 2),
    GolpeFendido("Golpe Fendido", 12,null,4),
    DefesaDeFerro("Defesa de Ferro", 0,null,2),
    ContraAtaqueRapido("Contra-Ataque Rápido", 8,null,2),
    VisaoDoEspírito("Visão do Espírito", 0,null,2),
    SombraIlusoria("Sombra Ilusória", 0,null,2),
    RastreamentoSelvagem("Rastreamento Selvagem", 0,null,2),
    ExplorarFraquezas("Explorar Fraquezas", 0,null,2);

    // Definição de atributos ! //

    private final String nome;
    private final Integer dano;
    private final String efeito;
    private final Integer custo;

    // Construtor ! //
    Habilidade(String nome, Integer dano, String efeito,Integer custo){
        this.nome = nome;
        this.dano = dano;
        this.efeito = efeito;
        this.custo = custo;
    }

    Dados dado = new Dados();
    public Integer efeito(String nome){
        Integer efeito = null;
        if(Habilidade.valueOf(nome).equals(ExplorarFraquezas)){
            System.out.println("1d6 de dano extra até o final da batalha!");
            efeito = dado.getD6();
        }
        return efeito;
    }

}

