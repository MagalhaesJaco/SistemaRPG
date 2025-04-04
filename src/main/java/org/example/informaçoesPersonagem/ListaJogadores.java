package org.example.informaçoesPersonagem;
import java.util.ArrayList;
import java.util.List;

public class ListaJogadores {
    private List<Personagens> personagens =  new ArrayList<>();

    public void addPersonagen (Personagens personagem){
        this.personagens.add(personagem);
    }

    public List<Personagens> getPersonagens (){
        return personagens;
    }

}
