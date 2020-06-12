package src;
import java.util.LinkedList;


public abstract class Ordenamiento<T extends Comparable<T>> {

	protected LinkedList<Paso> pasos;

	public abstract T[] ordenar(final T[] arregloInmutable);

	protected void intercambiar(T[] arreglo, int i, int j) {
		T temporal = arreglo[i];
		arreglo[i] = arreglo[j];
		arreglo[j] = temporal;
	}

	public LinkedList<Paso> getPasos() {
		return pasos;
	}

	public void setPasos(LinkedList<Paso> pasos) {
		this.pasos = pasos;
	}

}
