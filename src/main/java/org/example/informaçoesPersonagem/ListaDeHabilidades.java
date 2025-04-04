package org.example.informaçoesPersonagem;
import java.util.ArrayList;
import java.util.List;

public class ListaDeHabilidades extends Habilidade {
    private List<Habilidade> habilidades = new ArrayList<>();

    public ListaDeHabilidades (){
        super();
    }

    public void addHabilidade (Habilidade habilidade){
        this.habilidades.add(habilidade);
    }

    public List<Habilidade> getHabiidade (){
        return habilidades;
    }
}
