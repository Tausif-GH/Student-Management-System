import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.applet.*;

public class Navigate extends JFrame implements ActionListener
{
	private JButton slogin,acclog,adminlog;
	public static SLogin log;
	public static AccLogin alog;
	public static AdLogin adlog;
	public static View view;
	public static AView aview;
	public static AccView accview;
	private JLabel l;
	public Navigate()
	{
		super("Login Window");
		log=new SLogin();
		alog=new AccLogin();
		adlog=new AdLogin();
		view=new View();
		aview=new AView();
		accview=new AccView();
		
		l=new JLabel("Student Management System");
		slogin=new JButton("Student Login");
		adminlog=new JButton("Admin Login");
		acclog=new JButton("Accountant Login");
		add(l);
		add(slogin); 
		add(adminlog); 
		add(acclog);
		
		slogin.addActionListener(this);
		acclog.addActionListener(this);
		adminlog.addActionListener(this);
		
		setLayout(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		l.setBounds(50,20,300,40);
		l.setFont(new Font("Impact", Font.PLAIN, 24));
		slogin.setBounds(90,100,200,40);
		adminlog.setBounds(90,200,200,40);
		acclog.setBounds(90,300,200,40);
		
		
		setSize(400,500);
	}
	public void actionPerformed(ActionEvent e)
	{
		System.out.println(e.getActionCommand());
		String sig=e.getActionCommand();
		if(sig.equals("Student Login"))
		{
			this.setVisible(false);
			log.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			log.setVisible(true);
			log.setParent(this);
		}
		else if(sig.equals("Admin Login"))
		{
			this.setVisible(false);
			adlog.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			adlog.setVisible(true);
			adlog.setParent(this);
		}
		else if(sig.equals("Accountant Login"))
		{
			this.setVisible(false);
			alog.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			alog.setVisible(true);
			alog.setParent(this);
			
		}
	}
}