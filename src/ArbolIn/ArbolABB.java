package ArbolIn;

import java.io.*;
import java.util.*;
import javax.swing.JFrame;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Ellipse2D;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class ArbolABB {

    Nodo raiz;
    int cont=0;
    String oracion="";

    public ArbolABB() {
        raiz=null;
    }

    public void preorden (Nodo nodo){
        if(nodo == null)
            return;
        else{
            System.out.print (nodo.dato + " ");

            preorden (nodo.izq);
            preorden (nodo.der);
           
        }
    }

    public JPanel getdibujo(){
      return new ArbolGrafico(this);
  }

  public void inserta(String aux, String sujeto, String verbo, String predicado, String simbolo){
    insertar(aux);
    insertar(sujeto);
    insertar(verbo);
    insertar(predicado);
    insertar(simbolo);
}

public void insertar (String aux) {
    Nodo nuevo ;
    nuevo = new Nodo ();
    nuevo.dato = aux;
    nuevo.izq = null;
    nuevo.der = null;
    cont++;
    if (raiz == null){
        raiz = nuevo;
    }else {
        Nodo anterior = null, reco;
        reco = raiz;
       // while (reco != null)  {
        switch(cont){
            case 2:
            reco.izq = nuevo;
            break;
            case 3:
            reco = reco.izq;
            reco.izq = nuevo;
            break;
            case 4:
            reco.der = nuevo;
            break;
            case 5:
            reco = reco.der;
            reco.izq = nuevo;
            break;
        }
        
    } 
    
}

public Nodo getRaiz() {
    return this.raiz;
}

public void setRaiz(Nodo nodo) {
    this.raiz = nodo;
}

public void getOracion (Nodo reco)
      {
          if (reco != null)
          {
              oracion += reco.dato + " ";
              getOracion (reco.izq);
              getOracion (reco.der);
          }
      }

      public String getOracion ()
      {
          getOracion (raiz);
          return oracion;
      }
}