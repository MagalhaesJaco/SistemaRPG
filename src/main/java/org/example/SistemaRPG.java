package org.example;
import org.example.informacoesPersonagem.*;

import java.util.Scanner;
import org.example.model.Dados;



public class SistemaRPG {
    public static void main(String[] args) {
        Dados dados = new Dados();
        Scanner scanner = new Scanner(System.in);

        ListaHabilidades HabilidadesOswaldo = new ListaHabilidades();
        ListaHabilidades HabilidadeAdalberto = new ListaHabilidades();

        HabilidadesOswaldo.addHabilidade(Habilidade.BolaDeFogo);

        HabilidadeAdalberto.addHabilidade(Habilidade.BolaDeFogo);

        ListaJogadores ListaJogadores = new ListaJogadores();

        Personagem Oswaldo = new Personagem(
                1,3,1,3,1,1,
                "Oswaldo",
                ItemDeUso.EspadaDeMadeira,
                Raca.Humano,
                Classe.Mago,
                HabilidadesOswaldo
        );

        Personagem Adalberto = new Personagem(
                1,2,2,2,1,2,
                "Adalberto",
                ItemDeUso.EspadaDeMadeira,
                Raca.Gnomo,
                Classe.Guerreiro,
                HabilidadesOswaldo
        );

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
            boolean personagemEncontrado = false;

            for (Personagem personagem : ListaJogadores.getPersonagens()) {
                if (personagem.getNome().contains(InputUsuario)) {
                    personagemEncontrado = true;
                    personagem.atualizarVidaPorNivel();
                    personagem.verFicha(personagem);
                    System.out.println("# Habilidades #");
                    personagem.getHabilidades().getHabilidades().forEach(habilidade -> {
                        System.out.println("-------------------");
                        System.out.println("Nome: " + habilidade.getNome());
                        System.out.println("Dano: " + habilidade.getDano());
                        System.out.println("-------------------");
                    });

                    int status = 0;
                    int dado = 0;
                    int valores = 0;

                    while (true) {
                        String obsInputUsuario = scanner.nextLine();
                        switch (obsInputUsuario) {
                            case "d20":
                                System.out.println("Rolou: " + (dado = dados.getD20()) + " + " + (valores = status) + " = " + (dado + valores));
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
                                status = personagem.getForca() + personagem.getClasse().getForcaBonus();
                                System.out.println("Força selecionada!");
                                break;
                            case "2":
                                status = personagem.getAgilidade() + personagem.getClasse().getAgilidadeBonus();
                                System.out.println("Agilidade selecionada!");
                                break;
                            case "3":
                                status = personagem.getIntelecto() + personagem.getClasse().getIntelectoBonus();
                                System.out.println("Intelecto selecionado!");
                                break;
                            case "4":
                                status = personagem.getPresenca() + personagem.getClasse().getPresencaBonus();
                                System.out.println("Presença selecionada!");
                                break;
                            case "5":
                                status = personagem.getVigor() + personagem.getClasse().getPresencaBonus();
                                System.out.println("Vigor selecionado!");
                                break;
                            case "0":
                                status = 0;
                                System.out.println("Dado base selecionado!");
                                break;
                        }
                    }
                }
            }

            if (personagemEncontrado == false) {
                System.out.println("Selecione um personagem!");
            }
        }
    }
}