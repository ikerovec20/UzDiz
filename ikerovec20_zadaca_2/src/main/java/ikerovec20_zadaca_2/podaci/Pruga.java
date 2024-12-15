package ikerovec20_zadaca_2.podaci;


import ikerovec20_zadaca_2.iteratori.PrugaIterator;

public class Pruga {
	public Stanica pocetnaStanica;
	public Stanica zavrsnaStanica;
	public String oznakaPruge;
	public String kategorijaPruge;
	public String vrstaPruge;
	public String statusPruge;
	public int ukupnoKm = 0;
	
	public String getOznakaPruge() {
		return oznakaPruge;
	}

	public String getKategorijaPruge() {
		return kategorijaPruge;
	}

	public String getVrstaPruge() {
		return vrstaPruge;
	}

	public String getStatusPruge() {
		return statusPruge;
	}

	public Stanica dohvatiPrvuStanicu() {
		return pocetnaStanica;
	}
	
	public Stanica dohvatiZadnjuStanicu() {
		return zavrsnaStanica;
	}

	public int vratiUkupnuDuzinu() {
		return ukupnoKm;
	}
	
	public PrugaIterator dohvatiIterator() {
		return new PrugaIterator(oznakaPruge, pocetnaStanica);
	}
	
	public PrugaIterator dohvatiIterator(Stanica pocetnaStanica) {
		return new PrugaIterator(oznakaPruge, pocetnaStanica);
	}
	
	public Pruga(String oznakaPruge, String kategorijaPruge,
			String vrstaPruge, String statusPruge) {
		super();
		this.oznakaPruge = oznakaPruge;
		this.kategorijaPruge = kategorijaPruge;
		this.vrstaPruge = vrstaPruge;
		this.statusPruge = statusPruge;
	}

	public Pruga() {

	}
}
