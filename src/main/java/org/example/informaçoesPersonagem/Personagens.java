package org.example.informaçoesPersonagem;

public class Personagens {

    private String nome;
    private Raca raca;
    private ItemDeUso itemDeUso;
    private  ListaHabilidades habilidade;
    private Classe classe;
    private Status status;

    public Personagens(String nome,ItemDeUso itemDeUso, Raca raca ,Classe classe,ListaHabilidades habilidade, Status status){
        setNome(nome);
        setRaca(raca);
        setHabilidades(habilidade);
        setStatus(status);
        setClasse(classe);

    }

    public String getNome() {
        return nome;
    }
    public ListaHabilidades getHabilidades(){
        return habilidade;
    }
    public Status getStatus() {
        return status;
    }
    public Classe getClasse() {
        return classe;
    }
    public Raca getRaca (){
        return raca;
    }
    public ItemDeUso getItemDeUso(){
        return itemDeUso;
    }


    public void setClasse(Classe classe) {
        this.classe = classe;
    }

    public void setNome (String nome){
        this.nome = nome;
    }
    public void setRaca (Raca raca){
        this.raca = raca;
    }
    public void setHabilidades(ListaHabilidades habilidades) {
        this.habilidade = habilidades;
    }
    public void setStatus(Status status) {
        this.status = status;
    }
}