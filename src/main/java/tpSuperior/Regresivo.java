package tpSuperior;

import java.util.ArrayList;
import java.util.List;

public class Regresivo extends NewtonGregory{ 
	
	public Regresivo() {
		super();
		this.nombreMetodo = "NewtonGregory Regresivo";
		// TODO Auto-generated constructor stub
	}
	@Override
	public void calcularPolinomio(int cantidadDePuntos, ArrayList<Punto> listaDePuntos) {
		int orden = 0;
		int cantidadDePuntosParaArray = cantidadDePuntos -1;
		int posicionOrden = 3;
		for(int i=cantidadDePuntosParaArray; i>=0;i--) {
			if(i==cantidadDePuntosParaArray) {
				this.setPolinomio(String.valueOf(listaDePuntos.get(i).getY())+ "+"); //la primera vez es Y inicial
				orden++; 
			}
			else {
				String nuevoPolinomio = this.getPolinomio().concat(String.valueOf(this.listaDeOrdenes.get(posicionOrden))); //concateno el numero de orden
				posicionOrden+= cantidadDePuntosParaArray-orden;
				int posicionInicialProgresivo = cantidadDePuntosParaArray;
				for(int j=0;j<orden;j++,posicionInicialProgresivo--) {
					String puntoEnX = String.valueOf(listaDePuntos.get(posicionInicialProgresivo).getX());
					nuevoPolinomio = nuevoPolinomio.concat("(x-"+ puntoEnX + ") "); 
				}
				if(orden+1 < cantidadDePuntos) {
					nuevoPolinomio = nuevoPolinomio.concat("+"); //para que la ultima vez no me ponga el +					
				}
				this.setPolinomio(nuevoPolinomio);
				orden++;
			}
		}
		
	}
}
