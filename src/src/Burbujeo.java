package src;

import java.util.Arrays;
import java.util.LinkedList;

public class Burbujeo<T extends Comparable<T>> extends Ordenamiento<T> {

	public T[] ordenar(final T[] arregloInmutable) {
		T[] arreglo = Arrays.copyOf(arregloInmutable, arregloInmutable.length);

		pasos = new LinkedList<Paso>();

		boolean huboCambio = false;
		do {
			huboCambio = false;
			for (int i = 0; i < arreglo.length-1; i++) {
				long tiempoIniComp = System.nanoTime();
				if (arreglo[i].compareTo(arreglo[i + 1]) > 0) {
					long tiempoIniInt = System.nanoTime();
					intercambiar(arreglo, i, i + 1);
					huboCambio = true;
					long tiempoFinInt = System.nanoTime();
					pasos.add(new Paso(TipoPaso.INTERCAMBIO, tiempoFinInt - tiempoIniInt, i, i + 1));
				}
				long tiempoFinComp = System.nanoTime();
				pasos.add(new Paso(TipoPaso.COMPARACION, tiempoFinComp - tiempoIniComp, i, i + 1));
			}
		} while (huboCambio);

		return arreglo;
	}

}
