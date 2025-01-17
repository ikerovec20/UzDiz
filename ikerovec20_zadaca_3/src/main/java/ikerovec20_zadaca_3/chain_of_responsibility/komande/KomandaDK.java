package ikerovec20_zadaca_3.chain_of_responsibility.komande;

import java.util.regex.Matcher;

import ikerovec20_zadaca_3.App.TvrtkaSingleton;

public class KomandaDK extends Komanda {

	public KomandaDK(String predlozak) {
		super(predlozak);
	}

	@Override
	protected void obradi(Matcher parametri) {
		String ime = parametri.group("ime");
		String prezime = parametri.group("prezime");
		
		TvrtkaSingleton.getInstance().dodajKorisnika(ime, prezime);
	}

}
