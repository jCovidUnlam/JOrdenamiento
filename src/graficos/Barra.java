package graficos;

public class Barra implements Comparable<Barra>{
	
	/*
	 * Vamos a tener muchas barritas en memoria
	 * vamos a ordenarla por su altura, 
	 * que sera proporcional a la cantidad de elementos que haya
	 */
	
	private double ancho;
	private double alto;
	
	
	public Barra(double ancho, double alto) {
		super();
		this.ancho = ancho;
		this.alto = alto;
	}


	@Override
	public int compareTo(Barra o) {
		if(this.alto < o.alto)
			return -1;
		if(this.alto > o.alto)
			return 1;
		//Esto no deberia pasar!!
		//Si no es mayor es menor
		//pero lo dejo por las dudas :)
		return 0;
	}

	

}
