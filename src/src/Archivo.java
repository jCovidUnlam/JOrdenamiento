package src;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import enums.AlgoritmoOrdenamiento;
import enums.CasoOrdenamiento;

//# algoritmo,condicion,elementos,tiempo en segundos
//burbujeo,casi ordenado,1000,10.3

public class Archivo {
	public static void crearCsv(AlgoritmoOrdenamiento algoritmoOrdenamiento, CasoOrdenamiento casoOrdenamiento,
			Integer elementos, double tiempo) throws IOException {

		String delim = ";";
		File file = new File("RegistroEstadistico.csv");
		FileWriter fw;
		StringBuilder builder = new StringBuilder();

		if (!file.exists()) {
			fw = new FileWriter(file);
			String ColumnNamesList = "algoritmo;condicion;elementos;tiempo en segundos";
			builder.append(ColumnNamesList).append("\n");
		} else
			fw = new FileWriter(file, true);

		BufferedWriter output = new BufferedWriter(fw);

		builder.append(algoritmoOrdenamiento).append(delim);
		builder.append(casoOrdenamiento).append(delim);
		builder.append(String.valueOf(elementos)).append(delim);
		builder.append(String.valueOf(tiempo)).append(delim).append("\n");

		output.write(builder.toString());
		output.close();
	}
}
