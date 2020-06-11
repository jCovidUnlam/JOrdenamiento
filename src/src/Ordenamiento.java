package src;
import java.util.Queue;

public abstract class Ordenamiento<T extends Comparable<T>> {

	

	protected Queue<Paso> pasos;

	public abstract T[] ordenar(final T[] arregloInmutable);

	protected void intercambiar(T[] arreglo, int i, int j) {
		T temporal = arreglo[i];
		arreglo[i] = arreglo[j];
		arreglo[j] = temporal;
	}

	public Queue<Paso> getPasos() {
		return pasos;
	}

	public void setPasos(Queue<Paso> pasos) {
		this.pasos = pasos;
	}

}
