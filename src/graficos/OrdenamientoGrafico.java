package graficos;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

import javax.swing.JFrame;
import javax.swing.JPanel;

import src.AlgoritmoOrdenamiento;
import src.Burbujeo;
import src.CasoOrdenamiento;
import src.Paso;
import src.SetUp;
import src.TipoPaso;

public class OrdenamientoGrafico extends JFrame {
	public OrdenamientoGrafico() {
	}

	/*
	 * mi idea sería tener un array de barras en memoria, todas con su ancho y alto
	 * respectivo. Se construyen todas con igual ancho, y distintas alturas, de
	 * menor a mayor. dependiendo de como necesitemos el caso (ordenado, invertido,
	 * etc) , lo podemos dejar asi en orden, darlo vuelta, o ver como desordenarlo
	 * (eso si que ni idea como hacerlo, je)
	 */

	private static final long serialVersionUID = 1L;

	private DrawPanel drawPanel;

	private ArrayList<Barra> barras; // esto es lo que vamos a ordenar graficamente

	private boolean is_running = true;

	private SetUp setUp;

	// OJO: Los valores de SKIP son un resultado de una división entera!
	private final int SECOND = 1000;
	private final int FRAMES_PER_SECOND = 60;
	private final int SKIP_FRAMES = SECOND / FRAMES_PER_SECOND;
	private final int TICKS_PER_SECOND = 1000;
	private final int SKIP_TICKS = SECOND / TICKS_PER_SECOND;

	private int loops = 0;
	private int fps = 0;

	private Queue<Paso> pasos = new LinkedList<Paso>();

	public void init(Queue<Paso> pasosParam, Integer[] arrayEntrada, Integer[] arrayOrdenado) {

		drawPanel = new DrawPanel();
		getContentPane().add(drawPanel);

		setUp = new SetUp(30, 100, CasoOrdenamiento.ORDENADO, AlgoritmoOrdenamiento.BURBUJEO);

		barras = new ArrayList<Barra>(setUp.getCantElementos());

		pasos = pasosParam;

		int ancho = (int) (getPreferredSize().getWidth() / setUp.getCantElementos());

		for (int i = 0; i < arrayEntrada.length; i++) {
			barras.add(new Barra(ancho, arrayEntrada[i] * 10));
		}
		for (Barra barra : barras) {
			double valorBarra = (barra.getAlto() / 10);

			for (int j = 0; j < arrayOrdenado.length; j++) {

				if (valorBarra == arrayOrdenado[j])
					barra.setPosFinal(j);
			}

		}

		pack();
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		// setResizable(false);
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
			g2.setColor(Color.BLACK);
			g2.setFont(new Font("Dialog", Font.BOLD, 15));
			g2.drawString("Comparaciones: " + 1234, 20, 25);
			g2.drawString("Intercambios: " + 4321, 20, 45);
			g2.drawString("Tiempo: " + " X ms", 20, 65);
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
		return new Dimension(800, 450);
	}

	public void run(Integer[] arrayOrdenado) throws InterruptedException {
		// System.nanoTime no es seguro entre distintos Threads
		// En caso de querer utilizarse igual para aumentar la precision en
		// valores altos de fps o de ticks se debe aumentar también el valor
		// de las constantes, para que esten en ns y no en ms

		long next_game_tick = System.currentTimeMillis();
		long next_game_frame = System.currentTimeMillis();
		long next_frame_calc = System.currentTimeMillis();
		int frames = 0;

		Paso pasoActual = new Paso();

		pasoActual = pasos.poll();

		while (pasoActual != null) {
			// if (System.currentTimeMillis() > next_game_tick) {
			loops++;
			next_game_tick += SKIP_TICKS;
			// chekear que si es nulo no haga mas intercambios
			update(pasoActual);
			// }
			// if (System.currentTimeMillis() > next_game_frame) {
			frames++;
			next_game_frame += SKIP_FRAMES;
			display();
			// }
			// if (System.currentTimeMillis() > next_frame_calc) {
			fps = frames;
			next_frame_calc += SECOND;
			frames = 0;
			// }

			java.util.concurrent.TimeUnit.MILLISECONDS.sleep(100);

			barras.get(pasoActual.getPosElem1()).setColor(Color.white);
			barras.get(pasoActual.getPosElem2()).setColor(Color.white);

			// pinta la barra que quedo en su lugar final
			// Esto es horrible, no funciona si hay repetidos, asi que hay que cambiarlo
			int posFinalBarra2 = barras.get(pasoActual.getPosElem2()).getPosFinal();
			int posActualBarra2 = pasoActual.getPosElem2();

			int posFinalBarra1 = barras.get(pasoActual.getPosElem1()).getPosFinal();
			int posActualBarra1 = pasoActual.getPosElem1();

			if (posFinalBarra2 == posActualBarra2)
				barras.get(pasoActual.getPosElem2()).setColor(Color.green);

			if (posFinalBarra1 == posActualBarra1 && barras.get(pasoActual.getPosElem2()).getColor() == Color.green)
				barras.get(pasoActual.getPosElem1()).setColor(Color.green);

		
			pasoActual = pasos.poll();

		}

	}

	public void update(Paso paso) {
		// Aca se deberían hacer el intercambio de dos barras... o algo asi
		Barra auxBarra = new Barra();

		barras.get(paso.getPosElem1()).barraActual();

		barras.get(paso.getPosElem2()).barraActual();

		if (paso.getTipo() != TipoPaso.COMPARACION) {
			auxBarra = barras.get(paso.getPosElem1());
			barras.set(paso.getPosElem1(), barras.get(paso.getPosElem2()));
			barras.set(paso.getPosElem2(), auxBarra);
		}

	}

	public void display() {
		drawPanel.repaint();

	}

	public static void main(String[] args) throws Exception {
		OrdenamientoGrafico ord = new OrdenamientoGrafico();

		Integer arrayEntrada[] = { 20, 11, 9, 6, 1, 3, 5, 7, 8, 2, 15, 13, 10 };
		Integer arrayOrdenado[] = new Integer[arrayEntrada.length];

		Burbujeo<Integer> burbujeo = new Burbujeo<Integer>();

		arrayOrdenado = burbujeo.ordenar(arrayEntrada);

		ord.init(burbujeo.getPasos(), arrayEntrada, arrayOrdenado);

		ord.run(arrayOrdenado);

		System.out.println();

	}

}
