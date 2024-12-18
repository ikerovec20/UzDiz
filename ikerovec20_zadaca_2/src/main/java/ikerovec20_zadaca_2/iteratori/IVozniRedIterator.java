package ikerovec20_zadaca_2.iteratori;

import ikerovec20_zadaca_2.composite.IKomponentaVoznogReda;

public interface IVozniRedIterator {
	public IKomponentaVoznogReda dohvatiTrenutnuKomponentu();
	public IKomponentaVoznogReda dohvatiSljedecuKomponentu();
	public IKomponentaVoznogReda vratiPrvuKomponentu();
	public boolean postojiSljedecaKomponenta();
}
