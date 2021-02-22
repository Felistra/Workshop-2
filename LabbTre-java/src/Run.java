
import java.util.*;

/**
 * Klassen Run som programmet k�rs ifr�n
 * 
 * @author Henrik Karlsson
 */
public class Run {

	/**
	 * Main med en meny d�r anv�ndare av programmet kan g�ra ett antal val f�r att anv�nda programmet
	 */
	public static void main(String[] args) {
		Register list=new Register();
		
		Scanner tgb = new Scanner(System.in);
		
		int svar;
		boolean a = true;
		
		while(a) {
			System.out.println("Registrera bes�kare!");
			System.out.println("1: Vanlig bes�kare");
			System.out.println("2: Student (Volont�r)");
			System.out.println("3: F�retagskund");
			System.out.println("4: Kolla hur m�nga som �r h�r");
			System.out.println("5: Kolla kassan");
			System.out.println("6: Skriv ut alla bes�kare");
			System.out.println("7: Kolla antal f�retagskunder");
			System.out.println("0: Avsluta");
			svar=tgb.nextInt();
			
			switch(svar){
			case 1: {
				if(list.listStorlek()<50) {
					list.regKund(new Normal());
					break;
				}
				else {
					System.out.println("Enligt coronarestriktionerna f�r inte fler �n 50 personer vistas i denna lokal");
					break;
				}
			}
			case 2: {
				if(list.beraknaSaldo()<50) {
					System.out.println("Eventet har inte r�d med fler studenter!");
					break;
				}
				if(list.listStorlek()<50) {
					list.regKund(new Student());
					break;
				}
				else {
					System.out.println("Enligt coronarestriktionerna f�r inte fler �n 50 personer vistas i denna lokal");
					break;
				}
			}
			case 3: {
				if(list.listStorlek()<50) {
					list.regKund(new Foretag());
					break;
				}
				else {
					System.out.println("Enligt coronarestriktionerna f�r inte fler �n 50 personer vistas i denna lokal");
					break;
				}
			}
			case 4: {
				System.out.println("Det �r " + list.listStorlek()+ " bes�kare h�r just nu");
				break;
			}
			case 5: {
				System.out.println("Kassan inneh�ller " + list.beraknaSaldo()+ " SEK");
				break;
			}
			case 6: {
				System.out.println(list.getLista());
				break;
			}
			case 7: {
				System.out.println(list.getLopNr());
				break;
			}
			case 0: {
				System.out.println("Hej d�!");
				System.exit(0);
			}
			default: {
				System.out.println("Ange ett alternativ fr�n listan!");
			}
			}		
		}
		
		tgb.close();
	}
}
