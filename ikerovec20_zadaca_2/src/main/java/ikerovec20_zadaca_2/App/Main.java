package ikerovec20_zadaca_2.App;

import java.util.Scanner;

import ikerovec20_zadaca_2.chain_of_responsibility.Komanda;
import ikerovec20_zadaca_2.chain_of_responsibility.KomandaIEV;
import ikerovec20_zadaca_2.chain_of_responsibility.KomandaIEVD;
import ikerovec20_zadaca_2.chain_of_responsibility.KomandaIK;
import ikerovec20_zadaca_2.chain_of_responsibility.KomandaIP;
import ikerovec20_zadaca_2.chain_of_responsibility.KomandaISI2S;
import ikerovec20_zadaca_2.chain_of_responsibility.KomandaISP;
import ikerovec20_zadaca_2.chain_of_responsibility.KomandaIV;
import ikerovec20_zadaca_2.konfiguracija.Konfiguracija;

public class Main {
	public static void main(String[] args) {
		try {
			if (!Konfiguracija.getInstance().ucitajKonfiguraciju(args)) {
				return;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		Komanda komandaIP = new KomandaIP("IP");
		Komanda komandaISP = new KomandaISP("^ISP (?<oznaka>\\w+) (?<redoslijed>N|O)$");
		Komanda komandaISI2S = new KomandaISI2S("^ISI2S (?<pocetna>[ \\p{L}]([ \\p{L} -]*[ \\p{L}])) - (?<zavrsna>[ \\p{L}]([ \\p{L} -]*[ \\p{L}]))$");
		Komanda komandaIK = new KomandaIK("^IK (?<oznaka>\\w+)$");
		Komanda komandaIV = new KomandaIV("IV");
		Komanda komandaIEV = new KomandaIEV("^IEV (?<oznaka>[\\w-\\s]+)$");
		Komanda komandaIEVD = new KomandaIEVD("^IEVD (?<dani>[ \\p{L}]([ \\p{L} -]*[ \\p{L}]))");
		
		komandaIP.postaviSljedeceg(komandaISP);
		komandaISP.postaviSljedeceg(komandaISI2S);
		komandaISI2S.postaviSljedeceg(komandaIK);
		komandaIK.postaviSljedeceg(komandaIV);
		komandaIV.postaviSljedeceg(komandaIEV);
		komandaIEV.postaviSljedeceg(komandaIEVD);
		//posebna klasa za dane u tjednu, parsiraju se i ima metodu za provjeru
		Scanner citac = new Scanner(System.in);
		
		while (true) {
			String ulaz = citac.nextLine();
			
			if (!ulaz.matches("Q")) {
				var ispravno = komandaIP.obradiKomandu(ulaz);
				if (!ispravno) {
					System.out.println("Neispravna komanda.");
				}
			}
			else {
				citac.close();
				break;
			}
		}
	}

}
