package procesador;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Procesador {
	
	public static final List<String> listaCaracteristicas = new ArrayList<String>();
	
	public static void cargarConfiguracion() {
		BufferedReader br = null;
		try {
			String linea;
			br = new BufferedReader(new FileReader("confProducto/configuracion.txt"));	
			while ((linea = br.readLine()) != null) {
				listaCaracteristicas.add(linea.trim());
				System.out.println(linea);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null)br.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}
}
