package src;

import java.util.List;
import java.util.Queue;

public abstract class Ordenamiento<T extends Comparable<T>> {

	protected long cantIntercambios;

	protected long cantComparaciones;

	protected double tiempoTranscurrido;

	protected Queue<Paso> pasos;

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

	public Queue<Paso> getPasos() {
		return pasos;
	}

	public void setPasos(Queue<Paso> pasos) {
		this.pasos = pasos;
	}

}
