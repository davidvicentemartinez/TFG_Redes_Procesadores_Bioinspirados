package nodo;

import java.util.List;

import formula.Formula;
import tools.Polarizacion;

public class NodoIn extends Nodo {

	public NodoIn() {
		super();
		// TODO Auto-generated constructor stub
	}

	public NodoIn(List<Nodo> listaAdyacentes, Polarizacion polarización) {
		super(listaAdyacentes, polarización);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Formula aplicarReglas(Formula formula) {
		return null;
		
	}
	
	public Formula aplicarReglasV(Formula formula) {
		Formula formulaNueva = new Formula();
		formulaNueva.setClausulas(formula.getClausulas());
		
		String var = formula.getVar();
		String t_m = formula.getT_m();
		int phi = formula.getPhi();
		
		formulaNueva.setPhi(phi);
		formulaNueva.setT_m(t_m);
		formulaNueva.setVar(var);
		
		
		if(var.contains("b")) {
			var = var.replaceFirst("b","V");
			phi--;
		}
		if(t_m.contains("k")) {
			t_m = String.valueOf(t_m.charAt(0));
			phi--;
		}
		formulaNueva.setPhi(phi);
		formulaNueva.setT_m(t_m);
		formulaNueva.setVar(var);
		
		return formulaNueva;
	}
	
	public Formula aplicarReglasF(Formula formula) {
		Formula formulaNueva = new Formula();
		formulaNueva.setClausulas(formula.getClausulas());
		
		String var = formula.getVar();
		String t_m = formula.getT_m();
		int phi = formula.getPhi();
		
		formulaNueva.setPhi(phi);
		formulaNueva.setT_m(t_m);
		formulaNueva.setVar(var);
		
		
		if(var.contains("b")) {
			var = var.replaceFirst("b","F");
			phi--;
		}
		if(t_m.contains("k")) {
			t_m = String.valueOf(t_m.charAt(0));
			phi--;
		}
		formulaNueva.setPhi(phi);
		formulaNueva.setT_m(t_m);
		formulaNueva.setVar(var);
		
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
