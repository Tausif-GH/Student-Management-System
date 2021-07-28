import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
public class Sremove extends Frame implements ActionListener, WindowListener
{
    public DataAccess da;
	private TextField tf,pf,T;
	private Button removeButton,backButton;
	private TextArea ta;
	private Frame parent;
    private Label x,y;
	public static Aremove1 aremove1=new Aremove1();
	public Sremove(){
		super("Remove Window");
        removeButton=new Button("Remove");
		backButton=new Button("Back");
        x = new Label("Insert SID to remove: ");
		T = new TextField(5);
		ta=new TextArea(10,50);
		ta.setEditable(false);
		add(ta);
        add(x);
		add(T);
		add(removeButton);add(backButton);
		backButton.addActionListener(this);
		removeButton.addActionListener(this);
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
    public void removeData(){
		try{
            String sql="delete from student where sID='"+T.getText()+"'";
			System.out.println(sql);
			
			da=da.getInstance();
            if(isEmpty(T)){
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
			removeData();
            loadData();
		}
		else if(e.getSource()==backButton){
			this.setVisible(false);
			aremove1.setVisible(true);
			aremove1.loadData();
			
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