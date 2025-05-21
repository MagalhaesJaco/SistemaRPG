package org.example;

import org.example.informacoesPersonagem.*;
import org.example.model.Combate;
import org.example.model.Dados;

import java.util.Scanner;

public class SistemaRPG {
    private static final Scanner scanner = new Scanner(System.in);
    private static final Dados dados = new Dados();
    private static final ListaMonstros Bestiario = new ListaMonstros();
    public static void main(String[] args) {
        ListaMonstros bestiario = new ListaMonstros();
        System.out.println(bestiario.buscarPorNome("Lobo"));

        // Habilidades
        ListaHabilidades habilidadesOswaldo = new ListaHabilidades();
        habilidadesOswaldo.addHabilidade(Habilidade.BolaDeFogo);

        ListaHabilidades habilidadesAdalberto = new ListaHabilidades();
        habilidadesAdalberto.addHabilidade(Habilidade.BolaDeFogo);

        // Jogadores
        ListaJogadores listaJogadores = new ListaJogadores();

        Personagem oswaldo = new Personagem(
                1, 3, 1, 3, 1, 1,
                "Oswaldo",
                ItemDeUso.EspadaDeMadeira,
                Raca.Humano,
                Classe.Mago,
                habilidadesOswaldo
        );

        Personagem adalberto = new Personagem(
                1, 2, 2, 2, 1, 2,
                "Adalberto",
                ItemDeUso.VENENO_DE_ARANHA,
                Raca.Gnomo,
                Classe.Guerreiro,
                habilidadesAdalberto
        );

        listaJogadores.addPersonagen(oswaldo);
        listaJogadores.addPersonagen(adalberto);

        exibirMenuInicial();
        while (true) {
            System.out.print("\nDigite o nome do personagem: ");
            String nomeInput = scanner.nextLine();

            Personagem personagemSelecionado = buscarPersonagemPorNome(listaJogadores, nomeInput);
            if (personagemSelecionado != null) {
                interagirComPersonagem(personagemSelecionado);

            } else {
                System.out.println("Personagem não encontrado! Tente novamente.");
            }
        }
    }

    private static void exibirMenuInicial() {
        System.out.println("Chat Geral!");
        System.out.println("Para rolar atributos siga a lista abaixo:\n" +
                "1 = Força\n" +
                "2 = Agilidade\n" +
                "3 = Intelecto\n" +
                "4 = Presença\n" +
                "5 = Vigor\n" +
                "0 = Base\n");
    }

    private static Personagem buscarPersonagemPorNome(ListaJogadores lista, String nome) {
        return lista.getPersonagens().stream()
                .filter(p -> p.getNome().equalsIgnoreCase(nome))
                .findFirst()
                .orElse(null);
    }

    private static void interagirComPersonagem(Personagem personagem) {
        personagem.atualizarVidaPorNivel();
        personagem.verFicha(personagem);

        System.out.println(personagem.getResumoPersonagem());

        int statusBonus = 0;

        while (true) {
            System.out.print("> ");
            String comando = scanner.nextLine().trim().toLowerCase();

            switch (comando) {
                case "d20" -> rolarEDisplay("d20", dados.getD20(), statusBonus);
                case "d12" -> rolarEDisplay("d12", dados.getD12(), 0);
                case "d10" -> rolarEDisplay("d10", dados.getD10(), 0);
                case "d8"  -> rolarEDisplay("d8", dados.getD8(), 0);
                case "d6"  -> rolarEDisplay("d6", dados.getD6(), 0);
                case "d4"  -> rolarEDisplay("d4", dados.getD4(), 0);
                case "0", "base" -> {
                    statusBonus = 0;
                    System.out.println("Dado base selecionado!");
                }
                case "1" -> statusBonus = selecionarStatus(personagem, "forca");
                case "2" -> statusBonus = selecionarStatus(personagem, "agilidade");
                case "3" -> statusBonus = selecionarStatus(personagem, "intelecto");
                case "4" -> statusBonus = selecionarStatus(personagem, "presenca");
                case "5" -> statusBonus = selecionarStatus(personagem, "vigor");
                case "caçar" -> {
                    Personagem criatura = Bestiario.monstroAleatorio();
                    personagem.combate(personagem,criatura);
                }
                case "sair", "exit" -> {
                    System.out.println("Saindo do personagem...\n");
                    return;
                }
                default -> System.out.println("Comando inválido!");
            }
        }
    }

    private static void rolarEDisplay(String dado, int valorRolado, int bonus) {
        int total = valorRolado + bonus;
        String mensagem = bonus > 0 ? (valorRolado + " + " + bonus + " = " + total) : String.valueOf(valorRolado);
        System.out.println("Rolou (" + dado + "): " + mensagem);
    }

    private static int selecionarStatus(Personagem personagem, String tipo) {
        int valor = switch (tipo) {
            case "forca" -> personagem.getForca() + personagem.getClasse().getForcaBonus();
            case "agilidade" -> personagem.getAgilidade() + personagem.getClasse().getAgilidadeBonus();
            case "intelecto" -> personagem.getIntelecto() + personagem.getClasse().getIntelectoBonus();
            case "presenca" -> personagem.getPresenca() + personagem.getClasse().getPresencaBonus();
            case "vigor" -> personagem.getVigor() + personagem.getClasse().getVigorBonus();
            default -> 0;
        };
        System.out.println(tipo.substring(0, 1).toUpperCase() + tipo.substring(1) + " selecionado!");
        return valor;
    }
}
