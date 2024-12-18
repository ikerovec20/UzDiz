package ikerovec20_zadaca_2.observer;

public interface IDojavljac {
	public void pretplati(IKorisnik korisnik, String oznakaVlaka, String stanica);
	public void makniPretplatu(IKorisnik korisnik);
	public void javiKorisnicima(String vlak, String stanica);
}
