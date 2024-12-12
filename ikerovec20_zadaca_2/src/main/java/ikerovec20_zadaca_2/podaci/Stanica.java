package ikerovec20_zadaca_2.podaci;

import java.util.ArrayList;

public class Stanica {
	
	public String stanica;
	public String vrstaStanice;
	public String statusStanice;
	public boolean putniciUlazIzlaz;
	public boolean robaUtovarIstovar;
	public int brojPerona;
	
	
	public ArrayList<Veza> veze = new ArrayList<Veza>();
	
	public void dodajVezu(Stanica stanica, KomponentaPruge pruga) {
		Veza veza = new Veza(stanica, pruga);
		veze.add(veza);
	}

	public Stanica(String stanica, String vrstaStanice, String statusStanice, boolean putniciUlazIzlaz,
			boolean robaUtovarIstovar, int brojPerona) {
		super();
		this.stanica = stanica;
		this.vrstaStanice = vrstaStanice;
		this.statusStanice = statusStanice;
		this.putniciUlazIzlaz = putniciUlazIzlaz;
		this.robaUtovarIstovar = robaUtovarIstovar;
		this.brojPerona = brojPerona;
	}
}
