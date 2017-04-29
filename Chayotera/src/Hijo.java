import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class Hijo extends Thread{
	private ArrayList<Hijo> Hijos = new ArrayList();
	private Tipo tipo;
	private int posX;
	private int posY;

	public void Pintar(){
		
		int index=0;

		Graphics g = Chayotera.getInstance().getG();

		if(tipo.toString()=="Rama"){
			int pivote =Hijos.size()/2; 
			int posFinal=pivote*100;
			int HijoX;
			g.setColor(Color.red);
			g.drawRect(posX, posY, 10, 10);
			g.fillRect(posX, posY, 10, 10);
			g.setColor(Color.GRAY);

			if (Hijos.size() % 2 == 0) {
				posFinal = posFinal+posX+50;

			}
			else{
				posFinal = posFinal +posX;
			}
			while(index<=Hijos.size()-1){   
				HijoX=0;
				Hijos.get(index).setPosY(posY+50);
				HijoX=posFinal-(100*index);

				
				
				
				
				
				Hijos.get(index).setPosX(HijoX);
				g.setColor(Color.green);

				g.drawLine(posX, posY, HijoX, posY+50);
				Hijos.get(index).Pintar();
				index++;
				g.setColor(Color.GRAY);

			}

			
		}
		else if (tipo.toString()=="Chayote"){
			g.setColor(Color.green);
			g.fillOval(posX, posY, 15, 15);
			g.drawOval(posX, posY, 15, 15);
			g.setColor(Color.GRAY);

		}
		


	
	    


	}

	
	public void run(){
		Chayotera Raiz=Chayotera.getInstance();
		Random r = new Random();
		
		while(Raiz.getChayotes()<Raiz.getMaxChayotes() && Hijos.size()!=Raiz.getMaxHijos()){
			System.out.println("Numero de chayotes"+ Raiz.getChayotes() + "MAX"+Raiz.getMaxChayotes() );
			try {
				Thread.sleep(Raiz.getSegundos()*1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			int Result = r.nextInt(100);
			Hijo hijo = new Hijo();
			Hijos.add(hijo);

			if(Result>Raiz.getPorcentaje()){

				hijo.setTipo(Tipo.Rama);
				hijo.start();
				
			}
			else{

				hijo.setTipo(Tipo.Chayote);
				Raiz.setChayotes(Raiz.getChayotes()+1);
			}	

		}
		if(Raiz.getChayotes()>=Raiz.getMaxChayotes()){
			Raiz.setEncrecimiento(false);
		}
		int index=0;
		if(Hijos.size() == Raiz.getMaxHijos()){
			boolean terminar=true;

			while(index!=Raiz.getMaxHijos()){
				if(Hijos.get(index).getTipo().toString()=="Rama"){
					terminar=false;
					break;
				}
				index++;
				
			}
			if(terminar==true){
				Raiz.setEncrecimiento(false);
			}
		}

	}
		
	


	public int getPosX() {
		return posX;
	}


	public void setPosX(int posX) {
		this.posX = posX;
	}


	public int getPosY() {
		return posY;
	}


	public void setPosY(int posY) {
		this.posY = posY;
	}


	public ArrayList getHijos() {
		return Hijos;
	}


	public void setHijos(ArrayList hijos) {
		Hijos = hijos;
	}


	public Tipo getTipo() {
		return tipo;
	}


	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}
}
