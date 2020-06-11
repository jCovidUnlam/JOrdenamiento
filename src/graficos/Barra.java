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
	private int posFinal;
	
	
	public int getPosFinal() {
		return posFinal;
	}


	public void setPosFinal(int posFinal) {
		this.posFinal = posFinal;
	}


	public Barra(double ancho, double alto) {
		super();
		this.ancho = ancho;
		this.alto = alto;
		this.color = Color.WHITE;
		//this.posFinal = posFinal;
	}
	
	
	public double getAncho() {
		return ancho;
	}

	public Barra() {
		super();
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
	
	public void setBarraActual() {
		this.color = Color.PINK;
	}
	
	public void setYaOrdenada() {
		this.color = Color.DARK_GRAY;
	}

	

}
