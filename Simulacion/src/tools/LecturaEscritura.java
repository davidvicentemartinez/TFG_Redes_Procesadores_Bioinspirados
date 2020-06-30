package tools;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import formula.Formula;
import formula.ListaFormulas;
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

public class LecturaEscritura {	
	
	public static void escribirFormula (ListaFormulas listaFormula, Nodo nodo) {
		if(listaFormula != null) {
			try {
				String ruta;
				if(nodo.getClass() == NodoV.class || nodo.getClass() == NodoF.class) {
					ruta = "nodos/" + nodo.getClass().toString() + nodo.getNumNodo() + nodo.getPosVar() + ".txt";
				}
				else {
					ruta = "nodos/" + nodo.getClass().toString() + nodo.getNumNodo() + ".txt";
				}
				FileOutputStream fileOut = new FileOutputStream(ruta);
				ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
				objectOut.writeObject(listaFormula);
				objectOut.close();
				fileOut.close();
			} catch (Exception e) {
				e.printStackTrace();
			}			
		}

	}
	
	public static ListaFormulas leerFormula (Nodo nodo) {
		try {
			String ruta;
			if(nodo.getClass() == NodoV.class || nodo.getClass() == NodoF.class) {
				ruta = "nodos/" + nodo.getClass().toString() + nodo.getNumNodo() + nodo.getPosVar() + ".txt";
			}
			else {
				ruta = "nodos/" + nodo.getClass().toString() + nodo.getNumNodo() + ".txt";
			}
			FileInputStream fileIn = new FileInputStream(ruta);
			ObjectInputStream objectIn = new ObjectInputStream(fileIn);
			ListaFormulas listaFormula = (ListaFormulas) objectIn.readObject();
			objectIn.close();
			fileIn.close();
			return listaFormula;
		} catch (EOFException e) {
			return null;
		}
		catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static void borrarArchivos () {
		File dir = new File("nodos/");
		for(File file : dir.listFiles()) {
			file.delete();
		}
	}
	
	public static void crearArchivos (Formula formula) {
		int length = formula.getClausulas().size();
		
		String nodoIn = NodoIn.class.toString() + "0";
		String nodoOut = NodoOut.class.toString() + "0";
		String nodoX = NodoX.class.toString() + "0";
		String nodoZ = NodoZ.class.toString() + "0";
		
		File dir;
		try {
			dir = new File("nodos/" + nodoIn + ".txt");
			dir.createNewFile();
			dir = new File("nodos/" + nodoOut + ".txt");
			dir.createNewFile();
			dir = new File("nodos/" + nodoX + ".txt");
			dir.createNewFile();
			dir = new File("nodos/" + nodoZ + ".txt");
			dir.createNewFile();
			
			for(int i=0; i<length; i++) {
				String nodoK = NodoK.class.toString() + i;
				String nodoA = NodoA.class.toString() + i;
				String nodoB = NodoB.class.toString() + i;
				String nodoC = NodoC.class.toString() + i;
				String nodoF_A = NodoF.class.toString() + i + "0";
				String nodoV_A = NodoV.class.toString() + i + "0";
				String nodoF_B = NodoF.class.toString() + i + "1";
				String nodoV_B = NodoV.class.toString() + i + "1";
				String nodoF_C = NodoF.class.toString() + i + "2";
				String nodoV_C = NodoV.class.toString() + i + "2";
				dir = new File("nodos/" + nodoK + ".txt");
				dir.createNewFile();
				dir = new File("nodos/" + nodoA + ".txt");
				dir.createNewFile();
				dir = new File("nodos/" + nodoB + ".txt");
				dir.createNewFile();
				dir = new File("nodos/" + nodoC + ".txt");
				dir.createNewFile();
				dir = new File("nodos/" + nodoF_A + ".txt");
				dir.createNewFile();
				dir = new File("nodos/" + nodoV_A + ".txt");
				dir.createNewFile();
				dir = new File("nodos/" + nodoF_B + ".txt");
				dir.createNewFile();
				dir = new File("nodos/" + nodoV_B + ".txt");
				dir.createNewFile();
				dir = new File("nodos/" + nodoF_C + ".txt");
				dir.createNewFile();
				dir = new File("nodos/" + nodoV_C + ".txt");
				dir.createNewFile();
			}
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public static void mostrarSolucion(ListaFormulas lista, String variables) {
		Formula formula = lista.getFormula(0);
		String soluciones = formula.getVar();
		
		System.out.println("\nSOLUCIÓN:");
		for(int i=0; i<variables.length();i++) {
			System.out.println(variables.charAt(i) + " = " + soluciones.charAt(i));
		}
		
	}
	

}

