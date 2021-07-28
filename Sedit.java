import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
public class Sedit extends Frame implements ActionListener, WindowListener
{
    public DataAccess da;
	private TextField tf,pf,t1,t2,t3,t4,t5;
	private Button backButton,p;
	private TextArea ta;
	private Frame parent;
    private Label y,x1,x2,x5;
	public Sedit(){
		super("Change Password Window");
		backButton=new Button("Back");
		p=new Button("Change Password");
        x2 = new Label("Password:");
        x5 = new Label("sID");
		
        t2 = new TextField(6);
        
        t5 = new TextField(4);
		
		ta=new TextArea(10,50);
		ta.setEditable(false);
		add(ta);
        add(x5);add(t5);add(x2);add(t2);add(p);
		add(backButton);
		backButton.addActionListener(this);
        p.addActionListener(this);
		addWindowListener(this);
		setLayout(null);
		
		
		x5.setBounds(45,50,80,30);
		t5.setBounds(130,50,100,30);
		
		x2.setBounds(45,100,80,30);
		t2.setBounds(130,100,170,30);
		p.setBounds(300,100,110,30);
		
		backButton.setBounds(150,150,100,30);
		setSize(460,300);
    }
    private boolean isEmpty(TextField s){
		boolean flag=false;
		if(s.getText().length()==0)flag=true;
		//System.out.println(s.getText()+":"+flag);
		return flag;
	}

    public void loadData(String s){
		String sql="select * from student,grade where sID='"+t5.getText()+"'and student.Grade=grade.Grade";
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
    public void editP(){
		try{
            String sql="update student set Password = '"+t2.getText()+"' where student.sID = '"+t5.getText()+"'";
			System.out.println(sql);
         
			da=da.getInstance();
            if(isEmpty(t2)){
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
        if(e.getSource()==p){
			editP();
            loadData(t5.getText());
		}
		else if(e.getSource()==backButton){
			this.setVisible(false);
			parent.setVisible(true);
			
			
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