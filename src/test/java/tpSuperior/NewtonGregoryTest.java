package tpSuperior;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

public class NewtonGregoryTest {
	NewtonGregory metodoNG = new NewtonGregory();
	@Test
	public void calculaBienLosOrdenes() { //ejemplo de la carpeta
		  double x1= 1.0;
		  double y1= 1.0;
		  double x2= 3.0;
		  double y2= 3.0;
		  double x3= 4.0;
		  double y3= 13.0;
		  double x4= 5;
		  double y4= 37.0;
		  double x5= 7.0;
		  double y5= 151.0;
		  Punto p1= new Punto(x1,y1);
		  Punto p2= new Punto(x2,y2);
		  Punto p3= new Punto(x3,y3);
		  Punto p4= new Punto(x4,y4);
		  Punto p5= new Punto(x5,y5);
		  ArrayList<Punto> listadoPuntos = new ArrayList<>();
		  listadoPuntos .add(p1);
		  listadoPuntos .add(p2);
		  listadoPuntos .add(p3);
		  listadoPuntos .add(p4);
		  listadoPuntos .add(p5);
		  metodoNG.inicializar(listadoPuntos);
		  List<Double> listaDeseada = new ArrayList<>(Arrays.asList(1.0,10.0,24.0,57.0,3.0,7.0,11.0,1.0,1.0,0.0));
		Assert.assertTrue(metodoNG.listaDeOrdenes.stream().allMatch(punto -> listaDeseada.contains(punto)));
	}
}
