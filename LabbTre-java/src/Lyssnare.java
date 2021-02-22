
import java.awt.event.*;
import java.io.*;
import javax.swing.*;

/**
 * Klassen Lyssnare som inneh�ller metoder f�r att skapa funktionalitet i grafiken
 * 
 * @author Henrik Karlsson
 */
public class Lyssnare extends WindowAdapter implements ActionListener {
	private Register list;
	private Grafik g;
	private boolean sparadFlag;
	
	/**
	 * Konstruktor f�r Lyssnare
	 * @param Ett objekt av typen Grafik
	 */
	public Lyssnare(Grafik gIn) {
		list=new Register();
		g=gIn;
		sparadFlag=true;
	}

	@SuppressWarnings("unchecked")
	
	/**
	 * �verlagring av metoden actionPerformed
	 * 
	 * @param e Tar emot ett actionevent som k�rs mot olika kontroller f�r olika typer av "val"
	 */
	public void actionPerformed(ActionEvent e) { 
		
		if(e.getSource() instanceof JButton) { //Knapplystnare som lyssnar p� knapptryck
			JButton b=(JButton)e.getSource();
			if(b.getText().equals("Vanlig")){ //Om man trycker p� knappen med texten "Vanlig"
				if(list.listStorlek()<50) {
					list.regKund(new Normal());
					g.setKassa(list.beraknaSaldo(),list.listStorlek());
					g.setTa(list.getLista());
					g.setVisitors(list.listStorlek());
					sparadFlag=false;
				}
				else {
					g.varnaAntal();	//Om registret �r fullt anropas varnaAntal			
				}
			}
			if(b.getText().equals("Student")){ //Om man trycker p� knappen med texten "Student"
				if(list.beraknaSaldo()>=50&&list.listStorlek()<50) { //Kontroll g�rs f�r att se om det finns nog med pengar och inte registret �r fullt
					list.regKund(new Student());
					g.setKassa(list.beraknaSaldo(),list.listStorlek());
					g.setTa(list.getLista());
					g.setVisitors(list.listStorlek());
					sparadFlag=false;
				}
				else if(list.beraknaSaldo()<50) { 
					g.varnaPengar(); //Om inte pengar finns s� skickas en varning om detta
				}
				else { 
					g.varnaAntal(); //Om registret �r fullt anropas varnaAntal
				}
			}
			if(b.getText().equals("F�retag")){ //Om man trycker p� knappen med texten "F�retag"
				if(list.listStorlek()<50) {
					list.regKund(new Foretag());
					g.setKassa(list.beraknaSaldo(),list.listStorlek());
					g.setTa(list.getLista());
					g.setVisitors(list.listStorlek());
					sparadFlag=false;
				}
				else {
					g.varnaAntal(); //Om registret �r fullt anropas varnaAntal
				}
			}
		}
		if(e.getSource() instanceof JMenuItem) { //Menylyssnare
			JMenuItem m=(JMenuItem) e.getSource();
			if(m.getText().equals("Ny")){ //Om man v�ljer menyval med texten "Ny"
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
			if(m.getText().equals("�ppna")){ //Om man v�ljer menyval med texten "�ppna"
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
			if(m.getText().equals("Spara")){ //Om man v�ljer menyval med texten "Spara"
				sparaFil();
			}
			if(m.getText().equals("St�ng")){ //Om man v�ljer menyval med texten "St�ng"
				
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
		if(e.getSource() instanceof JComboBox) { //Combobox-lyssnare f�r att filtrera mellan de olika subklasserna av Kund
			JComboBox<String> cb=(JComboBox<String>) e.getSource();
			if(cb.getSelectedItem().equals("Filtrera")){ //Om man v�ljer comboboxval med texten "Filtrera"
				g.setTa(list.getLista());
			}
			if(cb.getSelectedItem().equals("Vanlig")){ //Om man v�ljer comboboxval med texten "Vanlig"
				g.setTa(list.getN());
			}
			if(cb.getSelectedItem().equals("Student")){ //Om man v�ljer comboboxval med texten "Student"
				g.setTa(list.getS());
			}
			if(cb.getSelectedItem().equals("F�retag")){ //Om man v�ljer comboboxval med texten "F�retag"
				g.setTa(list.getF());
			}
		}
	}	
	
	/**
	 * Metod f�r att spara ett Register-objekt till fil
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
				System.out.print("Allm�nt fel");
			}
		}
	}
	
	/**
	 * Metod f�r att �ppna en fil inneh�llande ett objekt av Register-typ om man v�ljer en korrekt fil
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
				System.out.print("Allm�nt fel");
			}
		}
	}
	
	/**
	 * �verlagring av metoden windowClosing
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
