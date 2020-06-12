package src;

import java.util.Arrays;
import java.util.LinkedList;

public class Seleccion<T extends Comparable<T>> extends Ordenamiento<T> {

    @Override
    public T[] ordenar(T[] arregloInmutable) {
        T[] arreglo = Arrays.copyOf(arregloInmutable, arregloInmutable.length);
        pasos = new LinkedList<>();

        T menor;
        int i, j, posicionDelMenor;

        for (i = 0; i < arreglo.length - 1; i++) {
            menor = arreglo[i];
            posicionDelMenor = i;

            for (j = i + 1; j < arreglo.length; j++) {
                long tiempoIniComp = System.nanoTime();

                if (arreglo[j].compareTo(menor) < 0) {
                    menor = arreglo[j];
                    posicionDelMenor = j;
                }

                long tiempoFinComp = System.nanoTime();
                pasos.add(new Paso(TipoPaso.COMPARACION, tiempoFinComp - tiempoIniComp, j, posicionDelMenor));
            }

            if (posicionDelMenor != i) {
                long tiempoIniInt = System.nanoTime();

                intercambiar(arreglo, i, posicionDelMenor);

                long tiempoFinInt = System.nanoTime();
                pasos.add(new Paso(TipoPaso.INTERCAMBIO, tiempoFinInt - tiempoIniInt, i, posicionDelMenor));
            }
        }
        return arreglo;
    }
}
