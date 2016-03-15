package ArbolIn;

import java.io.*;
import java.util.*;

public class Nodo{
	String dato;
	Nodo der, izq;

	public Nodo(String dato){
		this.dato=dato;
	}

	public Nodo(){
		
	}

	public Nodo getIzq(){
		return izq;
	}

	public Nodo getDer(){
		return der;
	}

	public String getDato(){
		return dato;
	}

	public void setIzq(Nodo izq){
		this.izq=izq;
	}

	public void setDer(Nodo der){
		this.der=der;
	}

}	