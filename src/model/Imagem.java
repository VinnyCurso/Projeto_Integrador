/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.FileReader;

/**
 *
 * @author vinicius caetano
 */
public class Imagem {
    
    private int codigo;
    private byte[] imagem;

    public Imagem() {
    }

    public Imagem(int codigo, byte[] imagem) {
        this.codigo = codigo;
        this.imagem = imagem;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public byte[] getImagem() {
        return imagem;
    }

    public void setImagem(byte[] imagem) {
        this.imagem = imagem;
    }
    
    
}
