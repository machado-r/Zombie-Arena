package game;

import java.awt.Image;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Formatter;
import java.util.Scanner;

import javax.swing.ImageIcon;

/**
 *
 * @author rafael
 */
public class Mapa {
	
    private int TamanhoX, TamanhoY;
    private int[][] Eventos;
    private Image[][] tiles;
    private Image chao,muro,caixa,espinho;
    private int numX, numY;
    
    public Mapa(int TamX, int TamY){
    	ImageIcon referencia = new ImageIcon("res//world//teste3.jpg");
		chao = referencia.getImage();
		referencia = new ImageIcon("res//world//teste2.jpg");
		muro = referencia.getImage();
        TamanhoX = TamX;
        TamanhoY = TamY;
        numX = TamanhoX/32;
        numY = TamanhoY/32;
        Eventos = new int [numX][numY];
        tiles = new Image[numX+1][numY+1];
       // this.importaMapa("batata");
       this.criarMapa();
      // this.exportaMapa("batata");
      // this.imprimeMapa();
    }
    
    public int getX(){
        return TamanhoX;
        }
    public int getY(){
        return TamanhoY;
    }
    
    public void criarMapa(){ //Vai criar um mapa "Vazio", só com muros nas laterais
        for(int i = 0; i<numX; i++){
            for(int j = 0; j<numY; j++){
                if (i==0 || i==numX -1 || j==0 || j==numY -1){
                    Eventos[i][j] = 1; //Muros
                    tiles[i][j] = muro;
                }
                else{
                    Eventos[i][j] = 0;//Espaço "livre"  
                    tiles[i][j] = chao;
                }  
                
            }
        }
    }
    
    public void imprimeMapa(){ //Imprime a matriz do mapa no console
         for(int i = 0; i<numX; i++){
            for(int j = 0; j<numY; j++){
                System.out.print(Eventos[i][j]+" ");
                
            }
            System.out.println();
        }
    
    }
    public boolean daDano(int X, int Y){ //Vai dizer se é possível andar nessa posição
        if (Eventos[(int)X][(int)Y] == 3) return true;
        else return false;
    }
    public boolean taBloqueado(double X, double Y){ //Vai dizer se é possível andar nessa posição
        if (Eventos[(int)X/32][(int)Y/32] == 1) return true;
        else return false;
    }
    public Image getTile(int X, int Y){
        return tiles[X][Y];
        }
    @SuppressWarnings("resource")
	public void exportaMapa(String nomeArquivo) {
    	try{
			Formatter f = new Formatter(nomeArquivo + ".txt");
			System.out.println("Arquivo criado com sucesso:"+ nomeArquivo);
			f.format("%d\n%d\n",this.TamanhoX, this.TamanhoY);
			for(int i=0; i<this.numX; i++){
				for(int j=0; j<this.numY; j++){
					f.format("%d ", Eventos[i][j]);
				}
				f.format("\n");
			}
			f.close();
		}catch(Exception e){
			System.out.println("ERRO: Não foi possível criar o arquivo "+ nomeArquivo);
		}
    	
        
    }
    public void importaMapa(String nome){
    	try{
			Scanner s = new Scanner ( new File(nome+".txt"));	
			this.TamanhoX = s.nextInt();
			this.TamanhoY = s.nextInt();
			int cont=0;
			System.out.println("TamanhoX: :"+TamanhoX+" TamanhoY :"+TamanhoY+"\n");
            for(int i=0; i<TamanhoX/32; i++){
            	for(int j=0; i<TamanhoY/32; j++){
            		Eventos[i][j] = s.nextInt();
            		System.out.print(Eventos[i][j]+" ");
            	}
            }
            s.close();

		}catch(Exception e){
			System.out.println("Falhou ao tentar ler o arquivo");
		}
	    	
	}
}
    

