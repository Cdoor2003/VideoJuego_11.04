package principal;

import model.Arena;
import model.Personaje;

import java.util.Scanner;

public class main {
    public static void main(String[] args){
        inicializar();
    }
    public static void inicializar(){
        Scanner teclado = new Scanner(System.in);
        Arena arena = new Arena();
        System.out.println("Ingresa el nombre de tu personaje: ");
        String nombre = teclado.next();
        System.out.println("Ingresa la fuerza de tu personaje (20-100): ");
        int fuerza = teclado.nextInt();
        System.out.println("Ingresa la velocidad de tu personaje (20-100): ");
        int velocidad = teclado.nextInt();
        System.out.println("Ingresa la vida de tu personaje (20-100): ");
        int vida = teclado.nextInt();
        System.out.println("Ingresa el nombre del ataque de tu personaje: ");
        String ataque = teclado.next();
        Personaje personaje = new Personaje(nombre,fuerza,velocidad,vida,ataque);
        arena.menuPrincipal(personaje,vida);
    }
}
