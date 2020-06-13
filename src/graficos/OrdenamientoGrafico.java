package graficos;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

import enums.TipoPaso;
import src.Paso;
import src.SetUp;

public class OrdenamientoGrafico extends JFrame {
	public OrdenamientoGrafico(SetUp setUp) {
		this.setUp = setUp;
	}

	private static final long serialVersionUID = 1L;

	private DrawPanel drawPanel;

	private ArrayList<Barra> barras; // esto es lo que vamos a ordenar graficamente

	private SetUp setUp;
	
	private Paso pasoActual;
	
	private long cantIntercambios;
	
	private long cantComparaciones;
	
	private long tiempoTranscurrido;

	private BufferedImage background;

	private LinkedList<Paso> pasos = new LinkedList<Paso>();

	public void init(LinkedList<Paso> pasosParam, Integer[] arrayEntrada, Integer[] arrayOrdenado, SetUp setUp) {

		try {
			background = ImageIO.read(new File("jcovid.jpeg"));
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		drawPanel = new DrawPanel();
		getContentPane().add(drawPanel);

		barras = new ArrayList<Barra>(setUp.getCantElementos());

		pasos = pasosParam;

		int ancho = (int) (getPreferredSize().getWidth() / setUp.getCantElementos());
		
		int multiploAltura = setUp.getCantElementos() < 47? 10: 4;

		for (int i = 0; i < arrayEntrada.length; i++) {
			barras.add(new Barra(ancho, arrayEntrada[i] * multiploAltura, arrayEntrada[i]));
		}
		for (Barra barra : barras) {
			double valorBarra = (barra.getAlto() / multiploAltura);

			for (int j = 0; j < arrayOrdenado.length; j++) {

				if (valorBarra == arrayOrdenado[j])
					barra.setPosFinal(j);
			}

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
			// g2.scale(currentDimension.getWidth() / 800, currentDimension.getHeight() /
			// 450);

			g2.drawImage(background, null, 0, 0);

			g2.setColor(Color.BLACK);
			g2.setFont(new Font("Dialog", Font.BOLD, 15));
			g2.drawString("Comparaciones: " + cantComparaciones, 20, 25);
			g2.drawString("Intercambios: " + cantIntercambios, 20, 45);
			g2.drawString("Tiempo: " + String.format("%6s", tiempoTranscurrido+ " ns"), 20, 65);
			g2.drawString("Algoritmo: " + setUp.getAlgoritmo(), 200, 25);
			g2.drawString("Cant. Elementos: " + setUp.getCantElementos(), 200, 45);
			g2.fillRect(390, 3, 298, 70);
			g2.setColor(Color.WHITE);
			g2.drawString("No ordenado", 400, 25);
			g2.setColor(Color.PINK);
			g2.drawString("Comparando", 400, 45);
			g2.setColor(Color.CYAN);
			g2.drawString("Pivot (QS)", 500, 25);
			g2.setColor(Color.RED);
			g2.drawString("Posible Intercambio (QS)", 500, 45);
			
			g2.setColor(Color.GRAY);
			g2.drawString("Ordenado", 400, 65);
			
			
			int i = 0;
			for (Barra b : barras) {
				g2.setColor(b.getColor());
				g2.fillRect(i, (int) (currentDimension.getHeight() - b.getAlto()), (int) b.getAncho(),
						(int) b.getAlto());
				g2.setColor(Color.BLACK);
				g2.drawRect(i, (int) (currentDimension.getHeight() - b.getAlto()), (int) b.getAncho(),
						(int) b.getAlto());
				i += b.getAncho();

			}
		}
	}

	@Override
	public Dimension getPreferredSize() {
		return new Dimension(800, 500);
	}

	public void run(Integer[] arrayOrdenado) throws InterruptedException {

		pasoActual = pasos.poll();

		while (pasoActual != null) {
			
			if(pasoActual.getTipo() == TipoPaso.COMPARACION)
				cantComparaciones++;
			else
				cantIntercambios++;
			
			tiempoTranscurrido += pasoActual.getTiempoTranscurrido();
			
			update(pasoActual);
			display();

			Thread.sleep(setUp.getTiempoEntreOperaciones());

			barras.get(pasoActual.getPosElem1()).setColor(Color.white);
			barras.get(pasoActual.getPosElem2()).setColor(Color.white);

			if (barras.get(pasoActual.getPosElem1()).getPosFinal() == pasoActual.getPosElem1()
					|| barras.get(pasoActual.getPosElem1()).getPosFinal() == pasoActual.getPosElem2()) {

				if (!buscarSiBarraEstaEnPasos(barras.get(pasoActual.getPosElem1()).getValor()))
					barras.get(pasoActual.getPosElem1()).setYaOrdenada();
			}

			if (barras.get(pasoActual.getPosElem2()).getPosFinal() == pasoActual.getPosElem2()
					|| (barras.get(pasoActual.getPosElem2()).getPosFinal() == pasoActual.getPosElem1())) {
				if (!buscarSiBarraEstaEnPasos(barras.get(pasoActual.getPosElem2()).getValor()))
					barras.get(pasoActual.getPosElem2()).setYaOrdenada();
			}
			
			pasoActual = pasos.poll();

		}

	}

	public void update(Paso paso) {
		Barra auxBarra = new Barra();

		barras.get(paso.getPosElem1()).setBarraActual();

		barras.get(paso.getPosElem2()).setBarraActual();
		
		if(paso.isTienePivot()) {
			barras.get(paso.getPivot()).setPivot();
			if(paso.getTipo() == TipoPaso.COMPARACION) {
				 
				barras.get(paso.getPosibleIntercambio()).setPosibleIntercambio();
			}
		}
		
		if (paso.getTipo() != TipoPaso.COMPARACION) {
			auxBarra = barras.get(paso.getPosElem1());
			barras.set(paso.getPosElem1(), barras.get(paso.getPosElem2()));
			barras.set(paso.getPosElem2(), auxBarra);
		}

	}

	public void display() {
		drawPanel.repaint();

	}

	public long getTiempoTranscurrido() {
		return tiempoTranscurrido;
	}

	private boolean buscarSiBarraEstaEnPasos(Double valorBarra) {

		double valorBarra1EnPasos;
		double valorBarra2Enpasos;
		for (Paso paso : pasos) {

			valorBarra1EnPasos = barras.get(paso.getPosElem1()).getValor();
			valorBarra2Enpasos = barras.get(paso.getPosElem2()).getValor();

			// lo encontro en un paso entonces devuelvo true
			if ((valorBarra == valorBarra1EnPasos || valorBarra == valorBarra2Enpasos)
					&& paso.getTipo() != TipoPaso.COMPARACION)
				return true;
		}

		return false;

	}

}