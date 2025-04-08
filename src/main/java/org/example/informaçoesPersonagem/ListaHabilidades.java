package org.example.informaçoesPersonagem;

import java.util.ArrayList;
import java.util.List;

public class ListaHabilidades {

    private List<Habilidade> ListaHabilidades = new ArrayList<>();

    public ListaHabilidades(){
    }

    public void addHabilidade (Habilidade habilidade){
        this.ListaHabilidades.add(habilidade);
    }
    public List<Habilidade> getHabilidades() {
        return ListaHabilidades;
    }
}
