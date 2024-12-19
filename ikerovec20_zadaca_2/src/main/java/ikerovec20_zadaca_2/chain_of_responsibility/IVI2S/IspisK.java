package ikerovec20_zadaca_2.chain_of_responsibility.IVI2S;

import java.util.ArrayList;

import ikerovec20_zadaca_2.composite.EtapnaStanica;
import ikerovec20_zadaca_2.composite.IKomponentaVoznogReda;

public class IspisK extends IspisIVI2S {

	private int ukupnoKm;
	
	public IspisK(ArrayList<IKomponentaVoznogReda> komponente) {
		super(komponente);
		ukupnoKm = 0;
		oznaka = 'K';
	}

	@Override
	protected void ispisi() {
		if (index == -1) {
			System.out.printf("%-4s", "km");
			index++;
			return;
		}
		System.out.printf("%-4s", ukupnoKm);
		index++;
		if (index < komponente.size()) {
			var prva = (EtapnaStanica) komponente.get(index);
			var druga = (EtapnaStanica) komponente.get(index-1);
			
			int izracun = prva.stanica.dohvatiVezu(druga.stanica).pruga.duzina;
				ukupnoKm += izracun;	
		}
	}
	
}
