package ikerovec20_zadaca_2.observer;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class DojavljacKorisnika implements IDojavljac {

	private Map<String, IKorisnik> korisniciVlak = new ConcurrentHashMap<String, IKorisnik>();
	
	
	@Override
	public void pretplati(IKorisnik korisnik) {
		// TODO Auto-generated method stub

	}

	@Override
	public void makniPretplatu(IKorisnik korisnik) {
		// TODO Auto-generated method stub

	}

	@Override
	public void javiKorisnicima() {
		// TODO Auto-generated method stub

	}

}
