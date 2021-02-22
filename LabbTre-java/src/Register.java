
import java.io.Serializable;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * Klassen Register, inneh�ller en lista �ver kundobjekt samt ett antal metoder f�r att "manipulera" listan
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
	 * Konstruktor f�r Register
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
	 * Metod f�r att r�kna ut hur mycket pengar som finns genom att iterera genom den l�nkade listan
	 * 
	 * @return saldo den totala m�ngden "pengar i kassan"
	 */
	public int beraknaSaldo() {
		int saldo = 0;
		ListIterator<Kund> li = lista.listIterator(0);
		
		while(li.hasNext())
			saldo=saldo+li.next().getBelopp();
		
		return saldo;
	}
	
	/**
	 * Metod f�r att l�gga till en kund av r�tt klass med l�mpliga attribut och metodanrop
	 * beroende p� vilken subklass som skapats
	 *
	 * @param a Ett objekt av n�gon av superklassen Kunds subklasser
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
	 * Metod f�r att komma �t det privata attributet lopNr
	 * 
	 * @return lopNr antalet f�retagskunder i listan
	 */
	public int getLopNr() {
		return lopNr;
	}
	
	/**
	 * Metod f�r att skapa en str�ng best�ende av l�mplig text fr�n listans objekt
	 * 
	 * @return s Str�ng som byggs upp genom att anropa toString i alla objekt i listan
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
	 * @return normal - En str�ng inneh�llande texten fr�n toString i samtliga objekt skapade av Normal
	 */
	public String getN() {
		return normal;
	}
	
	/**
	 * 
	 * @return En str�ng inneh�llande texten fr�n toString i samtliga objekt skapade av Student
	 */
	public String getS() {
		return student;
	}
	
	/**
	 * 
	 * @return En str�ng inneh�llande texten fr�n toString i samtliga objekt skapade av Foretag
	 */
	public String getF() {
		return foretag;
	}
	
	
	/**
	 * Metod f�r att kunna kolla antalet objekt(kunder) i listan
	 * 
	 * @return antalet objekt i listan
	 */
	public int listStorlek() {
		return lista.size();
	}	
}
