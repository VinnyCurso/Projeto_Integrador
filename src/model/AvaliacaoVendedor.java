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
public class AvaliacaoVendedor {
    
    private int codigo;
    private String anuncio;
    private String usuario;
    private int nota;
    private String sugestao;

    public AvaliacaoVendedor() {
    }

    public AvaliacaoVendedor(int codigo, int nota, String anuncio, String usuario, String sugestao) {
        this.codigo = codigo;
        this.nota = nota;
        this.anuncio = anuncio;
        this.usuario = usuario;
        this.sugestao = sugestao;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public int getNota() {
        return nota;
    }

    public void setNota(int nota) {
        this.nota = nota;
    }

    public String getAnuncio() {
        return anuncio;
    }

    public void setAnuncio(String anuncio) {
        this.anuncio = anuncio;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getSugestao() {
        return sugestao;
    }

    public void setSugestao(String sugestao) {
        this.sugestao = sugestao;
    }

    
    
}
