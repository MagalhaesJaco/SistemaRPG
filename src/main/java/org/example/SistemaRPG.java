package org.example;

import org.example.atributos.*;
import org.example.monstro.ListaMonstros;
import org.example.monstro.Monstro;
import org.example.personagem.*;
import org.example.classesSuportes.Dados;
import org.example.personagem.Personagem;
import org.example.monstro.Monstro;
import vercaoComBanco.enty.service.ItemDeUsoService;

import java.sql.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SistemaRPG {
    private static final Scanner scanner = new Scanner(System.in);
    private static final Dados dados = new Dados();
    private static final ListaMonstros Bestiario = new ListaMonstros();
    public static void main(String[] args) throws SQLException {
        ListaMonstros bestiario = new ListaMonstros();
        // Habilidades
        ListaHabilidades habilidadesOswaldo = new ListaHabilidades();
        habilidadesOswaldo.addHabilidade(Habilidade.buscarPorId(1));
        habilidadesOswaldo.addHabilidade(Habilidade.buscarPorId(7));

        ListaHabilidades habilidadesAdalberto = new ListaHabilidades();
        habilidadesAdalberto.addHabilidade(Habilidade.buscarPorId(1));

        // Jogadores
        ListaJogadores listaJogadores = new ListaJogadores();

        Personagem oswaldo = new Personagem(
                1, 3, 1, 3, 1, 1,
                "Oswaldo",
                ItemDeUso.buscarItemPorNome("Espada de Madeira"),
                Raca.buscarRacaPorId(2),
                Classe.Mago,
                habilidadesOswaldo
        );

        Personagem adalberto = new Personagem(
                1, 2, 2, 2, 1, 2,
                "Adalberto",
                ItemDeUso.buscarItemPorNome("Veneno de aranha"),
                Raca.buscarRacaPorId(1),
                Classe.Guerreiro,
                habilidadesAdalberto
        );
        System.out.println(habilidadesOswaldo.get(1));

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
        System.out.println("========= Chat Geral =========");
        System.out.println("Use os comandos abaixo para rolar e ações:");
        System.out.println("  1 - Força");
        System.out.println("  2 - Agilidade");
        System.out.println("  3 - Intelecto");
        System.out.println("  4 - Presença");
        System.out.println("  5 - Vigor");
        System.out.println("  0 - Base (sem bônus)");
        System.out.println(" caçar - procurar um inimigo");
        System.out.println("==============================");
    }

    private static Personagem buscarPersonagemPorNome(ListaJogadores lista, String nome) {
        return lista.getPersonagens().stream()
                .filter(p -> p.getNome().equalsIgnoreCase(nome))
                .findFirst()
                .orElse(null);
    }

    private static void interagirComPersonagem(Personagem personagem) {
        personagem.atualizarVidaPorNivel();
        verFicha(personagem);

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
                    Monstro criatura = Bestiario.monstroAleatorio();
                    personagem.combate(personagem, criatura);
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
            case "forca"    -> personagem.getForcaTotal();
            case "agilidade"-> personagem.getAgilidadeTotal();
            case "intelecto"-> personagem.getIntelectoTotal();
            case "presenca" -> personagem.getPresencaTotal();
            case "vigor"    -> personagem.getVigorTotal();
            default         -> 0;
        };
        System.out.println(tipo.substring(0, 1).toUpperCase() + tipo.substring(1) + " selecionado!");
        return valor;
    }

    private static void verFicha(Personagem personagem) {
        System.out.println("======= Ficha do Personagem =======");
        System.out.println("Nome: " + personagem.getNome());
        System.out.println("Raça: " + personagem.getRaca().getNome());
        System.out.println("Classe: " + personagem.getClasse());
        System.out.println("Item: " + (personagem.getItemDeUso() != null ? personagem.getItemDeUso().getNome() : "Nenhum"));
        System.out.println("Nível: " + personagem.getNivel());
        System.out.println("Vida: " + personagem.getVidaAtual() + "/" + personagem.getVidaMax());

        System.out.println("\nAtributos:");
        System.out.printf("  Força:     %d (Total: %d)%n", personagem.getForca(), personagem.getForcaTotal());
        System.out.printf("  Agilidade: %d (Total: %d)%n", personagem.getAgilidade(), personagem.getAgilidadeTotal());
        System.out.printf("  Vigor:     %d (Total: %d)%n", personagem.getVigor(), personagem.getVigorTotal());
        System.out.printf("  Intelecto: %d (Total: %d)%n", personagem.getIntelecto(), personagem.getIntelectoTotal());
        System.out.printf("  Presença:  %d (Total: %d)%n", personagem.getPresenca(), personagem.getPresencaTotal());

        System.out.println("======= Habilidades =========");

        for(Habilidade h: personagem.getHabilidades().getHabilidade())
            System.out.println(" " + h.getNome());


        System.out.println("=============================");
    }
}
