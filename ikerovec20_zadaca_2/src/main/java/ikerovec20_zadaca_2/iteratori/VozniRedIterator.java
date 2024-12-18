package ikerovec20_zadaca_2.iteratori;

import java.util.ArrayList;

import ikerovec20_zadaca_2.composite.IKomponentaVoznogReda;

public class VozniRedIterator implements IVozniRedIterator {

	private int pozicija;	
	private ArrayList<IKomponentaVoznogReda> komponente;
	private IKomponentaVoznogReda trenutnaKomponenta;
	
	public VozniRedIterator(ArrayList<IKomponentaVoznogReda> komponente) {
		this.komponente = komponente;
		this.trenutnaKomponenta = komponente.getFirst();
		pozicija = 0;
	}
	
	@Override
	public IKomponentaVoznogReda dohvatiTrenutnuKomponentu() {
		return trenutnaKomponenta;
	}

	@Override
	public IKomponentaVoznogReda dohvatiSljedecuKomponentu() {
		if (postojiSljedecaKomponenta()) {
			var komp = komponente.get(pozicija);
			pozicija++;
			return komp;
		}
		return null;
	}

	@Override
	public IKomponentaVoznogReda vratiPrvuKomponentu() {
		return komponente.get(0);
	}

	@Override
	public boolean postojiSljedecaKomponenta() {
		return pozicija < komponente.size();
	}

}
