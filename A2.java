import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.locks.ReentrantLock;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JOptionPane;
import javax.swing.JPanel;



public class A2 extends JFrame implements ActionListener,MouseListener {
	
	
	public JTextField seats,agents,time,createdText;
	public JButton buttonCreate,buttonBook;
	// public JFrame myFrame;
	 public JPanel panel;
	 private ArrayList<JTextField> myList;
	 public ArrayList<Integer> randomNumbers;
		private Random random = new Random();
		private ArrayList<Thread> AG = new ArrayList<Thread>();
		private ReentrantLock k = new ReentrantLock();
	
	// i defined all data that i will use
	
	
	
	
	
	
	
	public static void main(String[] args) {
		new A2();

} 
	public void init() {
		
		
		seats= new JTextField("number of seats");
		seats.setLocation(50, 50);
		seats.setSize(150, 30);
		add(seats);
		
		agents= new JTextField("number of agents");
		agents.setLocation(220, 50);
		agents.setSize(150, 30);
		add(agents);
		
		time= new JTextField("max waiting time");
		time.setLocation(400, 50);
		time.setSize(150, 30);
		add(time);
		
		buttonCreate= new JButton("Create Seats");
		buttonCreate.setLocation(580,50);
		buttonCreate.setSize(150, 30);
		add(buttonCreate);
		
		buttonBook=new  JButton ("Book");
		buttonBook.setSize(90, 30);
		buttonBook.setLocation(740, 50);
		add(buttonBook);
		
		panel = new JPanel();
		panel.setLocation(0, 70);
		panel.setSize(800, 800);
		panel.setLayout(new GridLayout(10,0,10,10));
		
		add(panel);
	}
	
	//constructor
	public A2() {
		setLayout( null);
		init();
		
	//my frame's properties
		//JFrame myFrame = new JFrame();
		 setSize(900,1000);
			setResizable(true);//ekraný büyütüp küçültür
		setDefaultCloseOperation(EXIT_ON_CLOSE);

	setVisible(true);
	buttonCreate.addMouseListener(this);
	buttonBook.addMouseListener(this);
	myList = new ArrayList<JTextField>();
	
		
	}


	
	
	
	

	@Override
	public void actionPerformed(ActionEvent e) {

		
		
	}


	@Override
	public void mouseClicked(MouseEvent e) {
		//create tuþuna basýldýðýnda for döngüsüyle koltukklarý frame e bastýrýyor
		if(e.getSource().equals(buttonCreate)) {
			panel.setVisible(false);
			int a = Integer.parseInt(seats.getText().trim());
			for(int i=0; i<a; i++) {
				myList.add(i, new JTextField());
				myList.get(i).setText("Not booked");
				myList.get(i).setSize(130, 60);
				myList.get(i).setOpaque(true);
				panel.add(myList.get(i));
							
			}
			panel.setVisible(true);
			
		}
		//1//book tuþuna basýldýðýnda agente yazdýðýmýz sayý kadar random numbers üretiliyor array listin içine EKLENÝYOR.
		//2// AG bizim threadlerimizi tutan bir array list ve girdiðimiz sayý kadar thread oluþturuyor.
		else if(e.getSource().equals(buttonBook)) {
			int A = Integer.parseInt(agents.getText().trim());
			int D = Integer.parseInt(time.getText().trim());
			randomNumbers = new ArrayList<Integer>();
			
		//1  
			for(int i=0; i<A; i++) {
				randomNumbers.add(random.nextInt(D) + 1); //Avoiding 0 case
			}
			
			//2
			for(int i=0; i<A; i++) {
				AG.add(new Thread(new Runnable() {
					
					@Override
					
	              	

	              	//FOR GETTÝNG THE NUMBER OF THREAD
                  public void run() {
						String a = Thread.currentThread().getName();
						String[] b = a.split("-");
						
						int m = Integer.parseInt(b[1]) - 1;
						for(int i=0; i<myList.size(); i++) {
							if(!(myList.get(i).getBackground().equals(Color.red))) {
								k.lock();
								myList.get(i).setBackground(Color.red);
								myList.get(i).setText(String.valueOf("Booked by Agent " + m));
								k.unlock();
								try {
									Thread.currentThread().sleep(randomNumbers.get(m-1));
								} catch (InterruptedException e) {
									e.printStackTrace();
								}
							}
						}
						if(Thread.currentThread().equals(AG.get(AG.size() - 1))) {
							String message = "  ";
							ArrayList<Integer> pane = new ArrayList<Integer>();
							int order = 1;
							
							for(int i=0; i<Integer.parseInt(agents.getText().trim()); i++) {
								
								pane.add(0);
							}
							
							while(order<=AG.size()) {
								message += "Agent " + order + " booked " + ok(order) + " seats.\n";
								order++;
							}

							JOptionPane.showMessageDialog(null, message);
						}
					}}));
				AG.get(i).start();
			}	
			
			
			if(myList.get(myList.size()-1).equals(Color.red)) {
				
			}
			}
	}
	

	
	
	
	public int ok (int a) {
		int sayý = 0;
		for(int i=0; i<myList.size(); i++) {
			if(myList.get(i).getText().equals("Booked by Agent " + a)) {
				sayý++;
			}
		}
		return sayý;
	}
	
			
		
		
	
	
	
	
	
	
	


	




	private JTextField JTextField(String string) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-g
		
	}}
	
	
	


