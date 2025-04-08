package org.example.informaçoesPersonagem;

public enum Habilidade {

    BolaDeFogo("Bola-de-fogo",10);
    private final String nome;
    private final Integer dano;

    Habilidade(String nome, Integer dano){
        this.nome = nome;
        this.dano = dano;
    }

    public Integer getDano() {
        return dano;
    }
    public String getNome() {
        return nome;
    }
}

