package tpSuperior;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runners.Parameterized.Parameter;


public class NewtonGregoryTest {

	@Parameter
	Progresivo metodoNGP = new Progresivo();
	Regresivo metodoNGR = new Regresivo();
	
	ArrayList<Punto> listadoPuntos1;
	ArrayList<Punto> listadoPuntos2;
	ArrayList<Punto> listadoPuntos3;

	@Before
	public void init() {
		Punto p11 = new Punto(1.0, 1.0);
		Punto p12 = new Punto(3.0, 3.0);
		Punto p13 = new Punto(4.0, 13.0);
		Punto p14 = new Punto(5.0, 37.0);
		Punto p15 = new Punto(7.0, 151.0);
		listadoPuntos1 = new ArrayList<>();
		listadoPuntos1.addAll(Arrays.asList(p11,p12,p13,p14,p15));
		
		Punto p21 = new Punto(1.0, 1.0);
		Punto p22 = new Punto(1.05, 1.02470);
		Punto p23 = new Punto(1.10, 1.04881);
		Punto p24 = new Punto(1.15, 1.07238);
		Punto p25 = new Punto(1.20, 1.09544);
		Punto p26 = new Punto(1.25, 1.11803);
		Punto p27 = new Punto(1.30, 1.14017);
		listadoPuntos2 = new ArrayList<>();
		listadoPuntos2.addAll(Arrays.asList(p21,p22,p23,p24,p25,p26,p27));
		
		Punto p31 = new Punto(1.0, 1.2);
		Punto p32 = new Punto(3.0, 2.4);
		Punto p33 = new Punto(5.0, 10.0);
		Punto p34 = new Punto(6.0, 19.2);
		Punto p35 = new Punto(8.0, 54.4);
		listadoPuntos3 = new ArrayList<>();
		listadoPuntos3.addAll(Arrays.asList(p31,p32,p33,p34,p35));
		
	}

	
	@Test
	public void calculaBienLosOrdenesYProgresivo1() { // ejemplo de la carpeta
		metodoNGP.inicializar(listadoPuntos1);
		List<Double> listaDeseada = new ArrayList<>(
				Arrays.asList(1.0, 10.0, 24.0, 57.0, 3.0, 7.0, 11.0, 1.0, 1.0, 0.0));
		System.out.println(metodoNGP.getPolinomio());
		Assert.assertTrue(metodoNGP.listaDeOrdenes.stream().allMatch(punto -> listaDeseada.contains(punto)));
	}

	@Test
	public void calculaBienLosOrdenesYRegresivo1() { // ejemplo de la carpeta
		metodoNGR.inicializar(listadoPuntos1);
		List<Double> listaDeseada = new ArrayList<>(
				Arrays.asList(1.0, 10.0, 24.0, 57.0, 3.0, 7.0, 11.0, 1.0, 1.0, 0.0));
		System.out.println(metodoNGR.getPolinomio());
		Assert.assertTrue(metodoNGR.listaDeOrdenes.stream().allMatch(punto -> listaDeseada.contains(punto)));
	}
	
	@Test
	public void especializaBienRegresivo() { // ejemplo de la carpeta
		metodoNGR.inicializar(listadoPuntos1);
		System.out.println(metodoNGR.getPolinomio());
		Assert.assertTrue(151 == metodoNGR.especializarPolinomio(7));
	}
	
	@Test
	public void especializaBienProgresivo() { // ejemplo de la carpeta
		metodoNGP.inicializar(listadoPuntos1);
		System.out.println(metodoNGP.getPolinomio());
		Assert.assertTrue(3 == metodoNGP.especializarPolinomio(3));
	}
	
	/*
	@Test
	public void calculaBienLosOrdenesYProgresivo2() { // ejemplo de la carpeta
		metodoNGP.inicializar(listadoPuntos2);
		List<Double> listaDeseada = new ArrayList<>(
				Arrays.asList(0.49399999999999844, 0.482, 0.471, 0.461, 0.452, 0.443, -0.118, -0.108, -0.102, -0.094, -0.09, 0.067, 0.04, 0.053, 0.027, -0.133, 0.067, -0.133, 0.8, -0.8, -5.333));
				System.out.println(metodoNGP.getPolinomio());
		Assert.assertTrue(metodoNGP.listaDeOrdenes.stream().allMatch(punto -> listaDeseada.contains(punto)));
	}
	
	//(0.494, 0.482, 0.471, 0.461, 0.452, 0.443, -0.118, -0.108, -0.102, -0.094, -0.09, 0.067, 0.04, 0.053, 0.027, -0.13, 0.067, -0.13, 0.8, -0.8, -5.33));
	//(-247.0/500.0, 2411.0/5000.0, 2357.0/5000.0, 1153.0/2500.0, 2259.0/5000.0, 1107.0/2500.0, -59.0/500.0, -27.0/250.0, -51.0/500.0, -47.0/500.0, -9.0/100.0, 1.0/15.0, 1.0/25.0, 4.0/75.0, 2.0/75.0, -2.0/15.0, 1.0/15.0, -2.0/15.0, 4.0/5.0, -4.0/5.0, -16.0/3.0));
	
	@Test
	public void calculaBienLosOrdenesYRegresivo2() { // ejemplo de la carpeta
		metodoNGR.inicializar(listadoPuntos2);
		List<Double> listaDeseada = new ArrayList<>(
				Arrays.asList(0.50, 0.48, 0.47, 0.46, 0.45, 0.44, -0.12, -0.11, -0.10, -0.09, -0.09, 0.07, 0.04, 0.05, 0.03, -0.1, 0.067, -0.1, 0.8, -0.8, -5.3));
		System.out.println(metodoNGR.getPolinomio());
		Assert.assertTrue(metodoNGR.listaDeOrdenes.stream().allMatch(punto -> listaDeseada.contains(punto)));
	}
	*/
	
	
	@Test
	public void calculaBienLosOrdenesYProgresivo3() { // ejemplo de la carpeta
		metodoNGP.inicializar(listadoPuntos3);
		List<Double> listaDeseada = new ArrayList<>(
				Arrays.asList(0.6, 3.8, 9.2, 17.6, 0.8, 1.8, 2.8, 0.2, 0.2, 0.0));
		System.out.println(metodoNGP.getPolinomio());
		Assert.assertTrue(metodoNGP.listaDeOrdenes.stream().allMatch(punto -> listaDeseada.contains(punto)));
	}

	@Test
	public void calculaBienLosOrdenesYRegresivo3() { // ejemplo de la carpeta
		metodoNGR.inicializar(listadoPuntos3);
		List<Double> listaDeseada = new ArrayList<>(
				Arrays.asList(0.6, 3.8, 9.2, 17.6, 0.8, 1.8, 2.8, 0.2, 0.2, 0.0));
		System.out.println(metodoNGR.getPolinomio());
		Assert.assertTrue(metodoNGR.listaDeOrdenes.stream().allMatch(punto -> listaDeseada.contains(punto)));
	}

}
