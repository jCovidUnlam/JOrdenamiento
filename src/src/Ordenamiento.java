package src;

public abstract class Ordenamiento <T extends Comparable<T>> {
	
	protected long cantIntercambios;
	
	protected long cantComparaciones;
	
	protected double tiempoTranscurrido;

	public abstract T[] ordenar(final T[] arregloInmutable);

	protected void intercambiar(T[] arreglo, int i, int j) {
		T temporal = arreglo[i];
		arreglo[i] = arreglo[j];
		arreglo[j] = temporal;
	}

	public long getCantIntercambios() {
		return cantIntercambios;
	}

	public long getCantComparaciones() {
		return cantComparaciones;
	}

	public double getTiempoTranscurrido() {
		return tiempoTranscurrido;
	}
	
	

}
