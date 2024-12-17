package ikerovec20_zadaca_2.iteratori;

import ikerovec20_zadaca_2.podaci.Stanica;

public interface IPrugaIterator {
	
	public Stanica dohvatiTrenutnuStanicu();
	public Stanica dohvatiSljedecuStanicu();
	public Stanica vratiPrvuStanicu();
	public boolean postojiSljedecaStanica();
}
