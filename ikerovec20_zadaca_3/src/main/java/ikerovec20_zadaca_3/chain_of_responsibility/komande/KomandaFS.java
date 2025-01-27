package ikerovec20_zadaca_3.chain_of_responsibility.komande;

import java.util.regex.Matcher;

import ikerovec20_zadaca_3.App.TvrtkaSingleton;

public class KomandaFS extends Komanda {

	public KomandaFS(String predlozak) {
		super(predlozak);
	}

	@Override
	protected void obradi(Matcher parametri) {
		String stanica = parametri.group("stanica");
		
		TvrtkaSingleton.getInstance().dodajStanicuFilt(stanica);
	}

}
