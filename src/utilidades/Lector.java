package utilidades;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import modelos.Agente;

public class Lector {

	public static File fichero;

	public static ArrayList<Agente> leerFicheroYCargarDatos() throws FileNotFoundException, IOException {

		FileReader fr = new FileReader(fichero);
		BufferedReader br = new BufferedReader(fr);

		String[] dimensiones = br.readLine().split(" ");
		int numFilas = Integer.parseInt(dimensiones[0]);
		int numColumnas = Integer.parseInt(dimensiones[1]);

		if (numFilas != numColumnas) {

			System.err.println("La matriz cargada no es cuadrada");
			br.close();
			return null;

		}

		return leerMatriz(numFilas, br);

	}

	public static ArrayList<Agente> leerMatriz(int numFilas, BufferedReader br) throws IOException {

		ArrayList<Agente> arraylistAgentesParaDevolver = new ArrayList<Agente>();

		for (int i = 0; i < numFilas; i++) {

			String[] filaTexto = br.readLine().split(" ");
			int[] filaNums = new int[filaTexto.length];

			for (int j = 0; j < filaTexto.length; j++) {

				filaNums[j] = Integer.parseInt(filaTexto[j]);

				if (filaNums[j] < 0) {

					System.err.println("Error con los costes, no pueden ser negativos");
					return null;

				}

			}

			Agente a = new Agente(filaNums);
			arraylistAgentesParaDevolver.add(a);

		}

		return arraylistAgentesParaDevolver;

	}

}
