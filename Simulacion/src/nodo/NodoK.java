package nodo;

import java.util.ArrayList;
import java.util.List;

import formula.Formula;
import tools.Polarizacion;

public class NodoK extends Nodo {

	public NodoK() {
		super();
		// TODO Auto-generated constructor stub
	}

	public NodoK(List<Nodo> listaAdyacentes, Polarizacion polarización, int numNodo) {
		super(listaAdyacentes, polarización, numNodo);
		// TODO Auto-generated constructor stub
	}

	public NodoK(List<Nodo> listaAdyacentes, Polarizacion polarización) {
		super(listaAdyacentes, polarización);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Formula aplicarReglas(Formula formula) {
		Formula formulaNueva = new Formula();
		
		List<List<String>> clausulas = formula.getClausulas();
		String var = formula.getVar();
		String t_m = formula.getT_m();
		int phi = formula.getPhi();
		
		formulaNueva.setVar(var);
		formulaNueva.setT_m(t_m);
		
		List<String> clausula = clausulas.get(this.getNumNodo());
		String aux = clausula.get(clausula.size()-1);
		boolean modificado = false;
		List<String> nuevaClausula = new ArrayList<String>();
		List<List<String>> nuevasClausulas = new ArrayList<List<String>>();
		
		if(!aux.equals("PRIMA") && !aux.equals("PRIMA_2") && !aux.equals("A") && !aux.equals("B") && !aux.equals("C")) {
			modificado = true;
			
			nuevaClausula.add(clausula.get(0));
			nuevaClausula.add(clausula.get(1));
			nuevaClausula.add(clausula.get(2));
			nuevaClausula.add("PRIMA");
			
			for(int i=0; i<clausulas.size();i++) {
				if(i == this.getNumNodo()) {
					nuevasClausulas.add(nuevaClausula);
				}
				else {
					nuevasClausulas.add(clausulas.get(i));
				}				
			}
			phi--;
		}
		
		if(modificado) {
			formulaNueva.setClausulas(nuevasClausulas);
		}
		else {
			formulaNueva.setClausulas(clausulas);
		}
		
		formulaNueva.setPhi(phi);
		
		
		return formulaNueva;
		
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
