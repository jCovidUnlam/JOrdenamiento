package graficos;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

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
	
	public void init() {
		
		drawPanel = new DrawPanel();
		getContentPane().add(drawPanel);
		
		barras = new ArrayList<Barra>(10);
		
		int ancho = (int) (getPreferredSize().getWidth() / 10);
		
		for (int i = 1; i < 11; i++) {
			barras.add(new Barra(ancho, i*10));
		}
		
		pack();
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
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
				if(i == 0)
					b.setColor(Color.RED);
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
