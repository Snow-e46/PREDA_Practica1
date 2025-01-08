package servicios;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.PriorityQueue;

import modelos.Agente;
import modelos.Nodo;

public class AsignadorTareas {

	public static void asignarTareas(ArrayList<Agente> agentes, String archivoSalida, boolean trazar) {
		int n = agentes.size();

		// Cola de prioridad para nodos mas prometedores
		PriorityQueue<Nodo> cola = new PriorityQueue<>();

		// Nodo raiz
		Nodo raiz = new Nodo(0, 0, new ArrayList<>());
		cola.add(raiz);

		int costoMinimo = Integer.MAX_VALUE;
		ArrayList<Integer> mejorAsignacion = null;

		if (trazar) {
			System.out.println("--- Inicio de la traza del algoritmo ---");
		}

		while (!cola.isEmpty()) {
			Nodo actual = cola.poll();

			if (trazar) {
				System.out.println("Procesando nodo en nivel: " + actual.getNivel());
				System.out.println("Costo acumulado: " + actual.getCostoAcumulado());
				System.out.println("Asignaciones actuales: " + actual.getAsignaciones());
			}

			if (actual.getNivel() == n) {
				// Nodo hoja: verificar si es solucion mejor
				if (actual.getCostoAcumulado() < costoMinimo) {
					costoMinimo = actual.getCostoAcumulado();
					mejorAsignacion = actual.getAsignaciones();
				}
				continue;
			}

			// Expandir el nodo actual
			for (int tarea = 0; tarea < n; tarea++) {
				if (!actual.getAsignaciones().contains(tarea)) {
					ArrayList<Integer> nuevaAsignacion = new ArrayList<>(actual.getAsignaciones());
					nuevaAsignacion.add(tarea);

					// Obtener el costo del agente actual para la tarea especifica
					int nuevoCosto = actual.getCostoAcumulado()
							+ agentes.get(actual.getNivel()).getTareasAgente()[tarea];

					if (nuevoCosto < costoMinimo) { // Poda
						Nodo nuevoNodo = new Nodo(actual.getNivel() + 1, nuevoCosto, nuevaAsignacion);
						cola.add(nuevoNodo);
						if (trazar) {
							System.out.println("Añadiendo nuevo nodo: " + nuevoNodo);
						}
					} else if (trazar) {
						System.out.println("Nodo podado debido a costo elevado: " + nuevoCosto);
					}
				}
			}
		}

		if (trazar) {
			System.out.println("--- Fin de la traza del algoritmo ---");
		}

		// Guardar los resultados
		if (archivoSalida.equalsIgnoreCase(" ")) {
			// Imprimir por la salida estandar

			for (int i = 0; i < mejorAsignacion.size(); i++) {
				System.out.println((i + 1) + " " + (mejorAsignacion.get(i) + 1));
			}

		} else {

			try (FileWriter writer = new FileWriter(archivoSalida)) {
				for (int i = 0; i < mejorAsignacion.size(); i++) {
					writer.write((i + 1) + " " + (mejorAsignacion.get(i) + 1) + "\n");
				}
				System.out.println("Resultado guardado en " + archivoSalida);
			} catch (IOException e) {
				System.err.println("Error al escribir el archivo de salida: " + e.getMessage());
			}

		}

	}

}
