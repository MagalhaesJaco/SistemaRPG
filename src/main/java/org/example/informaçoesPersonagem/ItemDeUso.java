package org.example.informaçoesPersonagem;

public enum ItemDeUso {

    EspadaDeMadeira("Espada-de-madeira", 5);

    private final String nome;
    private Integer dano;

    ItemDeUso(String nome, Integer dano){
        this.nome = nome;
        this.dano = dano;
    }

    public void setDano(Integer dano) {
        this.dano = dano;
    }

    public String getNome() {
        return nome;
    }

    public Integer getDano() {
        return dano;
    }
}
