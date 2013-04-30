package game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Core extends JPanel implements ActionListener{

	private Image fundo;
	private Jogador player;
	//private Inimigo zumbi;
	private Timer timer; //timer para atualizar a tela
	private boolean emJogo; //vai controlar o estado de jogo
	private Mapa mapa;
	private List<Inimigo> zumbis;
	private int conWaves = -1;

	
	public Core(){		
		emJogo = true; //seta um estado de jogo, no caso, jogo ativo;				
		setFocusable(true);
		setDoubleBuffered(true);
		addKeyListener(new TecladoAdapter());		
		ImageIcon referencia = new ImageIcon("res//characters//fundo2000.jpg" );
		fundo = referencia.getImage();				
		Status statusDePers = new Status(100, 20, 3 , 0);
		Status statusInimigo = new Status(100,1,0.9,0);
		player = new Jogador(statusDePers);
		mapa = new Mapa(1000,700);
		zumbis = new ArrayList<Inimigo>();
		this.criaInimigo();		
		timer = new Timer(1,this);
		timer.start();
	}
	
	/*
	 * este método controla a horda de inimigos. Serão sempre 6 inimigos por horda
	 * Um nova horda só é gerada depois que a primeira horda morre.
	 */
	public void criaInimigo(){
		conWaves++;
		
		//Timer timerAtraso = new Timer(3000,this);//garante um atraso de 3 segundos entre as hordas
		//timerAtraso.start();
	for (int cont = 0; cont<6 ; cont++){
			//statusInimigo.add(new Status(100,5,0.3,0));
			zumbis.add(new Inimigo(new Status(100+10*conWaves,1+2*conWaves,0.3+0.1*conWaves,0)));
			//zumbis.get(cont).run();
			
			//zumbi = new Inimigo(statusDeInimigo);				
		}			
	}
	

	public void paint(Graphics g){

		Graphics2D graficos = (Graphics2D) g;
		graficos.drawImage(fundo,0,0,null);
		
		for (int k=0; k<mapa.getX()/32; k++){
			for (int l=0; l<mapa.getY()/32; l++){
				graficos.drawImage(mapa.getTile(k, l), k*32, l*32+20, this);
			}
		}
		
		List<AtaqueDistancia> tiros = player.getTiros();
		for (int i =0; i< tiros.size(); i++){
			AtaqueDistancia t = (AtaqueDistancia) tiros.get(i);
			graficos.drawImage(t.getImagem(), (int)t.getX(), (int)t.getY(), this);
		}
		for(int i =0; i< zumbis.size(); i++){
				Inimigo temp = (Inimigo) zumbis.get(i);
				//graficos.setFont(new Font("Serif", 1, 18));
				graficos.setColor(new Color(255,0,0));
				graficos.drawImage(temp.getImage(),(int)temp.getX(),(int)temp.getY(),this);
				graficos.drawString(" "+temp.statusPers.getVida()+" ", (int)temp.getX()+5,(int)temp.getY()-2);	
		}
		//graficos.setFont(this.getFonte(30));
		graficos.setColor(new Color(255,0,0));
		graficos.drawImage(player.getImage(),(int)player.getX(),(int)player.getY(),this);
		graficos.drawString("Pontuacao: "+player.getPontuacao(), 1000, 50);
		graficos.drawString("Inimigos: "+zumbis.size(), 1000, 100);
		graficos.drawString("Vida: "+player.statusPers.getVida(), 1000, 150);
		graficos.drawString("Wave: "+conWaves, 1000, 200);
		g.dispose();
	}
	
	
	


	public void actionPerformed(ActionEvent arg0) {
		if(player.statusPers.getVida()<=0){			
			emJogo = false; //o player morreu			
		}		
		
		if(zumbis.size() == 0){
			this.criaInimigo();
		}
		
		List<AtaqueDistancia> tiros = player.getTiros();
		for (int i =0; i< tiros.size(); i++){
			AtaqueDistancia t = (AtaqueDistancia) tiros.get(i);
			if (t.isVisivel()) t.movimenta(player.getDirecao());
			else tiros.remove(i);
		}		
		
		
		for(int i =0; i< zumbis.size(); i++){			
			Inimigo tempInimigo = zumbis.get(i);
			if (tempInimigo.statusPers.isAlive()){
				tempInimigo.seguir(player.getX(),player.getY());
				tempInimigo.andar();
			}
			else{
				zumbis.remove(i);
				player.setPontuacao(player.getPontuacao()+50);
			}
				
		}
		
		verificaColisao();
		
		player.movimenta();
		

		repaint();
	}

	public void verificaColisao(){
		Rectangle recPlayer = player.getBounds();
		Rectangle recInimigo;
		Rectangle recTiro;
		
		for (int i=0 ; i< zumbis.size(); i++){
			/*
			 * Declaramos uma referência temporária/auxiliar para chegar a colisão 
			 * dos inimigos. Essa ref. recebe o inimigo que está na arrayList[i].		
			 */
			Inimigo tempInimigo = zumbis.get(i);
			recInimigo = tempInimigo.getBounds();
			
			if(recPlayer.intersects(recInimigo)){
				//zumbis.get(i).atacar(player);
				player.statusPers.tomaDano(1);
				//zumbis.get(i).run();
			}		
		
		}
		
		for (int i=0; i< player.getTiros().size();i++){
			AtaqueDistancia tempTiro = player.getTiros().get(i);
			recTiro = tempTiro.getBounds();
			
			for (int j=0 ; j< zumbis.size(); j++){
				Inimigo tempInimigo = zumbis.get(j);
				recInimigo = tempInimigo.getBounds();
				
				if(recTiro.intersects(recInimigo)){
					//zumbis.get(i).atacar(player);
					//player.statusPers.tomaDano(5);
					//zumbis.get(j).statusPers.setEstaVivo(false);
					zumbis.get(j).statusPers.tomaDano(50);
					player.acertouTiro(i);
				}		
			
			}
		}
		
		
		
	}

	private class TecladoAdapter extends KeyAdapter{

		@Override
		public void keyPressed(KeyEvent e) {
			player.keyPressed(e);
		}

		@Override
		public void keyReleased(KeyEvent e) {
			player.keyReleased(e);
		}
	}

	public Font getFonte( int tamanho ){  
	    Font font = null;  
	    try{  
	        File file = new File( "res//Zdarx Simpl.ttf" );  
	        FileInputStream fis = new FileInputStream( file );  
	        font = Font.createFont( Font.TRUETYPE_FONT , fis );  
	    }catch( Exception e ){  
	        System.out.println( e.getMessage() );  
	    }  
	    font = font.deriveFont( Font.BOLD , tamanho );  
	    return font;  
	}  

}











