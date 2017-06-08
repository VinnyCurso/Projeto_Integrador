/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.time.LocalDate;

/**
 *
 * @author vinicius caetano
 */
public class Venda {
    
    private int codigo;
    private LocalDate dataHora;
    private float valorTotal;

    public Venda() {
    }

    public Venda(int codigo, LocalDate dataHora, float valorTotal) {
        this.codigo = codigo;
        this.dataHora = dataHora;
        this.valorTotal = valorTotal;
    }
    
    

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public LocalDate getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDate dataHora) {
        this.dataHora = dataHora;
    }

    public float getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(float valorTotal) {
        this.valorTotal = valorTotal;
    }
 
    
}
