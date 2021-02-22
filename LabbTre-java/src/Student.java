import java.io.Serializable;

/**
 * Klassen Student, f�r  volont�rarbetare till eventet
 * 
 * @author Henrik Karlsson
 */
public class Student extends Kund implements Serializable{

	private static final long serialVersionUID = 1L;

	/**
	 * �verlagring av toString-metoden s� att den g�r att anropa specifikt f�r Student-klassen
	 * 
	 * @return textrad d�r bland annat datum och "l�nen" f�r studenten returneras
	 */
	public String toString() {	
		return this.getDatum() + " anl�nde en fattig student, l�n betalas ut: 50 SEK";
	}
	
	/**
	 * �verlagring av metoden nyBalans fr�n Superklassen Kund f�r att "betala ut l�n" till studenter 
	 * som volont�rjobbar p� eventet
	 * 
	 * @param iIn Ett heltal skickas med f�r ber�kning
	 * @return V�rdet av den totala "kassan" - inparametern
	 */
	public int nyBalans(int iIn) {
		return super.getBelopp() -iIn;
	}
}
