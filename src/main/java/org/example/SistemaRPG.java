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
        ListaDeHabilidades HabilidadeDoOswaldo = new ListaDeHabilidades();
        ListaDeHabilidades HabilidadeDoAdalberto = new ListaDeHabilidades();
        Status statusOswaldo = new Status(3, 3, 5, 7, 8);
        Status statusAdalberto = new Status(2, 2, 10, 5, 10);
        Habilidade bolaDeFogo = new Habilidade("Bola de fogo", 10);

        HabilidadeDoOswaldo.addHabilidade(bolaDeFogo);
        HabilidadeDoAdalberto.addHabilidade(bolaDeFogo);

        ListaJogadores ListaJogadores = new ListaJogadores();
        Personagens Oswaldo = new Personagens("Oswaldo", "Humano", HabilidadeDoOswaldo, statusOswaldo);
        Personagens Adalberto = new Personagens("Adalberto", "gnomo", HabilidadeDoAdalberto, statusAdalberto);
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
                    System.out.println(personagens.getRaca());
                    System.out.println("# Status #");
                    System.out.println(" Força: " + statusOswaldo.getForca());
                    System.out.println(" Agilidade: " + statusOswaldo.getAgilidade());
                    System.out.println(" Vigor: " + statusOswaldo.getVigor());
                    System.out.println(" Intelecto: " + statusOswaldo.getIntelecto());
                    System.out.println(" Presença: " + statusOswaldo.getPresenca());
                    System.out.println("# Habilidades #");
                    personagens.getHabilidades().getHabiidade().forEach(habilidade -> {
                        System.out.println("-------------------");
                        System.out.println("Nome: " + habilidade.getNome());
                        System.out.println("Dano: " + habilidade.getDano());
                        System.out.println("-------------------");
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

