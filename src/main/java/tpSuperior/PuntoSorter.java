package tpSuperior;

import java.util.ArrayList;
import java.util.Collections;

public class PuntoSorter {
	ArrayList<Punto> listaDePuntos = new ArrayList<>();
	  public PuntoSorter(ArrayList<Punto> Puntos) {         
	    this.listaDePuntos = Puntos;     
	  }       
	  public ArrayList<Punto> getSortedPuntoByX() {         
	    Collections.sort(listaDePuntos);         
	    return listaDePuntos;     
	  } 
}
