package grafo;

import java.util.ArrayList;
import java.util.List;

import formula.Formula;
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
import tools.Polarizacion;

public class Grafo {

	private NodoIn nodoIn;
	private NodoOut nodoOut;
	private Formula formula;
	
	public Grafo(Formula formula) {
		this.formula = formula;
		int numeroNodosK = formula.getClausulas().size();
		
		nodoIn = new NodoIn();
		nodoIn.setPolarización(Polarizacion.POSITIVO);
		nodoOut = new NodoOut();
		nodoOut.setPolarización(Polarizacion.NEGATIVO);
		NodoX nodoX = new NodoX();
		NodoZ nodoZ = new NodoZ();
		
		nodoZ.addAdyacente(nodoIn);
		
		crearNodosK(numeroNodosK, nodoX, nodoZ);
		
		nodoIn.addAdyacente(nodoZ);
		nodoIn.addAdyacente(nodoX);
		nodoX.addAdyacente(nodoIn);
		nodoOut.addAdyacente(nodoX);
		nodoX.addAdyacente(nodoOut);
		
	
	}
	
	private void crearNodosK(int numNodosK, NodoX nodoX, NodoZ nodoZ) {
		List<Nodo> nodosAuxZ = new ArrayList<Nodo>();
		List<Nodo> nodosAuxX = new ArrayList<Nodo>();
		NodoK[] listaNodosK = new NodoK[numNodosK];
		NodoA[] listaNodosA = new NodoA[numNodosK];
		NodoB[] listaNodosB = new NodoB[numNodosK];
		NodoC[] listaNodosC = new NodoC[numNodosK];
		NodoV[][] matrizNodosV = new NodoV[numNodosK][3];
		NodoF[][] matrizNodosF = new NodoF[numNodosK][3];
		
		nodosAuxZ.add(nodoZ);
		nodosAuxX.add(nodoX);
		for(int i=0; i<numNodosK;i++) {
			for(int j=0; j<3; j++) {

				matrizNodosV[i][j] = new NodoV();
				matrizNodosV[i][j].addAdyacente(nodoZ);
				matrizNodosV[i][j].setPolarización(Polarizacion.POSITIVO);
				matrizNodosV[i][j].setNumNodo(i);
				matrizNodosV[i][j].setPosVar(j);
				
				matrizNodosF[i][j] = new NodoF();
				matrizNodosF[i][j].addAdyacente(nodoZ);
				matrizNodosF[i][j].setPolarización(Polarizacion.NEGATIVO);
				matrizNodosF[i][j].setNumNodo(i);
				matrizNodosF[i][j].setPosVar(j);
				
				nodoZ.addAdyacente(matrizNodosV[i][j]);
				nodoZ.addAdyacente(matrizNodosF[i][j]);
				
				if(j == 0) {
					listaNodosA[i] = new NodoA();
					listaNodosA[i].setNumNodo(i);
					listaNodosA[i].addAdyacente(matrizNodosV[i][j]);
					listaNodosA[i].addAdyacente(matrizNodosF[i][j]);
					matrizNodosV[i][j].addAdyacente(listaNodosA[i]);
					matrizNodosF[i][j].addAdyacente(listaNodosA[i]);
				}
				else if(j == 1) {
					listaNodosB[i] = new NodoB();
					listaNodosB[i].setNumNodo(i);
					listaNodosB[i].addAdyacente(matrizNodosV[i][j]);
					listaNodosB[i].addAdyacente(matrizNodosF[i][j]);
					matrizNodosV[i][j].addAdyacente(listaNodosB[i]);
					matrizNodosF[i][j].addAdyacente(listaNodosB[i]);
				}
				else if(j == 2) {
					listaNodosC[i] = new NodoC();
					listaNodosC[i].setNumNodo(i);
					listaNodosC[i].addAdyacente(matrizNodosV[i][j]);
					listaNodosC[i].addAdyacente(matrizNodosF[i][j]);
					matrizNodosV[i][j].addAdyacente(listaNodosC[i]);
					matrizNodosF[i][j].addAdyacente(listaNodosC[i]);
				}
				else {
					System.out.println("ERROR");
				}
			}
			listaNodosK[i] = new NodoK();
			listaNodosK[i].setNumNodo(i);
			listaNodosK[i].setPolarización(Polarizacion.POSITIVO);
			listaNodosK[i].addAdyacente(nodoX);
			listaNodosA[i].addAdyacente(listaNodosK[i]);
			listaNodosB[i].addAdyacente(listaNodosK[i]);
			listaNodosC[i].addAdyacente(listaNodosK[i]);
			listaNodosK[i].addAdyacente(listaNodosA[i]);
			listaNodosK[i].addAdyacente(listaNodosB[i]);
			listaNodosK[i].addAdyacente(listaNodosC[i]);
			nodoX.addAdyacente(listaNodosK[i]);
			
			
		}

	}
	

	public NodoIn getNodoIn() {
		return nodoIn;
	}

	public void setNodoIn(NodoIn nodoIn) {
		this.nodoIn = nodoIn;
	}

	public NodoOut getNodoOut() {
		return nodoOut;
	}

	public void setNodoOut(NodoOut nodoOut) {
		this.nodoOut = nodoOut;
	}

	public Formula getFormula() {
		return formula;
	}

	public void setFormula(Formula formula) {
		this.formula = formula;
	}
	
}
