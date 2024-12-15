package ikerovec20_zadaca_2.composite;

import java.util.LinkedHashMap;
import java.util.Map;

import ikerovec20_zadaca_2.podaci.Stanica;

public class Etapa implements IKomponentaVoznogReda {
	public Map<String, Stanica> stanice = new LinkedHashMap<String, Stanica>();
}
