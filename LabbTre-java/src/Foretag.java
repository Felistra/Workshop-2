import java.io.Serializable;

/**
 * Klassen Foretag, f�r f�retagsbes�kare till eventet
 * 
 * @author Henrik Karlsson
 */
public class Foretag extends Kund implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private int lopNr;
	
	/**
	 * Konstruktor f�r Foretag
	 * Det privata attributet lopNr initierat
	 */
	public Foretag() {
		lopNr=0;
	}
	
	/**
	 * �verlagring av toString-metoden s� att den g�r att anropa specifikt f�r Foretag-klassen
	 * 
	 * @return textrad d�r datum, l�pnummer och information om att faktura skickas
	 */
	public String toString() {
		return this.getDatum() + " anl�nde en f�retagsbes�kare, faktura skickas med l�pnr " + this.getLopNr();
	}
	
	/**
	 * Metod f�r att returnera attributet lopNr
	 * 
	 * @return heltalet som finns i lopNr
	 */
	public int getLopNr() {
		return lopNr;
	}
	
	/**
	 * �verlagring av metoden setLopNr i Superklassen Kund f�r att kunna tilldela ett korrekt heltal till lopNr
	 * 
	 * @param iIn heltal som tilldelas till lopNr
	 */
	public void setLopNr(int iIn) {
		lopNr=iIn;
	}
}
