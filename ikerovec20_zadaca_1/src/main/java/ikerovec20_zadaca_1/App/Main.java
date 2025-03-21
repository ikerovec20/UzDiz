package ikerovec20_zadaca_1.App;

import java.util.Scanner;
import java.util.regex.Pattern;

import ikerovec20_zadaca_1.konfiguracija.Konfiguracija;

public class Main {

	public static void ispisiPrugu(String parametri) {
		Pattern naredbaPredlozak = Pattern.compile("^ISP (?<oznaka>\\w+) (?<redoslijed>N|O)$");
		var poklapanje = naredbaPredlozak.matcher(parametri);
		if (poklapanje.matches()) {
			String oznaka = poklapanje.group("oznaka");
			String redoslijed = poklapanje.group("redoslijed");
			TvrtkaSingleton.getInstance().ispisiPrugu(oznaka, redoslijed.matches("N"));
		}
		else {
			System.out.println("Neispravni argumenti za naredbu");
		}
	}
	
	public static void ispisiStanice(String parametri) {
		Pattern naredbaPredlozak = Pattern.compile("^ISI2S (?<pocetna>[ \\p{L}]([ \\p{L} -]*[ \\p{L}])) - (?<zavrsna>[ \\p{L}]([ \\p{L} -]*[ \\p{L}]))$");
		var poklapanje = naredbaPredlozak.matcher(parametri);
		if (poklapanje.matches()) {
			String pocetna = poklapanje.group("pocetna");
			String zavrsna = poklapanje.group("zavrsna");
			TvrtkaSingleton.getInstance().ispisiStanice(pocetna, zavrsna);
		}
		else {
			System.out.println("Neispravni argumenti za naredbu");
		}
	}
	
	public static void ispisiKompoziciju(String parametri) {
		Pattern naredbaPredlozak = Pattern.compile("^IK (?<oznaka>\\w+)$");
		var poklapanje = naredbaPredlozak.matcher(parametri);
		if (poklapanje.matches()) {
			String oznaka = poklapanje.group("oznaka");
			TvrtkaSingleton.getInstance().ispisiKompoziciju(oznaka);
		}
		else {
			System.out.println("Neispravni argumenti za naredbu");
		}
	}
	
	public static void main(String[] args) {
		try {
			Konfiguracija.getInstance().ucitajKonfiguraciju(args);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		Scanner citac = new Scanner(System.in);
		
		while (true) {
			String ulaz = citac.nextLine();
			
			if (ulaz.matches("IP")) {
				TvrtkaSingleton.getInstance().ispisiPruge();
			}
			else if (ulaz.startsWith("ISP")) {
				ispisiPrugu(ulaz);
			}
			else if (ulaz.startsWith("IK")) {
				ispisiKompoziciju(ulaz);
			}
			else if (ulaz.startsWith("ISI2S")) {
				ispisiStanice(ulaz);
			}
			else if (ulaz.matches("Q")) {
				citac.close();
				break;
			}
			else {
				System.out.println("Nepoznata naredba");
			}
		}
	}

}
