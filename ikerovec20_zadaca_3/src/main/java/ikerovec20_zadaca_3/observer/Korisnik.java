package ikerovec20_zadaca_3.observer;

public class Korisnik implements IKorisnik {

	public String ime;
	public String prezime;
	
	
	
	public Korisnik(String ime, String prezime) {
		this.ime = ime;
		this.prezime = prezime;
	}
	
	@Override
	public void primiDojavu(String dojava) {
		var builder = new StringBuilder();
		builder.append(ime).append(" ").append(prezime).append(" je primio obavijest: ").append(dojava);
		System.out.println(builder.toString());
	}

	@Override
	public String toString() {
		return ime + " " + prezime;
	}
}
