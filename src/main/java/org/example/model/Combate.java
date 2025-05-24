package org.example.model;

import org.example.informacoesPersonagem.Habilidade;
import org.example.informacoesPersonagem.Personagem;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

public abstract class Combate {

    private Personagem atacante;
    private Personagem defensor;
    private int defesaExtraTemporaria = 0;
    private int danoExtra;

    Dados roll = new Dados();
    Scanner scanner = new Scanner(System.in);

    public Integer atacar(Personagem atacante, Personagem defensor) {
        System.out.println("\n" + atacante.getNome() + " prepara um ataque contra " + defensor.getNome() + "!");

        int dado = roll.getD20();
        String tipo = atacante.getItemDeUso().getTipo();
        int acerto;
        int atributo = 0;

        if (atacante.getClasse() == null) {
            if (atacante.getForca() > atacante.getAgilidade()) {
                atributo = atacante.getForca();
            } else {
                atributo = atacante.getAgilidade();
            }
        } else {
            switch (tipo) {
                case "normal" -> {
                    if (atacante.getForcaTotal() >= atacante.getAgilidadeTotal()) {
                        atributo = atacante.getForcaTotal();
                    } else {
                        atributo = atacante.getAgilidadeTotal();
                    }
                }
                case "pesada" -> atributo = atacante.getForcaTotal();
                case "leve" -> atributo = atacante.getAgilidadeTotal();
            }
        }
        acerto = dado + atributo;
        int defesa = defensor.getDefence();
        int dano = atacante.getItemDeUso().getDano();

        System.out.println(atacante.getNome() + " rolou um D20 (" + dado + ") + Força (" + atributo + ") = " + acerto);
        System.out.println(defensor.getNome() + " possui Defesa total de: " + defesa);


        if (acerto >= defesa) {
            if (dado == 20) {
                dano = 2 * dano;
            }

            defensor.setVidaAtual(defensor.getVidaAtual() - (dano + danoExtra));
            System.out.println("💥 Ataque bem-sucedido! " + atacante.getNome() + " causou " + dano +" " + danoExtra + " de dano!\n");
        } else {
            if (dado == 1) {
                atacante.setVidaAtual(atacante.getVidaAtual() - dano);
            }
            System.out.println("❌ Ataque falhou! " + defensor.getNome() + " defendeu com sucesso.\n");
            dano = 0;
        }

        return dano;
    }

