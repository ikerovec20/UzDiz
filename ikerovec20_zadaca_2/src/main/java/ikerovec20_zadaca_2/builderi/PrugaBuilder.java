package ikerovec20_zadaca_2.builderi;

import ikerovec20_zadaca_2.podaci.Pruga;
import ikerovec20_zadaca_2.podaci.Stanica;

public class PrugaBuilder implements IPrugaBuilder {
	private Pruga pruga = new Pruga();

	@Override
	public IPrugaBuilder setOznakaPruge(String oznaka) {
		pruga.oznakaPruge = oznaka;
		return this;
	}

	@Override
	public IPrugaBuilder setKategorijaPruge(String kategorija) {
		pruga.kategorijaPruge = kategorija;
		return this;
	}

	@Override
	public IPrugaBuilder setVrstaPruge(String vrsta) {
		pruga.vrstaPruge = vrsta;
		return this;
	}

	@Override
	public IPrugaBuilder setStatusPruge(String status) {
		pruga.statusPruge = status;
		return this;
	}

	@Override
	public IPrugaBuilder postaviPocetnuStanicu(Stanica stanica) {
		pruga.pocetnaStanica = stanica;
		return this;
	}

	@Override
	public IPrugaBuilder postaviUkupnoKm(int km) {
		pruga.ukupnoKm = km;
		return this;
	}

	@Override
	public IPrugaBuilder postaviZavrsnuStanicu(Stanica stanica) {
		pruga.zavrsnaStanica = stanica;
		return this;
	}

	@Override
	public Pruga build() {
		return pruga;
	}
	
}
