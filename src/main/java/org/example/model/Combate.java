package org.example.model;

import org.example.informacoesPersonagem.Personagem;
import java.util.Scanner;

public abstract class Combate {

    private Personagem atacante;
    private Personagem defensor;
    private int defesaExtraTemporaria = 0;

    Dados roll = new Dados();
    Scanner scanner = new Scanner(System.in);

    public Integer atacar(Personagem atacante, Personagem defensor) {
        System.out.println("\n" + atacante.getNome() + " prepara um ataque contra " + defensor.getNome() + "!");

        int dado = roll.getD20();
        int acerto = dado + atacante.getForca();
        int defesa = defensor.getDefence();
        int dano = atacante.getItemDeUso().getDano();

        System.out.println(atacante.getNome() + " rolou um D20 (" + dado + ") + Força (" + atacante.getForca() + ") = " + acerto);
        System.out.println(defensor.getNome() + " possui Defesa total de: " + defesa);

        if (acerto >= defesa) {
            defensor.setVidaAtual(defensor.getVidaAtual() - dano);
            System.out.println("💥 Ataque bem-sucedido! " + atacante.getNome() + " causou " + dano + " de dano!");
        } else {
            System.out.println("❌ Ataque falhou! " + defensor.getNome() + " defendeu com sucesso.");
            dano = 0;
        }

        return dano;
    }

    public void reacao(Personagem atacante, Personagem defensor) {
        System.out.println("\n🔁 " + defensor.getNome() + ", escolha sua reação:");
        System.out.println("1 - 🌀 Esquivar (usa agilidade para aumentar defesa temporariamente)");
        System.out.println("2 - ⚔️ Contra-atacar (se o ataque falhar, você revida)");
        System.out.println("3 - 🛡️ Resistir (reduz o dano recebido com base no vigor)");

        int escolha = scanner.nextInt();

        switch (escolha) {
            case 1 -> {
                defesaExtraTemporaria = defensor.getAgilidade();
                defensor.setDefence(defensor.getDefence() + defesaExtraTemporaria);
                System.out.println(defensor.getNome() + " se esquiva! Defesa aumentada em +" + defesaExtraTemporaria + " por 1 turno.");
            }
            case 2 -> {
                System.out.println(defensor.getNome() + " se prepara para um contra-ataque!");
                if (atacar(atacante, defensor) <= 0) {
                    System.out.println(defensor.getNome() + " revida com força total!");
                    atacar(defensor, atacante);
                }
            }
            case 3 -> {
                System.out.println(defensor.getNome() + " se posiciona para resistir ao golpe...");
                int danoRecebido = atacar(atacante, defensor);
                int reducao = defensor.getVigor();
                int danoFinal = danoRecebido - reducao;

                if (danoRecebido > reducao) {
                    defensor.setVidaAtual(defensor.getVidaAtual() + reducao); // anula parte do dano
                    System.out.println(defensor.getNome() + " resiste ao ataque! Dano reduzido em " + reducao + ". Dano final: " + danoFinal + "\n");
                }
                if(danoRecebido <= reducao){
                    defensor.setVidaAtual(defensor.getVidaAtual() + danoRecebido);
                    System.out.println(defensor.getNome() + " resiste ao ataque! Dano reduzido em " + reducao + ". Dano final: " + 0 + "\n");
                }


            }
            default -> System.out.println("❗ Opção inválida. Nenhuma reação realizada.");
        }
    }

    public void combate(Personagem jogador, Personagem inimigo) {
        this.atacante = jogador;
        this.defensor = inimigo;

        System.out.println("⚔️ Início do Combate entre " + jogador.getNome() + " e " + inimigo.getNome() + "!\n");

        while (jogador.getVidaAtual() > 0 && inimigo.getVidaAtual() > 0) {
            System.out.println("====== STATUS ======");
            System.out.println(jogador.getResumoPersonagem());
            System.out.println(inimigo.getResumoPersonagem());
            System.out.println("====================\n");

            System.out.println("👉 " + jogador.getNome() + ", escolha sua ação:\n1 - Atacar");
            if (scanner.nextInt() == 1) {
                atacar(jogador, inimigo);
                if (inimigo.getVidaAtual() <= 0) {
                    System.out.println("\n🏆 " + inimigo.getNome() + " foi derrotado!");
                    break;
                }
            }

            System.out.println("\n⛑️ Sua reação ao ataque:");
            reacao(inimigo, jogador);

            if (jogador.getVidaAtual() <= 0) {
                System.out.println("\n☠️ " + jogador.getNome() + " foi derrotado!");
                break;
            }

            // Remover bônus de esquiva (após o turno)
            if (defesaExtraTemporaria > 0) {
                jogador.setDefence(jogador.getDefence() - defesaExtraTemporaria);
                System.out.println("🔽 Bônus de esquiva de " + jogador.getNome() + " expirou. Defesa retornou ao normal.");
                defesaExtraTemporaria = 0;
            }
        }

        System.out.println("\n🔚 Combate encerrado!");
    }
}
