import java.awt.*;
import javax.swing.*;

/**
 * Klassen Grafik d�r layouten byggs upp
 * 
 * @author Henrik Karlsson
 */
public class Grafik {
	private JFrame f;
	
	private JMenuBar mainMenu;
	private JMenu meny;
	private JMenuItem ch1, ch2, ch3, ch4;
	
	private JPanel n,s,c,sb; 
	
	private JLabel lRubrik, lRubrik2, lAntBes, lKassa, lFilNamn;
	private JTextField antBes,kassa,filNamn;
	
	private JButton b1, b2, b3;
	
	private JScrollPane sp;
	private JTextArea ta;
	
	private JComboBox<String> cb;
	
	private Lyssnare ly;

	private ImageIcon logo;
	
	/**
	 * Konstruktor f�r Grafik
	 */
	public Grafik() {
		f=new JFrame("Putte i Parken 2021!");
		f.setFont(null);
				
		logo = new ImageIcon("logo.png");
		f.setIconImage(logo.getImage());
		ly=new Lyssnare(this);
		
		mainMenu=new JMenuBar();
		mainMenu.setBackground(Color.gray);
		meny=new JMenu("Meny");
		ch1=new JMenuItem("Ny");
		ch1.addActionListener(ly);
		ch2=new JMenuItem("�ppna");
		ch2.addActionListener(ly);
		ch3=new JMenuItem("Spara");
		ch3.addActionListener(ly);
		ch4=new JMenuItem("St�ng");
		ch4.addActionListener(ly);
		meny.add(ch1); 
		meny.add(ch2);
		meny.add(ch3);
		meny.addSeparator();
		meny.add(ch4);
		mainMenu.add(meny);
		f.setJMenuBar(mainMenu);
		
		lRubrik=new JLabel("Coronakontroll:");
		lRubrik2=new JLabel("Vilken typ av bes�kare vill du registrera?");
		lRubrik2.setHorizontalAlignment(JLabel.CENTER);
		lAntBes=new JLabel("av 50 pers");
		lKassa=new JLabel("$"); //Fel valuta men den fick ers�tta texten kassa eftersom den inte fick plats i vissa uppl�sningar
		lFilNamn=new JLabel("Fil:");
		
		antBes=new JTextField(2);
		antBes.setText(""+0);
		antBes.setEditable(false);
	
		kassa=new JTextField(6);
		kassa.setText(0+" SEK");
		kassa.setEditable(false);
		
		filNamn=new JTextField(8);
		filNamn.setText("Ny/osparad");
		filNamn.setEditable(false);
	
		ta=new JTextArea(22,52);
		ta.setEditable(false);
		ta.setBackground(Color.white);
		
		sp=new JScrollPane(ta);
		sp.setHorizontalScrollBar(null);
		
		String[] alt={"Filtrera","Vanlig","Student","F�retag"};
		cb = new JComboBox<String>(alt);
		cb.addActionListener(ly);
		cb.setForeground(Color.white);
		cb.setBackground(Color.DARK_GRAY);
		cb.setFocusable(false);
		
		n=new JPanel();
		n.setBackground(new Color(100,100,105,200));
		n.setBounds(0, 0, 500, 30);
		c=new JPanel();
		c.setBackground(new Color(100,100,105,200));
		c.setBounds(0, 30, 500, 370);
		s=new JPanel();
		s.setBackground(new Color(100,100,105,200));
		s.setBounds(0, 400, 500, 60);
		s.setLayout(new BorderLayout());
		sb=new JPanel();
		sb.setBackground(new Color(100,100,105,200));
		sb.setBounds(0, 390, 500, 50);
		
		c.add(sp);
		
		n.add(lFilNamn);
		n.add(filNamn);
		n.add(lRubrik);
		n.add(antBes);
		n.add(lAntBes);
		
		b1=new JButton("Vanlig");
		b1.addActionListener(ly);
		b1.setFocusable(false);
		b1.setForeground(Color.white);
		b1.setBackground(Color.DARK_GRAY);
		
		b2=new JButton("Student");
		b2.addActionListener(ly);
		b2.setFocusable(false);
		b2.setForeground(Color.white);
		b2.setBackground(Color.DARK_GRAY);
		
		b3=new JButton("F�retag");
		b3.addActionListener(ly);
		b3.setFocusable(false);
		b3.setForeground(Color.white);
		b3.setBackground(Color.DARK_GRAY);
		
		sb.add(lKassa);
		sb.add(kassa);
		sb.add(b1);
		sb.add(b2);
		sb.add(b3);
		sb.add(cb);
		
		s.add(lRubrik2,BorderLayout.NORTH);
		s.add(sb,BorderLayout.SOUTH);
		
		f.add(n);
		f.add(c);
		f.add(s);
		f.setLayout(null);

		f.setVisible(true);
		f.setSize(510, 520);
		f.addWindowListener(ly);
		f.setResizable(false);
		f.setDefaultCloseOperation(0);
		
	}

