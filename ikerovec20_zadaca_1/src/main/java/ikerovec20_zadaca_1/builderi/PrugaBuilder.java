package ikerovec20_zadaca_1.builderi;

import ikerovec20_zadaca_1.podaci.Pruga;
import ikerovec20_zadaca_1.podaci.PruznaStanica;

public class PrugaBuilder implements IPrugaBuilder {
	private Pruga pruga = new Pruga();
	
	public PrugaBuilder setOznakaPruge(String oznaka) {
		pruga.oznakaPruge = oznaka;
		return this;
	}
	
	public PrugaBuilder setKategorijaPruge(String kategorija) {
		pruga.kategorijaPruge = kategorija;
		return this;
	}
	
	public PrugaBuilder setVrstaPruge(String vrsta) {
		pruga.vrstaPruge = vrsta;
		return this;
	}
	
	
	public PrugaBuilder dodajStanicu(PruznaStanica stanica) {
		pruga.stanice.add(stanica);
		return this;
	}
	
	public Pruga build() {
		return pruga;
	}

	@Override
	public PrugaBuilder setStatusPruge(String status) {
		pruga.statusPruge = status;
		return this;
	}
}
