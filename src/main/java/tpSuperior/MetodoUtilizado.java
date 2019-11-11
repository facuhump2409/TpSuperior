package tpSuperior;

import java.util.ArrayList;

public abstract class MetodoUtilizado {
	public String polinomio;
	public String pasos;
	protected String nombreMetodo;
	public int grado = 0;
	boolean esEquispaciado;
	ArrayList<Punto> listaDePuntosOrdenada;
	
	public boolean esEquispaciado() {
		return this.esEquispaciado;
	}
	public void setEsEquispaciado(boolean esEquispaciado) {
		this.esEquispaciado = esEquispaciado;
	}
	public String getPolinomio() {
		return this.polinomio;
	}
	public MetodoUtilizado() {
		
	}
	public void verSiEstaEquiespaciado(ArrayList<Punto> listaDePuntosOrdenada) {
		int cantidadDeElementos = listaDePuntosOrdenada.size()-1; //para no pasarme de indice
		double distanciaARespetar = listaDePuntosOrdenada.get(1).getX() - listaDePuntosOrdenada.get(0).getX(); 
		for(int i=0; i<cantidadDeElementos;i++) {
			if((listaDePuntosOrdenada.get(i+1).getX() - listaDePuntosOrdenada.get(i).getX()) != distanciaARespetar) {
				this.setEsEquispaciado(false);
				return;
			}
		}
		this.setEsEquispaciado(true);
	}
	public void runMethod(ArrayList<Punto> listaDePuntos) {
		//Aca deberia correr el metodo
		//El metodo corre cuando se inicializa, ahi se cargan todos los atributos
		
	}
	public void inicializar(ArrayList<Punto> listaDePuntos) {
		this.listaDePuntosOrdenada = this.ordenarPuntos(listaDePuntos);
		this.verSiEstaEquiespaciado(listaDePuntosOrdenada);
		this.calcularPolinomio(listaDePuntosOrdenada.size(),listaDePuntosOrdenada);
	} //El metodo corre aca y ya se cargan los atributos
	
	public abstract void calcularPolinomio(int cantidadDePuntos, ArrayList<Punto> listaDePuntos);
	
	public ArrayList<Punto> ordenarPuntos(ArrayList<Punto> listaDePuntos){
		return new PuntoSorter(listaDePuntos).getSortedPuntoByX();
	}    
	public String getPasos() {
		return pasos;
	}
	public void setPasos(String pasos) {
		this.pasos = pasos;
	}
	public int getGrado() {
		return grado;
	}
	public void setGrado(int grado) {
		this.grado = grado;
	}
	public void setPolinomio(String polinomio) {
		this.polinomio = polinomio;
	}
	public String getNombreMetodo() {
		return nombreMetodo;
	}
	public void setNombreMetodo(String nombreMetodo) {
		this.nombreMetodo = nombreMetodo;
	}
	@Override
	public String toString() {
		return nombreMetodo;
	}
	
}
