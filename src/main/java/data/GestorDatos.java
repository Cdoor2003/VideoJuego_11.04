package data;

import model.Arena;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class GestorDatos {
	private Arena arena;

	public static boolean registrarDatos(String pelea, String direccionArchivo) {
		try {
			File file = new File(direccionArchivo);
			FileWriter fw = new FileWriter(file, true);
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(pelea);
			bw.newLine();
			bw.close();
			fw.close();
			return true;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
	}
}