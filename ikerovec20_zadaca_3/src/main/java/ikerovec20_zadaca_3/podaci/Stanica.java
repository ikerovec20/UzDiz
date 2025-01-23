package ikerovec20_zadaca_3.podaci;

import java.util.ArrayList;

import ikerovec20_zadaca_3.prototipovi.IStanicaPrototype;

public class Stanica implements IStanicaPrototype {
	
	public String stanica;
	public String vrstaStanice;
	public String statusStanice;
	public boolean putniciUlazIzlaz;
	public boolean robaUtovarIstovar;
	public int brojPerona;
	public String statusPruge;
	
	public ArrayList<Veza> veze = new ArrayList<Veza>();
	
	public Veza dodajVezu(Stanica stanica, KomponentaPruge pruga, boolean smjer, String oznaka) {
		Veza veza = new Veza(stanica, pruga, smjer, oznaka);
		veze.add(veza);
		return veza;
	}
	
	public Veza dodajVezu(Veza veza) {
		veze.add(veza);
		return veza;
	}
	
	public Veza dohvatiBrzuVezu(String oznaka, boolean normalniSmjer) {
		for (var veza : veze) {
			if (veza.oznakaPruge.matches(oznaka) && veza.normalniSmjer == normalniSmjer && veza.vrijemeBrziVlak != -1) {
				return veza;
			}
		}
		return null;
	}
	
	public Veza dohvatiUbrzanuVezu(String oznaka, boolean normalniSmjer) {
		for (var veza : veze) {
			if (veza.oznakaPruge.matches(oznaka) && veza.normalniSmjer == normalniSmjer && veza.vrijemeUbrzaniVlak != -1) {
				return veza;
			}
		}
		return null;
	}
	
	public Veza dohvatiVezu(Stanica stanica) {
		for (var veza : veze) {
			if (veza.stanica.stanica.matches(stanica.stanica)) {
				return veza;
			}
		}
		return null;
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
		this.veze = new ArrayList<Veza>();
	}

	private Stanica(Stanica stanica) {
		this.stanica = stanica.stanica;
		this.brojPerona = stanica.brojPerona;
		this.putniciUlazIzlaz = stanica.putniciUlazIzlaz;
		this.robaUtovarIstovar = stanica.robaUtovarIstovar;
		this.statusStanice = stanica.statusStanice;
		this.vrstaStanice = stanica.vrstaStanice;
	}
	

	@Override
	public Stanica kloniraj() {
		return new Stanica(this);
	}
}
