package ikerovec20_zadaca_3.chain_of_responsibility.komande;

import java.util.regex.Matcher;

import ikerovec20_zadaca_3.App.TvrtkaSingleton;

public class KomandaIKKPV extends Komanda {

	public KomandaIKKPV(String predlozak) {
		super(predlozak);
	}

	@Override
	protected void obradi(Matcher parametri) {
		String indeks = parametri.group("index");
		
		if (indeks == null) {
			TvrtkaSingleton.getInstance().ispisiKarte(-1);
		}
		else {
			int index = Integer.parseInt(indeks);
			TvrtkaSingleton.getInstance().ispisiKarte(index);
		}
	}

}
