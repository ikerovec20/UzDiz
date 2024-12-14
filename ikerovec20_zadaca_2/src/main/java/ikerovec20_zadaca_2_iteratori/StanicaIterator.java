package ikerovec20_zadaca_2_iteratori;

import java.util.ArrayList;

import ikerovec20_zadaca_2.podaci.Stanica;

public class StanicaIterator implements IPrugaIterator {

	private Stanica trenutnaStanica;
	private ArrayList<Stanica> stanice;
	
	public StanicaIterator(ArrayList<Stanica> stanice) {
		this.stanice = stanice;
		trenutnaStanica = stanice.getFirst();
	}
	
	@Override
	public Stanica dohvatiTrenutnuStanicu() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Stanica dohvatiSljedecuStanicu() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int dohvatiUdaljenostDoStanice() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean postojiSljedecaStanica() {
		// TODO Auto-generated method stub
		return false;
	}

}
