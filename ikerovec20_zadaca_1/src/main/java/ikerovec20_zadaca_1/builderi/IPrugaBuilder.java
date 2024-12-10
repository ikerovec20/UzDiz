package ikerovec20_zadaca_1.builderi;

import ikerovec20_zadaca_1.podaci.Pruga;
import ikerovec20_zadaca_1.podaci.PruznaStanica;

public interface IPrugaBuilder {
	public PrugaBuilder setOznakaPruge(String oznaka);
	public PrugaBuilder setKategorijaPruge(String kategorija);
	public PrugaBuilder setVrstaPruge(String vrsta);
	public PrugaBuilder setStatusPruge(String status);
	public PrugaBuilder dodajStanicu(PruznaStanica stanica);
	public Pruga build();
}
