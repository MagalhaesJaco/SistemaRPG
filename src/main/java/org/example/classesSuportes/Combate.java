package org.example.classesSuportes;

import org.example.monstro.Monstro;
import org.example.atributos.Habilidade;
import org.example.personagem.Personagem;

import java.util.List;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

public abstract class Combate {

    private Personagem atacante;
    private Personagem defensor;
    private int defesaExtraTemporaria = 0;
    private int danoExtra;


    Dados roll = new Dados();
    Scanner scanner = new Scanner(System.in);

    public Integer atacarJogador(Monstro atacante, Personagem defensor) {
        System.out.println("\n" + atacante.getNome() + " prepara um ataque contra " + defensor.getNome() + "!");

        int dado = roll.getD20();
        String tipo = atacante.getItemDeUso().getTipo();
        int acerto;
        int atributo = 0;

        //Define atributo usado
        switch (tipo) {
            case "normal" -> {
                if (atacante.getForcaTotalMonstro() >= atacante.getAgilidadeTotalMonstro()) {
                    atributo = atacante.getForcaTotalMonstro();
                } else {
                    atributo = atacante.getAgilidadeTotalMonstro();
                }
            }
            case "pesada" -> atributo = atacante.getForcaTotalMonstro();
            case "leve" -> atributo = atacante.getAgilidadeTotalMonstro();
        }

        acerto = dado + atributo;
        int defesa = defensor.getDefence();
        int dano = atacante.getItemDeUso().getDano();

        System.out.println(atacante.getNome() + " rolou um D20 (" + dado + ") + Força (" + atributo + ") = " + acerto);
        System.out.println(defensor.getNome() + " possui Defesa total de: " + defesa);

        //Define acerto critico
        if (acerto >= defesa) {
            if (dado == 20) {
                dano = 2 * dano;
            }
            defensor.setVidaAtual(defensor.getVidaAtual() - (dano));
            System.out.println("💥 Ataque bem-sucedido! " + atacante.getNome() + " causou " + dano + " de dano!\n");
        }else{
            System.out.println("❌ Ataque falhou! " + defensor.getNome() + " defendeu com sucesso.\n");
            dano = 0;
        }

        return dano;
    }
    public Integer atacarMonstro(Personagem atacante, Personagem defensor) {
        System.out.println("\n" + atacante.getNome() + " prepara um ataque contra " + defensor.getNome() + "!");

        int dado = roll.getD20();
        String tipo = atacante.getItemDeUso().getTipo();
        int acerto;
        int atributo = 0;

        //Define atributo usado
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

        acerto = dado + atributo;
        int defesa = defensor.getDefence();
        int dano = atacante.getItemDeUso().getDano();

        System.out.println(atacante.getNome() + " rolou um D20 (" + dado + ") + Força (" + atributo + ") = " + acerto);
        System.out.println(defensor.getNome() + " possui Defesa total de: " + defesa);

        //Define acerto critico
        if (acerto >= defesa) {
            if (dado == 20) {
                dano = 2 * dano;
            }
            defensor.setVidaAtual(defensor.getVidaAtual() - (dano + danoExtra));
            if(danoExtra > 0){
                System.out.println("💥 Ataque bem-sucedido! " + atacante.getNome() + " causou " + (dano + danoExtra) + " adicional de ( "+ danoExtra +" ) de dano!\n");
            }else {
                System.out.println("💥 Ataque bem-sucedido! " + atacante.getNome() + " causou " + dano + " de dano!\n");
            }
        }else{
            System.out.println("❌ Ataque falhou! " + defensor.getNome() + " defendeu com sucesso.\n");
            dano = 0;
        }

        return dano;
    }
    public Integer invocarHabilidade(Personagem atacante, Personagem defensor) {
        List<Habilidade> lista = atacante.getHabilidades().getHabilidade();

        System.out.println("======= Habilidades =========");
        for (int i = 0; i < lista.size(); i++) {
            System.out.println(i + " - " + lista.get(i).getNome());
        }
        System.out.println("=============================\n");
        System.out.print("Escolha a habilidade (número): ");

        int escolha = scanner.nextInt();
        if (escolha < 0 || escolha >= lista.size()) {
            System.out.println("Habilidade inválida!");
            return 0;
        }

        Habilidade habilidade = lista.get(escolha);

        if (habilidade.getCusto() > atacante.getManaAtual()) {
            System.out.println("Mana muito baixa!");
            return 0;
        }

        atacante.setManaAtual(atacante.getManaAtual() - habilidade.getCusto());
        System.out.println("\n" + atacante.getNome() + " usa " + habilidade.getNome() + "!");
        System.out.println("Essa habilidade custou: " + habilidade.getCusto());

        int dado = roll.getD20();
        int atributo = atacante.getIntelectoTotal();
        int acerto = dado + atributo;
        int defesa = defensor.getDefence();

        int dano = habilidade.getDano();

        if (habilidade.getAlvo().equalsIgnoreCase("inimigo")) {
            System.out.println(atacante.getNome() + " rolou um D20 (" + dado + ") + Intelecto (" + atributo + ") = " + acerto);
            System.out.println(defensor.getNome() + " possui Defesa total de: " + defesa);

            if (acerto >= defesa) {
                if (dado == 20) {
                    dano = (2 * dano);
                }
                defensor.setVidaAtual(defensor.getVidaAtual() - dano);
                System.out.println("💥 Ataque bem-sucedido! " + atacante.getNome() + " causou " + dano + " de dano!\n");
            } else {
                if (dado == 1) {
                    atacante.setVidaAtual(atacante.getVidaAtual() - dano);
                }
                System.out.println("❌ Ataque falhou! " + defensor.getNome() + " defendeu com sucesso.\n");
                dano = 0;
            }
        } else if (habilidade.getAlvo().equalsIgnoreCase("usuario")) {
            this.danoExtra = habilidade.getValorEfeito();  // ← Aqui o danoExtra será setado
            System.out.println("✨ Efeito aplicado ao usuário: " + habilidade.getEfeito());
        }

        return dano;
    }


