package tpSuperior;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import Jama.Matrix;

public class Lagrange extends MetodoUtilizado {
	public Lagrange() {
		nombreMetodo = "Lagrange";
	}



	public double[] findPolynomialFactors(List<Double> x, List<Double> y) throws RuntimeException {
		int n = x.size();

		double[][] data = new double[n][n];
		double[] rhs = new double[n];

		for (int i = 0; i < n; i++) {
			double v = 1;
			for (int j = 0; j < n; j++) {
				data[i][n - j - 1] = v;
				v *= x.get(i);
			}

			rhs[i] = y.get(i);
		}

		// Solve m * s = b

		Matrix m = new Matrix(data);
		Matrix b = new Matrix(rhs, n);

		Matrix s = m.solve(b);

		double f[] = s.getRowPackedCopy();
		this.setGrado(n - 1);
		this.setPasos("Primero: ordenamos la tabla." + "Segundo: calculamos el grado"
				+ "Tercero: calculamos el polinomio de Lagrange");
		for (int i = 1; i <= n; i++) {
			String grado = String.valueOf(n-i);
			if (i == 1)
				this.setPolinomio(String.valueOf(round(f[i-1],3)) + " X^" + grado + "+ ");
			else if (i == n)
				this.setPolinomio(this.getPolinomio().concat(String.valueOf(round(f[i - 1],3)) + " X^" + grado));
			else
				this.setPolinomio(this.getPolinomio().concat(String.valueOf(round(f[i - 1],3)) + " X^" + grado + "+ "));
		}
		// TODO ver tema de si es negativo quizas queda medio feo
		return s.getRowPackedCopy();
	}

	public static double round(double value, int places) {
	    if (places < 0) throw new IllegalArgumentException();

	    BigDecimal bd = BigDecimal.valueOf(value);
	    bd = bd.setScale(places, RoundingMode.HALF_UP);
	    return bd.doubleValue();
	}
	
	
	public static void main(String args[]) {
		/*
				  List <Double> x = new ArrayList<Double>(Arrays.asList(1.0, 2.0,5.0));
				  List <Double> y = new ArrayList<Double>(Arrays.asList(3.0, 4.0,10.0));
				  List <Punto> listaDePuntos = new ArrayList<Punto>(Arrays.asList());
				  //double x[] = {1.0, 2.0,5.0};
				  //double y[] = {3.0, 4.0,10.0};
				  //Esto seria un ejemplo para test
				  double x3= 5.0;
				  double y3= 10.0;
				  double x2= 2.0;
				  double y2= 4.0;
				  double x1= 1.0;
				  double y1= 3.0;
				  Punto p1= new Punto(x1,y1);
				  Punto p2= new Punto(x2,y2);
				  Punto p3= new Punto(x3,y3);
				  ArrayList<Punto> listadoPuntos = new ArrayList<>();
				  listadoPuntos .add(p1);
				  listadoPuntos .add(p2);
				  listadoPuntos .add(p3);
				  MetodoUtilizado metodoLagrange = new Lagrange();
				  metodoLagrange.inicializar(listadoPuntos);
				  System.out.println (metodoLagrange.getPolinomio());
		 */
	}

	@Override
	public void calcularPolinomio(int cantidadDePuntos, ArrayList<Punto> listaDePuntos) {

		List<Double> x = listaDePuntosOrdenada.stream().map(punto -> punto.getX()).collect(Collectors.toList());
		List<Double> y = listaDePuntosOrdenada.stream().map(punto -> punto.getY()).collect(Collectors.toList());
		this.findPolynomialFactors(x, y);
		
	}
}
