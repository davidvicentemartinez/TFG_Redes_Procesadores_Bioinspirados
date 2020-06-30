package formula;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ListaFormulas implements Serializable{
	
	private List<Formula> listaFormulas;
	
	public ListaFormulas() {
		listaFormulas = new ArrayList<Formula>();
	}
	
	public ListaFormulas(List<Formula> listaFormulas) {
		this.listaFormulas = listaFormulas;
	}
	
	
	
	public void addFormula(Formula formula) {
		listaFormulas.add(formula);
	}
	
	public void addFormula(Formula formula, int index) {
		listaFormulas.add(index, formula);
	}
	 
	public Formula getFormula(int index) {
		return listaFormulas.get(index);
	}
	
	public void remove(int index) {
		listaFormulas.remove(index);
	}
	
	public void remove(Formula formula) {
		listaFormulas.remove(formula);
	}
	
	public boolean isEmpty() {
		return listaFormulas.isEmpty();
	}
	
	public boolean contains(Formula formula) {
		String aux = formula.toString();
		boolean result = false;
		for(Formula formulaLista : listaFormulas) {
			if(formulaLista.toString().equals(aux)) {
				result = true;
				break;
			}
		}
		return result;
	}
	
	public int length() {
		return listaFormulas.size();
	}
	
	public String toString() {
		String output = "";
		for(int i=0; i<listaFormulas.size(); i++) {
			output = output.concat(listaFormulas.get(i).toString() + " | ");
		}
		return output;
	}

}
