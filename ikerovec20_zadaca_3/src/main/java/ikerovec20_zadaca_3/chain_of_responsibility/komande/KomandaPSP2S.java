package ikerovec20_zadaca_3.chain_of_responsibility.komande;

import java.util.regex.Matcher;

import ikerovec20_zadaca_3.App.TvrtkaSingleton;

public class KomandaPSP2S extends Komanda {

	public KomandaPSP2S(String predlozak) {
		super(predlozak);
	}

	@Override
	protected void obradi(Matcher parametri) {
		String oznaka = parametri.group("oznaka");
		String polaznaStanica = parametri.group("polaznaStanica");
		String odredisnaStanica = parametri.group("odredisnaStanica");
		String status = parametri.group("status");
		System.out.println(oznaka + " " + polaznaStanica + " " + odredisnaStanica + " " + status);
		
		TvrtkaSingleton.getInstance().promjeniStanjePruge(oznaka, polaznaStanica, odredisnaStanica, status);
	}

}
