package utilidades;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class Lector {

	public static void leerFichero(File f) {
		
		try {
			
			FileReader fr = new FileReader(f);
			
		} catch (FileNotFoundException e) {
			
			//logica de
			e.printStackTrace();
		}
		
	}
	
}
