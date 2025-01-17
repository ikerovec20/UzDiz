package ikerovec20_zadaca_3.App;

import java.util.Scanner;

import ikerovec20_zadaca_3.chain_of_responsibility.komande.Komanda;
import ikerovec20_zadaca_3.chain_of_responsibility.komande.KomandaDK;
import ikerovec20_zadaca_3.chain_of_responsibility.komande.KomandaDPK;
import ikerovec20_zadaca_3.chain_of_responsibility.komande.KomandaIEV;
import ikerovec20_zadaca_3.chain_of_responsibility.komande.KomandaIEVD;
import ikerovec20_zadaca_3.chain_of_responsibility.komande.KomandaIK;
import ikerovec20_zadaca_3.chain_of_responsibility.komande.KomandaIP;
import ikerovec20_zadaca_3.chain_of_responsibility.komande.KomandaISI2S;
import ikerovec20_zadaca_3.chain_of_responsibility.komande.KomandaISP;
import ikerovec20_zadaca_3.chain_of_responsibility.komande.KomandaIV;
import ikerovec20_zadaca_3.chain_of_responsibility.komande.KomandaIVI2S;
import ikerovec20_zadaca_3.chain_of_responsibility.komande.KomandaIVRV;
import ikerovec20_zadaca_3.chain_of_responsibility.komande.KomandaPK;
import ikerovec20_zadaca_3.chain_of_responsibility.komande.KomandaSVV;
import ikerovec20_zadaca_3.chain_of_responsibility.komande.KomandaX;
import ikerovec20_zadaca_3.konfiguracija.Konfiguracija;

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
		Komanda komandaIEVD = new KomandaIEVD("^IEVD (?<dani>[PoUSrČPeSuN]+)$");
		Komanda komandaIVRV = new KomandaIVRV("^IVRV (?<oznaka>[\\w-\\s]+)$");
		Komanda komandaDK = new KomandaDK("^DK (?<ime>[ \\p{L}]([ \\p{L} -]*[ \\p{L}])) (?<prezime>[ \\p{L}]([ \\p{L} -]*[ \\p{L}]))$");
		Komanda komandaPK = new KomandaPK("PK");
		Komanda komandaDPK = new KomandaDPK("^DPK (?<ime>[ \\p{L}]([ \\p{L} -]*[ \\p{L}])) (?<prezime>[ \\p{L}]([ \\p{L} -]*[ \\p{L}])) - (?<oznakaVlaka>[\\w-\\s]+)( - (?<stanica>[ \\p{L}]([ \\p{L} -]*[ \\p{L}]))?)?$");
		Komanda komandaSVV = new KomandaSVV("^SVV (?<oznaka>[\\w-\\s]+) - (?<dan>[PoUSrČPeSuN]+) - (?<koeficijent>[1-9]\\d*)$");
		Komanda komandaX = new KomandaX("X");
		Komanda komandaIVI2S = new KomandaIVI2S("^IVI2S (?<polazna>[ \\p{L}]([ \\p{L} -]*[ \\p{L}])) - (?<odredisna>[ \\p{L}]([ \\p{L} -]*[ \\p{L}])) - (?<dan>[PoUSrČPeSuN]+) - (?<pocetno>([0-1]?[0-9]|2[0-3]):[0-5][0-9]) - (?<zavrsno>([0-1]?[0-9]|2[0-3]):[0-5][0-9]) - (?<stupci>[KVPS]+)$");
		
		komandaIP.postaviSljedeceg(komandaISP);
		komandaISP.postaviSljedeceg(komandaISI2S);
		komandaISI2S.postaviSljedeceg(komandaIK);
		komandaIK.postaviSljedeceg(komandaIV);
		komandaIV.postaviSljedeceg(komandaIEV);
		komandaIEV.postaviSljedeceg(komandaIEVD);
		komandaIEVD.postaviSljedeceg(komandaIVRV);
		komandaIVRV.postaviSljedeceg(komandaDK);
		komandaDK.postaviSljedeceg(komandaPK);
		komandaPK.postaviSljedeceg(komandaDPK);
		komandaDPK.postaviSljedeceg(komandaSVV);
		komandaSVV.postaviSljedeceg(komandaX);
		komandaX.postaviSljedeceg(komandaIVI2S);
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
