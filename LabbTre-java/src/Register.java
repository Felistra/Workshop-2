
import java.io.Serializable;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * Klassen Register, innehåller en lista över kundobjekt samt ett antal metoder för att "manipulera" listan
 * 
 * @author Henrik Karlsson
 */
public class Register implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private LinkedList <Kund> lista;
	private int lopNr;
	private String normal;
	private String student;
	private String foretag;
	
	/**
	 * Konstruktor för Register
	 *
	 */
	public Register() {
	 	lista=new LinkedList<Kund>();
		lopNr=0;
		normal="";
		student="";
		foretag="";
	}
	
	/**
	 * Metod för att räkna ut hur mycket pengar som finns genom att iterera genom den länkade listan
	 * 
	 * @return saldo den totala mängden "pengar i kassan"
	 */
	public int beraknaSaldo() {
		int saldo = 0;
		ListIterator<Kund> li = lista.listIterator(0);
		
		while(li.hasNext())
			saldo=saldo+li.next().getBelopp();
		
		return saldo;
	}
	
	/**
	 * Metod för att lägga till en kund av rätt klass med lämpliga attribut och metodanrop
	 * beroende på vilken subklass som skapats
	 *
	 * @param a Ett objekt av någon av superklassen Kunds subklasser
	 */
	public void regKund(Kund a) {
		Foretag f = new Foretag();
		Normal n = new Normal();
		Student s = new Student();
		
		LocalDateTime dot=LocalDateTime.now();
		DateTimeFormatter dotF = DateTimeFormatter.ofPattern("dd-MM - HH:mm:ss");
	    String b = dot.format(dotF);

		if(a.getClass().equals(n.getClass())) {				
			a.setDatum(b);
			a.setBelopp(100);
			lista.add(lista.size(), a);
			normal=normal+a.toString()+"\n";
		}
		if(a.getClass().equals(s.getClass())) {	
			a.setDatum(b);
			a.setBelopp(a.nyBalans(50));
			lista.add(lista.size(), a);
			student=student+a.toString()+"\n";
		}	
		if(a.getClass().equals(f.getClass())) {
			lopNr++;
			a.setLopNr(lopNr);
			a.setDatum(b);
			lista.add(lista.size(), a);
			foretag=foretag+a.toString()+"\n";
		}
	}
	
	/**
	 * Metod för att komma åt det privata attributet lopNr
	 * 
	 * @return lopNr antalet företagskunder i listan
	 */
	public int getLopNr() {
		return lopNr;
	}
	
	/**
	 * Metod för att skapa en sträng bestående av lämplig text från listans objekt
	 * 
	 * @return s Sträng som byggs upp genom att anropa toString i alla objekt i listan
	 */
	public String getLista() {
		String s="";
		ListIterator<Kund> li = lista.listIterator(0);
		
		while(li.hasNext()) {
			s=s+li.next().toString() + "\n";
		}
		return s;
	}
	
	/**
	 * 
	 * @return normal - En sträng innehållande texten från toString i samtliga objekt skapade av Normal
	 */
	public String getN() {
		return normal;
	}
	
	/**
	 * 
	 * @return En sträng innehållande texten från toString i samtliga objekt skapade av Student
	 */
	public String getS() {
		return student;
	}
	
	/**
	 * 
	 * @return En sträng innehållande texten från toString i samtliga objekt skapade av Foretag
	 */
	public String getF() {
		return foretag;
	}
	
	
	/**
	 * Metod för att kunna kolla antalet objekt(kunder) i listan
	 * 
	 * @return antalet objekt i listan
	 */
	public int listStorlek() {
		return lista.size();
	}	
}
