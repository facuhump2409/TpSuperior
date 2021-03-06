package tpSuperior;

public class Punto implements Comparable<Punto>{
	double x;
	double y;
	public double getX() {
		return x;
	}
	public void setX(double x) {
		this.x = x;
	}
	public double getY() {
		return y;
	}
	public void setY(double y) {
		this.y = y;
	}
	public Punto(int x, int y) {
		this.x=Double.valueOf(x);
		this.y=Double.valueOf(y);
	}
	public Punto(Double x,Double y) {
		this.x=x;
		this.y=y;
	}
	public int compareTo(Punto punto) {          
	    return (this.getX() < punto.getX() ? -1 : 
	            (this.getX() == punto.getX() ? 0 : 1));     
	  }   
}
