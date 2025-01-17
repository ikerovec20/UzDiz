package ikerovec20_zadaca_3.podaci;

import java.util.LinkedHashMap;
import java.util.Map;

public class Kompozicija {
	private String oznaka;
	public String getOznaka() {
		return oznaka;
	}
	public void setOznaka(String oznaka) {
		this.oznaka = oznaka;
	}
	public Map<Vozilo, String> prijevoznaSredstva = new LinkedHashMap<Vozilo, String>();
}
