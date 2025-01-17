package ikerovec20_zadaca_3.observer;

import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class DojavljacKorisnika implements IDojavljac {

	private Map<String, ArrayList<IKorisnik>> korisniciVlak = new ConcurrentHashMap<String, ArrayList<IKorisnik>>();
	private Map<String, ArrayList<IKorisnik>> korisniciStanica = new ConcurrentHashMap<String, ArrayList<IKorisnik>>();
	
	@Override
	public void pretplati(IKorisnik korisnik, String oznakaVlaka, String stanica) {
		
		if (postojiKorisnik(korisnik)) {
			System.out.println("Korisnik " + korisnik.toString() + " vec postoji.");
			return;
		}
		
		var vlak = korisniciVlak.get(oznakaVlaka);
		if (vlak == null) {
			korisniciVlak.put(oznakaVlaka, new ArrayList<IKorisnik>());
		}
		
		korisniciVlak.get(oznakaVlaka).add(korisnik);
		if (stanica != null) {
			var stn = korisniciStanica.get(stanica);
			if (stn == null) {
				korisniciStanica.put(stanica, new ArrayList<IKorisnik>());
			}
			korisniciStanica.get(stanica).add(korisnik);
		}
	}

	@Override
	public void makniPretplatu(IKorisnik korisnik) {
		for (var kor : korisniciVlak.entrySet()) {
			var lista = kor.getValue();
			for (int i = 0; i < lista.size(); i++) {
				if (lista.get(i).equals(korisnik)) {
					lista.remove(i);
				}
			}
		}
		
		for (var stanice : korisniciStanica.entrySet()) {
			var lista = stanice.getValue();
			for (int i = 0; i < lista.size(); i++) {
				if (lista.get(i).equals(korisnik)) {
					lista.remove(i);
				}
			}
		}
	}
	
	private boolean postojiKorisnik(IKorisnik korisnik) {
		for (var kor : korisniciVlak.entrySet()) {
			var lista = kor.getValue();
			for (int i = 0; i < lista.size(); i++) {
				if (lista.get(i).equals(korisnik)) {
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public void javiKorisnicima(String oznakaVlaka, String stanica) {
		var builder = new StringBuilder();
		builder.append("Vlak ").append(oznakaVlaka).append(" stigao je na stanicu ").append(stanica);
		var vlak = korisniciVlak.get(oznakaVlaka);
		if (vlak != null) {
			for (var kor : vlak) {
				var stn = korisniciStanica.get(stanica);
				if (stn != null && stn.contains(kor)) {
					kor.primiDojavu(builder.toString());
				}
				else {
					boolean ispisi = true;
					for (var stanice : korisniciStanica.entrySet()) {
						if (stanice.getValue().contains(kor)) {
							ispisi = false;
						}
					}
					if (ispisi) {
						kor.primiDojavu(builder.toString());
					}
				}
			}
		}
	}

}
