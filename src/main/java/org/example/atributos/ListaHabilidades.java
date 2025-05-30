package org.example.atributos;

import java.util.ArrayList;
import java.util.List;

public class ListaHabilidades {

    private List<Habilidade> ListaHabilidades = new ArrayList<>();

    public ListaHabilidades(){
    }

    public void addHabilidade (Habilidade habilidade){
        this.ListaHabilidades.add(habilidade);
    }

    public List<Habilidade> getHabilidade() {
        return ListaHabilidades;
    }
    public Habilidade get(Integer position){
        return ListaHabilidades.get(position);
    }

}
