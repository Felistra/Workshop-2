import java.io.Serializable;

/**
 * Klassen Student, för  volontärarbetare till eventet
 * 
 * @author Henrik Karlsson
 */
public class Student extends Kund implements Serializable{

	private static final long serialVersionUID = 1L;

	/**
	 * Överlagring av toString-metoden så att den går att anropa specifikt för Student-klassen
	 * 
	 * @return textrad där bland annat datum och "lönen" för studenten returneras
	 */
	public String toString() {	
		return this.getDatum() + " anlände en fattig student, lön betalas ut: 50 SEK";
	}
	
	/**
	 * Överlagring av metoden nyBalans från Superklassen Kund för att "betala ut lön" till studenter 
	 * som volontärjobbar på eventet
	 * 
	 * @param iIn Ett heltal skickas med för beräkning
	 * @return Värdet av den totala "kassan" - inparametern
	 */
	public int nyBalans(int iIn) {
		return super.getBelopp() -iIn;
	}
}
