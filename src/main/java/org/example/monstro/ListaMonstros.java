package org.example.monstro;

import lombok.Getter;
import org.example.atributos.ItemDeUso;
import org.example.atributos.ListaHabilidades;
import org.example.atributos.Raca;
import org.example.monstro.Monstro;
import org.example.personagem.Personagem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class ListaMonstros {
    Random aleatorio = new Random();
    @Getter
    private Monstro[] listaMonstros = {
            new Monstro(1, 5, 4, 3, 1, 2, "Lobo", ItemDeUso.GarraDeFera, Raca.Fera),
            new Monstro(2, 4, 3, 2, 2, 1, "Esqueleto", ItemDeUso.OSSADA, Raca.MortoVivo),
            new Monstro(1, 3, 5, 2, 2, 2, "Goblin", ItemDeUso.FACA_RUSTICA, Raca.Humanoide),
            new Monstro(3, 8, 2, 6, 1, 1, "Ogro", ItemDeUso.PEDRA_GRANDE, Raca.Gigante),
            new Monstro(2, 4, 6, 3, 2, 1, "Aranha Gigante", ItemDeUso.VENENO_DE_ARANHA, Raca.Insetoide),
            new Monstro(1, 5, 2, 5, 1, 1, "Zumbi", ItemDeUso.CARNE_PODRE, Raca.MortoVivo),
            new Monstro(5, 9, 5, 6, 4, 5, "Dragão Filhote", ItemDeUso.ESCAMA_DE_DRAGAO, Raca.Draconico),
            new Monstro(2, 3, 7, 2, 3, 2, "Serpente", ItemDeUso.VENENO_CONCENTRADO, Raca.Reptil),
            new Monstro(4, 10, 1, 8, 1, 1, "Golem de Pedra", ItemDeUso.FRAGMENTO_DE_PEDRA, Raca.Construto),
            new Monstro(3, 2, 8, 2, 7, 5, "Espectro", ItemDeUso.ESSENCIA_SOMBRIA, Raca.Espirito)

    };
    public ListaMonstros (){};


    private Personagem buscarPersonagemPorNome(ListaMonstros lista, String nome) {
        return Arrays.stream(getListaMonstros())
                .filter(p -> p.getNome().equalsIgnoreCase(nome))
                .findFirst()
                .orElse(null);
    }
    public Monstro buscarPorNome(String nome) {
        for (Monstro monstro : listaMonstros) {
            if (monstro.getNome().equalsIgnoreCase(nome)) {
                return monstro;
            }
        }
        return null;
    }


    public Monstro monstroAleatorio(){
        int posicaoAletoria = aleatorio.nextInt(listaMonstros.length);
        return listaMonstros[posicaoAletoria];
    }
}