    public Integer invocarHabilidae(Personagem atacante, Personagem defensor) {
        System.out.println("Selecione a habilidade!\n");
        List lista = atacante.getHabilidades().getHabilidade();
        int i = 0;
        System.out.println("======= Habilidades =========");
        while (true) {
            System.out.println(i + " " + atacante.getHabilidades().getHabilidade().get(i).getNome());
            i++;
            if (i == lista.size()) {
                break;
            }
        }
        System.out.println("=============================\n");
        int dado = roll.getD20();
        int atributo = atacante.getIntelectoTotal();
        int acerto = dado + atributo;
        int defesa = defensor.getDefence();
        AtomicInteger dano = new AtomicInteger();
        int finalI = -1;
        while (true) {
            finalI++;
            if (atacante.getHabilidades().get(scanner.nextInt()) == atacante.getHabilidades().get(finalI)) {
                if (atacante.getHabilidades().get(finalI).getCusto() >= atacante.getManaAtual()) {
                    atacante.setManaAtual(atacante.getManaAtual());
                    System.out.println("Mana muito baixa!");
                } else {
                    if (atacante.getHabilidades().get(finalI).getAlvo().equals("inimigo")) {
                        atacante.setManaAtual(atacante.getManaAtual() - atacante.getHabilidades().get(finalI).getCusto());
                        System.out.println("\n" + atacante.getNome() + " usa " + atacante.getHabilidades().get(finalI).getNome() + " contra " + defensor.getNome() + "!");


                        System.out.println("Essa habilidade custou: " + atacante.getHabilidades().get(finalI).getCusto());
                        System.out.println(atacante.getNome() + " rolou um D20 (" + dado + ") + Intelecto (" + atributo + ") = " + acerto);
                        System.out.println(defensor.getNome() + " possui Defesa total de : " + defesa);
                        dano.set(atacante.getHabilidades().get(finalI).getDano());
                        if (acerto >= defesa) {
                            if (dado == 20) {
                                dano.set(2 * dano.get());
                            }

                            defensor.setVidaAtual(defensor.getVidaAtual() - dano.get());
                            System.out.println("💥 Ataque bem-sucedido! " + atacante.getNome() + " causou " + dano + " de dano!\n");
                        } else {
                            if (dado == 1) {
                                atacante.setVidaAtual(atacante.getVidaAtual() - dano.get());
                            }
                            System.out.println("❌ Ataque falhou! " + defensor.getNome() + " defendeu com sucesso.\n");
                            dano.set(0);
                        }
                    }
                    if (atacante.getHabilidades().get(finalI).getAlvo().equals("usuario")) {
                        this.danoExtra = atacante.getHabilidades().get(finalI).efeito();
                    }
                }
            }
            return dano.get();
        }
    }
        public void reacao (Personagem atacante, Personagem defensor){
            System.out.println("\n🔁 " + defensor.getNome() + ", escolha sua reação:");
            System.out.println("1 - 🌀 Esquivar (usa agilidade para aumentar defesa temporariamente)");
            System.out.println("2 - ⚔️ Contra-atacar (se o ataque falhar, você revida)");
            System.out.println("3 - 🛡️ Resistir (reduz o dano recebido com base no vigor)");

            int escolha = scanner.nextInt();

            switch (escolha) {
                case 1 -> {
                    defesaExtraTemporaria = defensor.getAgilidadeTotal();
                    defensor.setDefence(defensor.getDefence() + defesaExtraTemporaria);
                    if (atacar(atacante, defensor) > 0) {
                        System.out.println(defensor.getNome() + " falha na esquiva. recebeu ataque em cheio!");
                    } else {
                        System.out.println(defensor.getNome() + " se esquiva! Defesa aumentada em +" + defesaExtraTemporaria + " por 1 turno.");
                    }
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
                    int reducao = defensor.getVigorTotal();
                    int danoFinal = danoRecebido - reducao;

                    if (danoRecebido > reducao) {
                        defensor.setVidaAtual(defensor.getVidaAtual() + reducao); // anula parte do dano
                        System.out.println(defensor.getNome() + " resiste ao ataque! Dano reduzido em " + reducao + ". Dano final: " + danoFinal + "\n");
                    }
                    if (danoRecebido <= reducao) {
                        defensor.setVidaAtual(defensor.getVidaAtual() + danoRecebido);
                        System.out.println(defensor.getNome() + " resiste ao ataque! Dano reduzido em " + reducao + ". Dano final: " + 0 + "\n");
                    }


                }
                default -> System.out.println("❗ Opção inválida. Nenhuma reação realizada.");
            }
        }

        public void combate (Personagem jogador, Personagem inimigo){
            this.atacante = jogador;
            this.defensor = inimigo;

            System.out.println("⚔️ Início do Combate entre " + jogador.getNome() + " e " + inimigo.getNome() + "!\n");

            while (jogador.getVidaAtual() > 0 && inimigo.getVidaAtual() > 0) {
                System.out.println("====== STATUS ======");
                System.out.println(jogador.getResumoPersonagem());
                System.out.println(inimigo.getResumoPersonagem());
                System.out.println("====================\n");

                System.out.println("👉 " + jogador.getNome() + ", escolha sua ação:" +
                        "\n1 - Ataque Basico" +
                        "\n2 - Usar Habilidade");
                if (danoExtra <= 0) {
                    jogador.setManaAtual(jogador.getManaAtual() - 1);
                }
                switch (scanner.nextInt()) {
                    case 1 -> {
                        atacar(jogador, inimigo);
                        if (inimigo.getVidaAtual() <= 0) {
                            System.out.println("\n🏆 " + inimigo.getNome() + " foi derrotado!");
                            break;
                        }

                    }
                    case 2 -> {
                        invocarHabilidae(jogador, inimigo);
                        if (inimigo.getVidaAtual() <= 0) {
                            System.out.println("\n🏆 " + inimigo.getNome() + " foi derrotado!");
                            break;
                        }
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


