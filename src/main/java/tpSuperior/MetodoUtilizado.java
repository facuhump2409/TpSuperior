package tpSuperior;

import java.util.ArrayList;

public abstract class MetodoUtilizado {
	public String polinomio;
	public String pasos;
	public int grado;
	
	public boolean esEquispaciado() {
		return false;
	}
	public String getPolinomio() {
		return this.polinomio;
	}
	public MetodoUtilizado(ArrayList<Punto> listaDePuntos) {
		super();
		this.inicializar(listaDePuntos);
	}
	public abstract void inicializar(ArrayList<Punto> listaDePuntos);
	
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
}
