package modelos;

import java.util.Arrays;

public class Agente {

	private int tareasAgente[] = new int[4];

	// Constructor

	public Agente() {
		super();
	}

	// Getters y Setters

	public int[] getTareasAgente() {
		return tareasAgente;
	}

	public void setTareasAgente(int[] tareasAgente) {
		this.tareasAgente = tareasAgente;
	}

	// To String

	@Override
	public String toString() {
		return "Agente [tareasAgente=" + Arrays.toString(tareasAgente) + "]";
	}

}
