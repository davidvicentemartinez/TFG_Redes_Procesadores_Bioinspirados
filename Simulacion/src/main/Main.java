package main;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.TimerTask;

import formula.Formula;
import formula.ListaFormulas;
import grafo.Grafo;
import nodo.Nodo;
import nodo.NodoA;
import nodo.NodoB;
import nodo.NodoC;
import nodo.NodoF;
import nodo.NodoIn;
import nodo.NodoK;
import nodo.NodoOut;
import nodo.NodoV;
import nodo.NodoX;
import nodo.NodoZ;
import tools.Codificador;
import tools.LecturaEscritura;
import tools.Polarizacion;

public class Main {
	static long startTime;
	static int numPasos;
	
	public static void main(String[] args) {
		boolean continuar = false;
		do {
			Scanner scan = new Scanner(System.in);
			System.out.println("\nIntroduzca una fórmula: ");
			String textoFormula = scan.nextLine();
			System.out.println("Introduzca las variables: ");
			String variablesFormula = scan.nextLine();
			
			//Ejemplo de prueba
			
			//String textoFormula = "(abc)(¬def)";
			//String variablesFormula = "abcdef";
			
			Formula formulaEntrada = new Formula(textoFormula, variablesFormula);
			System.out.println("Fórmula codificada: " + formulaEntrada.toString());
			
			Grafo grafo = new Grafo(formulaEntrada);
			NodoIn nodoIn = grafo.getNodoIn();
			
			ListaFormulas listaInicial = new ListaFormulas();
			listaInicial.addFormula(formulaEntrada);
			LecturaEscritura.borrarArchivos();
			//new java.util.Scanner(System.in).nextLine();
			LecturaEscritura.crearArchivos(formulaEntrada);
			LecturaEscritura.escribirFormula(listaInicial, nodoIn);
			
			System.out.println("Calculando Solución");
			Main.startTime = System.currentTimeMillis();
			Main.numPasos = 0;
			IniciarProceso(nodoIn);
			
			ListaFormulas lista = LecturaEscritura.leerFormula(grafo.getNodoOut());
			if(lista == null) {
				System.out.println("No existe ninguna solución");
			}
			else {
				System.out.println("\nLista Soluciones: \n" + lista.toString());
				LecturaEscritura.mostrarSolucion(lista, variablesFormula);
			}
			
			long endTime = System.currentTimeMillis() - startTime;
			System.out.println("\nTiempo total: " + endTime/1000 + "s");
			System.out.println("\nPulsa [y] para introducir una nueva fórmula");
			continuar = scan.next().equals("y");
			if(!continuar) {
				scan.close();
			}
		}
		while(continuar);

	}
	
	
	public static void IniciarProceso(Nodo nodo) {
		if(nodo.getClass() == NodoOut.class) {
			return;
		}
		else if(nodo.getClass() == NodoIn.class) {
			NodoIn nodoIn = (NodoIn) nodo;
			ProcesoNodoIn(nodoIn);
		}
		else {
			ProcesoNodo(nodo);
		}
	}
	
