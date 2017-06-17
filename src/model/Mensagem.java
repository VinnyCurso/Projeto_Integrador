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
public class Mensagem {
    
    private int codigo;
    private String usuario;
    private String categoria;
    private String tipo;
    private String mensagem;


    public Mensagem() {
    }

    public Mensagem(int codigo, String usuario, String categoria, String tipo, String mensagem) {
        this.codigo = codigo;
        this.usuario = usuario;
        this.categoria = categoria;
        this.tipo = tipo;
        this.mensagem = mensagem;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    @Override
    public String toString() {
        return "Mensagem{" + "usuario=" + usuario + ", tipo=" + tipo + ", mensagem=" + mensagem + '}';
    }

    
    
    
}
