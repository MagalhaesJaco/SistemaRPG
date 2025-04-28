package org.example.informaçoesPersonagem;

public class Status {
    private Integer nivel;
    private int forca;
    private int agilidade;
    private int vigor;
    private int intelecto;
    private int presenca;
    private int statusTotal;


    public Status(){};
    public Status(Integer nivel, Integer forca, Integer agilidade, Integer vigor, Integer intelecto, Integer presenca) {

        setNivel(nivel);
        setForca(forca);
        setAgilidade(agilidade);
        setVigor(vigor);
        setIntelecto(intelecto);
        setPresenca(presenca);
    }

    public boolean podeAtribuir(Integer valor) {
        return valor <= getStatusRestantes();
    }

    public Integer getForca() {
        return forca;
    }

    public Integer getIntelecto() {
        return intelecto;
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

    public Integer getNivel() {
        return nivel;
    }

    public void setAgilidade(Integer agilidade) {
        if (podeAtribuir(agilidade)) {
            this.agilidade = agilidade;
        }
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
        if (nivel != 0) {
            atualizarStatusPorNivel(nivel);
        }
    }

    public void setForca(int forca) {
        if (podeAtribuir(forca)) {
            this.forca = forca;
        }
    }

    public void setIntelecto(Integer intelecto) {
        if (podeAtribuir(intelecto)) {
            this.intelecto = intelecto;
        }
    }

    public void setPresenca(Integer presenca) {
        if (podeAtribuir(presenca)) {
            this.presenca = presenca;
        }
    }

    public void setVigor(Integer vigor) {
        if (podeAtribuir(vigor)) {
            this.vigor = vigor;
        }
    }

    public void setStatusTotal(int statusTotal) {
        this.statusTotal = statusTotal;
    }

    public void atualizarStatusPorNivel(int nivel) {
        this.statusTotal = 9 + ((nivel - 1) * 2);
    }

    public Integer getStatusTotal() {
        return statusTotal;
    }

    public int getStatusRestantes() {
        int usados = forca + agilidade + intelecto + vigor;
        return statusTotal - usados;
    }
}

