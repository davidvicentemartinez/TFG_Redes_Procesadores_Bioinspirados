package nodo;

import java.util.List;

import formula.Formula;
import tools.Polarizacion;

public class NodoOut extends Nodo {

	public NodoOut() {
		super();
		// TODO Auto-generated constructor stub
	}

	public NodoOut(List<Nodo> listaAdyacentes, Polarizacion polarización) {
		super(listaAdyacentes, polarización);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Formula aplicarReglas(Formula formula) {
		return null;
		
	}

	@Override
	public int getPosVar() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setPosVar(int posVar) {
		// TODO Auto-generated method stub
		
	}



}
