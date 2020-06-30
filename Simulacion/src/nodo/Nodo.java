package nodo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import formula.Formula;
import tools.Polarizacion;

public abstract class Nodo implements Serializable {
	private List<Nodo> listaAdyacentes;
	private Polarizacion polarizaci�n;
	private int numNodo;
	
	public Nodo() {
		listaAdyacentes = new ArrayList<Nodo>();
		polarizaci�n = Polarizacion.NEUTRO;
	}
	
	public Nodo(List<Nodo> listaAdyacentes, Polarizacion polarizaci�n) {
		this.listaAdyacentes = listaAdyacentes;
		this.polarizaci�n = polarizaci�n;
	}
	
	public Nodo(List<Nodo> listaAdyacentes, Polarizacion polarizaci�n, int numNodo) {
		this.listaAdyacentes = listaAdyacentes;
		this.polarizaci�n = polarizaci�n;
		this.numNodo = numNodo;
	}
	
	public abstract Formula aplicarReglas(Formula formula);	
	public abstract int getPosVar();
	public abstract void setPosVar(int posVar);
	
	public List<Nodo> getListaAdyacentes() {
		return listaAdyacentes;
	}
	
	public void setListaAdyacentes(List<Nodo> listaAdyacentes) {
		this.listaAdyacentes = listaAdyacentes;
	}
	
	public Polarizacion getPolarizaci�n() {
		return polarizaci�n;
	}
	
	public void setPolarizaci�n(Polarizacion polarizaci�n) {
		this.polarizaci�n = polarizaci�n;
	}

	public int getNumNodo() {
		return numNodo;
	}

	public void setNumNodo(int numNodo) {
		this.numNodo = numNodo;
	}
	
	public void addAdyacente(Nodo nuevoNodo) {
		this.listaAdyacentes.add(nuevoNodo);
	}
	
}
