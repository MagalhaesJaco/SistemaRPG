package org.example.informaçoesPersonagem;

public class Status {
    private Integer forca;
    private Integer agilidade;
    private Integer vigor;
    private Integer Intelecto;
    private Integer presenca;

    public Status (Integer forca,Integer agilidade,Integer vigor,Integer Intelecto,Integer presenca){
        setForca(forca);
        setAgilidade(agilidade);
        setVigor(vigor);
        setIntelecto(Intelecto);
        setPresenca(presenca);
    }

    public Integer getForca() {
        return forca;
    }

    public Integer getIntelecto() {
        return Intelecto;
    }

    public Integer getPresenca() {
        return presenca;
    }

    public Integer getVigor() {
        return vigor;
    }

    public Integer getAgilidade() {
        return agilidade;
    }

    public void setAgilidade(Integer agilidade) {
        this.agilidade = agilidade;
    }

    public void setForca(Integer forca) {
        this.forca = forca;
    }

    public void setIntelecto(Integer intelecto) {
        Intelecto = intelecto;
    }

    public void setPresenca(Integer presenca) {
        this.presenca = presenca;
    }

    public void setVigor(Integer vigor) {
        this.vigor = vigor;
    }
}

