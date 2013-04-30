package game;

import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;

public class Jogador extends Personagem implements Runnable{
	private List <AtaqueDistancia> tiros;
	private int Pontuacao=0;

	public Jogador(Status statusDePersonagem) {
		super(statusDePersonagem);	
		
		ImageIcon referencia = new ImageIcon("res//characters//charfrente1.png");
		parado = referencia.getImage();
		referencia = new ImageIcon("res//characters//charesq1.png");
		esquerda = referencia.getImage();
		referencia = new ImageIcon("res/characters///chardir1.png");
		direita = referencia.getImage();
		referencia = new ImageIcon("res//characters//charcostas1.png");
		praCima = referencia.getImage();
		referencia = new ImageIcon("res//characters//charfrente1.png");
		praBaixo = referencia.getImage();
		Image = parado;
		
		this.largura = Image.getWidth(null);
		this.altura = Image.getHeight(null);
		
		tiros = new ArrayList<AtaqueDistancia>();
		this.x = 400;
		this.y = 500;
		direcao = 1; //determina a direçao do tiro quando ele é disparado
	}
	
	public List<AtaqueDistancia> getTiros() {
		return tiros;
	}
	
	public void acertouTiro(int pos){
		tiros.remove(pos);
	}

	public boolean isVivo() {
		if (statusPers.getVida() >=0) return false;
		else return true;
	}
	public short getDirecao() {
		return direcao;
	}
	
	
	public void atira(){
		switch (direcao){
		case 1: 
			this.tiros.add(new AtaqueDistancia(x+largura/2,y+altura, (short)1));
			break;
		case 2:
			this.tiros.add(new AtaqueDistancia(x+largura/2,y,(short)2));
			break;
		case 3:
			this.tiros.add(new AtaqueDistancia(x+60,y+altura/2,(short)3));
			break;
		case 4:
			this.tiros.add(new AtaqueDistancia(x,y+altura/2,(short)4));
			break;
		}
		
	}

	public void movimenta(){
		
		this.x += this.dx; // 17 717 
		this.y += this.dy; //  1  476
	}
	
	
	
	
	public double getX() {
		return x;
	}
	public double getY() {
		return y;
	}
	public Image getImage() {
		return Image;
	}
	public Image getParado() {
		return parado;
	}
	public Image getDireita() {
		return direita;
	}
	public Image getEsquerda() {
		return esquerda;
	}
	public Image getPraCima() {
		return praCima;
	}
	public Image getPraBaixo() {
		return praBaixo;
	}
	public Image getAtirando() {
		return atirando;
	}	
	
	
	public void keyPressed(KeyEvent tecla){
		int codigo = tecla.getKeyCode();
		
		
		if (codigo == KeyEvent.VK_SPACE) {
			this.atira();
		}
		
		if (codigo == KeyEvent.VK_UP){
			this.direcao = 2;
			this.dy = - statusPers.getVelocidadeMov();
			this.Image = praCima;
		}
		if (codigo == KeyEvent.VK_DOWN){
			this.direcao = 1;
			this.dy = statusPers.getVelocidadeMov();
			this.Image = praBaixo;
		}
		if (codigo == KeyEvent.VK_LEFT){
			this.direcao = 4;
			this.dx = -statusPers.getVelocidadeMov();
			this.Image = esquerda;
		}
		if (codigo == KeyEvent.VK_RIGHT){
			this.direcao = 3;
			this.dx =  statusPers.getVelocidadeMov();
			this.Image = direita;
		}
		
	}
	public void keyReleased(KeyEvent tecla){
		int codigo = tecla.getKeyCode();
		
		if (codigo == KeyEvent.VK_UP){
			this.dy = 0;
		}
		if (codigo == KeyEvent.VK_DOWN){
			this.dy = 0;
		}
		if (codigo == KeyEvent.VK_LEFT){
			this.dx = 0;
		}
		if (codigo == KeyEvent.VK_RIGHT){
			this.dx = 0;
		}
			
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}

	public int getPontuacao() {
		return Pontuacao;
	}

	public void setPontuacao(int pontuacao) {
		Pontuacao = pontuacao;
	}
	
}
