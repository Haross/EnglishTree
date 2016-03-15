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
public class Main{
	
	

	public static void main(String[] args) {
		ArbolABB abo = new ArbolABB();

		//Do you work hard?
		abo.inserta("do","you","work","hard","?");
		//abo.preorden(abo.raiz);
		//abo.getOracion();
		 System.out.println(abo.getOracion());
		  JFrame arbolB = new JFrame( "Arbol Binario" );
        arbolB.add(abo.getdibujo() ); // agrega el panel de dibujo a la ventana
        arbolB.setSize( 1350, 720 ); // establece el tamaño de la ventana
        arbolB.setVisible( true ); // muestra la ventana
	}
}