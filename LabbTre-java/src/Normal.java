import java.io.Serializable;

/**
 * Klassen Normal, för vanliga besökare till eventet
 * 
 * @author Henrik Karlsson
 */
public class Normal extends Kund implements Serializable{

	private static final long serialVersionUID = 1L;

	/**
	 * Överlagring av toString-metoden så att den går att anropa specifikt för Normal-klassen
	 * 
	 * @return textrad där bland annat datum och kostnader för besökaren returneras
	 */
	public String toString() {
		return this.getDatum() +" anlände en vanlig besökare och betalade 100 SEK";
	}
}

