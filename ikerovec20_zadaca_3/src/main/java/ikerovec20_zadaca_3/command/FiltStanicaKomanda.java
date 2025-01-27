package ikerovec20_zadaca_3.command;

import java.util.ArrayList;

import ikerovec20_zadaca_3.composite.IKomponentaVoznogReda;

public class FiltStanicaKomanda extends FiltKomanda {

	private String stanica;
	
	public FiltStanicaKomanda(String stanica) {
		this.stanica = stanica;
	}

	@Override
	public ArrayList<IKomponentaVoznogReda> izvrsi(ArrayList<IKomponentaVoznogReda> vlakovi) {
		for (int i = 0; i < vlakovi.size(); i++) {
			if (!vlakovi.get(i).postojiStanica(stanica)) {
				vlakovi.remove(i--);
			}
		}
		return vlakovi;
	}

}
