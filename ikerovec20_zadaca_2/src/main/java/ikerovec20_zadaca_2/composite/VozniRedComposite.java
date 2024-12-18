package ikerovec20_zadaca_2.composite;

import java.util.ArrayList;

import ikerovec20_zadaca_2.iteratori.IVozniRedIterator;
import ikerovec20_zadaca_2.iteratori.VozniRedIterator;

public abstract class VozniRedComposite implements IKomponentaVoznogReda {
	
	protected ArrayList<IKomponentaVoznogReda> komponente;
	
	public VozniRedComposite() {
		komponente = new ArrayList<IKomponentaVoznogReda>();
	}
	
	public void dodajKomponentu(IKomponentaVoznogReda komponenta) {
		komponente.add(komponenta);
	}
	
	public void ukloniKomponentu(IKomponentaVoznogReda komponenta) {
		komponente.remove(komponenta);
	}
	
	public IKomponentaVoznogReda dohvatiKomponentu(int index) {
		if (index >= komponente.size() || index < 0) {
			return null;
		}
		return komponente.get(index);
	}
	
	public ArrayList<IKomponentaVoznogReda> vratiKomponente() {
		return komponente;
	}
	
	public IVozniRedIterator dohvatiIterator() {
		IVozniRedIterator iterator = new VozniRedIterator(komponente);
		return iterator;
	}
}
