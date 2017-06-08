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
public class Negociacao {
    
    private int codigo;
    private String tipoNegociacao;
    private String formaNegociacao;
    private String descricao;
    private Mensagem mensagem;

    public Negociacao() {
    }

    public Negociacao(int codigo, String tipoNegociacao, String formaNegociacao, String descricao, Mensagem mensagem) {
        this.codigo = codigo;
        this.tipoNegociacao = tipoNegociacao;
        this.formaNegociacao = formaNegociacao;
        this.descricao = descricao;
        this.mensagem = mensagem;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getTipoNegociacao() {
        return tipoNegociacao;
    }

    public void setTipoNegociacao(String tipoNegociacao) {
        this.tipoNegociacao = tipoNegociacao;
    }

    public String getFormaNegociacao() {
        return formaNegociacao;
    }

    public void setFormaNegociacao(String formaNegociacao) {
        this.formaNegociacao = formaNegociacao;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Mensagem getMensagem() {
        return mensagem;
    }

    public void setMensagem(Mensagem mensagem) {
        this.mensagem = mensagem;
    }
    
    
    
    
}
