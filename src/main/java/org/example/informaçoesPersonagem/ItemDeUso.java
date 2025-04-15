package org.example.informaçoesPersonagem;

public enum ItemDeUso {

    EspadaDeMadeira("Espada-de-madeira", 5),
    MachadoDeGuerra("Machado de Guerra", 12),
    CajadoMagico("Cajado Mágico", 8),
    ArcoSimples("Arco Simples", 7),
    AdagaSombria("Adaga Sombria", 6),
    MarteloPesado("Martelo Pesado", 15),
    LancaDeCaçador("Lança de Caçador", 9),
    LivroDeFeiticos("Livro de Feitiços", 10),
    GarraDeFera("Garra de Fera", 11);

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
