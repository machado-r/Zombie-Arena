package game;


import java.awt.Image;
import java.util.Random;

import javax.swing.ImageIcon;
import java.util.Timer;
import java.util.TimerTask;

public class Inimigo extends Personagem implements Runnable{
	
	
	public Inimigo(Status enemyStatus){
		super(enemyStatus);
		
		ImageIcon referencia = new ImageIcon("res//characters//zumbi.png");
		parado = referencia.getImage();
		referencia = new ImageIcon("res//characters//zumbi.png");
		esquerda = referencia.getImage();
		referencia = new ImageIcon("res/characters//zumbi.png");
		direita = referencia.getImage();
		referencia = new ImageIcon("res//characters//zumbi.png");
		praCima = referencia.getImage();
		referencia = new ImageIcon("res//characters//zumbi.png");
		praBaixo = referencia.getImage();
		Image = parado;
		largura = Image.getWidth(null);
		altura = Image.getHeight(null);
				
		Random gerador = new Random();	
		this.x = gerador.nextInt(600);
		this.y = gerador.nextInt(400);
	}
	
	@Override
	public void andar(){
		
		
		x +=dx; // 17 717 
		y +=dy; //  1  476
		
		if (x<-17) x = -17;
		if (x>717) x = 717;

		if (y<1) y = 1;
		if (y>476) y = 476;
	}
	
	public void run(){
			try	{				
				Thread.sleep((int)300);
			}
			catch (InterruptedException e){					
			}		
	}
	
	
	public void seguir(double xAlvo, double yAlvo){ //vai "perseguir" o personagem
		if (yAlvo < y - altura){
			dy = - statusPers.getVelocidadeMov();
			Image = praCima;
		}
		if (yAlvo > y + altura){
			dy= + statusPers.getVelocidadeMov();
			Image = praBaixo;
		}
		if (xAlvo < x + largura){
			dx=-statusPers.getVelocidadeMov();
			Image = esquerda;
		}
		if (xAlvo > x - largura){
			dx=statusPers.getVelocidadeMov();
			Image = direita;
		}
		if (x>xAlvo-largura && x<xAlvo+largura){
			if (yAlvo < y ){
				dy = - statusPers.getVelocidadeMov();
				Image = praCima;
			}
			if (yAlvo > y){
				dy= + statusPers.getVelocidadeMov();
				Image = praBaixo;
			}
			
		}
		if (y>yAlvo-altura && y<yAlvo+altura){
			if (xAlvo < x ){
				dx = - statusPers.getVelocidadeMov();
				Image = esquerda;
			}
			if (xAlvo > x){
				dx= + statusPers.getVelocidadeMov();
				Image = direita;
			}
			
			}
			if (y==yAlvo && x==xAlvo){
					Image = parado;
				
					
			}

		}

	public void atacar(Personagem player){
		/*try	{				
			Thread.sleep(300);
		}
		catch (InterruptedException e){					
		}	*/	
	}
			
	public boolean isVivo() {
		if (statusPers.getVida() >=0) return false;
		else return true;
	}

	public Image getImage() {
		return Image;
	}

	public double getX() {
		return x;
	}
	public void setX(double d) {
		this.x = d;
	}

	public double getY() {
		return y;
	}



	
	

}

