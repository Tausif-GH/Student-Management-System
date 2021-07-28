import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
public class AccView extends Frame implements ActionListener, WindowListener
{
	public DataAccess da;
    private TextField tf,pf,T;
	private Button logoutButton,addButton,editButton;
	private TextArea ta;
	private Frame parent;
    private Label y;
	public static Sremove sremove = new Sremove();
	public static Sadd sadd = new Sadd();
	public static Status status = new Status();

	
	public AccView(){
		super("Accounts Window");
        
		logoutButton=new Button("Logout");
		addButton=new Button("Add Student");
		editButton=new Button("Change Status");
		ta=new TextArea(10,50);
		ta.setEditable(false);
		add(ta);
		add(addButton);add(editButton);add(logoutButton);
		logoutButton.addActionListener(this);
		addButton.addActionListener(this);
		editButton.addActionListener(this);
		addWindowListener(this);
		setLayout(new FlowLayout());
		setSize(400,500);
    }
    private boolean isEmpty(TextField s){
		boolean flag=false;
		if(s.getText().length()==0)flag=true;
		//System.out.println(s.getText()+":"+flag);
		return flag;
	}
    public void loadData(){
		String sql="select * from student,grade where student.Grade=grade.Grade";
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
		if(e.getSource()==addButton){
			
			this.setVisible(false);
			sadd.setVisible(true);
			sadd.setParent(this);
			sadd.loadData();
		   
		}
		else if(e.getSource()==editButton){
			
			this.setVisible(false);
			status.setVisible(true);
			status.setParent(this);
			status.loadData();
		   
		}
		else if(e.getSource()==logoutButton){
			this.setVisible(false);
			Navigate.alog.setVisible(true);
		}
	}

    public void windowActivated(WindowEvent e){	}
	public void windowClosed(WindowEvent e){}
	public void windowClosing(WindowEvent e){
		System.exit(0);
	}
	public void windowDeactivated(WindowEvent e){}
	public void windowDeiconified(WindowEvent e){}
	public void windowIconified(WindowEvent e){}
	public void windowOpened(WindowEvent e){}
}