package ikerovec20_zadaca_2.composite;

import java.util.ArrayList;

public class VozniRedComposite implements IKomponentaVoznogReda {
	protected ArrayList<IKomponentaVoznogReda> komponente;
	
	public void dodajKomponentu(IKomponentaVoznogReda komponenta) {
		komponente.add(komponenta);
	}
	
	public void ukloniKomponentu(IKomponentaVoznogReda komponenta) {
		komponente.remove(komponenta);
	}
}
