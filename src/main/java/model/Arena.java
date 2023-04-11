package model;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import data.GestorDatos;
import model.Enemigo;
import model.Personaje;

public class Arena {
	private GestorDatos gestorDatos;
	private ArrayList<Enemigo> enemigos = new ArrayList<Enemigo>();
	private ArrayList<Personaje> personajes = new ArrayList<Personaje>();

	public void mostrarMenuPrincipal() {
		System.out.println("1) Pelea contra La Marina");
		System.out.println("2) Pelea contra otro personaje");
		System.out.println("3) Salir del juego");
		System.out.println("Seleccione una opción");
	}

	public void salirMenu() {
		System.out.println("Saliste del juego.");
	}

	public void menuPrincipal(Personaje personaje,int vidaPersonaje) {
		Scanner teclado = new Scanner(System.in);
		int opcion = 0;
		do {
			try {
				mostrarMenuPrincipal();
				opcion = teclado.nextInt();
				if (opcion < 1 || opcion > 3) {
					teclado.nextLine();
					System.err.println("Opción ingresada no valida, por favor intente de nuevo");
				}
				switch (opcion) {
					case 1: {
						opcionPeleaContraMarina(personaje,vidaPersonaje);
					}
					case 2:{
						System.out.println("Ingrese el nombre del personaje enemigo: ");
						String nombre = teclado.next();
						System.out.println("Ingrese el nombre del ataque del enemigo: ");
						String nombreAtaque = teclado.next();
						Personaje personaje1 = new Personaje(nombre,((int)(Math.random()*(100-1+1)+1)),((int)(Math.random()*(100-1+1)+1)),((int)(Math.random()*(100-1+1)+1)),nombreAtaque);
						peleaContraPersonaje(personaje,personaje1,vidaPersonaje);
					}
				}
			} catch (InputMismatchException e) {
				teclado.nextLine();
				System.err.println("Opción ingresada no valida, por favor intente de nuevo");
			}
		} while (opcion != 3);
		salirMenu();
		System.exit(0);
	}

	public void opcionPeleaContraMarina(Personaje personaje,int vidaPersonaje) {
		Scanner teclado = new Scanner(System.in);
		int opcion = 0;
		do {
			try {
				mostrarMenuPeleaContraMarina();
				opcion = teclado.nextInt();
				if (opcion < 1 || opcion > 4) {
					teclado.nextLine();
					System.err.println("Opción ingresada no valida, por favor intente de nuevo");
				}
				switch (opcion) {
					case 1:{
						peleaContraCapitan(personaje,vidaPersonaje);
					}
					case 2:{
						peleaContraViceAlmirante(personaje,vidaPersonaje);
					}
					case 3:{
						peleaContraAlmirante(personaje,vidaPersonaje);
					}
				}
			} catch (InputMismatchException e) {
				teclado.nextLine();
				System.err.println("Opción ingresada no valida, por favor intente de nuevo");
			}
		} while (opcion != 4);
		salirMenu();
		menuPrincipal(personaje,vidaPersonaje);
	}

