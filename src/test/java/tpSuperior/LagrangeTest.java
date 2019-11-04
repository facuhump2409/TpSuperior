package tpSuperior;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runners.Parameterized.Parameter;

public class LagrangeTest {
	
	@Parameter
	Lagrange metodoL = new Lagrange();
	
	ArrayList<Punto> listadoPuntos1;
	ArrayList<Punto> listadoPuntos2;
	ArrayList<Punto> listadoPuntos3;
	ArrayList<Punto> listadoPuntos4;
	
	@Before
	public void init() {
		Punto p11 = new Punto(1.0, 3.0);
		Punto p12 = new Punto(2.0, 4.0);
		Punto p13 = new Punto(5.0, 10.0);
		listadoPuntos1 = new ArrayList<>();
		listadoPuntos1.addAll(Arrays.asList(p11,p12,p13));
		
		Punto p21 = new Punto(1.0, 1.0);
		Punto p22 = new Punto(2.0, 8.0);
		Punto p23 = new Punto(4.0, 64.0);
		listadoPuntos2 = new ArrayList<>();
		listadoPuntos2.addAll(Arrays.asList(p21,p22,p23));
		
		Punto p31 = new Punto(1.0, 3.0);
		Punto p32 = new Punto(7.0, 4.0);
		listadoPuntos3 = new ArrayList<>();
		listadoPuntos3.addAll(Arrays.asList(p31,p32));
		
		Punto p41 = new Punto(-1.0, 0.0);
		Punto p42 = new Punto(1.0, 1.0);
		Punto p43 = new Punto(2.0, 8.0);
		Punto p44 = new Punto(4.0, 64.0);
		listadoPuntos4 = new ArrayList<>();
		listadoPuntos4.addAll(Arrays.asList(p41,p42,p43,p44));
		
	}
	
	@Test
	public void calculaBienPolinomioPorLagrange1() {
	  metodoL.inicializar(listadoPuntos1);
	  System.out.println (metodoL.getPolinomio());
	}  
	
	@Test
	public void calculaBienPolinomioPorLagrange2() {
		  metodoL.inicializar(listadoPuntos2);
		  System.out.println (metodoL.getPolinomio());
		}  

	@Test
	public void calculaBienPolinomioPorLagrange3() {
		  metodoL.inicializar(listadoPuntos3);
		  System.out.println (metodoL.getPolinomio());
		}  
	
	@Test
	public void calculaBienPolinomioPorLagrange4() {
		  metodoL.inicializar(listadoPuntos4);
		  System.out.println (metodoL.getPolinomio());
		} 
}
