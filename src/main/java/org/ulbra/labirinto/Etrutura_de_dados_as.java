
package org.ulbra.labirinto;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;


public class Etrutura_de_dados_as {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Labirinto labTeste = new Labirinto();
        List<Jogador> jogadores = new ArrayList<>();
        List<Sala> salas = new ArrayList<>();
        int pontuacaoInicial = 10;
        int idSala = 0;
        int numeroMinimo = 4;

        while (true) { 
            System.out.println("\nJogo do labirinto");
            System.out.println("Escolha uma opção (aperte qualquer outra tecla para sair): \n1 - CRIAR SALA\n2 - LISTAR SALAS\n3 - REMOVER SALA\n4 - CRIAR JOGADOR\n5 - INICAR JOGO");
            String opcao = sc.nextLine();
            
            switch (opcao) {
                case "1" -> {
                    System.out.println("\nDigite uma descrição para a sala");
                    String desc = sc.nextLine();

                    System.out.println("Qual o tipo da sala? (normal, armadilha, premio ou saida)");
                    String tipo = sc.nextLine();
                    TipoSala tipoSala;

                    switch (tipo.toUpperCase()) {
                        case "NORMAL" -> tipoSala = TipoSala.NORMAL;
                        case "ARMADILHA" -> tipoSala = TipoSala.ARMADILHA;
                        case "PREMIO" -> tipoSala = TipoSala.PREMIO;
                        case "SAIDA" -> tipoSala = TipoSala.SAIDA;
                        default -> throw new AssertionError("Tipo inválido");
                            
                    }

                    Sala salaCriada = new Sala(desc, idSala, tipoSala);
                    labTeste.adicionar(salaCriada);
                    salas.add(salaCriada);
                    idSala += 1;
                }
                case "2" -> {
                    System.out.println("\nSalas do labirinto");
                    labTeste.imprimirLista();
                }
                case "3" -> {
                    labTeste.imprimirLista();
                    System.out.println("\nDigite o id da sala que deseja remover");
                    String idSalaRemovida = sc.nextLine();
                    salas.forEach(sala -> {
                        if(sala.getId() == Integer.parseInt(idSalaRemovida)) {
                            labTeste.remover(sala);
                        }
                    });
                    System.out.println(labTeste.tamanhoLista());
                }
                case "4" -> {
                    System.out.println("\nDigite um nome para o jogador");
                    String player = sc.nextLine();

                    Jogador jogadorCriado = new Jogador(player, pontuacaoInicial);
                    jogadores.add(jogadorCriado);
                }
                case "5" -> {
                    if(labTeste.tamanhoLista() < numeroMinimo) {
                        System.out.println("\nO jogo não pode ser iniciado com menos de 10 salas no labirinto");
                    } else if(jogadores.isEmpty()) {
                        System.out.println("\nCrie um jogador antes de inicar o jogo");
                    } else {
                        iniciarJogo(labTeste, jogadores.getFirst());
                    }
                }
                default -> throw new AssertionError("Opção invalida");
            }
        }
        
    }

    public static void iniciarJogo(Labirinto labirinto, Jogador jogador) {
        Scanner jogo = new Scanner(System.in);
        No<Sala> salaAtual = labirinto.inicio;
        int descontoPontos = 5;
        boolean fimDeJogo = false;

        System.out.println("\nO jogador " + jogador.getNome() + " começa na sala " + salaAtual.dado.getDescricao());
        while (!fimDeJogo) { 

            if(salaAtual == labirinto.fim) {
                System.out.println("Voce explorou tudo, parabens");
                System.out.println(jogador.getNome() + " | " + jogador.getPontuacao());
                System.out.println(Arrays.toString(jogador.getItens().toArray()));
                System.out.println("Salas visitadas: " + labirinto.tamanhoLista());
                break;
            }

            System.out.println("O que deseja fazer?");
            System.out.println("1 - AVANÇAR\n2 - VOLTAR\n3 - EXIBIR DESCRIÇÃO DA SALA");
            String opcao = jogo.nextLine();
            int evento = (int)Math.round(Math.random());

            

            switch (opcao) {
                case "1" -> {
                    System.out.println();
                    System.out.println(jogador.getNome() + " avança uma sala...");
                    salaAtual = salaAtual.proximo;
                    
                    switch (salaAtual.dado.getTipo()) {
                        case TipoSala.ARMADILHA -> {
                            System.out.println("\nVocê encontrou uma armadilha!");
                            if(evento == 0) {
                                System.out.println("O jogador perde " + descontoPontos + " pontos");
                                jogador.setPontuacao(jogador.getPontuacao() - descontoPontos);
                            } else {
                                System.out.println("O jogador volta uma casa");
                                salaAtual = salaAtual.anterior;
                            }
                        }
                        case TipoSala.PREMIO -> {
                            System.out.println("\nVocê entra numa sala de prêmio");

                            if(evento == 0) {
                                System.out.println("O jogador ganha " + descontoPontos + " pontos");
                                jogador.setPontuacao(jogador.getPontuacao() + descontoPontos);
                            } else {
                                System.out.println("\nParabéns! Você encontrou um item raro.");
                                System.out.println("O jogador pega o item");
                                jogador.pegarItem("Adaga de diamante");
                            }
                        }
                        case TipoSala.SAIDA -> {
                            if(salaAtual == labirinto.fim) {
                                System.out.println("\nVocê venceu o jogo!");
                                System.out.println(jogador.getNome() + " | " + jogador.getPontuacao() + "pts");
                                System.out.println("Seus itens: ");
                                System.out.println(Arrays.toString(jogador.getItens().toArray()));
                                System.out.println("Salas visitadas: " + labirinto.tamanhoLista());
                                fimDeJogo = true;
                            } else {
                                System.out.println("\nVocê chegou à saída, mas ainda não explorou tudo!");
                            }
                        }
                        case TipoSala.NORMAL -> {
                            System.out.println(jogador.getNome() + " chega a sala " + salaAtual.dado.getDescricao());
                        }
                        default -> throw new AssertionError();
                    }
                }
                case "2" -> {
                    if(salaAtual == labirinto.inicio) {
                        System.out.println("Voce esta na primeira sala ainda!");
                        break;
                    }

                    System.out.println();
                    System.out.println(jogador.getNome() + " volta uma sala...");
                    salaAtual = salaAtual.anterior;



                    switch (salaAtual.dado.getTipo()) {
                        case TipoSala.ARMADILHA -> {
                            System.out.println("\nVocê encontrou uma armadilha!");
                            if(evento == 0) {
                                System.out.println("O jogador perde " + descontoPontos + " pontos");
                                jogador.setPontuacao(jogador.getPontuacao() - descontoPontos);
                            } else {
                                System.out.println("O jogador volta uma casa");
                                salaAtual = salaAtual.anterior;
                            }
                        }
                        case TipoSala.PREMIO -> {
                            System.out.println("\nParabéns! Você encontrou um item raro.");

                            if(evento == 0) {
                                System.out.println("O jogador ganha " + descontoPontos + " pontos");
                                jogador.setPontuacao(jogador.getPontuacao() + descontoPontos);
                            } else {
                                System.out.println("O jogador pega o item");
                                jogador.pegarItem("Adaga de diamante");
                            }
                        }
                        case TipoSala.SAIDA -> {
                            if(salaAtual == labirinto.fim) {
                                System.out.println("Você venceu o jogo!");
                                System.out.println(jogador.getNome() + " | " + jogador.getPontuacao());
                                System.out.println(Arrays.toString(jogador.getItens().toArray()));
                                System.out.println("Salas visitadas: " + labirinto.tamanhoLista());
                                
                            } else {
                                System.out.println("Você chegou à saída, mas ainda não explorou tudo!");
                            }
                            
                        
                        }
                        case TipoSala.NORMAL -> {
                            System.out.println(jogador.getNome() + "chega a sala " + salaAtual.dado.getDescricao());

                        }

                        default -> throw new AssertionError();
                    }
                }
                case "3" -> {
                    System.out.println();
                    System.out.println(salaAtual.dado.getDescricao());
                }
                default -> {
                    jogo.close();
                    throw new AssertionError();
                }
            }

           
        }

        
    }
}
