package ikerovec20_zadaca_1.podaci;

import ikerovec20_zadaca_1.prototipovi.IStanicaPrototype;

public class PruznaStanica implements IStanicaPrototype {
	public String stanica;
	public String vrstaStanice;
	public String statusStanice;
	public boolean putniciUlazIzlaz;
	public boolean robaUtovarIstovar;
	public int brojPerona;
	
	public int brojKolosjeka;
	public float dozvoljenoOpterecenjeOsovina;
	public float dozvoljenoOpterecenjeDuzniMetar;
	public int duzina;
	public PruznaStanica(String stanica, String vrstaStanice, String statusStanice, boolean putniciUlazIzlaz, boolean robaUtovarIstovar,
			int brojPerona, int brojKolosjeka, float dozvoljenoOpterecenjeOsovina, float dozvoljenoOpterecenjeDuzniMetar,
			int duzina) {
		super();
		this.stanica = stanica;
		this.vrstaStanice = vrstaStanice;
		this.statusStanice = statusStanice;
		this.putniciUlazIzlaz = putniciUlazIzlaz;
		this.robaUtovarIstovar = robaUtovarIstovar;
		this.brojPerona = brojPerona;
		this.brojKolosjeka = brojKolosjeka;
		this.dozvoljenoOpterecenjeOsovina = dozvoljenoOpterecenjeOsovina;
		this.dozvoljenoOpterecenjeDuzniMetar = dozvoljenoOpterecenjeDuzniMetar;
		this.duzina = duzina;
	}
	
	private PruznaStanica(PruznaStanica stanica) {
		this.stanica = stanica.stanica;
		this.vrstaStanice = stanica.vrstaStanice;
		this.putniciUlazIzlaz = stanica.putniciUlazIzlaz;
		this.robaUtovarIstovar = stanica.robaUtovarIstovar;
		this.brojKolosjeka = stanica.brojKolosjeka;
		this.dozvoljenoOpterecenjeOsovina = stanica.dozvoljenoOpterecenjeOsovina;
		this.dozvoljenoOpterecenjeDuzniMetar = stanica.dozvoljenoOpterecenjeDuzniMetar;
		this.duzina = stanica.duzina;
	}

	public PruznaStanica(String[] podaci) {
		String imeStanice = podaci[0];
		String vrstaStanice = podaci[2];
		String statusStanice = podaci[3];
		boolean putniciUlazIzlaz = podaci[4].matches("DA");
		boolean robaUtovarIstovar = podaci[5].matches("DA");
		int brojPerona = Integer.parseInt(podaci[7]);
		int brojKolosjeka = Integer.parseInt(podaci[9]);
		float dozvoljenoOpterecenjeOsovina = Float.parseFloat(podaci[10].replace(",", "."));
		float dozvoljenoOpterecenjeDuzniMetar = Float.parseFloat(podaci[11].replace(",", "."));
		int duzina = Integer.parseInt(podaci[13]);
		
		this.stanica = imeStanice;
		this.vrstaStanice = vrstaStanice;
		this.statusStanice = statusStanice;
		this.putniciUlazIzlaz = putniciUlazIzlaz;
		this.robaUtovarIstovar = robaUtovarIstovar;
		this.brojPerona = brojPerona;
		this.brojKolosjeka = brojKolosjeka;
		this.dozvoljenoOpterecenjeOsovina = dozvoljenoOpterecenjeOsovina;
		this.dozvoljenoOpterecenjeDuzniMetar = dozvoljenoOpterecenjeDuzniMetar;
		this.duzina = duzina;
	}
	
	public void postaviPrugu(int brojKolosjeka, float dozvoljenoOpterecenjeOsovina, 
			float dozvoljenoOpterecenjeDuzniMetar, int duzina) {
		this.brojKolosjeka = brojKolosjeka;
		this.dozvoljenoOpterecenjeOsovina = dozvoljenoOpterecenjeOsovina;
		this.dozvoljenoOpterecenjeDuzniMetar = dozvoljenoOpterecenjeDuzniMetar;
		this.duzina = duzina;
	}
	
	@Override
	public PruznaStanica kloniraj() {
		return new PruznaStanica(this);
	}
}
