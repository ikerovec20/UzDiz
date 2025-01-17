package ikerovec20_zadaca_3.podaci;

public class KomponentaPruge {
	public String oznakaPruge;
	public int brojKolosjeka;
	public float dozvoljenoOpterecenjeOsovina;
	public float dozvoljenoOpterecenjeDuzniMetar;
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
	}
	
	
	
	
	
}
