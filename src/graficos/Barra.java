package graficos;

import java.awt.Color;

public class Barra implements Comparable<Barra>{
	
	/*
	 * Vamos a tener muchas barritas en memoria
	 * vamos a ordenarla por su altura, 
	 * que sera proporcional a la cantidad de elementos que haya
	 */
	
	private double ancho;
	private double alto;
	private Color color;
	
	
	public Barra(double ancho, double alto) {
		super();
		this.ancho = ancho;
		this.alto = alto;
		this.color = Color.WHITE;
	}
	
	
	public double getAncho() {
		return ancho;
	}

	public double getAlto() {
		return alto;
	}

	public Color getColor() {
		return color;
	}


	public void setColor(Color color) {
		this.color = color;
	}


	@Override
	public int compareTo(Barra o) {
		if(this.alto < o.alto)
			return -1;
		if(this.alto > o.alto)
			return 1;
		return 0;
	}
	
	public void barraActual() {
		this.color = Color.PINK;
	}
	
	public void yaOrdenada() {
		this.color = Color.GRAY;
	}

	

}
