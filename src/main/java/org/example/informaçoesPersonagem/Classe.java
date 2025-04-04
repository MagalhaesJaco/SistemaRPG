package org.example.informaçoesPersonagem;

public enum Classe {

    Guerreiro("Resistente",1);

    private final String descricao;
    private  final Integer bonus;

    Classe(String descricao, Integer bonus) {
        this.descricao = descricao;
        this.bonus = bonus;
    }

    public String getDescricao() {
        return descricao;
    }

    public Integer getBonus() {
        return bonus;
    }
}
