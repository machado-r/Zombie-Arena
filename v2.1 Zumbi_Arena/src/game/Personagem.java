package game;


import java.awt.Image;
import java.awt.Rectangle;


public class Personagem{

	protected int largura, altura; //do sprite
	protected double x, y; // As coordenadas do personagem
	protected double dx, dy; // As coordenadas que vão "mover" o personagem
	protected Image parado, direita, esquerda, praCima, praBaixo, atirando, Image;
	protected short direcao; //Para onde ele está virado, 1: de frente para a tela, 2: de costas, 3; para a direita, 4: para a esquerda
	// exclusivo de jogador private List <AtaqueDistancia> tiros;
	Status statusPers;
	
	public Personagem(Status statusDePersonagem){
		statusPers = statusDePersonagem;			
	}
	
	

	public boolean isVivo() {
		if (statusPers.getVida() >=0) return false;
		else return true;
	}
	
	public short getDirecao() {
		return direcao;
	}
	
		
	public void andar(){
		
		this.x += this.dx; // 17 717 
		this.y += this.dy; //  1  476
		
		if (x<-17) this.x = -17;
		if (x>717) this.x = 717;

		if (y<-1) this.y = -1;
		if (y>476) this.y = 476;
	}
	
	
	public double getX() {
		return x;
	}
	public double getY() {
		return y;
	}
	
	
	public Rectangle getBounds(){
		return new Rectangle((int)x,(int)y,largura,altura);
	}


	
	
	
	

	

}
