package ikerovec20_zadaca_2.iteratori;

import ikerovec20_zadaca_2.podaci.Stanica;

public interface IPrugaKolekcija {
	public PrugaIterator dohvatiIterator(boolean normalniSmjer);
	public PrugaIterator dohvatiIterator(Stanica pocetnaStanica, boolean normalniSmjer);
}
