package ikerovec20_zadaca_3.command;

import java.util.ArrayList;

import ikerovec20_zadaca_3.composite.IKomponentaVoznogReda;

public class FiltInvoker {

	private ArrayList<FiltKomanda> redKomandi = new ArrayList<FiltKomanda>();
	
	public ArrayList<IKomponentaVoznogReda> izvrsiKomande(ArrayList<IKomponentaVoznogReda> vlakovi) {
		for (int i = 0; i < redKomandi.size(); i++) {
			vlakovi = redKomandi.get(i).izvrsi(vlakovi);
		}
		return vlakovi;
	}
	
	public void makniKomandu() {
		if (!redKomandi.isEmpty()) {
			redKomandi.removeLast();
		}
	}
	
	public void makniSve() {
		while (!redKomandi.isEmpty()) {
			redKomandi.removeLast();
		}
	}
	
	public void dodajKomandu(FiltKomanda komanda) {
		redKomandi.add(komanda);
	}
}