	public void peleaContraCapitan(Personaje personaje,int vidaPersonaje) {
		Capitan capitan = new Capitan(((int)(Math.random()*(30-1+1)+1)),((int)(Math.random()*(30-1+1)+1)),((int)(Math.random()*(30-1+1)+1)));
		String[] ataquesEspeciales = capitan.getAtaquesEspecialesCapitan();
		String ataqueEspecial = ataquesEspeciales[(int)(Math.random()*(3-0+1)+0)];
		System.out.println("\u001B[31m" +capitan.toString()+ "\u001B[0m");
		while(personaje.getVida() > 0 && capitan.getVida() > 0){
			System.out.println("\n\u001B[31m" +"La vida del Capitan es: "+capitan.getVida()+ "\u001B[0m");
			System.out.println("\u001B[34m" +"La vida de "+personaje.getNombre()+" es de "+personaje.getVida()+"\u001B[0m");
			if(personaje.getVelocidad() > capitan.getVelocidad()){
				int dañoPersonaje = ((int)(Math.random()*((personaje.getFuerza())-(personaje.getFuerza()-20)+1)+(personaje.getFuerza()-20)));
				System.out.println("\u001B[34m" +personaje.getNombre()+" ataca con "+personaje.getAtaque()+ "\u001B[0m");
				System.out.println("\u001B[34m" +"Hace un daño de: "+dañoPersonaje+ "\u001B[0m");
				capitan.setVida(capitan.getVida()-dañoPersonaje);
				if(capitan.getVida() > 0){
					int dañoCapitan = ((int)(Math.random()*((capitan.getFuerza())-1+1)+1));
					System.out.println("\u001B[31m" +"Capitan de la marina ataca con: "+ataqueEspecial+ "\u001B[0m");
					System.out.println("\u001B[31m" +"Hace un daño de: "+dañoCapitan+ "\u001B[0m");
					personaje.setVida(personaje.getVida()-dañoCapitan);
				}
			}
			else{
				int dañoCapitan = ((int)(Math.random()*((capitan.getFuerza())-1+1)+1));
				System.out.println("\u001B[31m" +"Capitan de la marina ataca con: "+ataqueEspecial+ "\u001B[0m");
				System.out.println("\u001B[31m" +"Hace un daño de: "+dañoCapitan+ "\u001B[0m");
				personaje.setVida(personaje.getVida()-dañoCapitan);
				if(personaje.getVida() > 0){
					int dañoPersonaje = ((int)(Math.random()*((personaje.getFuerza())-(personaje.getFuerza()-20)+1)+(personaje.getFuerza()-20)));
					System.out.println("\u001B[34m" +personaje.getNombre()+" ataca con "+personaje.getAtaque()+ "\u001B[0m");
					System.out.println("\u001B[34m" +"Hace un daño de: "+dañoPersonaje+ "\u001B[0m");
					capitan.setVida(capitan.getVida()-dañoPersonaje);
				}
			}
		}
		if(personaje.getVida() < 0){
			System.out.println("Has perdido contra Capitan de la marina");
			gestorDatos.registrarDatos("El personaje "+personaje.getNombre()+" perdio contra Capitan","Peleas.txt");
		}
		else{
			System.out.println("Le has ganado al Capitan de la marina");
			gestorDatos.registrarDatos("El personaje "+personaje.getNombre()+" vencio a Capitan","Peleas.txt");
		}
		personaje.setVida(vidaPersonaje);
		menuPrincipal(personaje,vidaPersonaje);
	}

	public void mostrarMenuPeleaContraMarina() {
		System.out.println("1) Capitan");
		System.out.println("2) Vicealmirante");
		System.out.println("3) Almirante");
		System.out.println("4) Volver a menu principal");
		System.out.println("Seleccione la opcion que desee");
	}

	public void peleaContraViceAlmirante(Personaje personaje,int vidaPersonaje) {
		ViceAlmirante vicealmirante = new ViceAlmirante(((int)(Math.random()*(60-30+1)+30)),((int)(Math.random()*(60-30+1)+30)),((int)(Math.random()*(60-30+1)+30)));
		String[] ataquesEspeciales = vicealmirante.getAtaquesEspecialesVicealmirante();
		String ataqueEspecial = ataquesEspeciales[(int)(Math.random()*(3-0+1)+0)];
		System.out.println("\u001B[31m" +vicealmirante.toString()+ "\u001B[0m");
		while(personaje.getVida() > 0 && vicealmirante.getVida() > 0){
			System.out.println("\n\u001B[31m" +"La vida del ViceAlmirante es: "+vicealmirante.getVida()+ "\u001B[0m");
			System.out.println("\u001B[34m" +"La vida de "+personaje.getNombre()+" es de "+personaje.getVida()+"\u001B[0m");
			if(personaje.getVelocidad() > vicealmirante.getVelocidad()){
				int dañoPersonaje = ((int)(Math.random()*((personaje.getFuerza())-(personaje.getFuerza()-20)+1)+(personaje.getFuerza()-20)));
				System.out.println("\u001B[34m" +personaje.getNombre()+" ataca con "+personaje.getAtaque()+ "\u001B[0m");
				System.out.println("\u001B[34m" +"Hace un daño de: "+dañoPersonaje+ "\u001B[0m");
				vicealmirante.setVida(vicealmirante.getVida()-dañoPersonaje);
				if(vicealmirante.getVida() > 0){
					int dañoVicealmirante = ((int)(Math.random()*((vicealmirante.getFuerza())-1+1)+1));
					System.out.println("\u001B[31m" +"Vicealmirante de la marina ataca con: "+ataqueEspecial+ "\u001B[0m");
					System.out.println("\u001B[31m" +"Hace un daño de: "+dañoVicealmirante+ "\u001B[0m");
					personaje.setVida(personaje.getVida()-dañoVicealmirante);
				}
			}
			else{
				int dañoVicealmirante = ((int)(Math.random()*((vicealmirante.getFuerza())-1+1)+1));
				System.out.println("\u001B[31m" +"Vicealmirante de la marina ataca con: "+ataqueEspecial+ "\u001B[0m");
				System.out.println("\u001B[31m" +"Hace un daño de: "+dañoVicealmirante+ "\u001B[0m");
				personaje.setVida(personaje.getVida()-dañoVicealmirante);
				if(personaje.getVida() > 0){
					int dañoPersonaje = ((int)(Math.random()*((personaje.getFuerza())-(personaje.getFuerza()-20)+1)+(personaje.getFuerza()-20)));
					System.out.println("\u001B[34m" +personaje.getNombre()+" ataca con "+personaje.getAtaque()+ "\u001B[0m");
					System.out.println("\u001B[34m" +"Hace un daño de: "+dañoPersonaje+ "\u001B[0m");
					vicealmirante.setVida(vicealmirante.getVida()-dañoPersonaje);
				}
			}
		}
		if(personaje.getVida() < 0){
			System.out.println("Has perdido contra Vicealmirante de la marina");
			gestorDatos.registrarDatos("El personaje "+personaje.getNombre()+" perdio contra ViceAlmirante","Peleas.txt");
		}
		else{
			System.out.println("Le has ganado al Vicealmirante de la marina");
			gestorDatos.registrarDatos("El personaje "+personaje.getNombre()+" vencio a ViceAlmirante","Peleas.txt");
		}
		personaje.setVida(vidaPersonaje);
		menuPrincipal(personaje,vidaPersonaje);
	}

