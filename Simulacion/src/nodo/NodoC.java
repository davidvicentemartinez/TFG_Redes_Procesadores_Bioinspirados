package nodo;

import java.util.List;

import formula.Formula;
import tools.Polarizacion;

public class NodoC extends Nodo{

	public NodoC() {
		super();
		// TODO Auto-generated constructor stub
	}

	public NodoC(List<Nodo> listaAdyacentes, Polarizacion polarización, int numNodo) {
		super(listaAdyacentes, polarización, numNodo);
		// TODO Auto-generated constructor stub
	}

	public NodoC(List<Nodo> listaAdyacentes, Polarizacion polarización) {
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
		int ult = clausula.size();
		String aux = clausula.get(ult-1);
		
		if(aux.equals("PRIMA")) {
			if(clausula.get(2).endsWith("1")) {
				phi--;
			}
			else {
				phi++;
			}
			clausula.set(ult-1, "C");
			clausulas.set(this.getNumNodo(), clausula);
		}
		
		formulaNueva.setClausulas(clausulas);
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
