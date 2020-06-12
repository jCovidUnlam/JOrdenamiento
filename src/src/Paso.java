package src;

public class Paso {
	
	TipoPaso tipo;
	private int posElem1;
	private int posElem2;

	private double tiempoTranscurrido;
	
	
	public Paso(TipoPaso tipo, long tiempoTranscurrido, int posElem1, int posElem2) {
		super();
		this.tipo = tipo;			
		this.tiempoTranscurrido = tiempoTranscurrido;
		this.posElem1 = posElem1;
		this.posElem2 = posElem2;
	}

	public Paso() {
		super();
	}


	public double getTiempoTranscurrido() {
		return tiempoTranscurrido;
	}


	public void setTiempoTranscurrido(int tiempoTranscurrido) {
		this.tiempoTranscurrido = tiempoTranscurrido;
	}


	public int getPosElem1() {
		return posElem1;
	}


	public void setPosElem1(int posElem1) {
		this.posElem1 = posElem1;
	}


	public int getPosElem2() {
		return posElem2;
	}


	public TipoPaso getTipo() {
		return tipo;
	}


	public void setTipo(TipoPaso tipo) {
		this.tipo = tipo;
	}


	public void setPosElem2(int posElem2) {
		this.posElem2 = posElem2;
	}
	
	

}
