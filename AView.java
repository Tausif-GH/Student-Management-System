import java.awt.*;
import java.awt.event.*;
import java.sql.*;
public class AView extends Frame implements ActionListener, WindowListener
{
	public DataAccess da;
	private TextField tf,pf;
	private Button logoutButton,exitButton,studentButton,accountantButton,editButton,aEditButton;
	private TextArea ta,ta2;
	private Frame parent;
	public static Aremove1 aremove=new Aremove1();
	public static Acremove aremove2=new Acremove();
	public static AdSedit ased=new AdSedit();
	public static AdAEdit acc=new AdAEdit();
	public AView(){
		super("Admin View Window");
		logoutButton=new Button("Logout");
		exitButton=new Button("Exit");
		studentButton=new Button("Add/Remove Student");
		accountantButton=new Button("Add/Remove Accountant");
		editButton=new Button("Student Edit");
		aEditButton=new Button("Accountant Edit");
		ta=new TextArea(10,60);
		ta.setEditable(false);
		add(ta);
		ta2=new TextArea(10,60);
		ta2.setEditable(false);
		add(ta2);
		add(logoutButton);add(exitButton);
		add(studentButton);add(accountantButton);
		add(editButton); add(aEditButton);
		logoutButton.addActionListener(this);
		exitButton.addActionListener(this);
		studentButton.addActionListener(this);
		accountantButton.addActionListener(this);
		editButton.addActionListener(this);
		aEditButton.addActionListener(this);
		addWindowListener(this);
		setLayout(new FlowLayout());
		setSize(500,600);
	}
public void loadData()
{
	String sql="select * from student";
	try
	{
		
		da=da.getInstance();
		ResultSet rs=da.getData(sql);
		String data="Students\n\nID\tName\t\t\tGrade\tStatus\n";
		while(rs.next())
		{
			data=data+rs.getString("sID")+"\t";
			data=data+rs.getString("Name")+"\t\t";
			data=data+rs.getString("Grade")+"\t";
			data=data+rs.getString("Status")+"\n";
				
		}
		ta.setText(data);
	}
	catch(Exception ex)
	{
		System.out.println("Exception in View");
	}
}	
public void loadData2()
	{
		String sql="select * from accountants";
		try
		{
			
			da=da.getInstance();
			ResultSet rs=da.getData(sql);
			String data="Accountants\n\nID\tName\t\t\tJoining Year\t\t Salary\n";
			while(rs.next())
			{
				data=data+rs.getString("aID")+"\t";
				data=data+rs.getString("Name")+"\t\t";
				data=data+rs.getString("Joining Year")+"\t";
				data=data+rs.getString("Salary")+"\n";
			}
			ta2.setText(data);
		}
		catch(Exception ex)
		{
			System.out.println("Exception in View");
		}
	}
	public void setParent(Frame f){
		parent=f;
	}
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource()==logoutButton)
		{
			this.setVisible(false);
			parent.setVisible(true);
		}
		else if(e.getSource()==editButton)
		{
			this.setVisible(false);
			ased.setVisible(true);
			ased.setParent(this);
			ased.loadData();
		}
		else if(e.getSource()==aEditButton)
		{
			this.setVisible(false);
			acc.setVisible(true);
			acc.setParent(this);
			acc.loadData2();
		}
		else if(e.getSource()==exitButton)
		{
			System.exit(0);
		}
		else if(e.getSource()==studentButton)
		{
			this.setVisible(false);
			aremove.setVisible(true);
			aremove.setParent(this);
			aremove.loadData();
		}
		else if(e.getSource()==accountantButton)
		{
			this.setVisible(false);
			aremove2.setVisible(true);
			aremove2.setParent(this);
			aremove2.loadData();
		}
	}
	public void windowActivated(WindowEvent e){	}
	public void windowClosed(WindowEvent e){}
	public void windowClosing(WindowEvent e){
		this.setVisible(false);
		parent.setVisible(true);
	}
	public void windowDeactivated(WindowEvent e){}
	public void windowDeiconified(WindowEvent e){}
	public void windowIconified(WindowEvent e){}
	public void windowOpened(WindowEvent e){}
}