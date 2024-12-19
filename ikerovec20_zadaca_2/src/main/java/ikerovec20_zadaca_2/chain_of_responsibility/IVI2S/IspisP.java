package ikerovec20_zadaca_2.chain_of_responsibility.IVI2S;

import java.util.ArrayList;

import ikerovec20_zadaca_2.composite.EtapnaStanica;
import ikerovec20_zadaca_2.composite.IKomponentaVoznogReda;

public class IspisP extends IspisIVI2S {

	public IspisP(ArrayList<IKomponentaVoznogReda> komponente) {
		super(komponente);
		this.oznaka = 'P';
	}

	@Override
	protected void ispisi() {
		if (index == -1) {
			System.out.printf("%-13s", "Pruga");
			index++;
			return;
		}
		var stanica = (EtapnaStanica) komponente.get(index);
		System.out.printf("%-13s", stanica.oznakaPruge);
		index++;
	}

}
