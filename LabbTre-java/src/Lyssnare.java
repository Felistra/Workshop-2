
import java.awt.event.*;
import java.io.*;
import javax.swing.*;

/**
 * Klassen Lyssnare som innehåller metoder för att skapa funktionalitet i grafiken
 * 
 * @author Henrik Karlsson
 */
public class Lyssnare extends WindowAdapter implements ActionListener {
	private Register list;
	private Grafik g;
	private boolean sparadFlag;
	
	/**
	 * Konstruktor för Lyssnare
	 * @param Ett objekt av typen Grafik
	 */
	public Lyssnare(Grafik gIn) {
		list=new Register();
		g=gIn;
		sparadFlag=true;
	}

	@SuppressWarnings("unchecked")
	
	/**
	 * Överlagring av metoden actionPerformed
	 * 
	 * @param e Tar emot ett actionevent som körs mot olika kontroller för olika typer av "val"
	 */
	public void actionPerformed(ActionEvent e) { 
		
		if(e.getSource() instanceof JButton) { //Knapplystnare som lyssnar på knapptryck
			JButton b=(JButton)e.getSource();
			if(b.getText().equals("Vanlig")){ //Om man trycker på knappen med texten "Vanlig"
				if(list.listStorlek()<50) {
					list.regKund(new Normal());
					g.setKassa(list.beraknaSaldo(),list.listStorlek());
					g.setTa(list.getLista());
					g.setVisitors(list.listStorlek());
					sparadFlag=false;
				}
				else {
					g.varnaAntal();	//Om registret är fullt anropas varnaAntal			
				}
			}
			if(b.getText().equals("Student")){ //Om man trycker på knappen med texten "Student"
				if(list.beraknaSaldo()>=50&&list.listStorlek()<50) { //Kontroll görs för att se om det finns nog med pengar och inte registret är fullt
					list.regKund(new Student());
					g.setKassa(list.beraknaSaldo(),list.listStorlek());
					g.setTa(list.getLista());
					g.setVisitors(list.listStorlek());
					sparadFlag=false;
				}
				else if(list.beraknaSaldo()<50) { 
					g.varnaPengar(); //Om inte pengar finns så skickas en varning om detta
				}
				else { 
					g.varnaAntal(); //Om registret är fullt anropas varnaAntal
				}
			}
			if(b.getText().equals("Företag")){ //Om man trycker på knappen med texten "Företag"
				if(list.listStorlek()<50) {
					list.regKund(new Foretag());
					g.setKassa(list.beraknaSaldo(),list.listStorlek());
					g.setTa(list.getLista());
					g.setVisitors(list.listStorlek());
					sparadFlag=false;
				}
				else {
					g.varnaAntal(); //Om registret är fullt anropas varnaAntal
				}
			}
		}
		if(e.getSource() instanceof JMenuItem) { //Menylyssnare
			JMenuItem m=(JMenuItem) e.getSource();
			if(m.getText().equals("Ny")){ //Om man väljer menyval med texten "Ny"
				if (sparadFlag) {
					list=new Register();
					g.setFilnamn("Ny/osparad");
					g.setTa(list.getLista());
					g.setKassa(list.beraknaSaldo(),list.listStorlek());
					g.setVisitors(list.listStorlek());
					sparadFlag=true;
				}
				else {
					int i = g.varnaSpara();
					
					if(i==0) { //Ja
						sparaFil();
					}
					if(i==1) { //Nej
						
					}
					list=new Register();
					g.setFilnamn("Ny/osparad");
					g.setTa(list.getLista());
					g.setKassa(list.beraknaSaldo(),list.listStorlek());
					g.setVisitors(list.listStorlek());
					sparadFlag=true;
				}
			}
			if(m.getText().equals("Öppna")){ //Om man väljer menyval med texten "Öppna"
				if (sparadFlag) {
					oppnaFil();
				}
				else {
					int i = g.varnaSpara();
					
					if(i==0) { //Ja
						sparaFil();
						oppnaFil();
					}
					if(i==1) { //Nej
						oppnaFil();
					}
				}
			}
			if(m.getText().equals("Spara")){ //Om man väljer menyval med texten "Spara"
				sparaFil();
			}
			if(m.getText().equals("Stäng")){ //Om man väljer menyval med texten "Stäng"
				
				if(!sparadFlag) {
					int i = g.varnaSpara();
					if(i==0) { //Ja
						sparaFil();
						System.exit(0);
					}
					if(i==1) { //Nej
						System.exit(0);
					}
				}
				else {
					System.exit(0);
				}
			}
		}
		if(e.getSource() instanceof JComboBox) { //Combobox-lyssnare för att filtrera mellan de olika subklasserna av Kund
			JComboBox<String> cb=(JComboBox<String>) e.getSource();
			if(cb.getSelectedItem().equals("Filtrera")){ //Om man väljer comboboxval med texten "Filtrera"
				g.setTa(list.getLista());
			}
			if(cb.getSelectedItem().equals("Vanlig")){ //Om man väljer comboboxval med texten "Vanlig"
				g.setTa(list.getN());
			}
			if(cb.getSelectedItem().equals("Student")){ //Om man väljer comboboxval med texten "Student"
				g.setTa(list.getS());
			}
			if(cb.getSelectedItem().equals("Företag")){ //Om man väljer comboboxval med texten "Företag"
				g.setTa(list.getF());
			}
		}
	}	
	
	/**
	 * Metod för att spara ett Register-objekt till fil
	 * 
	 */
	public void sparaFil() {
		
		JFileChooser chooser = new JFileChooser("d:");
		int val = chooser.showSaveDialog(g.getFrame());
		
		if(val == JFileChooser.APPROVE_OPTION) {
			File f=new File(chooser.getCurrentDirectory()+File.separator+chooser.getSelectedFile().getName());
			try {
				ObjectOutputStream oos=new ObjectOutputStream(new FileOutputStream(f));
				oos.writeObject(list);
				sparadFlag=true;
				g.setFilnamn(chooser.getSelectedFile().getName());
				oos.close();
			}
			catch(IOException ie) {
				System.out.print("IOfel");
			}
			catch(Exception e) {
				System.out.print("Allmänt fel");
			}
		}
	}
	
	/**
	 * Metod för att öppna en fil innehållande ett objekt av Register-typ om man väljer en korrekt fil
	 *
	 */
	public void oppnaFil() {
		
		JFileChooser chooser = new JFileChooser("d:");
		int val = chooser.showOpenDialog(g.getFrame());
		
		if(val == JFileChooser.APPROVE_OPTION) {
			File f=new File(chooser.getCurrentDirectory()+File.separator+chooser.getSelectedFile().getName());
			
			try {
				ObjectInputStream ois=new ObjectInputStream(new FileInputStream(f));
				list = (Register) ois.readObject();
				g.setTa(list.getLista());
				g.setVisitors(list.listStorlek());
				g.setKassa(list.beraknaSaldo(),list.listStorlek());
				g.setFilnamn(chooser.getSelectedFile().getName());
				sparadFlag=true;
				ois.close();
			}
			catch(IOException ie) {
				System.out.print("IOfel");
			}
			catch(Exception e) {
				System.out.print("Allmänt fel");
			}
		}
	}
	
	/**
	 * Överlagring av metoden windowClosing
	 * 
	 */
	public void windowClosing(WindowEvent e) {
		if(!sparadFlag) {
			int i = g.varnaSpara();
			if(i==0) { //Ja
				sparaFil();
				System.exit(0);
			}
			if(i==1) { //Nej
				System.exit(0);
			}
		}else {
			System.exit(0);
		}
	}
}
