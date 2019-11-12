package tpSuperior;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

public class Progresivo extends NewtonGregory{

	public Progresivo() {
		super();
		this.nombreMetodo = "NewtonGregory Progresivo";
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void calcularPolinomio(int cantidadDePuntos, ArrayList<Punto> listaDePuntos) {
		super.calcularPolinomio(cantidadDePuntos,listaDePuntos);
		int orden = 0;
		int posicionOrden = 0;
		//this.setGrado(cantidadDePuntos - 1);
		int cantidadDeCeros = 0;
		for(int i=0; i<cantidadDePuntos;i++) {
			if(i==0) {
				this.setPolinomio(String.valueOf(listaDePuntos.get(i).getY())+ "+"); //la primera vez es Y inicial
				orden++;
			}
			else {
				String nuevoPolinomio = this.getPolinomio().concat(String.valueOf(this.listaDeOrdenes.get(posicionOrden))); //concateno el numero de orden
				if(this.listaDeOrdenes.get(posicionOrden) == 0)
					cantidadDeCeros++;
				posicionOrden+= cantidadDePuntos-i;
				for(int j=0;j<orden;j++) {
					String puntoEnX = String.valueOf(listaDePuntos.get(j).getX());
					nuevoPolinomio = nuevoPolinomio.concat("(x-"+ puntoEnX + ") ");
				}
				if(i+1 < cantidadDePuntos) {
					nuevoPolinomio = nuevoPolinomio.concat("+"); //para que la ultima vez no me ponga el +					
				}
				this.setPolinomio(nuevoPolinomio);
				orden++;
 			}
		}
		this.setGrado(cantidadDePuntos - cantidadDeCeros -1);
		
	}

}
