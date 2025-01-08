package modelos;

import java.util.ArrayList;

public class Nodo implements Comparable<Nodo> {

	private int nivel; // Nivel del árbol
	private int costoAcumulado; // Costo acumulado hasta el nodo
	private ArrayList<Integer> asignaciones; // Asignaciones parciales

	// Constructor
	public Nodo(int nivel, int costoAcumulado, ArrayList<Integer> asignaciones) {
		this.nivel = nivel;
		this.costoAcumulado = costoAcumulado;
		this.asignaciones = new ArrayList<>(asignaciones);
	}

	public int getNivel() {
		return nivel;
	}

	public int getCostoAcumulado() {
		return costoAcumulado;
	}

	public ArrayList<Integer> getAsignaciones() {
		return asignaciones;
	}

	// Comparador para prioridad (menor costo primero)
	@Override
	public int compareTo(Nodo otro) {
		return Integer.compare(this.costoAcumulado, otro.costoAcumulado);
	}

	@Override
	public String toString() {
		return "Nodo [nivel=" + nivel + ", costoAcumulado=" + costoAcumulado + ", asignaciones=" + asignaciones + "]";
	}

}
