package ikerovec20_zadaca_1.podaci;

import java.util.ArrayList;

public class Pruga {
	public ArrayList<PruznaStanica> stanice = new ArrayList<PruznaStanica>();
	public String oznakaPruge;
	public String kategorijaPruge;
	public String vrstaPruge;
	public String statusPruge;
	
	public PruznaStanica dohvatiPrvuStanicu(boolean odPocetka) {
		if (odPocetka) {
			return stanice.getFirst();
		}
		else {
			return stanice.getLast();
		}
	}
	
	public PruznaStanica dohvatiZadnjuStanicu(boolean odPocetka) {
		if (odPocetka) {
			return stanice.getLast();
		}
		else {
			return stanice.getFirst();
		}
	}
	
	public int vratiUkupnuDuzinu() {
		int duzina = 0;
		for (var stanica : stanice) {
			duzina += stanica.duzina;
		}
		return duzina;
	}
}
