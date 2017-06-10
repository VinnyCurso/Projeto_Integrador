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
    private FileReader imagem;

    public Imagem() {
    }

    public Imagem(int codigo, FileReader imagem) {
        this.codigo = codigo;
        this.imagem = imagem;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public FileReader getImagem() {
        return imagem;
    }

    public void setImagem(FileReader imagem) {
        this.imagem = imagem;
    }
  
}
