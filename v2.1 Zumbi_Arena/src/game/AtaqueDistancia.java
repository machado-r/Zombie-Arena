package game;

import java.awt.Image;
import java.awt.Rectangle;
import javax.swing.ImageIcon;

public class AtaqueDistancia {
	private int largura, altura; //do sprite
	private short direcaoTiro;
	private Image imagem;
	private double x,y;
	private boolean isVisivel;
	private static final int LARGURA_TELA = 800, ALTURA_TELA = 600; 
	private static final float VELOCIDADE_TIRO = (float) 15.1;
	
	
	
	public AtaqueDistancia(double d, double e, short dir){
		this.x = d;
		this.y = e;
		direcaoTiro = dir;
		ImageIcon ref = new ImageIcon("res//characters//tiro.png");
		imagem = ref.getImage();
		isVisivel = true;
		largura = imagem.getWidth(null);
		altura = imagem.getHeight(null);
	}


	public void movimenta(short direcao){
		switch (direcaoTiro){
		case 1: 
			this.y += VELOCIDADE_TIRO;
			if (y>ALTURA_TELA) isVisivel = false;
			//System.out.println("Tiro: "+x+" "+y);
			break;
		case 2:
			this.y -= VELOCIDADE_TIRO;
			if (y<ALTURA_TELA-ALTURA_TELA) isVisivel = false;
			//System.out.println("Tiro: "+x+" "+y);
			break;
		case 3:
			this.x += VELOCIDADE_TIRO;
			if (x>LARGURA_TELA) isVisivel = false;
			//System.out.println("Tiro: "+x+" "+y);
			break;
		case 4:
			this.x -= VELOCIDADE_TIRO;
			if (x<LARGURA_TELA-LARGURA_TELA) isVisivel = false;
			//System.out.println("Tiro: "+x+" "+y);
			break;
	

		}

			
	}
	public boolean isVisivel() {
		return isVisivel;
	}



	public void setVisivel(boolean isVisivel) {
		this.isVisivel = isVisivel;
	}



	public Image getImagem() {
		return imagem;
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