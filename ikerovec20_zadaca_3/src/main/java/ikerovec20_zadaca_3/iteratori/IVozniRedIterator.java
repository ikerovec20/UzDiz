package ikerovec20_zadaca_3.iteratori;

import ikerovec20_zadaca_3.composite.IKomponentaVoznogReda;

public interface IVozniRedIterator {
	public IKomponentaVoznogReda dohvatiTrenutnuKomponentu();
	public IKomponentaVoznogReda dohvatiSljedecuKomponentu();
	public IKomponentaVoznogReda vratiPrvuKomponentu();
	public boolean postojiSljedecaKomponenta();
}
