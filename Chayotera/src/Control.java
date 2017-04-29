import java.awt.Graphics;

public class Control {
	
	Chayotera chayotera =Chayotera.getInstance();
	public void Nueva(){
		chayotera.Cortar();

		chayotera.Crecer();

	}
	
	

	public void SetPorcentaje(int porcentaje){
		chayotera.setPorcentaje(porcentaje);
	}
	public void SetTiempo(int Tiempo){
		chayotera.setSegundos(Tiempo);
	}
	public void SetHijosMax(int HijosMax){
		chayotera.setMaxHijos(HijosMax);
	}
	public void SetChayotesMax(int ChayotesMax){
		chayotera.setMaxChayotes(ChayotesMax);
	}
	
	public static void setGraphics(Graphics g) {
		Chayotera.setG(g);
	}
    public static void main(String args[]) {
    	Control control = new Control();


    java.awt.EventQueue.invokeLater(new Runnable() {
        public void run() {
            new UI(control).setVisible(true);
        }
    });
}
}
	

