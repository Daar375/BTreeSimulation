import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Random;

public class Chayotera {
	private static Chayotera chayotera;
	private static int Chayotes=0;
	private static ArrayList<Hijo> Hijos = new ArrayList();
	private static int MaxChayotes=30;
	private static int Porcentaje=90;
	private static int Segundos=2;
	private static int MaxHijos=3;
	private static boolean encrecimiento;
	private static Graphics g;

	public static int getMaxHijos() {
		return MaxHijos;
	}

	public static void setMaxHijos(int maxHijos) {
		MaxHijos = maxHijos;
	}

	private Chayotera() {
	}

	public static void PintarChayotera() {
		g.setColor(Color.GRAY);
		g.drawRect(0, 0, 2000, 2000);
		g.fillRect(0, 0, 2000, 2000);
		g.setColor(Color.red);
		g.fillRect(750, 0, 25, 25);
		g.drawRect(750, 0, 25, 25);

		int index = 0;
		int pivote = Hijos.size() / 2;
		int HijoX;
		int HijoY=40;

		int posFinal = pivote * 120;
		if (Hijos.size() % 2 == 0) {
			posFinal = posFinal + 780;

		}
		else{
			posFinal = posFinal + 750;
		}

		while (index <= Hijos.size() - 1) {
			HijoX = 0;
			Hijos.get(index).setPosY(HijoY);
			HijoX = posFinal - (120 * index);

			Hijos.get(index).setPosX(HijoX);
			g.setColor(Color.green);


			g.drawLine(760, 0, HijoX, HijoY);
			Hijos.get(index).Pintar();

			index++;
		}

	}

	public static Graphics getG() {
		return g;
	}

	public static void setG(Graphics g) {
		Chayotera.g = g;
	}



	public static void Crecer() {
		Random r = new Random();
		encrecimiento = true;
		while (Hijos.size() < MaxHijos && Chayotes < MaxChayotes) {
			try {
				Thread.sleep(getSegundos() * 1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			int Result = r.nextInt(100);
			Hijo hijo = new Hijo();
			Hijos.add(hijo);

			if (Result > Porcentaje) {
				System.out.println("Nueva rama");
				hijo.setTipo(Tipo.Rama);
				hijo.start();

			} else {
				System.out.println("Nueva Chayote");

				hijo.setTipo(Tipo.Chayote);
				
				Chayotes++;
			}
			PintarChayotera();

		}
		if (getChayotes() >= getMaxChayotes()) {
			setEncrecimiento(false);
		}
		int index=0;
		if(Hijos.size() == MaxHijos){
			boolean terminar=true;

			while(index!=MaxHijos){
				if(Hijos.get(index).getTipo().toString()=="Rama"){
					terminar=false;
					break;
				}
				index++;
			}
			if(terminar==true){
				encrecimiento=false;
			}
		}

	}

	public static boolean isEncrecimiento() {
		return encrecimiento;
	}

	public static void setEncrecimiento(boolean encrecimiento) {
		Chayotera.encrecimiento = encrecimiento;
	}

	public static void Cortar() {
		Hijos.removeAll(Hijos);
		Chayotes=0;
	}

	public static Chayotera getInstance() {
		if (chayotera == null) {
			chayotera = new Chayotera();
		}
		return chayotera;
	}

	public static int getChayotes() {
		return Chayotes;
	}

	public static void setChayotes(int chayotes) {
		Chayotes = chayotes;
	}

	public static int getMaxChayotes() {
		return MaxChayotes;
	}

	public static void setMaxChayotes(int maxChayotes) {
		MaxChayotes = maxChayotes;
	}

	public static int getPorcentaje() {
		return Porcentaje;
	}

	public static void setPorcentaje(int porcentaje) {
		Porcentaje = porcentaje;
	}

	public static int getSegundos() {
		return Segundos;
	}

	public static void setSegundos(int segundos) {
		Segundos = segundos;
	}

	public static ArrayList getHijos() {
		return Hijos;
	}

	public static void setHijos(ArrayList hijos) {
		Hijos = hijos;
	}

}
