package org.example;
import org.example.informaçoesPersonagem.*;

import java.util.List;
import java.util.Arrays;
import java.util.Scanner;
import org.example.model.Dados;



public class SistemaRPG {
    public static void main(String[] args) {
        Dados dados = new Dados();
        Scanner scanner = new Scanner(System.in);

        Status statusOswaldo = new Status(3, 3, 5, 7, 8);
        Status statusAdalberto = new Status(2, 2, 10, 5, 10);

        ListaHabilidades HabilidadesOswaldo = new ListaHabilidades();
        ListaHabilidades HabilidadeAdalberto = new ListaHabilidades();

        HabilidadesOswaldo.addHabilidade(Habilidade.BolaDeFogo);
        HabilidadeAdalberto.addHabilidade(Habilidade.BolaDeFogo);
        ListaJogadores ListaJogadores = new ListaJogadores();

        Personagens Oswaldo = new Personagens("Oswaldo",ItemDeUso.EspadaDeMadeira,Raca.Humano,Classe.Mago,HabilidadesOswaldo, statusOswaldo);
        Personagens Adalberto = new Personagens("Adalberto",ItemDeUso.EspadaDeMadeira,Raca.Gnomo,Classe.Guerreiro,HabilidadesOswaldo, statusAdalberto);

        ListaJogadores.addPersonagen(Oswaldo);
        ListaJogadores.addPersonagen(Adalberto);

        System.out.println("Chat Geral !");
        System.out.println("Para rola atributos siga a lista a baixo:\n" +
                "1 = Força\n" +
                "2 = Agilidade\n" +
                "3 = Intelecto\n" +
                "4 = Presença\n" +
                "5 = Vigor\n" +
                "0 = Base"
        );
        System.out.println("Qual personagem vai ser usado?");
        while (true) {
            String InputUsuario = scanner.nextLine();
            ListaJogadores.getPersonagens().forEach(personagens -> {
                if (personagens.getNome().contains(InputUsuario)) {
                    System.out.println("Você esta usando o: " + personagens.getNome());
                    System.out.println("# Ficha " + personagens.getNome() + " #");
                    System.out.println("Raça: " + personagens.getRaca());
                    System.out.println("Esta usando: " + personagens.getItemDeUso().getNome());
                    System.out.println("Classe: " + personagens.getClasse());
                    System.out.println("# Status #");
                    System.out.println(" Força: " + (personagens.getStatus().getForca() + personagens.getClasse().getForcaBonus() + personagens.getRaca().getForcaBonus()));
                    System.out.println(" Agilidade: " + (personagens.getStatus().getAgilidade() + personagens.getClasse().getAgilidadeBonus() + personagens.getRaca().getAgilidadeBonus()));
                    System.out.println(" Vigor: " + (personagens.getStatus().getVigor() + personagens.getClasse().getVigorBonus() + personagens.getRaca().getVigorBonus()));
                    System.out.println(" Intelecto: " + (personagens.getStatus().getIntelecto() + personagens.getClasse().getIntelectoBonus() + personagens.getRaca().getIntelectoBonus()));
                    System.out.println(" Presença: " + (personagens.getStatus().getPresenca() + personagens.getClasse().getPresencaBonus() + personagens.getRaca().getPresencaBonus()));
                    System.out.println("# Habilidades #");
                    personagens.getHabilidades().getHabilidades().forEach(habilidade -> {
                        System.out.println(habilidade.getNome());
                    });
                    int status = 0;
                    int dado = 0;
                    int valores =0;
                    while (true) {
                        String obsInputUsuario = scanner.nextLine();
                        switch (obsInputUsuario) {
                            case "d20":
                                System.out.println("Rolou: " + (dado = dados.getD20()) + " + " + (valores = status) + " = " + (dado + valores) );
                                break;
                            case "d12":
                                System.out.println("Rolou: " + dados.getD12());
                                break;
                            case "d10":
                                System.out.println("Rolou: " + dados.getD10());
                                break;
                            case "d8":
                                System.out.println("Rolou: " + dados.getD8());
                                break;
                            case "d6":
                                System.out.println("Rolou: " + dados.getD6());
                                break;
                            case "d4":
                                System.out.println("Rolou: " + dados.getD4());
                                break;
                            case "1":
                                status = personagens.getStatus().getForca();
                                System.out.println("Força selecionada!");
                                break;
                            case "2":
                                status = personagens.getStatus().getAgilidade();
                                System.out.println("Agilidade selecionada!");
                                break;
                            case "3":
                                status = personagens.getStatus().getIntelecto();
                                System.out.println("Intelect selecionada!");
                                break;
                            case "4":
                                status = personagens.getStatus().getPresenca();
                                System.out.println("Presença selecionada!");
                                break;
                            case "5":
                                status = personagens.getStatus().getVigor();
                                System.out.println("Vigor selecionada!");
                                break;
                            case "0":
                                status = 0;
                                System.out.println("Dado base selecionada!");
                                break;
                        }
                    }
                } else {
                    System.out.println("Selecione um personagem!");
                }
            });
        }
        }
    }

