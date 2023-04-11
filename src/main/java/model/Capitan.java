package model;

public class Capitan extends Enemigo {
	private String[] ataquesEspecialesCapitan;

	public Capitan(int fuerza, int velocidad, int vida) {
		super(fuerza, velocidad, vida);
		this.ataquesEspecialesCapitan = new String[]{"golpe de espada","golpe de puño","golpe de pie", "un disparo"};
	}

	public String[] getAtaquesEspecialesCapitan() {
		return ataquesEspecialesCapitan;
	}

	public void setAtaquesEspecialesCapitan(String[] ataquesEspecialesCapitan) {
		this.ataquesEspecialesCapitan = ataquesEspecialesCapitan;
	}

	@Override
	public String getTipo() {
		return "Capitan";
	}

	@Override
	public String toString() {
		return "Enemigo: Capitan \n Vida: "+getVida()+"\n Fuerza: "+getFuerza()+"\n Velocidad: "+getVelocidad();
	}
}