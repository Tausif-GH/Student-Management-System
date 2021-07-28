import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
public class Aremove1 extends Frame implements ActionListener, WindowListener
{
    public DataAccess da;
	private TextField tf,pf;
	private Button removeButton,backButton,addButton,editButton;
	private TextArea ta;
	private Frame parent;
	public static Sadd2 sadd=new Sadd2();
	public static AView aview=new AView();
	public static Sremove sremove=new Sremove();
	

	public Aremove1(){
		super("Student Information Window");
        removeButton=new Button("Remove");
		backButton=new Button("Back");
		addButton=new Button("Add");
		editButton=new Button("Edit");
		
		ta=new TextArea(10,50);
		ta.setEditable(false);
		add(ta);
		add(addButton);add(removeButton);add(backButton);
		
		backButton.addActionListener(this);
		removeButton.addActionListener(this);
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
			sadd.loadData();
		   
		}
		else if(e.getSource()==removeButton){
			this.setVisible(false);
			sremove.setParent(this);
			sremove.setVisible(true);
			sremove.loadData();
		}
		else if(e.getSource()==backButton){
			this.setVisible(false);
			Navigate.aview.setVisible(true);
			Navigate.aview.loadData();
			Navigate.aview.loadData2();
			
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