package org.example.informaçoesPersonagem;

public class Personagens {

    private String nome;
    private String raca;
    private String arma;
    private ListaDeHabilidades habilidades;
    private Classe classe;
    private Status status;

    public Personagens(String nome, String raca,Classe classe, ListaDeHabilidades habilidades, Status status){
        setNome(nome);
        setRaca(raca);
        setHabilidades(habilidades);
        setStatus(status);
        setClasse(classe);

    }

    public String getNome() {
        return nome;
    }
    public String getRaca() {
        return "Raça: " + raca;
    }
    public ListaDeHabilidades getHabilidades(){
        return habilidades;
    }
    public Status getStatus() {
        return status;
    }
    public Classe getClasse() {
        return classe;
    }

    public void setClasse(Classe classe) {
        this.classe = classe;
    }

    public void setNome (String nome){
        this.nome = nome;
    }
    public void setRaca (String raca){
        this.raca = raca;
    }
    public void setHabilidades(ListaDeHabilidades habilidades) {
        this.habilidades = habilidades;
    }
    public void setStatus(Status status) {
        this.status = status;
    }
}