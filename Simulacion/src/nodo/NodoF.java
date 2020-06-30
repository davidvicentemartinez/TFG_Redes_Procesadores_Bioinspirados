package nodo;

import java.util.List;

import formula.Formula;
import tools.Polarizacion;

public class NodoF extends Nodo{
	
	private int posVar;

	public NodoF() {
		super();
		// TODO Auto-generated constructor stub
	}

	public NodoF(List<Nodo> listaAdyacentes, Polarizacion polarización, int numNodo) {
		super(listaAdyacentes, polarización, numNodo);
		// TODO Auto-generated constructor stub
	}

	public NodoF(List<Nodo> listaAdyacentes, Polarizacion polarización) {
		super(listaAdyacentes, polarización);
		// TODO Auto-generated constructor stub
	}
	
	public NodoF(List<Nodo> listaAdyacentes, Polarizacion polarización, int numNodo, int posVar) {
		super(listaAdyacentes, polarización, numNodo);
		this.setPosVar(posVar);
	}

	public int getPosVar() {
		return posVar;
	}

	public void setPosVar(int posVar) {
		this.posVar = posVar;
	}

	@Override
	public Formula aplicarReglas(Formula formula) {
		Formula formulaNueva = new Formula();
		
		List<List<String>> clausulas = formula.getClausulas();
		String var = formula.getVar();
		String t_m = formula.getT_m();
		int phi = formula.getPhi();
		
		formulaNueva.setT_m(t_m);
		
		List<String> clausula = clausulas.get(this.getNumNodo());
		int ult = clausula.size();
		int posVar = this.getPosVar();

		String e = clausula.get(posVar);
		int pos_e = Character.getNumericValue(e.charAt(0));

		if(e.endsWith("1")) {
			String aux1 = var.substring(pos_e, pos_e + 1);
			String aux2 = var.substring(0, pos_e);
			String aux3 = var.substring(pos_e + 1);
			if(aux1.equals("F")) {
				aux1 = "f";
				phi++;
				var = aux2 + aux1 + aux3;	
			}
		}

		formulaNueva.setClausulas(clausulas);
		formulaNueva.setVar(var);;
		formulaNueva.setPhi(phi);
		
		return formulaNueva;
		
	}


}
