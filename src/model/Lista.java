package model;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;


public class Lista {

    private Anuncio primeiro;
    private Anuncio ultimo;

    //cria uma lista vazia
    Lista() {
        primeiro = null;
        ultimo = null;
    }

    //verifica se a lista está vazia
//    boolean eVazia(){
//        if(primeiro == null)
//            return true;
//        else
//            return false;
//    }
    public boolean eVazia() {
        return primeiro == null;
    }

    //insere objeto Aluno no inicio da lista
    public void addInicio(Anuncio ob) {
        //é primeiro?
        ob.setProximo(null);
        if (eVazia()) {
            primeiro = ob;
            ultimo = ob;
        } else {
            ob.setProximo(primeiro);
            primeiro = ob;
        }
    }

    public void addFim(Anuncio ob) {
        ob.setProximo(null);
        if (eVazia()) {
            primeiro = ob;
            ultimo = ob;
        } else {
            ultimo.setProximo(ob);
            ultimo = ob;
        }
    }

    public void addOrdenado(Anuncio ob) {
        ob.setProximo(null);

        if (eVazia()) {
            addInicio(ob);
            return;
        }

        Anuncio anterior = null;
        Anuncio atual = primeiro;

        while (atual != null && atual.getCodigo() < ob.getCodigo()) {
            anterior = atual;
            atual = anterior.getProximo();
        }

        //Insere no inicio
        if (anterior == null) {
            addInicio(ob);
            return;
        }

        //Insere no fim
        if (atual == null) {
            addFim(ob);
            return;
        }

        //Insere no meio
        anterior.setProximo(ob);
        ob.setProximo(atual);

    }

    public Anuncio addRemove(int codi) {
        if (eVazia()) {
           JOptionPane.showMessageDialog(null,"Não existe elemento na lista");
        }
        Anuncio anterior = this.primeiro;
        Anuncio atual = null;

        while (anterior != null && anterior.getCodigo() != codi) {
            atual = anterior;
            anterior = anterior.getProximo();
        }

        if (anterior != null) {
            atual.setProximo(anterior.getProximo());
            return anterior;
        }

        if (anterior == ultimo) {
            ultimo = atual;
            return anterior;
        }

        return null;
    }

    public List<Anuncio> mostraLista() {
        List<Anuncio> listas = new ArrayList();
        if (eVazia()) {
             JOptionPane.showMessageDialog(null,"Não existe elemento na lista");
            return null;
        }

      
        Anuncio aux = primeiro;
        while (aux != null) {
            listas.add(aux);
            aux = aux.getProximo();
        }
        return listas;
    }
}
