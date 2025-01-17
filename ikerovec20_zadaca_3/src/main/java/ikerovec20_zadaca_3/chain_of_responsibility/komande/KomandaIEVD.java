package ikerovec20_zadaca_3.chain_of_responsibility.komande;

import java.util.regex.Matcher;

import ikerovec20_zadaca_3.App.TvrtkaSingleton;

public class KomandaIEVD extends Komanda {

	public KomandaIEVD(String predlozak) {
		super(predlozak);
	}

	@Override
	protected void obradi(Matcher parametri) {
		String dani = parametri.group("dani");
		TvrtkaSingleton.getInstance().ispisiVlakoveDani(dani);
	}

}
