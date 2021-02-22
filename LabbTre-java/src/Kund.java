import java.io.Serializable;

/**
 * "Superklassen" Kund som ärvs ner till Student, Normal och Foretag
 * 
 * @author Henrik Karlsson
 */
public class Kund implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private int belopp;
	private String datum;
	
	/**
	 * Konstruktor för Kund
	 *  
	 * De privata attributen belopp och datum initieras till 0 respektive ""(tom sträng)
	 */
	public Kund() {
		belopp=0;
		datum="";
	}
	
	/**
	 * Metod för att kunna komma åt det privata attibutet belopp
	 * 
	 * @return belopp heltal
	 */
	public int getBelopp() {
		return belopp;
	}
	
	/**
	 * Metod för att öka det privata attributet belopp
	 * 
	 * @param iIn heltal som adderas till beloppet
	 */
	public void setBelopp(int iIn) {
		belopp=belopp+iIn;
	}
	
	/**
	 * Metod för att komma åt det privata attributet datum
	 * 
	 * @return datum 
	 */
	public String getDatum() {
		return datum;
	}
	
	/**
	 * Metod för att tilldela det privata attributet datum ett värde
	 * 
	 * @param sIn En sträng som ges ett värde när metoden anropas
	 */
	public void setDatum(String sIn) {
		datum=sIn;
	}
	
	/**
	 * Metod som överlagras i Studentklassen
	 * 
	 * @param iIn heltal
	 * @return heltal
	 */
	public int nyBalans(int iIn) {
		return belopp + iIn;
	}
	
	/**
	 * Metod som ska överlagras i Foretagklassen
	 * 
	 * @param iIn heltal
	 */
	public void setLopNr(int iIn) {
	}
}
