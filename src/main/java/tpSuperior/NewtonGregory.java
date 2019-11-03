package tpSuperior;

import java.util.ArrayList;
import java.util.List;

public abstract class NewtonGregory extends MetodoUtilizado{
	List<Double> listaDeOrdenes=  new ArrayList<Double>();

	static double calcularAn(double u, int n) 
	{ 
	    double temp = u; 
	    for (int i = 1; i < n; i++) 
	        temp = temp * (u - i); 
	    return temp; 
	} 
	  
	static int sumatoria(int n) 
	{ 
	    int f = 1; 
	    for (int i = 2; i <= n; i++) 
	        f += i; 
	    return f; 
	} 

	@Override
	public void inicializar(ArrayList<Punto> listaDePuntos) {
		super.inicializar(listaDePuntos);
		int posicionListaDeOrdenes = 0;
		int cantidadDePuntos = listaDePuntos.size();
		this.listaDeOrdenes.clear();
		for(int orden=1; orden<cantidadDePuntos; orden++) {
			int iteraciones = cantidadDePuntos-orden;
			if(orden==1) { //hago esta diferencia porque la primera vez usas Y y las otras usas las ordenes
				for(int j=0; j<iteraciones; j++) {
					Punto siguiente = this.listaDePuntosOrdenada.get(j+orden);
					Punto anterior = this.listaDePuntosOrdenada.get(j);
					double resultado = (siguiente.getY()-anterior.getY())/(siguiente.getX()-anterior.getX());
					listaDeOrdenes.add(resultado);
					posicionListaDeOrdenes++;
				}
			}
			else {
				for(int j= 0; j<iteraciones; j++) {
					double xSiguiente = this.listaDePuntosOrdenada.get(j+orden).getX();
					double xAnterior = this.listaDePuntosOrdenada.get(j).getX();
					double imagenSiguiente = listaDeOrdenes.get(posicionListaDeOrdenes-(cantidadDePuntos-orden));
					double imagenAnterior = listaDeOrdenes.get(posicionListaDeOrdenes-(cantidadDePuntos-orden)-1);
					double resultado = (imagenSiguiente-imagenAnterior)/ (xSiguiente-xAnterior);
					listaDeOrdenes.add(resultado);
					posicionListaDeOrdenes++;
				}
			}
		}
		this.calcularPolinomio(cantidadDePuntos,listaDePuntos);
		this.setPasos("Primero: ordenamos la lista de puntos"+
					"Segundo: Calculamos si es equiespaciado"
					+ "Tercero: sacamos los ordenes"
					+ "Cuarto: calculamos el polinomio de" + this.nombreMetodo);
	}
	public abstract void calcularPolinomio(int cantidadDePuntos, ArrayList<Punto> listaDePuntos);
	
	public static void main (String args[]) {
		  /*double x1= 1.0;
		  double y1= 1.0;
		  double x2= 1.05;
		  double y2= 1.02470;
		  double x3= 1.10;
		  double y3= 1.04881;
		  double x4= 1.15;
		  double y4= 1.07238;
		  double x5= 1.2;
		  double y5= 1.09544;
		  double x6= 1.25;
		  double y6= 1.11803;
		  double x7= 1.3;
		  double y7= 1.14017;
		  Punto p1= new Punto(x1,y1);
		  Punto p2= new Punto(x2,y2);
		  Punto p3= new Punto(x3,y3);
		  Punto p4= new Punto(x4,y4);
		  Punto p5= new Punto(x5,y5);
		  Punto p6= new Punto(x6,y6);
		  Punto p7= new Punto(x7,y7);
		  ArrayList<Punto> listadoPuntos = new ArrayList<>();
		  listadoPuntos .add(p1);
		  listadoPuntos .add(p2);
		  listadoPuntos .add(p3);
		  listadoPuntos .add(p4);
		  listadoPuntos .add(p5);
		  listadoPuntos .add(p6);
		  listadoPuntos .add(p7);
		  MetodoUtilizado Ng = new NewtonGregory();
		  Ng.inicializar(listadoPuntos);*/
	} 
}
