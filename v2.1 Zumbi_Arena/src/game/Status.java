package game;

public class Status {
	private boolean alive = true;


	private int vida;
	private int dano;
	private double velocidadeMov;
	private double velocidadeAtaque;


	public Status(int vida, int dano, double vm, int va) {
		this.vida = vida;
		this.dano = dano;
		velocidadeMov = vm;
		velocidadeAtaque = va;
	}
	
	public boolean isAlive() {
		return alive;
	}

	public void setEstaVivo(boolean estaVivo) {
		this.alive = estaVivo;
	}

	public int getVida() {
		return vida;
	}

	public void tomaDano(int dano) {
		this.vida =this.vida- dano;
		if( this.vida <= 0){
			this.alive = false;
		}
	}

	public int getDano() {
		return dano;
	}

	public void setDano(int dano) {
		this.dano = dano;
	}

	public double getVelocidadeMov() {
		return velocidadeMov;
	}

	public void setVelocidadeMov(float velocidadeMov) {
		this.velocidadeMov = velocidadeMov;
	}

	public double getVelocidadeAtaque() {
		return velocidadeAtaque;
	}

	public void setVelocidadeAtaque(float velocidadeAtaque) {
		this.velocidadeAtaque = velocidadeAtaque;
	}
}