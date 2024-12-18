package ikerovec20_zadaca_2.chain_of_responsibility.komande;

import java.util.regex.Matcher;

import ikerovec20_zadaca_2.App.TvrtkaSingleton;

public class KomandaISI2S extends Komanda {

	public KomandaISI2S(String predlozak) {
		super(predlozak);
	}

	@Override
	protected void obradi(Matcher parametri) {
		String pocetna = parametri.group("pocetna");
		String zavrsna = parametri.group("zavrsna");
		TvrtkaSingleton.getInstance().ispisiStanice(pocetna, zavrsna);
	}
}
