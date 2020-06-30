package nodo;

import java.util.List;

import formula.Formula;
import tools.Polarizacion;

public class NodoX extends Nodo {

	public NodoX() {
		super();
		// TODO Auto-generated constructor stub
	}

	public NodoX(List<Nodo> listaAdyacentes, Polarizacion polarización) {
		super(listaAdyacentes, polarización);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Formula aplicarReglas(Formula formula) {
		Formula formulaNueva = new Formula();
		formulaNueva.setClausulas(formula.getClausulas());
		
		String var = formula.getVar();
		String t_m = formula.getT_m();
		int phi = formula.getPhi();
		
		formulaNueva.setVar(var);
		
		if (t_m.length() == 1) {
			int tm = Integer.decode(t_m);
			if(tm == 0) {
				t_m = "0*";
				phi--;
			}
			else {
				tm--;
				t_m = String.valueOf(tm) + "'";
				phi++;
			}
		}
				
		formulaNueva.setT_m(t_m);
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
