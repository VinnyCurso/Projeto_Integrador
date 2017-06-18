/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author vinicius caetano
 */
public class Anuncio {
    
    private int codigo;
    private String descricao;
    private String categoria;
    private float valor;
    
     private Anuncio proximo;

    public Anuncio() {
    }

    public Anuncio(int codigo, String descricao, float valor, String categoria) {
        this.codigo = codigo;
        this.descricao = descricao;
        this.valor = valor;
        this.categoria = categoria;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
    
        public Anuncio getProximo() {
        return proximo;
    }

    public void setProximo(Anuncio proximo) {
        this.proximo = proximo;
    }
    
    
    
}
