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
				Paso pasoComp = new Paso(TipoPaso.COMPARACION, 0, i, i + 1);
				pasos.add(pasoComp);
				long tiempoIniComp = System.nanoTime();
				if (arreglo[i].compareTo(arreglo[i + 1]) > 0) {
					long tiempoIniInt = System.nanoTime();
					intercambiar(arreglo, i, i + 1);
					huboCambio = true;
					long tiempoFinInt = System.nanoTime();
					pasos.add(new Paso(TipoPaso.INTERCAMBIO, tiempoFinInt - tiempoIniInt, i, i + 1));
				}
				long tiempoFinComp = System.nanoTime();
				pasos.get(pasos.lastIndexOf(pasoComp)).setTiempoTranscurrido(tiempoFinComp-tiempoIniComp);;
			}
		} while (huboCambio);

		return arreglo;
	}

}
