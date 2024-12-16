package ikerovec20_zadaca_2.composite;

import java.util.ArrayList;

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
}
