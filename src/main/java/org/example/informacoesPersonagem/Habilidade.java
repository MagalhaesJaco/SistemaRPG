package org.example.informacoesPersonagem;

public enum Habilidade {

    // definicoes de habilidades !//
    BolaDeFogo("Bola-de-fogo",10),
    GolpeFendido("Golpe Fendido", 12),
    DefesaDeFerro("Defesa de Ferro", 0),
    ContraAtaqueRapido("Contra-Ataque Rápido", 8),
    VisaoDoEspírito("Visão do Espírito", 0),
    SombraIlusoria("Sombra Ilusória", 0),
    RastreamentoSelvagem("Rastreamento Selvagem", 0);

    // Definição de atributos ! //

    private final String nome;
    private final Integer dano;

    // Construtor ! //
    Habilidade(String nome, Integer dano){
        this.nome = nome;
        this.dano = dano;
    }

    // Gets ! //
    public Integer getDano() {
        return dano;
    }
    public String getNome() {
        return nome;
    }
}

