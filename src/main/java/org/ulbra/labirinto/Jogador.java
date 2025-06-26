/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package org.ulbra.labirinto;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jojob
 */
public class Jogador {
    private final String nome;
    private List<String> itens = new ArrayList<>();
    private int pontuacao;
    private Sala salaAtual;

    public Sala getSala() {
        return salaAtual;
    }

    public Jogador(String nome, int pontuacaoInicial) {
        this.nome = nome;
        this.pontuacao = pontuacaoInicial;
    }

    public List<String> getItens() {
        return itens;
    }

    public int getPontuacao() {
        return pontuacao;
    }

    public void pegarItem(String item) {
        this.itens.add(item);
    }

    public void setPontuacao(int pontuacao) {
        this.pontuacao = pontuacao;
    }

    public void setSalaAtual(Sala salaAtual) {
        this.salaAtual = salaAtual;
    }

    public String getNome() {
        return nome;
    }
}
