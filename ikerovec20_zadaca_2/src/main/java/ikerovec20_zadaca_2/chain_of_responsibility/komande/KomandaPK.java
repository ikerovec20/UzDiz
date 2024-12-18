package ikerovec20_zadaca_2.chain_of_responsibility.komande;

import java.util.regex.Matcher;

import ikerovec20_zadaca_2.App.TvrtkaSingleton;

public class KomandaPK extends Komanda {

	public KomandaPK(String predlozak) {
		super(predlozak);
	}

	@Override
	protected void obradi(Matcher parametri) {
		TvrtkaSingleton.getInstance().ispisiKorisnike();
	}

}
