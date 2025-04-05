package org.example.informaçoesPersonagem;

public enum Classe {

    Guerreiro("Forte e resistente",1),Mago("Bom em magia",4);



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

    public Integer addBonus(Personagens personagens){
        Integer statusMudado = 0;
        if(personagens.getClasse() == Classe.Guerreiro){
              statusMudado = personagens.getClasse().getBonus() + personagens.getStatus().getForca();
        }
        return statusMudado;
    }
}
