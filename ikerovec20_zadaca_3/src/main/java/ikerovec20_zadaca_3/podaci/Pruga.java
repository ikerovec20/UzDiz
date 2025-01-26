package ikerovec20_zadaca_3.podaci;


import ikerovec20_zadaca_3.iteratori.IPrugaKolekcija;
import ikerovec20_zadaca_3.iteratori.PrugaIterator;

public class Pruga implements IPrugaKolekcija {
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
	
	public PrugaIterator dohvatiIterator(boolean normalniSmjer) {
		if (normalniSmjer) {
			return new PrugaIterator(oznakaPruge, pocetnaStanica, true);	
		}
		else {
			return new PrugaIterator(oznakaPruge, zavrsnaStanica, false);
		}
	}
	
	public PrugaIterator dohvatiIterator(Stanica pocetnaStanica, boolean normalniSmjer) {
		return new PrugaIterator(oznakaPruge, pocetnaStanica, normalniSmjer);
	}
	
	public Pruga(String oznakaPruge, String kategorijaPruge,
			String vrstaPruge, String statusPruge) {
		super();
		this.oznakaPruge = oznakaPruge;
		this.kategorijaPruge = kategorijaPruge;
		this.vrstaPruge = vrstaPruge;
		this.statusPruge = statusPruge;
	}

	public boolean provjeriStatus(Stanica prva, Stanica druga, boolean normalniSmjer) {
		var iterator = dohvatiIterator(normalniSmjer);
		var stanica = iterator.dohvatiTrenutnuStanicu();
		boolean provjeri = false;
		while (iterator.postojiSljedecaStanica()) {
			var nova = iterator.dohvatiSljedecuStanicu();
			if (nova == prva) {
				provjeri = true;
			}
			if (provjeri) {
				var komp = stanica.dohvatiVezu(nova).pruga;
				if (!komp.stanje.dozvoljenaVoznja()) {
					return false;
				}
			}
			if (nova == druga) {
				break;
			}
			stanica = nova;
		}
		return true;
	}
	
	public boolean postojiStanica(Stanica stanica) {
		var iterator = dohvatiIterator(true);
		var st = iterator.dohvatiTrenutnuStanicu();
		while (iterator.postojiSljedecaStanica()) {
			st = iterator.dohvatiTrenutnuStanicu();
			System.out.println("STANICA " + st.stanica);
			if (st.stanica.matches(stanica.stanica)) {
				return true;
			}
			iterator.dohvatiSljedecuStanicu();
		}
		st = iterator.dohvatiTrenutnuStanicu();
		if (st.stanica.matches(stanica.stanica)) {
			return true;
		}
		return false;
	}
	
	public Pruga() {

	}
}
