package ikerovec20_zadaca_3.App;

import java.util.Scanner;

import ikerovec20_zadaca_3.chain_of_responsibility.komande.Komanda;
import ikerovec20_zadaca_3.chain_of_responsibility.komande.KomandaCVP;
import ikerovec20_zadaca_3.chain_of_responsibility.komande.KomandaDK;
import ikerovec20_zadaca_3.chain_of_responsibility.komande.KomandaDPK;
import ikerovec20_zadaca_3.chain_of_responsibility.komande.KomandaIEV;
import ikerovec20_zadaca_3.chain_of_responsibility.komande.KomandaIEVD;
import ikerovec20_zadaca_3.chain_of_responsibility.komande.KomandaIK;
import ikerovec20_zadaca_3.chain_of_responsibility.komande.KomandaIKKPV;
import ikerovec20_zadaca_3.chain_of_responsibility.komande.KomandaIP;
import ikerovec20_zadaca_3.chain_of_responsibility.komande.KomandaISI2S;
import ikerovec20_zadaca_3.chain_of_responsibility.komande.KomandaISP;
import ikerovec20_zadaca_3.chain_of_responsibility.komande.KomandaIV;
import ikerovec20_zadaca_3.chain_of_responsibility.komande.KomandaIVI2S;
import ikerovec20_zadaca_3.chain_of_responsibility.komande.KomandaIVRV;
import ikerovec20_zadaca_3.chain_of_responsibility.komande.KomandaKKPV2S;
import ikerovec20_zadaca_3.chain_of_responsibility.komande.KomandaPK;
import ikerovec20_zadaca_3.chain_of_responsibility.komande.KomandaPSP2S;
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
		
		
//		Komanda komandaIP = new KomandaIP("IP");
//		Komanda komandaISP = new KomandaISP("^ISP (?<oznaka>\\w+) (?<redoslijed>N|O)$");
//		Komanda komandaISI2S = new KomandaISI2S("^ISI2S (?<pocetna>[ \\p{L}]([ \\p{L} -]*[ \\p{L}])) - (?<zavrsna>[ \\p{L}]([ \\p{L} -]*[ \\p{L}]))$");
//		Komanda komandaIK = new KomandaIK("^IK (?<oznaka>\\w+)$");
//		Komanda komandaIV = new KomandaIV("IV");
//		Komanda komandaIEV = new KomandaIEV("^IEV (?<oznaka>[\\w-\\s]+)$");
//		Komanda komandaIEVD = new KomandaIEVD("^IEVD (?<dani>[PoUSrČPeSuN]+)$");
//		Komanda komandaIVRV = new KomandaIVRV("^IVRV (?<oznaka>[\\w-\\s]+)$");
//		Komanda komandaDK = new KomandaDK("^DK (?<ime>[ \\p{L}]([ \\p{L} -]*[ \\p{L}])) (?<prezime>[ \\p{L}]([ \\p{L} -]*[ \\p{L}]))$");
//		Komanda komandaPK = new KomandaPK("PK");
//		Komanda komandaDPK = new KomandaDPK("^DPK (?<ime>[ \\p{L}]([ \\p{L} -]*[ \\p{L}])) (?<prezime>[ \\p{L}]([ \\p{L} -]*[ \\p{L}])) - (?<oznakaVlaka>[\\w-\\s]+)( - (?<stanica>[ \\p{L}]([ \\p{L} -]*[ \\p{L}]))?)?$");
//		Komanda komandaSVV = new KomandaSVV("^SVV (?<oznaka>[\\w-\\s]+) - (?<dan>[PoUSrČPeSuN]+) - (?<koeficijent>[1-9]\\d*)$");
//		Komanda komandaX = new KomandaX("X");
//		Komanda komandaIVI2S = new KomandaIVI2S("^IVI2S (?<polazna>[ \\p{L}]([ \\p{L} -]*[ \\p{L}])) - (?<odredisna>[ \\p{L}]([ \\p{L} -]*[ \\p{L}])) - (?<dan>[PoUSrČPeSuN]+) - (?<pocetno>([0-1]?[0-9]|2[0-3]):[0-5][0-9]) - (?<zavrsno>([0-1]?[0-9]|2[0-3]):[0-5][0-9]) - (?<stupci>[KVPS]+)$");
		Komanda[] komande = {
				new KomandaIP("IP"),
				new KomandaISP("^ISP (?<oznaka>\\w+) (?<redoslijed>N|O)$"),
				new KomandaISI2S("^ISI2S (?<pocetna>[ \\p{L}]([ \\p{L} -]*[ \\p{L}])) - (?<zavrsna>[ \\p{L}]([ \\p{L} -]*[ \\p{L}]))$"),
				new KomandaIK("^IK (?<oznaka>\\w+)$"),
				new KomandaIV("IV"),
				new KomandaIEV("^IEV (?<oznaka>[\\w-\\s]+)$"),
				new KomandaIEVD("^IEVD (?<dani>[PoUSrČPeSuN]+)$"),
				new KomandaIVRV("^IVRV (?<oznaka>[\\w-\\s]+)$"),
				new KomandaDK("^DK (?<ime>[ \\p{L}]([ \\p{L} -]*[ \\p{L}])) (?<prezime>[ \\p{L}]([ \\p{L} -]*[ \\p{L}]))$"),
				new KomandaPK("PK"),
				new KomandaDPK("^DPK (?<ime>[ \\p{L}]([ \\p{L} -]*[ \\p{L}])) (?<prezime>[ \\p{L}]([ \\p{L} -]*[ \\p{L}])) - (?<oznakaVlaka>[\\w-\\s]+)( - (?<stanica>[ \\p{L}]([ \\p{L} -]*[ \\p{L}]))?)?$"),
				new KomandaSVV("^SVV (?<oznaka>[\\w-\\s]+) - (?<dan>[PoUSrČPeSuN]+) - (?<koeficijent>[1-9]\\d*)$"),
				new KomandaX("X"),
				new KomandaIVI2S("^IVI2S (?<polazna>[ \\p{L}]([ \\p{L} -]*[ \\p{L}])) - (?<odredisna>[ \\p{L}]([ \\p{L} -]*[ \\p{L}])) - (?<dan>[PoUSrČPeSuN]+) - (?<pocetno>([0-1]?[0-9]|2[0-3]):[0-5][0-9]) - (?<zavrsno>([0-1]?[0-9]|2[0-3]):[0-5][0-9]) - (?<stupci>[KVPS]+)$"),
				new KomandaCVP("^CVP (?<cijenaNormalni>\\d+(?:[\\.\\,]\\d+)?) (?<cijenaUbrzani>\\d+(?:[\\.\\,]\\d+)?) (?<cijenaBrzi>\\d+(?:[\\.\\,]\\d+)?) (?<popustSuN>\\d+(?:[\\.\\,]\\d+)?) (?<popustWebMob>\\d+(?:[\\.\\,]\\d+)?) (?<uvecanjeVlak>\\d+(?:[\\.\\,]\\d+)?)$"),
				new KomandaKKPV2S("^KKPV2S (?<oznaka>[\\w\\s]+) - (?<polaznaStanica>[ \\p{L}]([ \\p{L} -]*[ \\p{L}])) - (?<odredisnaStanica>[ \\p{L}]([ \\p{L} -]*[ \\p{L}])) - (?<datum>([0]?[1-9]|[1|2][0-9]|[3][0|1])[\\.]([0]?[1-9]|[1][0-2])[\\.]([0-9]{4}|[0-9]{2}))\\.? - (?<nacinKupovine>B|WM|V)$"),
				new KomandaIKKPV("^IKKPV( (?<index>[[1-9]\\d*]))?$"),
				new KomandaPSP2S("^PSP2S (?<oznaka>\\w+) - (?<polaznaStanica>[ \\p{L}]([ \\p{L} -]*[ \\p{L}])) - (?<odredisnaStanica>[ \\p{L}]([ \\p{L} -]*[ \\p{L}])) - (?<status>I|K|T|Z)$")
		};