	/**
	 * Metod som skriver ut text i textarean ta i mitten av framen
	 * 
	 * @param textIn str�ng som skrivs ut i textarean
	 */
	public void setTa(String textIn) {
		ta.setText(textIn);
	}
	
	/**
	 * Metod f�r att visa m�ngden pengar i kassan
	 * 
	 * @param kIn heltal som skrivs ut i textf�ltet kassa
	 */
	public void setKassa(int kIn, int ant) {
		if(kIn==0) {
			kassa.setBackground(Color.red);
		}
		if(kIn>0) {
			kassa.setBackground(Color.white);
		}
		if(ant<1) {
			kassa.setBackground(Color.white);
		}
		kassa.setText(kIn+" SEK");
	}
	
	/**
	 * Metod f�r att visa antal bes�kare
	 * 
	 * @param vIn heltal som skrivs ut i textf�ltet antBes
	 */
	public void setVisitors(int vIn) {
		if (vIn==50) {
			antBes.setBackground(Color.red);
		}
		antBes.setText(""+vIn);
	}
	
	/**
	 * Metod som varnar med en showMessageDialog n�r man har kommit upp i maxantalet bes�kare
	 */
	public void varnaAntal() {
		Toolkit.getDefaultToolkit().beep();
		JOptionPane.showMessageDialog(f, "Det �r f�r m�nga personer h�r!");
	}
	
	/**
	 * Metod som varnar med en showMessageDialog om det inte finns nog med pengar f�r att betala en student
	 */
	public void varnaPengar() {
		Toolkit.getDefaultToolkit().beep();
		JOptionPane.showMessageDialog(f, "Det finns inga pengar i kassan till l�n f�r denna stackars student");
	}
	
	/**
	 * Metod som varnar med en showConfirmDialog om man f�rs�ker st�nga, �ppna eller skapa ett nytt register utan att spara det befintliga
	 * 
	 * @return heltal 0, 1 eller 2 beroende p� vad man v�ljer i showConfirmDialog
	 */
	public int varnaSpara() {
		Toolkit.getDefaultToolkit().beep();
		return JOptionPane.showConfirmDialog(f, "Vill du spara f�rst?");
	}
	
	/**
	 * 
	 * @param filNamnIn namnet p� filen som ska visas i rutan filNamn
	 */
	public void setFilnamn(String filNamnIn) {
		filNamn.setText(filNamnIn);
	}
	
	/**
	 * Metod f�r att skicka det privata attributet f som �r framen till det grafiska gr�nssnittet
	 * 
	 * @return f JFrame som GUI best�r av
	 */
	public JFrame getFrame() {
		return f;
	}
	
	/**
	 * Main som skapar ett objekt av Grafik
	 */
	public static void main(String[] args) {
		new Grafik();
	}
}