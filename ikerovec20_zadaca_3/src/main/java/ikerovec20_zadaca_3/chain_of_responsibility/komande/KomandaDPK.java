package ikerovec20_zadaca_3.chain_of_responsibility.komande;

import java.util.regex.Matcher;

import ikerovec20_zadaca_3.App.TvrtkaSingleton;

public class KomandaDPK extends Komanda {

	public KomandaDPK(String predlozak) {
		super(predlozak);
	}

	@Override
	protected void obradi(Matcher parametri) {
		String ime = parametri.group("ime");
		String prezime = parametri.group("prezime");
		String oznakaVlaka = parametri.group("oznakaVlaka");
		String stanica = parametri.group("stanica");
		
		TvrtkaSingleton.getInstance().pretplatiKorisnika(ime, prezime, oznakaVlaka, stanica);
	}

}
