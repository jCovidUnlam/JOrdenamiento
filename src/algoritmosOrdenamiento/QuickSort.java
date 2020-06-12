package algoritmosOrdenamiento;

import java.util.Arrays;
import java.util.LinkedList;

import enums.TipoPaso;
import src.Ordenamiento;
import src.Paso;

public class QuickSort<T extends Comparable<T>> extends Ordenamiento<T> {

	
	public T[] ordenar(final T[] arregloInmutable) {
		T[] arreglo = Arrays.copyOf(arregloInmutable, arregloInmutable.length);
		pasos = new LinkedList<Paso>();
		ordenar(arreglo, 0, arreglo.length - 1);
		return arreglo;
	}

	private void ordenar(T arreglo[], int inferior, int superior) {
		if (inferior < superior) {
			int pivot = partition(arreglo, inferior, superior);

			ordenar(arreglo, inferior, pivot - 1);
			ordenar(arreglo, pivot + 1, superior);
		}
	}

	private int partition(T arreglo[], int inferior, int superior) {
		T pivot = arreglo[superior];
		int i = (inferior - 1);
		for (int j = inferior; j < superior; j++) {
			pasos.add(new Paso(TipoPaso.COMPARACION, 0, j, superior, superior,true));
			//long tiempoIniComp = System.nanoTime();
			if (arreglo[j].compareTo(pivot) < 0) {
				//long tiempoIniInt = System.nanoTime();
				i++;
				intercambiar(arreglo, i, j);
				//long tiempoFinInt = System.nanoTime();
				pasos.add(new Paso(TipoPaso.INTERCAMBIO, 0, i, j, superior,true));
			}
			//long tiempoFinComp = System.nanoTime();
			
		}
		//long tiempoIniInt = System.nanoTime();
		intercambiar(arreglo, i + 1, superior);
		//long tiempoFinInt = System.nanoTime();
		pasos.add(new Paso(TipoPaso.INTERCAMBIO, 0, i + 1, superior, superior,true));
		return i + 1;
	}

}
