package ikerovec20_zadaca_2_iteratori;

import ikerovec20_zadaca_2.podaci.Stanica;

public interface IPrugaIterator {
	
	public Stanica dohvatiTrenutnuStanicu();
	public Stanica dohvatiSljedecuStanicu();
	public int dohvatiUdaljenostDoStanice();
	public boolean postojiSljedecaStanica();
}
