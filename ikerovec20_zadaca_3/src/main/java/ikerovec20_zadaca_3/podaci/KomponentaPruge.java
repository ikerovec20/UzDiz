package ikerovec20_zadaca_3.podaci;

import ikerovec20_zadaca_3.state.IspravnoStanje;
import ikerovec20_zadaca_3.state.StanjePruge;
import ikerovec20_zadaca_3.state.UKvaruStanje;
import ikerovec20_zadaca_3.state.UTestiranjuStanje;
import ikerovec20_zadaca_3.state.ZatvorenoStanje;

public class KomponentaPruge {
	public String oznakaPruge;
	public int brojKolosjeka;
	public float dozvoljenoOpterecenjeOsovina;
	public float dozvoljenoOpterecenjeDuzniMetar;
	public StanjePruge stanje;
	public int duzina;
	
	public KomponentaPruge(int brojKolosjeka, float dozvoljenoOpterecenjeOsovina, float dozvoljenoOpterecenjeDuzniMetar, int duzina) {
		super();
		this.brojKolosjeka = brojKolosjeka;
		this.dozvoljenoOpterecenjeOsovina = dozvoljenoOpterecenjeOsovina;
		this.dozvoljenoOpterecenjeDuzniMetar = dozvoljenoOpterecenjeDuzniMetar;
		this.duzina = duzina;
	}
	
	public KomponentaPruge(String[] podaci) {
		int brojKolosjeka = Integer.parseInt(podaci[9]);
		float dozvoljenoOpterecenjeOsovina = Float.parseFloat(podaci[10].replace(",", "."));
		float dozvoljenoOpterecenjeDuzniMetar = Float.parseFloat(podaci[11].replace(",", "."));
		int duzina = Integer.parseInt(podaci[13]);
		
		this.oznakaPruge = podaci[1];
		this.brojKolosjeka = brojKolosjeka;
		this.dozvoljenoOpterecenjeOsovina = dozvoljenoOpterecenjeOsovina;
		this.dozvoljenoOpterecenjeDuzniMetar = dozvoljenoOpterecenjeDuzniMetar;
		this.duzina = duzina;
		
		switch (podaci[12]) {
		case "I":
			this.stanje = new IspravnoStanje(this);
			break;
		case "K":
			this.stanje = new UKvaruStanje(this);
			break;
		case "T":
			this.stanje = new UTestiranjuStanje(this);
			break;
		case "Z":
			this.stanje = new ZatvorenoStanje(this);
		}
	}	
}
