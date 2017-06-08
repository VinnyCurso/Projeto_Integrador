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
public class AvaliacaoComprador {
    
     private int codigo;
    private int nota;
    private Anuncio anuncio;
    private Usuario usuario;

    public AvaliacaoComprador() {
    }

    public AvaliacaoComprador(int codigo, int nota, Anuncio anuncio, Usuario usuario) {
        this.codigo = codigo;
        this.nota = nota;
        this.anuncio = anuncio;
        this.usuario = usuario;
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

    public Anuncio getAnuncio() {
        return anuncio;
    }

    public void setAnuncio(Anuncio anuncio) {
        this.anuncio = anuncio;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
    
}
