import java.awt.*;
import java.awt.event.*;
import java.sql.*;
public class View extends Frame implements ActionListener, WindowListener
{
	public DataAccess da;
	private TextField tf,pf;
	private Button logoutButton,exitButton,passwordButton;
	private TextArea ta;
	private Frame parent;
	public static Sedit sedit=new Sedit();
	public View(){
		super("Java View Window");
		logoutButton=new Button("Logout");
		exitButton=new Button("Exit");
		passwordButton=new Button("Change Password");
		ta=new TextArea(10,50);
		ta.setEditable(false);
		add(ta);add(passwordButton);
		add(logoutButton);add(exitButton);
		logoutButton.addActionListener(this);
		exitButton.addActionListener(this);
		passwordButton.addActionListener(this);
		addWindowListener(this);
		setLayout(new FlowLayout());
		setSize(400,300);
	}
	public void loadData(String s){
		String sql="select * from student,grade where sID='"+s+"' and student.Grade=grade.Grade";
		try{
		 
			da=da.getInstance();
			ResultSet rs=da.getData(sql);
			String data="ID\tName\t \t\tGrade\tAmount\tStatus\n";
			while(rs.next()){
				data=data+rs.getString("sID")+"\t";
				data=data+rs.getString("Name")+"\t \t";
				data=data+rs.getString("Grade")+"\t";
				data=data+rs.getString("Amount")+"\t";
				data=data+rs.getString("Status")+"\n";
				
			}
			ta.setText(data);
		}
		catch(Exception ex){
			System.out.println("Exception in View");
		}
	}
	public void setParent(Frame f){
		parent=f;
	}
	public void actionPerformed(ActionEvent e){
		if(e.getSource()==logoutButton){
			this.setVisible(false);
			parent.setVisible(true);
		}
		else if(e.getSource()==exitButton){
			System.exit(0);
		}
		else if(e.getSource()==passwordButton){
			this.setVisible(false);
			sedit.setParent(this);
			sedit.setVisible(true);
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