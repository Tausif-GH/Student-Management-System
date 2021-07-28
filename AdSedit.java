import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
public class AdSedit extends Frame implements ActionListener, WindowListener
{
    public DataAccess da;
	private TextField t1,t3,t5;
	private Button editButton,backButton,na,g;
	private TextArea ta;
	private Frame parent;
    private Label y,x1,x3,x5;
	public AdSedit(){
		super("Student Edit Window");
		backButton=new Button("Back");
        na=new Button("Update Name");
        g=new Button("Update Grade");
        int n, i;
        x1 = new Label("Name:");
        x3 = new Label("Grade:");
        x5 = new Label("Student ID");
		t1 = new TextField(25);
        t3 = new TextField(12);
        t5 = new TextField(4);
		ta=new TextArea(10,50);
		ta.setEditable(false);
		add(ta);
        add(x5);add(t5);add(x1);add(t1);add(na);add(x3);add(t3);add(g);
		add(backButton);
		backButton.addActionListener(this);
        na.addActionListener(this);
        g.addActionListener(this);
		addWindowListener(this);
		setLayout(null);
		
		ta.setBounds(20,50,420,200);
		
		x5.setBounds(45,250,80,30);
		t5.setBounds(130,250,100,30);
		
		x1.setBounds(45,300,80,30);
		t1.setBounds(130,300,170,30);
		na.setBounds(300,300,100,30);
		
		x3.setBounds(45,350,80,30);
		t3.setBounds(130,350,170,30);
		g.setBounds(300,350,100,30);
		
		backButton.setBounds(150,400,100,30);
		setSize(460,500);
		
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
    public void editN(){
		try{
            String sql="update student set Name = '"+t1.getText()+"' where student.sID = '"+t5.getText()+"'";
			System.out.println(sql);
            
			da=da.getInstance();
            if(isEmpty(t1)){
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
    public void editG(){
		try{
            String sql="update student set Grade = '"+t3.getText()+"' where student.sID = '"+t5.getText()+"'";
			System.out.println(sql);
            
			da=da.getInstance();
            if(isEmpty(t3)){
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
		if(e.getSource()==editButton){
			
            loadData();
		}
        else if(e.getSource()==na){
			editN();
            loadData();
		}
        else if(e.getSource()==g){
            editG();
            loadData();
		}
		else if(e.getSource()==backButton){
			this.setVisible(false);
			Navigate.aview.setVisible(true);
			Navigate.aview.loadData();
			
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