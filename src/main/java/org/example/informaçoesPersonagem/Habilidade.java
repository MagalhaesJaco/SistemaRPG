package org.example.informaçoesPersonagem;

public class Habilidade {

    private String nome;
    private Integer dano;

    public Habilidade (){};
    public Habilidade (String nome, Integer dano){
        setDano(dano);
        setNome(nome);
    }

    public void setDano(Integer dano) {
        this.dano = dano;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public Integer getDano() {
        return dano;
    }
    public String getNome() {
        return nome;
    }
}