//		
//		komandaIP.postaviSljedeceg(komandaISP);
//		komandaISP.postaviSljedeceg(komandaISI2S);
//		komandaISI2S.postaviSljedeceg(komandaIK);
//		komandaIK.postaviSljedeceg(komandaIV);
//		komandaIV.postaviSljedeceg(komandaIEV);
//		komandaIEV.postaviSljedeceg(komandaIEVD);
//		komandaIEVD.postaviSljedeceg(komandaIVRV);
//		komandaIVRV.postaviSljedeceg(komandaDK);
//		komandaDK.postaviSljedeceg(komandaPK);
//		komandaPK.postaviSljedeceg(komandaDPK);
//		komandaDPK.postaviSljedeceg(komandaSVV);
//		komandaSVV.postaviSljedeceg(komandaX);
//		komandaX.postaviSljedeceg(komandaIVI2S);
		
		var lanac = komande[0];
		for (int i = 1; i < komande.length; i++) {
			lanac.postaviSljedeceg(komande[i]);
			lanac = komande[i];
		}
		
		Scanner citac = new Scanner(System.in);
	
		while (true) {
			String ulaz = citac.nextLine();
			
			if (!ulaz.matches("Q")) {
				var ispravno = komande[0].obradiKomandu(ulaz);
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
