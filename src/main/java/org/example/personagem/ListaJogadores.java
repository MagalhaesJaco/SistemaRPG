package org.example.personagem;
import java.util.ArrayList;
import java.util.List;

public class ListaJogadores {
    private List<Personagem> personagens =  new ArrayList<>();

    public void addPersonagen (Personagem personagem){
        this.personagens.add(personagem);
    }

    public List<Personagem> getPersonagens (){
        return personagens;
    }

}
