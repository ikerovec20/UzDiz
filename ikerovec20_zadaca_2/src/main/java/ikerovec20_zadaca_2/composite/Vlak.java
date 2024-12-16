package ikerovec20_zadaca_2.composite;


public class Vlak extends VozniRedComposite {
	public String oznaka;
	public String vrstaVlaka;
	public String daniVoznje;
	
	public Vlak(String oznaka, String vrstaVlaka, String daniVoznje) {
		this.oznaka = oznaka;
		this.vrstaVlaka = vrstaVlaka;
		this.daniVoznje = daniVoznje;
	}
	
	@Override
	public void dodajKomponentu(IKomponentaVoznogReda komponenta) {
		if (!(komponenta instanceof Etapa)) {
			return;
		}
		
		int i = 0;
		int indeks = 0;
		Etapa komponentaEtapa = (Etapa) komponenta;
		for (var komp : komponente) {
			Etapa etapa = (Etapa) komp;
			if (komponentaEtapa.vrijemePolaska.isBefore(etapa.vrijemePolaska)) {
				indeks = i;
			}
		}
		
		komponente.add(indeks, komponentaEtapa);
	}
	
	public void validirajEtape() {
		
	}

	@Override
	public void ucitajStanice() {
		for (var etapa : komponente) {
			etapa.ucitajStanice();
		}
		
	}

	@Override
	public int vratiUkupnoKm() {
		int ukupno = 0;
		for (var etapa : komponente) {
			ukupno += etapa.vratiUkupnoKm();
		}
		return ukupno;
	}
}
