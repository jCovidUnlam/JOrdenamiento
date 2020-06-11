package src;

import java.util.Arrays;

public class Burbujeo<T extends Comparable<T>> extends Ordenamiento<T> {

	public T[] ordenar(final T[] arregloInmutable) {
		T[] arreglo = Arrays.copyOf(arregloInmutable, arregloInmutable.length);

		boolean huboCambio = false;
		do {
			huboCambio = false;
			for (int i = 0; i < arreglo.length - 1; i++) {
				this.cantComparaciones++;
				pasos.add(new Paso(TipoPaso.COMPARACION, 0, i, i+1));
				if (arreglo[i].compareTo(arreglo[i + 1]) > 0) {
					intercambiar(arreglo, i, i + 1);
					huboCambio = true;
					this.cantIntercambios++;
					pasos.add(new Paso(TipoPaso.INTERCAMBIO, 0, i, i+1));
				}
			}
		} while (huboCambio);

		return arreglo;
	}

}
