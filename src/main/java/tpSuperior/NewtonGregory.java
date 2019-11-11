package tpSuperior;
import java.math.BigDecimal;
import java.math.RoundingMode;
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
	public void calcularPolinomio(int cantidadDePuntos, ArrayList<Punto> listaDePuntos) {
		int posicionListaDeOrdenes = 0;
		this.listaDeOrdenes.clear();
		
		for(int orden=1; orden<cantidadDePuntos; orden++) {
			int iteraciones = cantidadDePuntos-orden;
			if(orden==1) { //hago esta diferencia porque la primera vez usas Y y las otras usas las ordenes
				for(int j=0; j<iteraciones; j++) {
					Punto siguiente = this.listaDePuntosOrdenada.get(j+orden);
					Punto anterior = this.listaDePuntosOrdenada.get(j);
					double resultado = (siguiente.getY()-anterior.getY())/(siguiente.getX()-anterior.getX());
					//double resultadoRedondeado = round(resultado, 3);
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
					double resultadoRedondeado = round(resultado, 3);
					listaDeOrdenes.add(resultadoRedondeado);
					posicionListaDeOrdenes++;
				}
			}
		}
		
		
		this.setPasos("\n Primero: ordenamos la lista de puntos "+
					"\n Segundo: Calculamos si es equiespaciado"
					+ "\n Tercero: sacamos los ordenes"
					+ "\n Cuarto: calculamos el polinomio de" + this.nombreMetodo);
	}
		
		
	public static double round(double value, int places) {
	    if (places < 0) throw new IllegalArgumentException();

	    BigDecimal bd = BigDecimal.valueOf(value);
	    bd = bd.setScale(places, RoundingMode.HALF_UP);
	    return bd.doubleValue();
	}
	
}
