package org.example.informacoesPersonagem;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.Getter;
import org.example.model.Dados;

@Getter
public enum ItemDeUso {

    // Definiçoes de itens ! //
    EspadaDeMadeira("Espada de Madeira", 4, "normal"),
    MachadoDeGuerra("Machado de Guerra", 12, "pesada"),
    CajadoMagico("Cajado Mágico", 8, "normal"),
    ArcoSimples("Arco Simples", 8, "normal"),
    AdagaSombria("Adaga Sombria", 6, "leve"),
    MarteloPesado("Martelo Pesado", 12, "pesada"),
    LancaDeCaçador("Lança de Caçador", 10, "normal"),
    LivroDeFeiticos("Livro de Feitiços", 10, "leve"),
    GarraDeFera("Garra de Fera", 8, "leve"),
    OSSADA("Ossada", 4, "leve"),
    FACA_RUSTICA("Faca Rústica", 4, "leve"),
    PEDRA_GRANDE("Pedra Grande", 6, "pesada"),
    VENENO_DE_ARANHA("Veneno de Aranha", 4, "leve"),
    CARNE_PODRE("Carne Podre", 4, "normal"),
    ESCAMA_DE_DRAGAO("Escama de Dragão", 8, "pesada"),
    VENENO_CONCENTRADO("Veneno Concentrado", 10, "leve"),
    FRAGMENTO_DE_PEDRA("Fragmento de Pedra", 4, "normal"),
    ESSENCIA_SOMBRIA("Essência Sombria", 8, "normal");


    // Definição de atributos !! //
    private final String nome;
    private Integer dano;
    private String tipo;

    // Construtor ! //

    ItemDeUso(String nome, Integer dano, String tipo){
        this.nome = nome;
        this.dano = dano;
        this.tipo = tipo;
    }

    // Gets e sets !! //
    public Integer roll(Integer face){
        Dados roll = new Dados();
        Integer dado = 0;
        switch (face){
            case 4 -> dado = roll.getD4();
            case 6 -> dado = roll.getD6();
            case 8 -> dado = roll.getD8();
            case 10 -> dado = roll.getD10();
            case 12 -> dado = roll.getD12();
        }
        return dado;
    }
}
