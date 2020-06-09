package src;

public class SetUp {
	
	private int cantElementos;
	private int tiempoEntreOperaciones;
	private CasoOrdenamiento casoOrd;
	private AlgoritmoOrdenamiento algoritmo;
	
	
	public SetUp(int cantElementos, int tiempoEntreOperaciones, CasoOrdenamiento casoOrd,
			AlgoritmoOrdenamiento algoritmo) {
		super();
		this.cantElementos = cantElementos;
		this.tiempoEntreOperaciones = tiempoEntreOperaciones;
		this.casoOrd = casoOrd;
		this.algoritmo = algoritmo;
	}


	public int getCantElementos() {
		return cantElementos;
	}


	public int getTiempoEntreOperaciones() {
		return tiempoEntreOperaciones;
	}


	public CasoOrdenamiento getCasoOrd() {
		return casoOrd;
	}


	public AlgoritmoOrdenamiento getAlgoritmo() {
		return algoritmo;
	}
	
	
	
	

}
