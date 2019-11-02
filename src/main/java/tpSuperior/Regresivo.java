package tpSuperior;

import java.util.ArrayList;
import java.util.List;

public class Regresivo extends NewtonGregory{ 
	
	public Regresivo() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public void inicializar(ArrayList<Punto> listaDePuntos) {
		//TODO ordenar arrayList
		int cantidadDePuntos = listaDePuntos.size();
		int cantidadDePosiciones = this.sumatoria(cantidadDePuntos-1);
		List<Double> listaDeOrdenes=  new ArrayList<Double>();
		int cantidadReccorida = 1;
		for(int pos=1; pos<cantidadDePuntos; pos++) {
			if(pos<cantidadDePuntos) {
				listaDeOrdenes.add(listaDePuntos.get(pos+1).getY() - listaDePuntos.get(pos).getY() / (listaDePuntos.get(pos+1).getX() - listaDePuntos.get(pos).getX()));
				cantidadReccorida++;
			}
			if(pos>=cantidadDePuntos && pos < cantidadDePuntos+ 2) {
				
			}
		}
		
	}
}
