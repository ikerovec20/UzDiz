package ikerovec20_zadaca_3.composite;

import java.time.LocalTime;

import ikerovec20_zadaca_3.podaci.Stanica;

public interface IKomponentaVoznogReda {
	public int vratiKm();
	public boolean validiraj();
	public Stanica vratiPrvuStanicu();
	public Stanica vratiZadnjuStanicu();
	public LocalTime vratiPocetnoVrijeme();
	public LocalTime vratiZavrsnoVrijeme();
	public boolean postojiStanica(String stanica);
	public EtapnaStanica dohvatiStanicu(String stanica);
}
