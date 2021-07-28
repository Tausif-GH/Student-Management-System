import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;		
public class Sadd extends Frame implements ActionListener, WindowListener
{
	public DataAccess da;
    private TextField name,studentId,dob,grade,password;
	private Button addButton,backButton;
	private TextArea ta;
	private Frame parent;
    private Label y,l1,l2,l3,l4,l5;
	public Sadd(){
		super("Entry Window");
        addButton=new Button("Add");
		backButton=new Button("Back");
        l1=new Label("Name: ");
		name=new TextField(10);
		l2=new Label("Password: ");
		password=new TextField(6);
		l3=new Label("Grade");
		grade=new TextField(2);
		ta=new TextArea(10,50);
		ta.setEditable(false);
		add(ta);
        add(l1);add(l2);add(l3);add(name);add(password);add(grade);
		add(addButton);add(backButton);
		backButton.addActionListener(this);
		addButton.addActionListener(this);
		addWindowListener(this);
		setLayout(null);
		
		ta.setBounds(20,50,390,200);
		l1.setBounds(45,250,80,30);
		name.setBounds(130,250,200,30);
		l2.setBounds(45,300,80,30);
		password.setBounds(130,300,200,30);
		l3.setBounds(45,350,80,30);
		grade.setBounds(130,350,200,30);
		
		addButton.setBounds(130,400,100,30);
		backButton.setBounds(220,400,100,30);
		setSize(450,500);
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
			String data="ID\t Name\t\t\tGrade\tAmount\tStatus\n";
			while(rs.next()){
				data=data+rs.getInt("sID")+"\t";
				data=data+rs.getString("Name")+"\t\t";
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
    public void addData(){
		try{
            
            String sql="INSERT INTO student (Name, Password, Grade, Status) VALUES ('"+name.getText()+"', '"+password.getText()+"', '"+grade.getText()+"', 'Valid')";
			System.out.println(sql);
            
			da=da.getInstance();
            if(isEmpty(name)||isEmpty(password)||isEmpty(grade)){
				JOptionPane.showMessageDialog(this,"All fields are mandatory");
			}
			else{
				if(da.updateDB(sql)>0){
					JOptionPane.showMessageDialog(this,"Data Updated");
					y.setText("Data Updated");
				}
				else{
					y.setText("Update Error");
					JOptionPane.showMessageDialog(this,"Update Error!");
				}
			}
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
			addData();
            loadData();
		}
		else if(e.getSource()==backButton)
		{
			this.setVisible(false);
			Navigate.accview.setVisible(true);
			Navigate.accview.loadData();
			
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