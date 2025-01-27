package ikerovec20_zadaca_3.command;

import java.util.ArrayList;

import ikerovec20_zadaca_3.composite.IKomponentaVoznogReda;
import ikerovec20_zadaca_3.composite.Vlak;

public class FiltDaniKomanda extends FiltKomanda {

	private String dani;
	
	public FiltDaniKomanda(String dani) {
		this.dani = dani;
	}

	@Override
	public ArrayList<IKomponentaVoznogReda> izvrsi(ArrayList<IKomponentaVoznogReda> vlakovi) {
		for (int i = 0; i < vlakovi.size(); i++) {
			Vlak vlak = (Vlak) vlakovi.get(i);
			if (!vlak.raspored.provjeriDane(dani)) {
				vlakovi.remove(i--);
			}
		}
		return vlakovi;
	}
}
