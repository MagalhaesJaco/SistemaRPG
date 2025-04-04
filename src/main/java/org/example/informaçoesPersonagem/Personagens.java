package org.example.informaçoesPersonagem;

public class Personagens {

    private String nome;
    private String raca;
    private String arma;
    private ListaDeHabilidades habilidades;
    private Status status;

    public Personagens(String nome, String raca, ListaDeHabilidades habilidades, Status status){
        setNome(nome);
        setRaca(raca);
        setHabilidades(habilidades);
        setStatus(status);

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