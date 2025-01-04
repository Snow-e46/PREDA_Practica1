package servicios;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import modelos.Agente;
import utilidades.Lector;

public class Principal {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		/*	Version 1.0 para la carga de los datos de coste
		 
		int [][] costes = {{11, 12, 18, 40}, {14, 15, 13, 22}, {11, 17, 19, 23}, {17, 14, 20, 28}};

		for (int i = 0; i < costes.length; i++) {
			for (int j = 0; j < costes[i].length; j++) {
				System.out.print(costes[i][j] + "\t");
			}
			System.out.println();
		}
		 */
		
		/*
		// Version 2.0 usando POO para la carga de los costes
		
		Agente a = new Agente();
		int [] costesAgenteA = {11, 12, 18, 40};
		a.setTareasAgente(costesAgenteA);
		
		Agente b = new Agente();
		int [] costesAgenteB = {14, 15, 13, 22};
		b.setTareasAgente(costesAgenteB);
		
		Agente c = new Agente();
		int [] costesAgenteC = {11, 17, 19, 23};
		c.setTareasAgente(costesAgenteC);
		
		Agente d = new Agente();
		int [] costesAgenteD = {17, 14, 20, 28};
		d.setTareasAgente(costesAgenteD);
		
		System.out.println(a);
		System.out.println(b);
		System.out.println(c);
		System.out.println(d);
		*/
		
		
		//Compruebo si se ha pasado algun argumento
		if (args.length < 1) {
			
			System.err.println("Cantidad de argumentos insuficientes");
			
		} else {
			
			//Organizacion segun la cantidad de parametros adjuntos
			
			if (args.length == 1) {
				
				//Condicional para mostrar ayuda
				
				if (args[0].equalsIgnoreCase("-h")) {
					
					imprimirAyuda();
					
				} else {
					
					System.err.println("Faltan argumentos, puede solicitar ayuda introduciendo \"-h\"");
					
				}
				
			} else if (args.length == 4) {
				
				//Implementacion logica para los 4 argumentos
				
				Lector.fichero = new File(args[2]);
				
				try {
					
					ArrayList<Agente> agentes = Lector.leerFicheroYCargarDatos();
					for (Agente agente : agentes) {
						System.out.println(Arrays.toString(agente.getTareasAgente()));
					}
					
				} catch (FileNotFoundException e) {
					// Logica para pedir los datos por entrada estandar
					e.printStackTrace();
				} catch (IOException e) {

					System.err.println("Error en la entrada y salida de datos: " + e.getMessage());
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
	
}
