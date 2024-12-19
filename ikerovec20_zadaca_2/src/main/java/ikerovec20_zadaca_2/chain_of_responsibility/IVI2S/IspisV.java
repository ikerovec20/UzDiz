package ikerovec20_zadaca_2.chain_of_responsibility.IVI2S;

import java.util.ArrayList;

import ikerovec20_zadaca_2.composite.EtapnaStanica;
import ikerovec20_zadaca_2.composite.IKomponentaVoznogReda;
import ikerovec20_zadaca_2.composite.Vlak;

public class IspisV extends IspisIVI2S {

	private ArrayList<IKomponentaVoznogReda> vlakovi;
	
	public IspisV(ArrayList<IKomponentaVoznogReda> komponente, ArrayList<IKomponentaVoznogReda> vlakovi) {
		super(komponente);
		this.vlakovi = vlakovi;
		this.oznaka = 'V';
	}

	@Override
	protected void ispisi() {
		if (index == -1) {
			for (int i = 0; i < vlakovi.size(); i++) {
				var vlak = (Vlak) vlakovi.get(i);
				System.out.printf("%-10s", vlak.oznaka);
			}
			index++;
			return;
		}

		for (int i = 0; i < vlakovi.size(); i++) {
			var vlak = (Vlak) vlakovi.get(i);
			var stanica = (EtapnaStanica) komponente.get(index);
			var stanicaNaPruzi = vlak.dohvatiStanicu(stanica.stanica.stanica);
			var vrijeme = stanicaNaPruzi.vratiPocetnoVrijeme().toString();
			switch (vlak.vrstaVlaka) {
			case "U":
				if (stanicaNaPruzi.vrijemeUbrzaniVlak == -1) {
					vrijeme = " -- ";
				}
				break;
			case "B":
				if (stanicaNaPruzi.vrijemeBrziVlak == -1) {
					vrijeme = " -- ";
				}
				break;
			}
			System.out.printf("%-10s", vrijeme);
		}
		index++;
	}

}
