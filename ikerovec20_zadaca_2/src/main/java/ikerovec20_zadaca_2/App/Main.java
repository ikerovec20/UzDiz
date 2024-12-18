package ikerovec20_zadaca_2.App;

import java.util.Scanner;

import ikerovec20_zadaca_2.chain_of_responsibility.komande.Komanda;
import ikerovec20_zadaca_2.chain_of_responsibility.komande.KomandaIEV;
import ikerovec20_zadaca_2.chain_of_responsibility.komande.KomandaIEVD;
import ikerovec20_zadaca_2.chain_of_responsibility.komande.KomandaIK;
import ikerovec20_zadaca_2.chain_of_responsibility.komande.KomandaIP;
import ikerovec20_zadaca_2.chain_of_responsibility.komande.KomandaISI2S;
import ikerovec20_zadaca_2.chain_of_responsibility.komande.KomandaISP;
import ikerovec20_zadaca_2.chain_of_responsibility.komande.KomandaIV;
import ikerovec20_zadaca_2.chain_of_responsibility.komande.KomandaIVRV;
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
		Komanda komandaIVRV = new KomandaIVRV("^IVRV (?<oznaka>[\\w-\\s]+)$");
		
		komandaIP.postaviSljedeceg(komandaISP);
		komandaISP.postaviSljedeceg(komandaISI2S);
		komandaISI2S.postaviSljedeceg(komandaIK);
		komandaIK.postaviSljedeceg(komandaIV);
		komandaIV.postaviSljedeceg(komandaIEV);
		komandaIEV.postaviSljedeceg(komandaIEVD);
		komandaIEVD.postaviSljedeceg(komandaIVRV);

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
