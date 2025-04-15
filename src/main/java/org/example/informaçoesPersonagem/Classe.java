package org.example.informaçoesPersonagem;

import org.example.model.Calculos;

public enum Classe {

    Guerreiro("Forte e resistente",0,0,0,0,0),
    Mago("Bom em magia",4,0,0,0,0),
    Ladino("Ágil e furtivo", 0, 3, 0, 2, 0),
    Paladino("Guerreiro sagrado, equilibrado entre força e presença", 2, 0, 1, 3, 0),
    Arqueiro("Especialista em ataques à distância", 1, 4, 0, 0, 0),
    Druida("Conectado à natureza, usa magia e vigor", 0, 0, 2, 1, 3),
    Bárbaro("Bruto e resistente, com força descomunal", 4, 0, 2, 0, 0),
    Feiticeiro("Mestre das artes arcanas, de intelecto elevado", 0, 0, 0, 1, 4);



    private final String descricao;
    private final Integer forcaBonus;
    private final Integer agilidadeBonus;
    private final Integer vigorBonus;
    private final Integer presencaBonus;
    private final Integer intelectoBonus;

    Classe(String descricao, Integer forcaBonus,Integer agilidadeBonus,Integer vigorBonus,Integer presencaBonus,Integer intelectoBonus) {
        this.descricao = descricao;
        this.forcaBonus = forcaBonus;
        this.agilidadeBonus = agilidadeBonus;
        this.vigorBonus = vigorBonus;
        this.presencaBonus = presencaBonus;
        this.intelectoBonus = intelectoBonus;
    }


    public String getDescricao() {
        return descricao;
    }

    public Integer getForcaBonus() {
        return forcaBonus;
    }

    public Integer getAgilidadeBonus() {
        return agilidadeBonus;
    }

    public Integer getIntelectoBonus() {
        return intelectoBonus;
    }

    public Integer getPresencaBonus() {
        return presencaBonus;
    }

    public Integer getVigorBonus() {
        return vigorBonus;
    }
}
