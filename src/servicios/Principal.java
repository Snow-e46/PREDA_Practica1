package servicios;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import modelos.Agente;
import utilidades.Lector;

public class Principal {

	public static void main(String[] args) {

		// Compruebo si se ha pasado algun argumento
		if (args.length < 1) {

			System.err.println("Cantidad de argumentos insuficientes");

		} else {

			// Organizacion segun la cantidad de parametros adjuntos

			// Logica para 1 parametro
			if (args.length == 1) {

				// Posibilidads: -t // -h // fichero_entrada

				// Condicional para mostrar ayuda
				if (args[0].equalsIgnoreCase("-t")) {

					System.err.println("Faltan argumentos, puede solicitar ayuda introduciendo \"-h\"");

				} else if (args[0].equalsIgnoreCase("-h")) {

					imprimirAyuda();

				} else {

					Lector.fichero = new File(args[0]);

					ejecutarLogica(" ", false); // Sin trazas

				}

				// Logica para 2 parametros
			} else if (args.length == 2) {

				// Posibilidades: -t -h // -t fichero_entrada // -h fichero_entrada //
				// fichero_entrada fichero_salida

				if ((args[0].equalsIgnoreCase("-t")) && (args[1].equalsIgnoreCase("-h"))) {

					imprimirAyuda();

				} else if ((args[0].equalsIgnoreCase("-h")) && (args[1].equalsIgnoreCase("-t"))) {

					System.err.println("Argumentos erroneos, puede solicitar ayuda introduciendo \"-h\"");

				} else if (args[0].equalsIgnoreCase("-t")) {
					// logica trazada con fichero_entrada
					Lector.fichero = new File(args[1]);
					ejecutarLogica(" ", true);

				} else if (args[0].equalsIgnoreCase("-h")) {

					imprimirAyuda();
					Lector.fichero = new File(args[1]);
					ejecutarLogica(" ", false);

				} else {

					Lector.fichero = new File(args[0]);
					ejecutarLogica(args[1], false);

				}

				// Logica para 3 parametros
			} else if (args.length == 3) {

				// Posibilidades: -t -h fichero_entrada // -t fichero_entrada fichero_salida //
				// -h fichero_entrada fichero_salida

				if ((args[0].equalsIgnoreCase("-t")) && (args[1].equalsIgnoreCase("-h"))) {

					imprimirAyuda();
					Lector.fichero = new File(args[2]);
					ejecutarLogica(" ", true);

				} else if ((args[0].equalsIgnoreCase("-h")) && (args[1].equalsIgnoreCase("-t"))) {

					System.err.println("Argumentos erroneos, puede solicitar ayuda introduciendo \"-h\"");

				} else if (args[0].equalsIgnoreCase("-t")) {
					// logica trazada con fichero_entrada
					Lector.fichero = new File(args[1]);
					ejecutarLogica(args[2], true);

				} else if (args[0].equalsIgnoreCase("-h")) {

					imprimirAyuda();
					Lector.fichero = new File(args[1]);
					ejecutarLogica(args[2], false);

				}

				// Logica para 4 parametros
			} else if (args.length == 4) {

				// Implementacion logica para los 4 argumentos

				if (args[1].equalsIgnoreCase("-h")) {

					imprimirAyuda();

					Lector.fichero = new File(args[2]);

					ejecutarLogica(args[3], true);

				} else {

					System.err.println("Argumentos erroneos, puede solicitar ayuda introduciendo \"-h\"");

				}

			} else {

				System.err.println("Argumentos erroneos, puede solicitar ayuda introduciendo \"-h\"");

			}

		}

	}

	public static void imprimirAyuda() {

		System.out.println("SINTAXIS: tareas [-t] [-h] [fichero entrada] [fichero salida]");
		System.out.println("-t \t \t \t Traza el algoritmo");
		System.out.println("-h \t \t \t Muestra esta ayuda");
		System.out.println("[fichero entrada] \t Nombre del fichero de entrada");
		System.out.println("[fichero salida] \t Nombre del fichero de salida");

	}

	public static ArrayList<Agente> solicitarAgentesEntradaEstandar(int dimension) {

		ArrayList<Agente> agentes = new ArrayList<Agente>();
		Scanner sc = new Scanner(System.in);

		for (int i = 0; i < dimension; i++) {

			System.out.println("Introduzca los costes para el agente " + (i + 1) + ": ");
			int[] costesPorAgente = new int[dimension];

			for (int j = 0; j < dimension; j++) {

				costesPorAgente[j] = sc.nextInt();

				if (costesPorAgente[j] < 0) {

					System.err.println("Error con los costes, no pueden ser negativos");
					return null;

				}

			}

			Agente nuevoAgenteEntradaEstandar = new Agente(costesPorAgente);
			agentes.add(nuevoAgenteEntradaEstandar);

		}

		return agentes;

	}

	public static void ejecutarLogica(String ficheroSalida, boolean trazar) {

		try {

			ArrayList<Agente> agentes = Lector.leerFicheroYCargarDatos();

			AsignadorTareas.asignarTareas(agentes, ficheroSalida, trazar);

		} catch (FileNotFoundException e) {
			// Logica para pedir los datos por entrada estandar

			System.err.println("No se ha encontrado " + Lector.fichero.getName() + " como [fichero entrada]");

			Scanner sc = new Scanner(System.in);

			System.out.println("Indique la dimension de la matriz cuadrada: ");

			int dimension = sc.nextInt();

			ArrayList<Agente> agentes = solicitarAgentesEntradaEstandar(dimension);

			AsignadorTareas.asignarTareas(agentes, ficheroSalida, trazar);

		} catch (IOException e) {

			System.err.println("Error en la entrada y salida de datos: " + e.getMessage());
		}

	}

}
