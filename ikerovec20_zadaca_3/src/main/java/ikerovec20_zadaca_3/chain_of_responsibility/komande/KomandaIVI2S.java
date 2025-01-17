package ikerovec20_zadaca_3.chain_of_responsibility.komande;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;

import ikerovec20_zadaca_3.App.TvrtkaSingleton;

public class KomandaIVI2S extends Komanda {

	public KomandaIVI2S(String predlozak) {
		super(predlozak);
	}

	@Override
	protected void obradi(Matcher parametri) {
		String polazna = parametri.group("polazna");
		String odredisna = parametri.group("odredisna");
		String dan = parametri.group("dan");
		String pocetnoVrijeme = parametri.group("pocetno");
		String zavrsnoVrijeme = parametri.group("zavrsno");
		String stupci = parametri.group("stupci");
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("H:mm");
		LocalTime pocetno = LocalTime.parse(pocetnoVrijeme, formatter);
		LocalTime zavrsno = LocalTime.parse(zavrsnoVrijeme, formatter);
		
		TvrtkaSingleton.getInstance().ispisIVI2S(polazna, odredisna, dan, pocetno, zavrsno, stupci);
	}

}
