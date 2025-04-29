package org.example.informacoesPersonagem;

public enum ItemDeUso {

    // Definiçoes de itens ! //

    EspadaDeMadeira("Espada-de-madeira", 5),
    MachadoDeGuerra("Machado de Guerra", 12),
    CajadoMagico("Cajado Mágico", 8),
    ArcoSimples("Arco Simples", 7),
    AdagaSombria("Adaga Sombria", 6),
    MarteloPesado("Martelo Pesado", 15),
    LancaDeCaçador("Lança de Caçador", 9),
    LivroDeFeiticos("Livro de Feitiços", 10),
    GarraDeFera("Garra de Fera", 11),
    GARRA_DE_FERA("Garra de Fera", 5),
    OSSADA("Ossada", 4),
    FACA_RUSTICA("Faca Rústica", 6),
    PEDRA_GRANDE("Pedra Grande", 7),
    VENENO_DE_ARANHA("Veneno de Aranha", 6),
    CARNE_PODRE("Carne Podre", 3),
    ESCAMA_DE_DRAGAO("Escama de Dragão", 8),
    VENENO_CONCENTRADO("Veneno Concentrado", 7),
    FRAGMENTO_DE_PEDRA("Fragmento de Pedra", 5),
    ESSENCIA_SOMBRIA("Essência Sombria", 7);


    // Definição de atributos !! //

    private final String nome;
    private Integer dano;

    // Construtor ! //

    ItemDeUso(String nome, Integer dano){
        this.nome = nome;
        this.dano = dano;
    }

    // Gets e sets !! //

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
