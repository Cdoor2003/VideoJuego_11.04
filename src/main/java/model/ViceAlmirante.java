package model;

public class ViceAlmirante extends Enemigo {
	private String[] ataquesEspecialesVicealmirante;

	public ViceAlmirante(int fuerza, int velocidad, int vida) {
		super(fuerza, velocidad, vida);
		this.ataquesEspecialesVicealmirante = new String[]{"Rankyaku","Shigan","golpe con Tekkai", "rokuogan"};
	}

	public String[] getAtaquesEspecialesVicealmirante() {
		return ataquesEspecialesVicealmirante;
	}

	public void setAtaquesEspecialesVicealmirante(String[] ataquesEspecialesVicealmirante) {
		this.ataquesEspecialesVicealmirante = ataquesEspecialesVicealmirante;
	}

	@Override
	public String getTipo() {
		return "ViceAlmirante";
	}

	public String toString() {
		return "Enemigo: ViceAlmirante \n Vida: "+getVida()+"\n Fuerza: "+getFuerza()+"\n Velocidad: "+getVelocidad();
	}
}