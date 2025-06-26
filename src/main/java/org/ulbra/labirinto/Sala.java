
package org.ulbra.labirinto;
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author jojob
 */
public class Sala {
    private final int id;
    private final String descricao;
    private final TipoSala tipo;

    public Sala(String descricao, int id, TipoSala tipo) {
        this.descricao = descricao;
        this.id = id;
        this.tipo = tipo;
    }

    public int getId() {
        return id;
    }

    public String getDescricao() {
        return descricao;
    }

    public TipoSala getTipo() {
        return tipo;
    }
    
}
