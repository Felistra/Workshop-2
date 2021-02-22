import java.io.Serializable;

/**
 * Klassen Foretag, för företagsbesökare till eventet
 * 
 * @author Henrik Karlsson
 */
public class Foretag extends Kund implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private int lopNr;
	
	/**
	 * Konstruktor för Foretag
	 * Det privata attributet lopNr initierat
	 */
	public Foretag() {
		lopNr=0;
	}
	
	/**
	 * Överlagring av toString-metoden så att den går att anropa specifikt för Foretag-klassen
	 * 
	 * @return textrad där datum, löpnummer och information om att faktura skickas
	 */
	public String toString() {
		return this.getDatum() + " anlände en företagsbesökare, faktura skickas med löpnr " + this.getLopNr();
	}
	
	/**
	 * Metod för att returnera attributet lopNr
	 * 
	 * @return heltalet som finns i lopNr
	 */
	public int getLopNr() {
		return lopNr;
	}
	
	/**
	 * Överlagring av metoden setLopNr i Superklassen Kund för att kunna tilldela ett korrekt heltal till lopNr
	 * 
	 * @param iIn heltal som tilldelas till lopNr
	 */
	public void setLopNr(int iIn) {
		lopNr=iIn;
	}
}
