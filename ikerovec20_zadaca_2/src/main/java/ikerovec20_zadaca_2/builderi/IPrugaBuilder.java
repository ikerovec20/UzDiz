package ikerovec20_zadaca_2.builderi;

import ikerovec20_zadaca_2.podaci.Pruga;
import ikerovec20_zadaca_2.podaci.PruznaStanica;

public interface IPrugaBuilder {
	public PrugaBuilder setOznakaPruge(String oznaka);
	public PrugaBuilder setKategorijaPruge(String kategorija);
	public PrugaBuilder setVrstaPruge(String vrsta);
	public PrugaBuilder setStatusPruge(String status);
	public PrugaBuilder dodajStanicu(PruznaStanica stanica);
	public Pruga build();
}