    public void reacao (Monstro atacante, Personagem defensor){
            System.out.println("\n🔁 " + defensor.getNome() + ", escolha sua reação:");
            System.out.println("1 - 🌀 Esquivar (usa agilidade para aumentar defesa temporariamente)");
            System.out.println("2 - ⚔️ Contra-atacar (se o ataque falhar, você revida)");
            System.out.println("3 - 🛡️ Resistir (reduz o dano recebido com base no vigor)");

            int escolha = scanner.nextInt();

            switch (escolha) {
                case 1 -> {
                    defesaExtraTemporaria = defensor.getAgilidadeTotal();
                    defensor.setDefence(defensor.getDefence() + defesaExtraTemporaria);
                    if (atacarJogador(atacante, defensor) > 0) {
                        System.out.println(defensor.getNome() + " falha na esquiva. recebeu ataque em cheio!");
                    } else {
                        System.out.println(defensor.getNome() + " se esquiva! Defesa aumentada em +" + defesaExtraTemporaria + " por 1 turno.");
                    }
                }
                case 2 -> {
                    System.out.println(defensor.getNome() + " se prepara para um contra-ataque!");
                    if (atacarJogador(atacante, defensor) <= 0) {
                        System.out.println(defensor.getNome() + " revida com força total!");
                        atacarMonstro(defensor, atacante);
                    }
                }
                case 3 -> {
                    System.out.println(defensor.getNome() + " se posiciona para resistir ao golpe...");
                    int danoRecebido = atacarJogador(atacante, defensor);
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
    public void combate (Personagem jogador, Monstro inimigo){
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
                switch (scanner.nextInt()) {
                    case 1 -> {
                        atacarMonstro(jogador, inimigo);
                        if (inimigo.getVidaAtual() <= 0) {
                            System.out.println("\n🏆 " + inimigo.getNome() + " foi derrotado!");
                            break;
                        } else {
                            System.out.println("\n⛑️ Sua reação ao ataque:");
                            reacao(inimigo, jogador);
                        }

                    }
                    case 2 -> {
                        invocarHabilidade(jogador, inimigo);
                        if (inimigo.getVidaAtual() <= 0) {
                            System.out.println("\n🏆 " + inimigo.getNome() + " foi derrotado!");
                            break;
                        }else{
                            System.out.println("\n⛑️ Sua reação ao ataque:");
                            reacao(inimigo, jogador);
                        }
                    }
                }
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


