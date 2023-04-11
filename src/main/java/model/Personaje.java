package model;

public class Personaje {
	private String nombre;
	private int fuerza;
	private int velocidad;
	private int vida;
	private String ataque;

	public Personaje(String nombre, int fuerza, int velocidad, int vida, String ataque) {
		this.nombre = nombre;
		this.fuerza = fuerza;
		this.velocidad = velocidad;
		this.vida = vida;
		this.ataque = ataque;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getFuerza() {
		return this.fuerza;
	}

	public void setFuerza(int fuerza) {
		this.fuerza = fuerza;
	}

	public int getVelocidad() {
		return this.velocidad;
	}

	public void setVelocidad(int velocidad) {
		this.velocidad = velocidad;
	}

	public int getVida() {
		return this.vida;
	}

	public void setVida(int vida) {
		this.vida = vida;
	}

	public String getAtaque() {
		return ataque;
	}

	public void setAtaque(String ataque) {
		this.ataque = ataque;
	}

	public String toString(){
		return "Nombre personaje: "+getNombre()+"\nFuerza: "+getFuerza()+"\nVelocidad: "+getVelocidad()+"\nVida: "+getVida();
	}
}