	public static void ProcesoNodoIn(NodoIn nodoIn) {
		Main.numPasos = Main.numPasos + 1;
		System.out.println("Paso evolutivo " + Main.numPasos + ". Aplicando reglas en: " + nodoIn.getClass());
		ListaFormulas lista = LecturaEscritura.leerFormula(nodoIn);
		if(lista == null) {
			lista = new ListaFormulas();
		}
		boolean reglaAplicada = false;
		int length = lista.length();

		for(int i=0; i<length; i++) {
			Formula formula = lista.getFormula(i);
			Formula formulaModificadaF = nodoIn.aplicarReglasF(formula);
			if(!lista.contains(formulaModificadaF)) {				
				lista.addFormula(formulaModificadaF);
				reglaAplicada = true;
			}
			Formula formulaModificadaV = nodoIn.aplicarReglasV(formula);
			if(!lista.contains(formulaModificadaV)) {
				lista.addFormula(formulaModificadaV);
				reglaAplicada = true;
			}		
		}
		
		//Se escribe y se vuelve a llamar al nodoIn si hay nuevas fórmulas
		if(reglaAplicada) {
			LecturaEscritura.escribirFormula(lista, nodoIn);
			ProcesoNodoIn(nodoIn);
		}
		//Escritura en nodos adyacentes
		else {
			List<Nodo> listaNodos = nodoIn.getListaAdyacentes();
			for(Nodo nodo : listaNodos) {
				ListaFormulas listaNodo;
				listaNodo = LecturaEscritura.leerFormula(nodo);
				if(listaNodo == null) {
					listaNodo = new ListaFormulas();
				}
				Polarizacion polarizacionNodo = nodo.getPolarización();
				boolean nuevaFormula = false;
				for(int i=0;i<lista.length();i++) {
					Formula formula = lista.getFormula(i);
					if(!listaNodo.contains(formula)) {
						if(formula.getPhi() < 0 && polarizacionNodo == Polarizacion.NEGATIVO) {
							listaNodo.addFormula(formula);
							nuevaFormula = true;
						}
						if(formula.getPhi() == 0 && polarizacionNodo == Polarizacion.NEUTRO) {
							listaNodo.addFormula(formula);
							nuevaFormula = true;
						}
						if(formula.getPhi() > 0 && polarizacionNodo == Polarizacion.POSITIVO) {
							listaNodo.addFormula(formula);
							nuevaFormula = true;
						}
					}
				}
				if(nuevaFormula) {
					LecturaEscritura.escribirFormula(listaNodo, nodo);
					IniciarProceso(nodo);
				}
			}
		}
	}
	
	public static void ProcesoNodo(Nodo nodo) {
		Main.numPasos = Main.numPasos + 1;
		if(nodo.getClass() == NodoZ.class | nodo.getClass() == NodoX.class) {
			System.out.println("Paso evolutivo " + Main.numPasos + ". Aplicando reglas en: " + nodo.getClass());
		}
		else {
			System.out.println("Paso evolutivo " + Main.numPasos + ". Aplicando reglas en: " + nodo.getClass() + "_" + nodo.getPosVar());			
		}

		ListaFormulas lista = LecturaEscritura.leerFormula(nodo);
		if(lista == null) {
			lista = new ListaFormulas();
		}

		boolean reglaAplicada = false;
		int length = lista.length();
		for(int i=0; i<length; i++) {
			Formula formula = lista.getFormula(i);
			Formula formulaModificada = nodo.aplicarReglas(formula);
			if(!lista.contains(formulaModificada)) {
				lista.addFormula(formulaModificada);
				reglaAplicada = true;
			}
		}
		//Se escribe y se vuelve a llamar al nodo si hay nuevas fórmulas
		if(reglaAplicada) {
			LecturaEscritura.escribirFormula(lista, nodo);
			ProcesoNodo(nodo);
		}
		//Se escribe en nodos adyacentes
		else {
			EscribirEnAdyacentes(nodo,lista);
		}
	}
	
	
	public static void EscribirEnAdyacentes(Nodo nodoOrigen, ListaFormulas listaOrigen) {
		List<Nodo> listaNodos = nodoOrigen.getListaAdyacentes();
		
		for(Nodo nodo : listaNodos) {
			ListaFormulas listaNodo;
			listaNodo = LecturaEscritura.leerFormula(nodo);
			if(listaNodo == null) {
				listaNodo = new ListaFormulas();
			}

			Polarizacion polarizacionNodo = nodo.getPolarización();
			boolean nuevaFormula = false;
			for(int i=0;i<listaOrigen.length();i++) {
				Formula formula = listaOrigen.getFormula(i);
				if(!listaNodo.contains(formula)) {
					if(formula.getPhi() < 0 && polarizacionNodo.equals(Polarizacion.NEGATIVO)) {
						listaNodo.addFormula(formula);
						nuevaFormula = true;
					}
					if(formula.getPhi() == 0 && polarizacionNodo.equals(Polarizacion.NEUTRO)) {
						listaNodo.addFormula(formula);
						nuevaFormula = true;
					}
					if(formula.getPhi() > 0 && polarizacionNodo.equals(Polarizacion.POSITIVO)) {
						listaNodo.addFormula(formula);
						nuevaFormula = true;
					}
				}
			}
			if(nuevaFormula) {
				LecturaEscritura.escribirFormula(listaNodo, nodo);
				IniciarProceso(nodo);				
			}
		}
	}
}
	

	
	

	
	
	

	
	      

