package ikerovec20_zadaca_2.builderi;

import ikerovec20_zadaca_2.podaci.Pruga;
import ikerovec20_zadaca_2.podaci.Stanica;

public interface IPrugaBuilder {
	public IPrugaBuilder setOznakaPruge(String oznaka);
	public IPrugaBuilder setKategorijaPruge(String kategorija);
	public IPrugaBuilder setVrstaPruge(String vrsta);
	public IPrugaBuilder setStatusPruge(String status);
	public IPrugaBuilder postaviPocetnuStanicu(Stanica stanica);
	public IPrugaBuilder postaviUkupnoKm(int km);
	public IPrugaBuilder postaviZavrsnuStanicu(Stanica stanica);
	public Pruga build();
}