	public void peleaContraAlmirante(Personaje personaje,int vidaPersonaje) {
		Almirante almirante = new Almirante(((int)(Math.random()*(100-60+1)+60)),((int)(Math.random()*(100-60+1)+60)),((int)(Math.random()*(100-60+1)+60)));
		String[] ataquesEspeciales = almirante.getAtaquesEspecialesAlmirante();
		String ataqueEspecial = ataquesEspeciales[(int)(Math.random()*(3-0+1)+0)];
		System.out.println("\u001B[31m" +almirante.toString()+ "\u001B[0m");
		while(personaje.getVida() > 0 && almirante.getVida() > 0){
			System.out.println("\n\u001B[31m" +"La vida del Almirante es: "+almirante.getVida()+ "\u001B[0m");
			System.out.println("\u001B[34m" +"La vida de "+personaje.getNombre()+" es de "+personaje.getVida()+"\u001B[0m");
			if(personaje.getVelocidad() > almirante.getVelocidad()){
				int dañoPersonaje = ((int)(Math.random()*((personaje.getFuerza())-(personaje.getFuerza()-20)+1)+(personaje.getFuerza()-20)));
				System.out.println("\u001B[34m" +personaje.getNombre()+" ataca con "+personaje.getAtaque()+ "\u001B[0m");
				System.out.println("\u001B[34m" +"Hace un daño de: "+dañoPersonaje+ "\u001B[0m");
				almirante.setVida(almirante.getVida()-dañoPersonaje);
				if(almirante.getVida() > 0){
					int dañoAlmirante = ((int)(Math.random()*((almirante.getFuerza())-1+1)+1));
					System.out.println("\u001B[31m" +"Almirante de la marina ataca con: "+ataqueEspecial+ "\u001B[0m");
					System.out.println("\u001B[31m" +"Hace un daño de: "+dañoAlmirante+ "\u001B[0m");
					personaje.setVida(personaje.getVida()-dañoAlmirante);
				}
			}
			else{
				int dañoAlmirante = ((int)(Math.random()*((almirante.getFuerza())-1+1)+1));
				System.out.println("\u001B[31m" +"Almirante de la marina ataca con: "+ataqueEspecial+ "\u001B[0m");
				System.out.println("\u001B[31m" +"Hace un daño de: "+dañoAlmirante+ "\u001B[0m");
				personaje.setVida(personaje.getVida()-dañoAlmirante);
				if(personaje.getVida() > 0){
					int dañoPersonaje = ((int)(Math.random()*((personaje.getFuerza())-(personaje.getFuerza()-20)+1)+(personaje.getFuerza()-20)));
					System.out.println("\u001B[34m" +personaje.getNombre()+" ataca con "+personaje.getAtaque()+ "\u001B[0m");
					System.out.println("\u001B[34m" +"Hace un daño de: "+dañoPersonaje+ "\u001B[0m");
					almirante.setVida(almirante.getVida()-dañoPersonaje);
				}
			}
		}
		if(personaje.getVida() < 0){
			System.out.println("Has perdido contra Almirante de la marina");
			gestorDatos.registrarDatos("El personaje "+personaje.getNombre()+" perdio contra Almirante","Peleas.txt");
		}
		else{
			System.out.println("Le has ganado al Almirante de la marina");
			gestorDatos.registrarDatos("El personaje "+personaje.getNombre()+" vencio a Almirante","Peleas.txt");
		}
		personaje.setVida(vidaPersonaje);
		menuPrincipal(personaje,vidaPersonaje);
	}

