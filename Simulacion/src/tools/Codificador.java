package tools;

import java.util.ArrayList;
import java.util.List;

public class Codificador {

	public static List<List<String>> codificarInput(String formula, String variables) {
		formula = formula.replace("(", "");
		formula = formula.replace(")", "");
		List<List<String>>clausulas = new ArrayList<List<String>>();
		
		char negacion = '0';
		char aux;
		List<String> aux2 = new ArrayList<String>();
		StringBuilder nuevoElemento;
		int cont = 0;
		
		for(int i=0; i<formula.length(); i++) {
			aux = formula.charAt(i);
			if(aux == '!') {
				negacion = '1';
				i++;
				aux = formula.charAt(i);
			}
			nuevoElemento = new StringBuilder().append(variables.indexOf(aux)).append(negacion);
			negacion = '0';
			aux2.add(nuevoElemento.toString());
			
			if(cont < 2) {
				cont++;
			}
			else {
				clausulas.add(aux2);
				aux2 = new ArrayList<String>();
				cont = 0;
			}
		}
		
		return clausulas;

	}
	
	
}
