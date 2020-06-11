package src;

import java.util.ArrayList;
import java.util.Collections;

import graficos.OrdenamientoGrafico;

public class Main {
	
	public static void main(String[] args) {
		
		SetUp setUp = new SetUp(13, 1000, CasoOrdenamiento.ORDENADO, AlgoritmoOrdenamiento.BURBUJEO);
		
		OrdenamientoGrafico graficos = new OrdenamientoGrafico();
		
		Ordenamiento<Integer> ord = setearOrdenamiento(setUp);
		
		Integer[] arrayEntrada = crearArryAOrdenar(setUp);
		Integer[] arrayOrdenado = crearArryOrdenado(setUp);
		
		arrayEntrada =  ord.ordenar(arrayEntrada);
		
		graficos.init(ord.getPasos(), arrayEntrada, arrayOrdenado, setUp);
		
		try {
			graficos.run(arrayOrdenado);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
	
	;
	public static Integer[] crearArryAOrdenar(SetUp setUp) {
		
		ArrayList<Integer> arrayEntrada = new ArrayList<Integer>(setUp.getCantElementos());
		
		for (int i = 0; i < arrayEntrada.size(); i++) {
			arrayEntrada.add(i);
		}
		
		switch (setUp.getCasoOrd()) {
		case INVERTIDO:
			Collections.reverse(arrayEntrada);
			break;
		case CASIORDENADO:
			arrayEntrada.set(arrayEntrada.size() - 2, arrayEntrada.get(0));
			break;
		case CASIINVERTIDO:
			Collections.reverse(arrayEntrada);
			arrayEntrada.set(arrayEntrada.size() - 2, arrayEntrada.get(0));
			break;
		case ALEATORIO:
			Collections.shuffle(arrayEntrada);
		case ORDENADO: //en este caso no hacemos nada
			break;
		default:
			break;
		}
		
		Integer[] entrada = new Integer[arrayEntrada.size()];
		entrada = arrayEntrada.toArray(entrada);
		
		return entrada;
	}
	
	public static Integer[] crearArryOrdenado(SetUp setUp) {
		
		ArrayList<Integer> arrayOrdenado = new ArrayList<Integer>(setUp.getCantElementos());
		
		for (int i = 0; i < arrayOrdenado.size(); i++) {
			arrayOrdenado.add(i);
		}
		
		Integer[] ordenado = new Integer[arrayOrdenado.size()];
		ordenado = arrayOrdenado.toArray(ordenado);
		return ordenado;
	}
	
	public static Ordenamiento<Integer> setearOrdenamiento(SetUp setUp) {
		Ordenamiento<Integer> ord = null;
		switch (setUp.getAlgoritmo()) {
		
		case BURBUJEO:
			ord = new Burbujeo<Integer>();
			break;
		case SELECCION:
			//ord = new Seleccion<Integer>();
			//Descomentar cuando este seleccion :)
			break;
		case QUICKSORT:
			//ord = new QuickSort<Integer>();
			//Descomentar cuando este Quicksort :)
			break;
		default:
			break;
		}
		return ord;
	}
	
}