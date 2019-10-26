package tpSuperior;

import java.util.ArrayList;

import Jama.Matrix;

public class Lagrange extends MetodoUtilizado{

	public Lagrange(ArrayList<Punto> listaDePuntos) {
		super(listaDePuntos);
	}

	@Override
	public void inicializar(ArrayList<Punto> listaDePuntos) {
		// TODO Auto-generated method stub
		
	}
	 public static double[] findPolynomialFactors (double[] x, double[] y)
			    throws RuntimeException
			  {
			    int n = x.length;

			    double[][] data = new double[n][n];
			    double[]   rhs  = new double[n];
			    
			    for (int i = 0; i < n; i++) {
			      double v = 1;
			      for (int j = 0; j < n; j++) {
			        data[i][n-j-1] = v;
			        v *= x[i];
			      }

			      rhs[i] = y[i];
			    }

			    // Solve m * s = b
			    
			    Matrix m = new Matrix (data);
			    Matrix b = new Matrix (rhs, n);

			    Matrix s = m.solve (b);

			    return s.getRowPackedCopy();
			  }


			  public static void main (String args[])
			  {
			    double x[] = {1.0, 5.0, 2.0,6.0};
			    double y[] = {3.0, 10.0, 4.0,5.0};
			    //Esto seria un ejemplo para test
			    double f[] = Lagrange.findPolynomialFactors (x, y);

			    for (int i = 0; i < 3; i++)
			      System.out.println (f[i]);
			  }
}
