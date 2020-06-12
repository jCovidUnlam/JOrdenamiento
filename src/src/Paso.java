package src;

import enums.TipoPaso;

public class Paso {
	
	TipoPaso tipo;
	private int posElem1;
	private int posElem2;
	private int pivot;
	private double tiempoTranscurrido;
	private boolean tienePivot = false;
	private int posibleIntercambio;
	
	
	public Paso(TipoPaso tipo, long tiempoTranscurrido, int posElem1, int posElem2) {
		super();
		this.tipo = tipo;			
		this.tiempoTranscurrido = tiempoTranscurrido;
		this.posElem1 = posElem1;
		this.posElem2 = posElem2;
	}

	public Paso(TipoPaso tipo, long tiempoTranscurrido, int posElem1, int posElem2, int pivot, boolean tienePivot, int posibleIntercambio) {
		super();
		this.tipo = tipo;			
		this.tiempoTranscurrido = tiempoTranscurrido;
		this.posElem1 = posElem1;
		this.posElem2 = posElem2;
		this.pivot = pivot;
		this.tienePivot = tienePivot;
		this.posibleIntercambio = posibleIntercambio;
	}

	
	public int getPosibleIntercambio() {
		return posibleIntercambio;
	}

	public void setPosibleIntercambio(int posibleIntercambio) {
		this.posibleIntercambio = posibleIntercambio;
	}

	public int getPivot() {
		return pivot;
	}

	public boolean isTienePivot() {
		return tienePivot;
	}

	public void setTienePivot(boolean tienePivot) {
		this.tienePivot = tienePivot;
	}

	public void setPivot(int pivot) {
		this.pivot = pivot;
	}

	public Paso() {
		super();
	}


	public double getTiempoTranscurrido() {
		return tiempoTranscurrido;
	}


	public void setTiempoTranscurrido(long l) {
		this.tiempoTranscurrido = l;
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
