package tpSuperior;

import java.util.ArrayList;

public class NewtonGregory extends MetodoUtilizado{
	
	public NewtonGregory() {
		nombreMetodo="Newton gregory";
	}
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
//	public double calcularOrden(int pos,ArrayList<Punto>listaDePuntos,int cantidadDePosiciones) {
//		for (int n=0; n<cantidadDePosiciones; n++) 
//				if(pos<listaDePuntos.size()) {
//			
//		}
//	}
	// NO COMMITEEN CODIGO QUE NO COMPILA. SLDS RODRI :)

	@Override
	public void inicializar(ArrayList<Punto> listaDePuntos) {
		// TODO Auto-generated method stub
		
	}
}
