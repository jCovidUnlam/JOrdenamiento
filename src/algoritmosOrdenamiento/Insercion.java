package algoritmosOrdenamiento;

import java.util.Arrays;
import java.util.LinkedList;

import enums.TipoPaso;
import src.Ordenamiento;
import src.Paso;

public class Insercion<T extends Comparable<T>> extends Ordenamiento<T> {
	
	public T[] ordenar(final T[] arregloInmutable) {
		T[] arreglo = Arrays.copyOf(arregloInmutable, arregloInmutable.length);
		
		pasos = new LinkedList<Paso>();
	
		for (int i = 1; i < arreglo.length; i++) {
			T valorActual = arreglo[i];
			int j = i - 1;

			while (j >= 0 && arreglo[j].compareTo(valorActual) > 0) {
				long tiempoIniComp = System.nanoTime();
				long tiempoFinComp = System.nanoTime();
				pasos.add(new Paso(TipoPaso.COMPARACION, tiempoFinComp-tiempoIniComp, i, j));
				
				long tiempoIniInt = System.nanoTime();
				arreglo[j + 1] = arreglo[j];
				long tiempoFinInt = System.nanoTime();
				pasos.add(new Paso(TipoPaso.INTERCAMBIO, tiempoFinInt-tiempoIniInt, j, j+1));
				j = j - 1;
			}

			arreglo[j + 1] = valorActual;
		}
	
		return arreglo;
	}

}
