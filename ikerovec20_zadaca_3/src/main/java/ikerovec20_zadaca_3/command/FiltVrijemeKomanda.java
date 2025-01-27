package ikerovec20_zadaca_3.command;

import java.time.LocalTime;
import java.util.ArrayList;

import ikerovec20_zadaca_3.composite.IKomponentaVoznogReda;

public class FiltVrijemeKomanda extends FiltKomanda {

	private LocalTime vrijeme;
	private boolean prije;
	
	public FiltVrijemeKomanda(LocalTime vrijeme, boolean prije) {
		this.vrijeme = vrijeme;
		this.prije = prije;
	}
	
	@Override
	public ArrayList<IKomponentaVoznogReda> izvrsi(ArrayList<IKomponentaVoznogReda> vlakovi) {
		for (int i = 0; i < vlakovi.size(); i++) {
			if (prije) {
				if (!vlakovi.get(i).vratiPocetnoVrijeme().isBefore(vrijeme)) {
					vlakovi.remove(i--);
				}	
			}
			else {
				if (!vlakovi.get(i).vratiPocetnoVrijeme().isAfter(vrijeme)) {
					vlakovi.remove(i--);
				}	
			}
		}
		return vlakovi;
	}

}
