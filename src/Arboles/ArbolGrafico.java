package Arboles;

import java.awt.*;
import java.util.*;
import javax.swing.*;

public class ArbolGrafico extends JPanel {
    
    private ArbolB ab;
    private HashMap posicionNodos = null; //Indica en que parte del mapa //pantalla// iran los nodos
    private HashMap subtreeSizes = null; //Indica en que parte del mapa //pantalla// iran los subarboles
    private boolean d = true;
    private int parent2child = 30, child2child = 40;
    private Dimension empty = new Dimension(0,0); //Indica el tamaño (ancho y alto) que tendran los rectangulos
    private FontMetrics fm = null; //Define una fuente

    public ArbolGrafico(){}
    
    public ArbolGrafico(ArbolB ab) {
          this.ab = ab;
          this.setBackground(Color.WHITE);
          posicionNodos = new HashMap();
          subtreeSizes = new HashMap();
          d = true;
         // repaint();      
    }
   
     // Calcula las posiciones de los nodos para saber en que posición van a ir dibujados los rectángulos 
    private void calcularPosiciones() {
         posicionNodos.clear();
         subtreeSizes.clear();
         Nodo root = this.ab.raiz; //obtengo el nodo raiz
         if (root != null) 
         {
             tamanoSubarbol(root);
             calcularPosicion(root, Integer.MAX_VALUE, Integer.MAX_VALUE, 0);
         }
    }
    
  
     // Calcula el Tamaño de cada subárbol y lo agrega al objeto subtreeSizes, que va a contener todos los subárboles del arbol.
     //Retorna el Tamano de cada subárbol.
    private Dimension tamanoSubarbol(Nodo n) {
          if (n == null) 
              return new Dimension(0,0);
 
          Dimension ld = tamanoSubarbol(n.izq);
          Dimension rd = tamanoSubarbol(n.der);
          
          int h = fm.getHeight() + parent2child + Math.max(ld.height, rd.height);
          int w = ld.width + child2child + rd.width;
          
          Dimension d = new Dimension(w, h);
          subtreeSizes.put(n, d);
          
          return d;
    }
    
     // Calcula la ubicación de cada nodo de cada subárbol y agrega cada nodo con un objeto de tipo Rectangule que tiene la ubicación 
     //y la información específica de dónde va a ser dibujado.
    private void calcularPosicion(Nodo n, int left, int right, int top) {
      if (n == null) 
          return;
      
      Dimension ld = (Dimension) subtreeSizes.get(n.izq);
      if (ld == null) 
          ld = empty;
      
      Dimension rd = (Dimension) subtreeSizes.get(n.der);
      if (rd == null) 
          rd = empty;
      
      int center = 0;
      
      if (right != Integer.MAX_VALUE)
          center = right - rd.width - child2child/2;
      else if (left != Integer.MAX_VALUE)
          center = left + ld.width + child2child/2;
      int width = fm.stringWidth(" "+ n.dato +" F.E.= "+n.dato); 
  
      
 
      posicionNodos.put(n,new Rectangle(center - width/2 - 3, top, width + 6, fm.getHeight()));
      
      calcularPosicion(n.izq, Integer.MAX_VALUE, center - child2child/2, top + fm.getHeight() + parent2child);
      calcularPosicion(n.der, center + child2child/2, Integer.MAX_VALUE, top + fm.getHeight() + parent2child);
    }
      
     /* Dibuja el árbol teniendo en cuenta las ubicaciones de los nodos y los subárboles calculadas anteriormente.
      g: Objeto de la clase Graphics2D que permite realizar el dibujo de las líneas, rectangulos y del String de la información que contiene el Nodo.
	  puntox: int con la posición en x desde donde se va a dibujar la línea hasta el siguiente hijo.
      puntoy: int con la posición en y desde donde se va a dibujar la línea hasta el siguiente hijo.
      yoffs: int con la altura del FontMetrics.    */
    private void dibujarArbol(Graphics2D g, Nodo n, int puntox, int puntoy, int yoffs) {
     if (n == null) 
         return;
     
     Rectangle r = (Rectangle) posicionNodos.get(n);
     g.draw(r);
  
        g.drawString(" "+n.dato+" F.E.= " +n.fe, r.x+3, r.y + yoffs);
     
     if (puntox != Integer.MAX_VALUE)
       
     g.drawLine(puntox, puntoy, (int)(r.x + r.width/2), r.y);
     
     dibujarArbol(g, n.izq, (int)(r.x + r.width/2), r.y + r.height, yoffs);
     dibujarArbol(g, n.der, (int)(r.x + r.width/2), r.y + r.height, yoffs);
   }  

   
     // Sobreescribe el metodo paint y se encarga de pintar todo el árbol.
     // g: Objeto de la clase Graphics.
   public void paint(Graphics g) {
         super.paint(g);
         fm = g.getFontMetrics();

         if (d) 
         {
           calcularPosiciones();
           d = false;
         }
         
         Graphics2D g2d = (Graphics2D) g;
         g2d.translate(getWidth() / 2, parent2child);
         dibujarArbol(g2d, this.ab.getRaiz(), Integer.MAX_VALUE, Integer.MAX_VALUE, fm.getLeading() + fm.getAscent());
         fm = null;
   }
   
 }