package formula;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import tools.Codificador;

public class Formula implements Serializable {
	private int phi;
	private String var;
	private List<List<String>> clausulas;
	private String T_m;
	
	public Formula(String formula, String variables) {
		try {
			if(!formula.matches("([(]!?\\w!?\\w!?\\w[)]){1,}")) {
				throw new Exception();
			}
		} catch (Exception e) {
			System.out.println("La fórmula no es válida");
			e.printStackTrace();
		}
		clausulas = Codificador.codificarInput(formula,variables);
		setT_m(Integer.toString(clausulas.size()));
		
		char[] chars = new char[variables.length()];
		Arrays.fill(chars, 'b');
		setVar(new String(chars));
		
		setPhi(variables.length());	
	}
	
	public Formula() {
		phi = 0;
		var = "";
		clausulas = null;
		T_m = "";
	}
	
	public List<List<String>> getClausulas() {
		return clausulas;
	}
	
	public void setClausulas(List<List<String>> clausulas) {
		this.clausulas = clausulas;
	}

	public int getPhi() {
		return phi;
	}

	public void setPhi(int phi) {
		this.phi = phi;
	}

	public String getVar() {
		return var;
	}

	public void setVar(String var) {
		this.var = var;
	}

	public String getT_m() {
		return T_m;
	}

	public void setT_m(String t_m) {
		T_m = t_m;
	}
	
	public String toString() {
		return phi + var + T_m + clausulas.toString();
	}

}
