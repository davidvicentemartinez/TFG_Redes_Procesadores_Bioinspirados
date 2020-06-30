package nodo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import formula.Formula;
import tools.Polarizacion;

public abstract class Nodo implements Serializable {
	private List<Nodo> listaAdyacentes;
	private Polarizacion polarización;
	private int numNodo;
	
	public Nodo() {
		listaAdyacentes = new ArrayList<Nodo>();
		polarización = Polarizacion.NEUTRO;
	}
	
	public Nodo(List<Nodo> listaAdyacentes, Polarizacion polarización) {
		this.listaAdyacentes = listaAdyacentes;
		this.polarización = polarización;
	}
	
	public Nodo(List<Nodo> listaAdyacentes, Polarizacion polarización, int numNodo) {
		this.listaAdyacentes = listaAdyacentes;
		this.polarización = polarización;
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
	
	public Polarizacion getPolarización() {
		return polarización;
	}
	
	public void setPolarización(Polarizacion polarización) {
		this.polarización = polarización;
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
