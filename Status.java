import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
public class Status extends Frame implements ActionListener, WindowListener
{
    public DataAccess da;
	private TextField tf,pf,t1;
	private Button removeButton,backButton,validButton;
	private TextArea ta;
	private Frame parent;
    private Label x,y;
	public static AccView accview=new AccView();
	public Status(){
		super("Change Status Window");
        removeButton=new Button("Invalid");
		validButton=new Button("Valid");
		backButton=new Button("Back");
        x = new Label("Insert ID of student to change: ");
		t1 = new TextField(5);
		ta=new TextArea(10,50);
		ta.setEditable(false);
		add(ta);
        add(x);
		add(t1);
		add(removeButton);add(validButton);add(backButton);
		
		backButton.addActionListener(this);
		removeButton.addActionListener(this);
		validButton.addActionListener(this);
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
    public void editStatus1(){
		try{
            String sql="UPDATE student SET Status = 'Invalid' WHERE student.sID = '"+t1.getText()+"'";
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
	 public void editStatus2(){
		try{
            String sql="UPDATE student SET Status = 'Valid' WHERE student.sID = '"+t1.getText()+"'";
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
    
    public void setParent(Frame f){
		parent=f;
	}
	public void actionPerformed(ActionEvent e){
		if(e.getSource()==removeButton){
			editStatus1();
            loadData();
		}
		else if(e.getSource()==validButton){
			editStatus2();
            loadData();
		}
		else if(e.getSource()==backButton){
			this.setVisible(false);
			accview.setVisible(true);
			accview.loadData();
			
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