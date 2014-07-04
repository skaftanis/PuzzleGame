import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class BoxForPc extends JFrame implements ActionListener {
	//setup UI
	int r;
	int tries;
	boolean change=false;
	JButton newGame = new JButton ("New Game");

	JButton b1 = new JButton("1");
	JButton b2 = new JButton("2");
	JButton b3 = new JButton("3");
	JButton b4 = new JButton("4");
	JButton b5 = new JButton("5");
	JButton b6 = new JButton("6");
	JButton b7 = new JButton("7");
	JButton b8 = new JButton("8");
	JButton empty = new JButton("");
	JPanel basic = new JPanel();
	Integer temp [][] = new Integer [3][3];

	JPanel down = new JPanel();
    JLabel moves = new JLabel("Moves: 0");
    
    
    public BoxForPc ()
    {
	   //JFrame frame = new JFrame();
	   BorderLayout bl = new BorderLayout();	
 	  //frame.setLayout(bl);
	   setLayout(bl);
		JPanel up = new JPanel();
		add("North", up);
		up.add("Center", newGame);
		
		add("Center", basic);
		
		GridLayout gr = new GridLayout(3,3,10,10);
		basic.setLayout(gr);
			
		
		tableCreation();
		
		add(basic);
		
	    down.add(moves);
	    add("South",down);
		
		setVisible(true);
		setSize(400,400);
		
		b1.addActionListener(this);
		b2.addActionListener(this);
		b3.addActionListener(this);
		b4.addActionListener(this);
		b5.addActionListener(this);
		b6.addActionListener(this);
		b7.addActionListener(this);
		b8.addActionListener(this);
		newGame.addActionListener(this);
		

   	 try {
   	      UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
   	     SwingUtilities.updateComponentTreeUI(this);
   	     } catch (Exception e) {
   	            System.out.println(e.getMessage());  } 
		
}
		
	
	public void actionPerformed (ActionEvent evt) {
		Object source = evt.getSource();
		if (source != newGame) {
				Move((JButton)source);
				removeAll();
				repaintAll();
				if (victoryCheck()==true) JOptionPane.showMessageDialog(null, "Well Done!");
				if (victoryCheck()==false && change==true) { 
					tries++; 
					down.removeAll(); 
					down.add(new JLabel("Moves: "+ tries));
					} 
		}
		else { 
			tries=0;
			down.removeAll(); 
			down.add(new JLabel("Moves: 0"));
			tableCreation();
			removeAll();
			repaintAll();
		}
		
	  
	}
	
	public void tableCreation () {

		int i;
		boolean valid [] = {false,false,false,false,false,false,false, false, false}; 
     	java.util.Random rand = new java.util.Random();
     
		for (i=0; i<=8; i++) {
			do {
			r=rand.nextInt(9);
			if (r==9) r=8;
			} while (valid[r]==true);
		    valid[r]=true;
	    if (r==0) 
		basic.add(empty);
	    else if ( r == 1)
		basic.add(b1);
	    else if (r ==2 )
		basic.add(b2);
	    else if (r==3)
		basic.add(b3);
	    else if (r==4)
		basic.add(b4);
	    else if (r==5)
		basic.add(b5);
	    else if (r==6)
		basic.add(b6);
	    else if (r==7)
		basic.add(b7); 
	    else if (r==8)
	    basic.add(b8);
	    	    
	    
	    if (i<=2) temp[0][i]=r;
	    if (i==3) temp[1][0]=r;
	    if (i==4) temp[1][1]=r;  
	    if (i==5) temp[1][2]=r;  
	    if (i==6) temp[2][0]=r;  
	    if (i==7) temp[2][1]=r; 
	    if (i==8) temp[2][2]=r; 
		}
	}
	
	public void removeAll () {
	    basic.remove(empty);
		basic.remove(b1);
		basic.remove(b2);
		basic.remove(b3);
		basic.remove(b4);
		basic.remove(b5);
		basic.remove(b6);
		basic.remove(b7);
		basic.remove(b8);
		basic.repaint();

	}

	public void repaintAll (){
		int i,j;
		//System.out.println(" (paint)ANOIGO");
		for (i=0; i<=2; i++)
			for (j=0;j<=2; j++) {
			   if (temp[i][j] == 0 ) basic.add(empty);
			   if (temp[i][j] == 1 ) basic.add(b1);
			   if (temp[i][j] == 2 ) basic.add(b2);
			   if (temp[i][j] == 3 ) basic.add(b3);
			   if (temp[i][j] == 4 ) basic.add(b4);
			   if (temp[i][j] == 5 ) basic.add(b5);
			   if (temp[i][j] == 6 ) basic.add(b6);
			   if (temp[i][j] == 7 ) basic.add(b7);
			   if (temp[i][j] == 8 ) basic.add(b8);	   
			   basic.repaint();
			   basic.revalidate();
		 	}
		
		
	}

	public void Move ( JButton button) {
		int i,j;
		String ar = button.getText();
	    int number=Integer.parseInt(ar);
		int temporary;
		int buttonX=0, buttonY=0, emptyX=0, emptyY=0;
			change=false;
			for (i=0;i<=2;i++)
				for(j=0;j<=2;j++)
				  if (temp[i][j] == number) { buttonX=i; buttonY=j; } 
			
			for (i=0;i<=2;i++)
				for (j=0;j<=2;j++)
					if (temp[i][j] == 0 ) { emptyX=i; emptyY=j; }
			
			if (buttonX==emptyX && ( buttonY==emptyY+1 || buttonY==emptyY-1) ) {
				change=true;
				temporary=temp[buttonX][buttonY];
				temp[buttonX][buttonY]=temp[emptyX][emptyY];
				temp[emptyX][emptyY]=temporary;
			}
			
			if (buttonY==emptyY && (buttonX==emptyX+1 || buttonX==emptyX-1 )){
				change=true;
				temporary=temp[buttonX][buttonY];
				temp[buttonX][buttonY]=temp[emptyX][emptyY];
				temp[emptyX][emptyY]=temporary;
			}

	}
	
	public boolean victoryCheck () {
		if (temp[0][0] == 1 && temp[0][1] == 2 && temp[0][2] == 3 && temp[1][0] == 4  && temp[1][1] == 5
				&& temp[1][2] == 6 && temp[2][0] == 7 && temp[2][1] == 8 && temp[2][2] == 0) return true;
		else return false;
	}
	
	public static void main (String args[]) {
		BoxForPc app = new BoxForPc();
		app.setBounds(400,200, 450, 450);
	}
	
	
}
