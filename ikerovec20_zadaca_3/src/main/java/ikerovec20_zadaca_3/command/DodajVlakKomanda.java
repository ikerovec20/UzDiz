package ikerovec20_zadaca_3.command;

import ikerovec20_zadaca_3.composite.Vlak;
import ikerovec20_zadaca_3.composite.VozniRedComposite;

public class DodajVlakKomanda extends VozniRedKomanda {

	public DodajVlakKomanda(VozniRedComposite primatelj, Vlak vlak) {
		super(primatelj);
		this.komponenta = vlak;
	}

	@Override
	public void izvrsi() {
		primatelj.dodajKomponentu(komponenta);
	}

	@Override
	public void vrati() {
		primatelj.ukloniKomponentu(komponenta);
	}

}
