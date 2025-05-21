package org.example.informacoesPersonagem;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.example.model.Dados;
public enum ItemDeUso {

    // Definiçoes de itens ! //
    EspadaDeMadeira("Espada-de-madeira", 4),
    MachadoDeGuerra("Machado de Guerra", 12),
    CajadoMagico("Cajado Mágico", 8),
    ArcoSimples("Arco Simples", 8),
    AdagaSombria("Adaga Sombria", 6),
    MarteloPesado("Martelo Pesado", 12),
    LancaDeCaçador("Lança de Caçador", 10),
    LivroDeFeiticos("Livro de Feitiços", 10),
    GarraDeFera("Garra de Fera", 8),
    OSSADA("Ossada", 4),
    FACA_RUSTICA("Faca Rústica", 4),
    PEDRA_GRANDE("Pedra Grande", 6),
    VENENO_DE_ARANHA("Veneno de Aranha", 4),
    CARNE_PODRE("Carne Podre", 4),
    ESCAMA_DE_DRAGAO("Escama de Dragão", 8),
    VENENO_CONCENTRADO("Veneno Concentrado", 10),
    FRAGMENTO_DE_PEDRA("Fragmento de Pedra", 4),
    ESSENCIA_SOMBRIA("Essência Sombria", 8);


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
        return roll(dano);
    }

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
