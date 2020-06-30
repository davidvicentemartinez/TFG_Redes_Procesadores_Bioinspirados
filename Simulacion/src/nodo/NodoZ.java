package nodo;

import java.util.ArrayList;
import java.util.List;

import formula.Formula;
import tools.Polarizacion;

public class NodoZ extends Nodo {

	public NodoZ() {
		super();
		// TODO Auto-generated constructor stub
	}

	public NodoZ(List<Nodo> listaAdyacentes, Polarizacion polarización) {
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
		
		//SUSTITUCIÓN T_M
		if(t_m.endsWith("'")) {
			t_m = t_m.substring(0,t_m.length() - 1) + "k";
		}
		
		//SUSTITUCIÓN VAR
		char[] aux = var.toCharArray();
		String nuevaVar = "";
		for(int i=0; i<aux.length; i++) {
			if(aux[i] == 'v') {
				aux[i] = 'V';
				phi++;
			}
			if(aux[i] == 'f') {
				aux[i] = 'F';
				phi--;
			}
			nuevaVar = nuevaVar + String.valueOf(aux[i]);
		}
		var = nuevaVar;
		
		//SUSTITUCION CLAUSULAS
		for(int i=0; i<clausulas.size();i++) {
			List<String> clausula = clausulas.get(i);
			List<String> nuevaClausula = new ArrayList<String>();
			int l = clausula.size();
			
			if(clausula.get(l-1).equals("A")) {
				if(clausula.get(0).endsWith("1")) {
					phi = phi + 2;
				}
				nuevaClausula.add(clausula.get(0));
				nuevaClausula.add(clausula.get(1));
				nuevaClausula.add(clausula.get(2));
				nuevaClausula.add("PRIMA_2");
				clausulas.set(i, nuevaClausula);
			}
			else if(clausula.get(l-1).equals("B")) {
				if(clausula.get(1).endsWith("1")) {
					phi = phi + 2;
				}
				nuevaClausula.add(clausula.get(0));
				nuevaClausula.add(clausula.get(1));
				nuevaClausula.add(clausula.get(2));
				nuevaClausula.add("PRIMA_2");
				clausulas.set(i, nuevaClausula);
			}
			else if(clausula.get(l-1).equals("C")) {
				if(clausula.get(2).endsWith("1")) {
					phi = phi + 2;
				}
				nuevaClausula.add(clausula.get(0));
				nuevaClausula.add(clausula.get(1));
				nuevaClausula.add(clausula.get(2));
				nuevaClausula.add("PRIMA_2");
				clausulas.set(i, nuevaClausula);
			}
			
		}
		
		formulaNueva.setT_m(t_m);
		formulaNueva.setClausulas(clausulas);
		formulaNueva.setVar(var);;
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
