import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
class TicTacToe extends JFrame
{
	JLabel bg= new JLabel(new ImageIcon(getClass().getResource("images/t2.jpg")));
	JPanel []p= new JPanel[3];
	JLabel msg= new JLabel("First Player Turn"); 
	JButton reset= new JButton("RESET");
	JButton []bt= new JButton[9];
	ImageIcon icon1 = new ImageIcon(getClass().getResource("images/user1.png"));
	ImageIcon icon2 = new ImageIcon(getClass().getResource("images/user2.png"));
	int user=1,count=0;
	String Player=null;
	boolean WinnerFound=false;
	public TicTacToe()
	{
		super("TicTacToe");
		setSize(600,640);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		add(bg);
		addPanels();
		setVisible(true);
	}
	private void addPanels()
	{
		for(int i=0;i<3;i++)
		{
			p[i]=new JPanel();
			bg.add(p[i]);
		}
		p[0].setBounds(100,30,400,40);
		p[1].setBounds(100,100,400,400);
		p[2].setBounds(100,530,400,40);
		addInfo();
		addButton();
	}
	private void addInfo()
	{
		p[0].add(msg);
		msg.setFont(new Font("script",Font.PLAIN,26));
		setForeground(Color.yellow);
		p[2].add(reset);
		reset.addActionListener(new resetListener());
		p[2].setOpaque(false);
		reset.setFont(new Font("elephant",Font.PLAIN,26));
		setForeground(Color.blue);	
		reset.setEnabled(false);
	}
	private void addButton()
	{
		p[1].setBorder(BorderFactory.createLineBorder(Color.blue));
		p[1].setLayout(new GridLayout(3,3));
		javax.swing.border.Border b=BorderFactory.createLineBorder(Color.blue);
		TicListener listener=new TicListener(); 
		for(int i=0;i<9;i++)
		{
			bt[i]= new JButton();
			bt[i].addActionListener(listener);
			bt[i].setBorder(b);
			bt[i].setBackground(Color.yellow);
			p[1].add(bt[i]);
		}
	}
	class TicListener implements ActionListener
	{
		public void actionPerformed(ActionEvent evt)
		{
			JButton bc=(JButton)evt.getSource();
			Icon ic=bc.getIcon();
			if(ic!=null || WinnerFound )
			  return;
			if(user==1)
			{
				bc.setIcon(icon1);
				msg.setText("Second Player Turn");
				user=2;
				Player="First";
				matchIcon(icon1);
			}
			else if(user==2)
			{	
				bc.setIcon(icon2);
				msg.setText("First player turn");
				user=1;
				Player="Second";
				matchIcon(icon2);
			}
			count++;
			if (count==9 && !WinnerFound)
			{
				msg.setText("IT's A TIE");
				msg.setForeground(Color.blue);
			}
		}
	}
	public void matchIcon(ImageIcon icon)	
	{
		if(bt[0].getIcon()==icon && bt[1].getIcon()==icon && bt[2].getIcon()==icon)
		AnnounceWinner(0,1,2);
		if(bt[3].getIcon()==icon && bt[4].getIcon()==icon && bt[5].getIcon()==icon)
		AnnounceWinner(3,4,5);
		if(bt[6].getIcon()==icon && bt[7].getIcon()==icon && bt[8].getIcon()==icon)
		AnnounceWinner(6,7,8);
		if(bt[0].getIcon()==icon && bt[3].getIcon()==icon && bt[6].getIcon()==icon)
		AnnounceWinner(0,3,6);
		if(bt[1].getIcon()==icon && bt[4].getIcon()==icon && bt[7].getIcon()==icon)
		AnnounceWinner(1,4,7);
		if(bt[2].getIcon()==icon && bt[5].getIcon()==icon && bt[8].getIcon()==icon)
		AnnounceWinner(2,5,8);
		if(bt[0].getIcon()==icon && bt[4].getIcon()==icon && bt[8].getIcon()==icon)
		AnnounceWinner(0,4,8);
		if(bt[2].getIcon()==icon && bt[4].getIcon()==icon && bt[6].getIcon()==icon)
		AnnounceWinner(2,4,6);
	}
	public void AnnounceWinner(int i1,int i2,int i3)
	{
		bt[i1].setBackground(Color.red);
		bt[i2].setBackground(Color.red);
		bt[i3].setBackground(Color.red);
		msg.setText(Player+ "Player is Winner");
		msg.setForeground(Color.blue);
		reset.setEnabled(true);
		WinnerFound=true;
	}
	class resetListener implements ActionListener
	{
		public void actionPerformed(ActionEvent evt)
		{
			WinnerFound=false;
			user=1;
			count=0;
			msg.setText("Player First Turn");
			msg.setForeground(Color.blue);
			for(int i=0;i<9;i++)
			{
				bt[i].setIcon(null);
				bt[i].setBackground(Color.green);
			}
			reset.setEnabled(false);
		}
	}
	public static void main(String []args)
	{
		setDefaultLookAndFeelDecorated(true);
		new TicTacToe();
	}
}