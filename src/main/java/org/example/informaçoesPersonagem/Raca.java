package org.example.informaçoesPersonagem;


public enum Raca {

    Gnomo("Bixu piqueno", 0,1,0,0,1),
    Humano("É apenas um humano... nada de mais...",0,0,1,1,0),
    Elfo("Ágil e com sentidos aguçados", 0, 2, 0, 1, 0),
    Anão("Baixo, parrudo e teimoso", 1, 0, 1, 0, 0),
    Orc("Forte, feroz e impulsivo", 2, 0, 0, 0, 0),
    Tiefling("Ser de sangue demoníaco, com forte presença e intelecto", 0, 0, 0, 2, 0),
    MeioDragao("Sangue dracônico corre em suas veias", 0, 1, 0, 1, 0),
    Sereiano("Habitante das águas, astuto e carismático", 0, 0, 0, 1, 1);
    private final String descricao1;
    private final Integer forcaBonus;
    private final Integer agilidadeBonus;
    private final Integer vigorBonus;
    private final Integer presencaBonus;
    private final Integer intelectoBonus;

    Raca(String descricao1, Integer forcaBonus,Integer agilidadeBonus,Integer vigorBonus,Integer presencaBonus,Integer intelectoBonus) {

        this.descricao1 = descricao1;
        this.forcaBonus = forcaBonus;
        this.agilidadeBonus = agilidadeBonus;
        this.vigorBonus = vigorBonus;
        this.presencaBonus = presencaBonus;
        this.intelectoBonus = intelectoBonus;
    }

    public String getDescricao() {
        return descricao1;
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

