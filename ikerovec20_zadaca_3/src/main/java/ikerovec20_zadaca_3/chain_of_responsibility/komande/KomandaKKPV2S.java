package ikerovec20_zadaca_3.chain_of_responsibility.komande;

import java.util.regex.Matcher;

import ikerovec20_zadaca_3.App.TvrtkaSingleton;

public class KomandaKKPV2S extends Komanda {

	public KomandaKKPV2S(String predlozak) {
		super(predlozak);
	}

	@Override
	protected void obradi(Matcher parametri) {
		String oznaka = parametri.group("oznaka");
		String polaznaStanica = parametri.group("polaznaStanica");
		String odredisnaStanica = parametri.group("odredisnaStanica");
		String datum = parametri.group("datum");
		String nacinKupovine = parametri.group("nacinKupovine");
		
		TvrtkaSingleton.getInstance().kupiKartu(oznaka, polaznaStanica, odredisnaStanica, datum, nacinKupovine);
	}

}
