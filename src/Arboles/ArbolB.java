package Arboles;

import javafx.scene.control.TextArea;


 

public class ArbolB {
   
    TextArea txtPre,txtMovimientos;
     Nodo raiz = null;
     public ArbolB(TextArea txtPre,TextArea txtMovimientos){
          this.txtPre = txtPre;
         this.txtMovimientos = txtMovimientos;
     }
     
    public int altura(Nodo N) {
        if (N == null) {
            return 0;
        }
        return N.altura;
    }
    public Nodo getRaiz(){
        return raiz;
    }
    
    int max(int a, int b) {
        return (a > b) ? a : b;
    }
 
   
    public Nodo rotacionSI(Nodo y) {
        Nodo x = y.izq;
        Nodo aux = x.der;
 
        
        x.der = y;
        y.izq = aux;
 
       
        y.altura = max(altura(y.izq), altura(y.der)) + 1;
        x.altura = max(altura(x.izq), altura(x.der)) + 1;
 
        
        return x;
    }
 
   
    public Nodo rotacionSD(Nodo x) {
        Nodo y = x.der;
        Nodo aux = y.izq;
     
        y.izq = x;
        x.der = aux;
   
        x.altura = max(altura(x.izq), altura(x.der)) + 1;
        y.altura = max(altura(y.izq), altura(y.der)) + 1;
 
        
        return y;
    }
 
    
    public int getFE(Nodo nodo) {
        if (nodo == null) {
            return 0;
        }
        return altura(nodo.izq) - altura(nodo.der);
    }
    public int getFEquilibrio(Nodo nodo) {
        if (nodo == null) {
            return 0;
        }
        return altura(nodo.der)-altura(nodo.izq);
    }
    public void imprimirFE(){
        imprimirFE(raiz);
    }
    public void imprimirFE(Nodo nodo){
        if(nodo != null){
            nodo.fe =getFEquilibrio(nodo);
            System.out.println("Dato: "+nodo.dato +" Frecuencia: " + nodo.fe);
            imprimirFE(nodo.izq);
            imprimirFE(nodo.der);
        }
    }

    public void insertar(int dato){
        txtMovimientos.setText(txtMovimientos.getText() + "\n--------Inserción numero: "+dato+"----");
        raiz = insertarBalanceado(raiz, dato);
        
        imprimirFE();
    }
 
    public Nodo insertarBalanceado(Nodo node, int dato) {
 
       
        if (node == null) {
            return (new Nodo(dato));
        }
 
        if (dato < node.dato) {
            node.izq = insertarBalanceado(node.izq, dato);
        } else {
            node.der = insertarBalanceado(node.der, dato);
        }
 
        /*  Actualiza altura  */
        node.altura = max(altura(node.izq), altura(node.der)) + 1;
 
        /*  Se obtiene factor de equilibrio */
        int balance = getFE(node);
 
        
       
        if (balance > 1 && dato < node.izq.dato) {
            System.out.println("Rotacion simple izquierda");
            txtMovimientos.setText(txtMovimientos.getText()+"\nRotación simple izquierda");
            return rotacionSI(node);
        }
 
        if (balance < -1 && dato > node.der.dato) {
            System.out.println("Rotacion simple derecha");
            txtMovimientos.setText(txtMovimientos.getText()+"\nRotacion simple derecha");
            return rotacionSD(node);
        }
 
        // Rotacion doble izquierda
        if (balance > 1 && dato > node.izq.dato) {
            System.out.println("Rotacion doble izquierda - derecha");
            txtMovimientos.setText(txtMovimientos.getText()+"\nRotacion doble izquierda - derecha");
            node.izq = rotacionSD(node.izq);
            return rotacionSI(node);
        }
 
        // Rotacion doble derecha
        if (balance < -1 && dato < node.der.dato) {
            System.out.println("Rotacion doble derecha - izquierda");
            txtMovimientos.setText(txtMovimientos.getText() +"\nRotacion doble derecha - izquierda");
            node.der = rotacionSI(node.der);
            return rotacionSD(node);
        }
 
        return node;
    }
 
    public Nodo minimoDerecho(Nodo node) {
        Nodo current = node;
        while (current.izq != null) {
            current = current.izq;
        }
        return current;
    }

    public void eliminar(int dato){
        if(raiz!= null)
        txtMovimientos.setText(txtMovimientos.getText() + "\n--------Eliminación numero: "+dato+"----");
      else
            txtMovimientos.setText(" ");
        raiz = eliminarBalanceado(raiz,dato);
       imprimirFE();
    }
 
    public Nodo eliminarBalanceado(Nodo raiz, int dato) {
 
       
        if (raiz == null) {
            
            return raiz;
        }
 
        
        if (dato < raiz.dato) {
            raiz.izq = eliminarBalanceado(raiz.izq, dato);
        } 
 
   
        else if (dato > raiz.dato) {
            raiz.der = eliminarBalanceado(raiz.der, dato);
        } 
         
        
        else {
 
           
            if ((raiz.izq == null) || (raiz.der == null)) {
                Nodo temp = null;
                if (temp == raiz.izq) {
                    temp = raiz.der;
                } else {
                    temp = raiz.izq;
                }
 
                // Caso: hoja
                if (temp == null) {
                    temp = raiz;
                    raiz = null;
                } else // Caso: 1 hijo
                {
                    raiz = temp; 
                }
            } else {
 
                // 2 hijos
                Nodo temp = minimoDerecho(raiz.der);
 
               
                raiz.dato = temp.dato;
 
            
                raiz.der = eliminarBalanceado(raiz.der, temp.dato);
            }
        }
 
      
        if (raiz == null) {
            return raiz;
        }
 
        // Actualiza la altura del nodo actual
        raiz.altura = max(altura(raiz.izq), altura(raiz.der)) + 1;
 
        //Consigue factor de equilibrio
        int balance = getFE(raiz);
 
        //Rotacion simple derecha
        if (balance > 1 && getFE(raiz.izq) >= 0) {
            System.out.println("Rotacion izquierda");
            txtMovimientos.setText(txtMovimientos.getText()+"\nRotación simple izquierda");
            return rotacionSI(raiz);
        }
 
        
        if (balance > 1 && getFE(raiz.izq) < 0) {
            System.out.println("Rotacion izquierda - derecha ");
            txtMovimientos.setText(txtMovimientos.getText()+"\nRotacion doble izquierda - derecha");
            raiz.izq = rotacionSD(raiz.izq);
            return rotacionSI(raiz);
        }
 
      
        if (balance < -1 && getFE(raiz.der) <= 0) {
            System.out.println("Rotacion simple derecha");
             txtMovimientos.setText(txtMovimientos.getText()+"\nRotacion simple derecha");
            return rotacionSD(raiz);
        }
 
        // Rotacion doble derecha
        if (balance < -1 && getFE(raiz.der) > 0) {
            txtMovimientos.setText(txtMovimientos.getText() +"\nRotacion doble derecha - izquierda");
            System.out.println("Rotacion derecha - izquierda");
            raiz.der = rotacionSI(raiz.der);
            return rotacionSD(raiz);
        }
 
        return raiz;
    }
 
    public void preorder(){
        txtPre.setText("");
        preOrder(raiz);
        
    }
    public void preOrder(Nodo node) {
        if (node != null) {
            txtPre.setText(txtPre.getText() + "\nDato: "+node.dato+" Frecuencia: "+node.fe);
            System.out.print(node.dato + " ");
            preOrder(node.izq);
            preOrder(node.der);
        }
    }
 
    
}