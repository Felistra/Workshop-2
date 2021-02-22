import java.io.Serializable;

/**
 * Klassen Normal, f�r vanliga bes�kare till eventet
 * 
 * @author Henrik Karlsson
 */
public class Normal extends Kund implements Serializable{

	private static final long serialVersionUID = 1L;

	/**
	 * �verlagring av toString-metoden s� att den g�r att anropa specifikt f�r Normal-klassen
	 * 
	 * @return textrad d�r bland annat datum och kostnader f�r bes�karen returneras
	 */
	public String toString() {
		return this.getDatum() +" anl�nde en vanlig bes�kare och betalade 100 SEK";
	}
}

