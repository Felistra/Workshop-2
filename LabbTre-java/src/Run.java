
import java.util.*;

/**
 * Klassen Run som programmet körs ifrån
 * 
 * @author Henrik Karlsson
 */
public class Run {

	/**
	 * Main med en meny där användare av programmet kan göra ett antal val för att använda programmet
	 */
	public static void main(String[] args) {
		Register list=new Register();
		
		Scanner tgb = new Scanner(System.in);
		
		int svar;
		boolean a = true;
		
		while(a) {
			System.out.println("Registrera besökare!");
			System.out.println("1: Vanlig besökare");
			System.out.println("2: Student (Volontär)");
			System.out.println("3: Företagskund");
			System.out.println("4: Kolla hur många som är här");
			System.out.println("5: Kolla kassan");
			System.out.println("6: Skriv ut alla besökare");
			System.out.println("7: Kolla antal företagskunder");
			System.out.println("0: Avsluta");
			svar=tgb.nextInt();
			
			switch(svar){
			case 1: {
				if(list.listStorlek()<50) {
					list.regKund(new Normal());
					break;
				}
				else {
					System.out.println("Enligt coronarestriktionerna får inte fler än 50 personer vistas i denna lokal");
					break;
				}
			}
			case 2: {
				if(list.beraknaSaldo()<50) {
					System.out.println("Eventet har inte råd med fler studenter!");
					break;
				}
				if(list.listStorlek()<50) {
					list.regKund(new Student());
					break;
				}
				else {
					System.out.println("Enligt coronarestriktionerna får inte fler än 50 personer vistas i denna lokal");
					break;
				}
			}
			case 3: {
				if(list.listStorlek()<50) {
					list.regKund(new Foretag());
					break;
				}
				else {
					System.out.println("Enligt coronarestriktionerna får inte fler än 50 personer vistas i denna lokal");
					break;
				}
			}
			case 4: {
				System.out.println("Det är " + list.listStorlek()+ " besökare här just nu");
				break;
			}
			case 5: {
				System.out.println("Kassan innehåller " + list.beraknaSaldo()+ " SEK");
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
				System.out.println("Hej då!");
				System.exit(0);
			}
			default: {
				System.out.println("Ange ett alternativ från listan!");
			}
			}		
		}
		
		tgb.close();
	}
}
