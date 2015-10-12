
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Font;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;


@SuppressWarnings("serial")
public class GameTCP extends JFrame {
	private static int PORT = 7000;

    private String twoWayData;
	private Socket socket;
	private ServerSocket serverSocket;
	private JButton block00, block11,block12, block01, block10, block02, block20, block21, block22;
	private JButton btnNewGame,createButton, joinButton;
	private String board[] = { "","","", "","","", "","","" };
	private Font buttonFont,textFont;
	private boolean signal; 										// signal for "WHOSE TURN"
	private boolean isThisMyTurn = true;
	private ObjectOutputStream oOutStream;
	private ObjectInputStream oInStream;
	private JTextArea textArea;
	private JScrollPane sp;
	private int noOfMoves = 0;	
	private String myMark = null; // "O" or "X"
	private String yourMark = null; // "X" or "O"

	// constructor
	public GameServerClient(){
		initComponents();
		}
	
	
	void initComponents() {
		
		/*/ here EXIT_ON_CLOSE is used
		screenSize = Toolkit.getDefaultToolkit().getScreenSize();	 // get screen size
		width = (int) screenSize.getWidth(); 						 // get width
		height = (int) screenSize.getHeight(); 						 // get height
		
		setSize(width/3, height/3); */
		setBounds(0, 0, 670, 500);
		setResizable(true);
		setTitle("TicTacToe");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		
		setLayout(new BorderLayout());

		// here EXIT_ON_CLOSE is used
	
		JPanel xoPanel = new JPanel();
		xoPanel.setLayout(new GridLayout(3, 3));
		
		buttonFont = new Font("Tahoma", Font.PLAIN, 33);
		textFont = new Font("Tahoma", Font.PLAIN, 18);
		
		block00 = new JButton("[]");
		block00.setFont(buttonFont);
		block00.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) 
			{
				if(signal)
				{
					block00.setText(myMark); // set X or O button text
					send(myMark + "1");
					send("true");
					signal = false;
					block00.setEnabled(false);
					++noOfMoves;
					isWinner();
				}
			}
		});
		xoPanel.add(block00);
		
		block01 = new JButton("[]");
		block01.setFont(buttonFont);
		block01.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) 
			{
				if(signal)
				{
					block01.setText(myMark); // set X or O button text
					send(myMark + "2");
					send("true");
					signal = false;
					block01.setEnabled(false);
					++noOfMoves;
					isWinner();
				}
			}
		});
		xoPanel.add(block01);
		
		block02 = new JButton("[]");
		block02.setFont(buttonFont);
		block02.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) 
			{
				if(signal)
				{
					block02.setText(myMark); // set X or O button text
					send(myMark + "3");
					send("true");
					signal = false;
					block02.setEnabled(false);
					textArea.append(myMark+"\n");
					++noOfMoves;
					isWinner();
				}
			}
		});
		xoPanel.add(block02);
		
		block10 = new JButton("[]");
		block10.setFont(buttonFont);
		block10.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) 
			{
				if(signal)
				{
					block10.setText(myMark); // set X or O button text
					send(myMark + "4");
					send("true");
					signal = false;
					block10.setEnabled(false);
					++noOfMoves;
					isWinner();
				}
			}
		});
		xoPanel.add(block10);
		
		block11 = new JButton("[]");
		block11.setFont(buttonFont);
		block11.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) 
			{
				if(signal)
				{
					block11.setText(myMark); // set X or O button text
					send(myMark + "5");
					send("true");
					signal = false;
					block11.setEnabled(false);
					++noOfMoves;
					isWinner();
				}
			}
		});
		xoPanel.add(block11);
		
		block12 = new JButton("[]");
		block12.setFont(buttonFont);
		block12.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) 
			{
				if(signal)
				{
					block12.setText(myMark); // set X or O button text
					send(myMark + "6");
					send("true");
					signal = false;
					block12.setEnabled(false);
					++noOfMoves;
					isWinner();
				}
			}
		});
		xoPanel.add(block12);
		
		block20 = new JButton("[]");
		block20.setFont(buttonFont);
		block20.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) 
			{
				if(signal)
				{
					block20.setText(myMark); // set X or O button text
					send(myMark + "7");
					send("true");
					signal = false;
					block20.setEnabled(false);
					++noOfMoves;
					isWinner();
				}
			}
		});
		xoPanel.add(block20);
		
		block21 = new JButton("[]");
		block21.setFont(buttonFont);
		block21.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) 
			{
				if(signal)
				{
					block21.setText(myMark); // set X or O button text
					send(myMark + "8");
					send("true");
					signal = false;
					block21.setEnabled(false);
					++noOfMoves;
					isWinner();
				}
			}
		});
		xoPanel.add(block21);
		
		block22 = new JButton("[]");
		block22.setFont(buttonFont);
		block22.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) 
			{
				if(signal)
				{
					block22.setText(myMark); // set X or O button text
					send(myMark + "9");
					send("true");
					signal = false;
					block22.setEnabled(false);
					++noOfMoves;
					isWinner();
				}
			}
		});
		xoPanel.add(block22);
				
		//switchButtonState(false); 	// set all buttons on false till we wait for client to join
		add(xoPanel, BorderLayout.CENTER);
		
		// --- EAST PANEL ---
		JPanel pEast = new JPanel();
		pEast.setLayout(new BorderLayout());
		pEast.setPreferredSize(new Dimension(300, 300));
		
		textArea = new JTextArea();
		textArea.setLineWrap(true);
		textArea.setEditable(false);
		textArea.setFont(textFont);
		textArea.append(""); 
		sp = new JScrollPane(textArea); 
		sp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		
		pEast.add(sp, BorderLayout.CENTER);
		add(pEast, BorderLayout.EAST);
		
		createButton = new JButton();
		createButton.setText("Create");
		createButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
        createButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                createButtonActionPerformed(evt);
            }
        });
        
        joinButton = new JButton();
        joinButton.setText("Join");
        joinButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
        joinButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	try	{ 	
        			socket = new Socket("localhost", PORT); 
        			
        			oOutStream = new ObjectOutputStream(socket.getOutputStream());
        			oOutStream.flush();
        			oInStream = new ObjectInputStream(socket.getInputStream());
        			
        			twoWayData = (String) oInStream.readObject();
        			textArea.append(twoWayData + "\n");
        			scrollToBottom();        			
        			
        			signal = false;
        				
        			twoWayData = (String) oInStream.readObject(); // get nick from host
        			myMark = twoWayData;
        			
          			textArea.append("My mark: "+myMark+"\n");
        			textArea.append("Waiting for other player to play\n");
        	
        			scrollToBottom();
        			
        			joinButton.setEnabled(false);
        			createButton.setEnabled(false);
        			      			
        			new Recieve("twoWayDataOfServer"); // thread for receive data from host		
        		}
        		catch(Exception e)	{
        			turnOffStream();
        			//restart();
        			try { JOptionPane.showMessageDialog(null, "JoinButton: Error: Server is offline: \n" + e); } catch (ExceptionInInitializerError exc) { }
        		}	
            }
        });
               
		btnNewGame = new JButton();
		btnNewGame.setText("New Game");
		btnNewGame.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnNewGame.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				send("RESET");
				switchButtonState(true);
				clear();
			}
		});	
				
		JPanel pNorth = new JPanel();
		pNorth.add(btnNewGame);
		pNorth.add(createButton);
		pNorth.add(joinButton);
		add(pNorth, BorderLayout.NORTH);
		
		// --- WINDOW LISTENER ---
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent event) {
				if(socket != null) {
					send("Going offline!");
				}
				turnOffStream();
			}
		});
	}
	
	// --- CREATE BUTTON ACTION PERFORMED ---
	protected void createButtonActionPerformed(ActionEvent evt) {
		// TODO Auto-generated method stub
		textArea.append("My mark is X\n");
		myMark ="X";
		yourMark="O";
		new CreateButtonThread("CreateButton");					
	}

	// --- CREATE BUTTON THREAD ---
	private class CreateButtonThread implements Runnable {	
		public CreateButtonThread(String name) {
			new Thread(this, name).start();
		}
		
		public void run(){
			try {
				createButton.setEnabled(false);
												
				serverSocket = new ServerSocket(PORT); 
				
				textArea.append("Waiting for client...\n");
				scrollToBottom();
				socket = serverSocket.accept();
				
				oOutStream = new ObjectOutputStream(socket.getOutputStream());
				oOutStream.flush();
				oInStream = new ObjectInputStream(socket.getInputStream());
				
				send("Player: Successfully connected!");
				textArea.append("Client Successfully connected!\n");
				textArea.append("Your turn\n");
				scrollToBottom();
				signal = true;
				send(yourMark);
				
				switchButtonState(true);
				scrollToBottom();
				new Recieve("twoWayDataOfClient"); 
			}
			catch (Exception e) { 
				turnOffStream();
				try { JOptionPane.showMessageDialog(null, "CreateButton: Error while creating game:\n" + e);  } catch (ExceptionInInitializerError exc) { }
				}
		}
		}
	
	// send data over the internet
	private void send(String p)	{			
		try{
			if(isThisMyTurn){
				oOutStream.writeObject(p);
				oOutStream.flush();
			}
		}
		catch(SocketException e){
			if(isThisMyTurn){
				isThisMyTurn = false;
				turnOffStream();
			}
		}
		catch(Exception e) { 
			if(isThisMyTurn){
				isThisMyTurn = false;
				turnOffStream();
			}
		}
	}
	
	private class Recieve implements Runnable {	
		private boolean flag;
		private String info;
		
		public Recieve(String i){
			flag = true;
			info = i;
			new Thread(this, info).start();
		}
		
		public void run() {
			while(flag){
				try {
					twoWayData = "";
					twoWayData = (String) oInStream.readObject();
					
					if(info.equals("twoWayDataOfServer")) {
						if(twoWayData.equalsIgnoreCase("true"))	{
							signal = true;
						}
						else if(twoWayData.equalsIgnoreCase("false")) {
							signal = false;
						}
						else if(twoWayData.equalsIgnoreCase("RESET")) {
							clear();
							switchButtonState(true);
						}
						else if(twoWayData.equalsIgnoreCase("DRAW")) {
							textArea.append("Draw");
						} 
						else if(twoWayData.equalsIgnoreCase("X1")){
							block00.setText("X");
							block00.setEnabled(false);
							isWinner();
						}
						else if(twoWayData.equalsIgnoreCase("X2")){
							block01.setText("X");
							block01.setEnabled(false);
							isWinner();
						}
						else if(twoWayData.equalsIgnoreCase("X3")){
							block02.setText("X");
							block02.setEnabled(false);
							isWinner();
						}
						else if(twoWayData.equalsIgnoreCase("X4")){
							block10.setText("X");
							block10.setEnabled(false);
							isWinner();
						}
						else if(twoWayData.equalsIgnoreCase("X5")){
							block11.setText("X");
							block11.setEnabled(false);
							isWinner();
						}
						else if(twoWayData.equalsIgnoreCase("X6")){
							block12.setText("X");
							block12.setEnabled(false);
							isWinner();
						}
						else if(twoWayData.equalsIgnoreCase("X7")){
							block20.setText("X");
							block20.setEnabled(false);
							isWinner();
						}
						else if(twoWayData.equalsIgnoreCase("X8")){
							block21.setText("X");
							block21.setEnabled(false);
							isWinner();
						}
						else if(twoWayData.equalsIgnoreCase("X9")){
							block22.setText("X");
							block22.setEnabled(false);
							isWinner();
						}						
					}
					else if(info.equals("twoWayDataOfClient"))			
					{
						if(twoWayData.equalsIgnoreCase("true"))
						{
							signal = true;
						}
						else if(twoWayData.equalsIgnoreCase("false")) {
							signal = false;
						}
						else if(twoWayData.equalsIgnoreCase("O1"))
						{
							++noOfMoves;
							block00.setText("O");
							block00.setEnabled(false);
							isWinner();
						}
						else if(twoWayData.equalsIgnoreCase("O2"))
						{
							++noOfMoves;
							block01.setText("O");
							block01.setEnabled(false);
							isWinner();
						}
						else if(twoWayData.equalsIgnoreCase("O3"))
						{
							++noOfMoves;
							block02.setText("O");
							block02.setEnabled(false);
							isWinner();
						}
						else if(twoWayData.equalsIgnoreCase("O4"))
						{
							++noOfMoves;
							block10.setText("O");
							block10.setEnabled(false);
							isWinner();
						}
						else if(twoWayData.equalsIgnoreCase("O5"))
						{
							++noOfMoves;
							block11.setText("O");
							block11.setEnabled(false);
							isWinner();
						}
						else if(twoWayData.equalsIgnoreCase("O6"))
						{
							++noOfMoves;
							block12.setText("O");
							block12.setEnabled(false);
							isWinner();
						}
						else if(twoWayData.equalsIgnoreCase("O7"))
						{
							++noOfMoves;
							block20.setText("O");
							block20.setEnabled(false);
							isWinner();
						}
						else if(twoWayData.equalsIgnoreCase("O8"))
						{
							++noOfMoves;
							block21.setText("O");
							block21.setEnabled(false);
							isWinner();
						}
						else if(twoWayData.equalsIgnoreCase("O9"))
						{
							++noOfMoves;
							block22.setText("O");
							block22.setEnabled(false);
							isWinner();
						}
						else if(twoWayData.equalsIgnoreCase("RESET")) {
							clear();
							switchButtonState(true);
						}
					}
				}catch(Exception e){
					turnOffStream();
				}
			}
		}
	}
			
	
	private void isWinner()	{
		
		if (block00.getText().toString().equals("X") && block01.getText().toString().equals("X") && block02.getText().toString().equals("X") ||
			block10.getText().toString().equals("X") && block11.getText().toString().equals("X") && block12.getText().toString().equals("X") ||
			block20.getText().toString().equals("X") && block21.getText().toString().equals("X") && block22.getText().toString().equals("X") ||

			block00.getText().toString().equals("X") && block10.getText().toString().equals("X") && block20.getText().toString().equals("X") ||
			block01.getText().toString().equals("X") && block11.getText().toString().equals("X") && block21.getText().toString().equals("X") ||
			block02.getText().toString().equals("X") && block12.getText().toString().equals("X") && block22.getText().toString().equals("X") ||
				
			block00.getText().toString().equals("X") && block11.getText().toString().equals("X") && block22.getText().toString().equals("X") ||				
			block02.getText().toString().equals("X") && block11.getText().toString().equals("X") && block20.getText().toString().equals("X") )
		{
			noOfMoves = 0;
			switchButtonState(false);
			textArea.append("X WINs!\n");		}
		
		else if
		(block00.getText().toString().equals("O") && block01.getText().toString().equals("O") && block02.getText().toString().equals("O") ||
		 block10.getText().toString().equals("O") && block11.getText().toString().equals("O") && block12.getText().toString().equals("O") ||
			block20.getText().toString().equals("O") && block21.getText().toString().equals("O") && block22.getText().toString().equals("O") ||
		
			block00.getText().toString().equals("O") && block10.getText().toString().equals("O") && block20.getText().toString().equals("O") ||
			block01.getText().toString().equals("O") && block11.getText().toString().equals("O") && block21.getText().toString().equals("O") ||
			block02.getText().toString().equals("O") && block12.getText().toString().equals("O") && block22.getText().toString().equals("O") ||
				
			block00.getText().toString().equals("O") && block11.getText().toString().equals("O") && block22.getText().toString().equals("O") ||
			block02.getText().toString().equals("O") && block11.getText().toString().equals("O") && block20.getText().toString().equals("O") )
		{
			noOfMoves = 0;
			switchButtonState(false);
			textArea.append(" O WINs!\n");
			
		}
		else
		{
			//CHECK IF IS DRAW
			if(noOfMoves >= 9)
			{
				noOfMoves = 0;
				try {
					send("DRAW");
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				textArea.append("The game is DRAWN\n");
			}
		}
	}
	
	
	
	public void clear() {
		for(int iter=0;iter<9;iter++){
			board[iter]="";
		}
		noOfMoves=0;
		
		block00.setText("[]");
		block01.setText("[]");
		block02.setText("[]");
		block10.setText("[]");
		block11.setText("[]");
		block12.setText("[]");		
		block20.setText("[]");
		block21.setText("[]");
		block22.setText("[]");
	}
	public void switchButtonState(boolean b) {
		block00.setEnabled(b);
		block01.setEnabled(b);
		block02.setEnabled(b);
		block10.setEnabled(b);
		block11.setEnabled(b);
		block12.setEnabled(b);		
		block20.setEnabled(b);
		block21.setEnabled(b);
		block22.setEnabled(b);
	}
	
	public void scrollToBottom()
	{
		textArea.setCaretPosition(textArea.getText().length());
	}
	
	private void turnOffStream()
	{
		try { oOutStream.flush(); 		} catch (Exception e) { }
		try { oOutStream.close(); 		} catch (Exception e) { }
		try { oInStream.close(); 		} catch (Exception e) { }
		try { serverSocket.close();	} catch (Exception e) { }
		try { socket.close(); 	} catch (Exception e) { }
	}
	
	
	
	/**
	 * Launch the application.
	 * @throws IOException 
	 */
	public static void main(String[] args) {
       	new GameServerClient().setVisible(true);
   	}
}