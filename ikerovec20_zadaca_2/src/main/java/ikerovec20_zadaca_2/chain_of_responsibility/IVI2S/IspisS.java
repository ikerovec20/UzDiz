package ikerovec20_zadaca_2.chain_of_responsibility.IVI2S;

import java.util.ArrayList;

import ikerovec20_zadaca_2.composite.EtapnaStanica;
import ikerovec20_zadaca_2.composite.IKomponentaVoznogReda;

public class IspisS extends IspisIVI2S {

	public IspisS(ArrayList<IKomponentaVoznogReda> komponente) {
		super(komponente);
		this.oznaka = 'S';
	}

	@Override
	protected void ispisi() {

		if (index == -1) {
			System.out.printf("%-23s", "Stanica");
			index++;
			return;
		}
		var stanica = (EtapnaStanica) komponente.get(index);
		System.out.printf("%-23s", stanica.stanica.stanica);
		index++;
	}

}
