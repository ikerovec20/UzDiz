package ikerovec20_zadaca_3.iteratori;

import ikerovec20_zadaca_3.podaci.Stanica;

public interface IPrugaIterator {
	
	public Stanica dohvatiTrenutnuStanicu();
	public Stanica dohvatiSljedecuStanicu();
	public Stanica vratiPrvuStanicu();
	public boolean postojiSljedecaStanica();
}
