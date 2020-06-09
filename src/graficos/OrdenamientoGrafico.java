package graficos;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

import src.AlgoritmoOrdenamiento;
import src.CasoOrdenamiento;
import src.SetUp;

public class OrdenamientoGrafico extends JFrame{
	public OrdenamientoGrafico() {
	}
	
	/*
	 * mi idea sería tener un array de barras en memoria, todas con su ancho y alto respectivo.
	 * Se construyen todas con igual ancho, y distintas alturas, de menor a mayor.
	 * dependiendo de como necesitemos el caso (ordenado, invertido, etc) , lo podemos dejar asi en orden, 
	 * darlo vuelta, o ver como desordenarlo (eso si que ni idea como hacerlo, je) 
	 */

	private static final long serialVersionUID = 1L;
	
	private DrawPanel drawPanel;
	
	private ArrayList<Barra> barras; //esto es lo que vamos a ordenar graficamente
	
	private boolean is_running = true;
	
	private SetUp setUp;
	
	public void init() {
		
		drawPanel = new DrawPanel();
		getContentPane().add(drawPanel);
		
		setUp = new SetUp(30, 100, CasoOrdenamiento.ORDENADO, AlgoritmoOrdenamiento.BURBUJEO);
		
		barras = new ArrayList<Barra>(setUp.getCantElementos());
		
		int ancho = (int) (getPreferredSize().getWidth() / setUp.getCantElementos());
		
		for (int i = 1; i <= setUp.getCantElementos(); i++) {
			barras.add(new Barra(ancho, i*10));
		}
		
		barras.get(3).barraActual();
		barras.get(7).yaOrdenada();
		
		pack();
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		//setResizable(false);
		setTitle("jOrdenamiento");
		setVisible(true);
		setFocusable(true);
		requestFocusInWindow();
	}
	
	private class DrawPanel extends JPanel {
		private static final long serialVersionUID = 91574813372177663L;

		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			Graphics2D g2 = (Graphics2D) g;

			Dimension currentDimension = getContentPane().getSize();
			//g2.scale(currentDimension.getWidth() / 800, currentDimension.getHeight() / 450);
			g2.setColor(Color.BLACK);
			g2.setFont(new Font("Dialog", Font.BOLD, 15));
			g2.drawString("Comparaciones: " + 1234, 20, 25);
			g2.drawString("Intercambios: " + 4321, 20, 45);
			g2.drawString("Tiempo: " + " X ms", 20, 65);
			int i = 0;
			for(Barra b : barras) {
				g2.setColor(b.getColor());
				g2.fillRect(i, (int)(currentDimension.getHeight() - b.getAlto()), (int)b.getAncho(), (int)b.getAlto());
				i+=b.getAncho();
			}
		}

	}
	
	@Override
	public Dimension getPreferredSize() {
		return new Dimension(800, 450);
	}
	
	public static void main(String[] args) throws Exception {
		OrdenamientoGrafico ord = new OrdenamientoGrafico();
		ord.init();
	}

}
