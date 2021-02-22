import java.io.Serializable;

/**
 * "Superklassen" Kund som �rvs ner till Student, Normal och Foretag
 * 
 * @author Henrik Karlsson
 */
public class Kund implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private int belopp;
	private String datum;
	
	/**
	 * Konstruktor f�r Kund
	 *  
	 * De privata attributen belopp och datum initieras till 0 respektive ""(tom str�ng)
	 */
	public Kund() {
		belopp=0;
		datum="";
	}
	
	/**
	 * Metod f�r att kunna komma �t det privata attibutet belopp
	 * 
	 * @return belopp heltal
	 */
	public int getBelopp() {
		return belopp;
	}
	
	/**
	 * Metod f�r att �ka det privata attributet belopp
	 * 
	 * @param iIn heltal som adderas till beloppet
	 */
	public void setBelopp(int iIn) {
		belopp=belopp+iIn;
	}
	
	/**
	 * Metod f�r att komma �t det privata attributet datum
	 * 
	 * @return datum 
	 */
	public String getDatum() {
		return datum;
	}
	
	/**
	 * Metod f�r att tilldela det privata attributet datum ett v�rde
	 * 
	 * @param sIn En str�ng som ges ett v�rde n�r metoden anropas
	 */
	public void setDatum(String sIn) {
		datum=sIn;
	}
	
	/**
	 * Metod som �verlagras i Studentklassen
	 * 
	 * @param iIn heltal
	 * @return heltal
	 */
	public int nyBalans(int iIn) {
		return belopp + iIn;
	}
	
	/**
	 * Metod som ska �verlagras i Foretagklassen
	 * 
	 * @param iIn heltal
	 */
	public void setLopNr(int iIn) {
	}
}