	public void peleaContraPersonaje(Personaje personaje, Personaje personaje1,int vidaPersonaje) {
		System.out.println("\u001B[31m" +personaje1.toString()+ "\u001B[0m");
		while(personaje.getVida() > 0 && personaje1.getVida() > 0){
			System.out.println("\n\u001B[31m" +"La vida de "+personaje1.getNombre()+" es: "+personaje1.getVida()+ "\u001B[0m");
			System.out.println("\u001B[34m" +"La vida de "+personaje.getNombre()+" es de "+personaje.getVida()+"\u001B[0m");
			if(personaje.getVelocidad() > personaje1.getVelocidad()){
				int dañoPersonaje = ((int)(Math.random()*((personaje.getFuerza())-(personaje.getFuerza()-20)+1)+(personaje.getFuerza()-20)));
				System.out.println("\u001B[34m" +personaje.getNombre()+" ataca con "+personaje.getAtaque()+ "\u001B[0m");
				System.out.println("\u001B[34m" +"Hace un daño de: "+dañoPersonaje+ "\u001B[0m");
				personaje1.setVida(personaje1.getVida()-dañoPersonaje);
				if(personaje1.getVida() > 0){
					int dañoPersonaje1 = ((int)(Math.random()*((personaje1.getFuerza())-1+1)+1));
					System.out.println("\u001B[31m" +personaje1.getNombre()+" ataca con: "+personaje1.getAtaque()+ "\u001B[0m");
					System.out.println("\u001B[31m" +"Hace un daño de: "+dañoPersonaje1+ "\u001B[0m");
					personaje.setVida(personaje.getVida()-dañoPersonaje1);
				}
			}
			else{
				int dañoPersonaje1 = ((int)(Math.random()*((personaje1.getFuerza())-1+1)+1));
				System.out.println("\u001B[31m" +personaje1.getNombre()+" ataca con: "+personaje1.getAtaque()+ "\u001B[0m");
				System.out.println("\u001B[31m" +"Hace un daño de: "+dañoPersonaje1+ "\u001B[0m");
				personaje.setVida(personaje.getVida()-dañoPersonaje1);
				if(personaje.getVida() > 0){
					int dañoPersonaje = ((int)(Math.random()*((personaje.getFuerza())-(personaje.getFuerza()-20)+1)+(personaje.getFuerza()-20)));
					System.out.println("\u001B[34m" +personaje.getNombre()+" ataca con "+personaje.getAtaque()+ "\u001B[0m");
					System.out.println("\u001B[34m" +"Hace un daño de: "+dañoPersonaje+ "\u001B[0m");
					personaje1.setVida(personaje1.getVida()-dañoPersonaje);
				}
			}
		}
		if(personaje.getVida() < 0){
			System.out.println("Has perdido contra "+personaje1.getNombre());
			gestorDatos.registrarDatos("El personaje "+personaje.getNombre()+" perdio contra "+personaje1.getNombre(),"Peleas.txt");
		}
		else{
			System.out.println("Le has ganado ha "+personaje1.getNombre());
			gestorDatos.registrarDatos("El personaje "+personaje.getNombre()+" vencio a "+personaje1.getNombre(),"Peleas.txt");
		}
		personaje.setVida(vidaPersonaje);
		menuPrincipal(personaje,vidaPersonaje);
	}
}