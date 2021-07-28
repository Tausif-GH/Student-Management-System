import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
public class Acadd extends Frame implements ActionListener, WindowListener
{	public DataAccess da;
    private TextField tf,pf,t1,t2,t3;
	private Button addButton,backButton;
	private TextArea ta;
	private Frame parent;
    private Label y,x1,x2,x3;
	public static Acremove acremove= new Acremove();
	public Acadd(){
		super("Entry Window");
        addButton=new Button("Add");
		backButton=new Button("Back");
        int n, i;
        x1 = new Label("Name:");
        x2 = new Label("Password:");
		x3 = new Label("Salary: ");
		t1 = new TextField(25);
        t2 = new TextField(6);
		t3 = new TextField(6);
		ta=new TextArea(10,50);
		ta.setEditable(false);
		add(ta);
        add(x1);add(t1);add(x2);add(t2);add(x3);add(t3);
		add(addButton);add(backButton);
		backButton.addActionListener(this);
		addButton.addActionListener(this);
		addWindowListener(this);
		setLayout(null);
		
		ta.setBounds(20,50,390,200);
		
		x1.setBounds(45,250,80,30);
		t1.setBounds(130,250,200,30);
		
		x2.setBounds(45,300,80,30);
		t2.setBounds(130,300,200,30);
		
		x3.setBounds(45,350,80,30);
		t3.setBounds(130,350,200,30);
		
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
		String sql="select * from accountants";
		try{
			
			da=da.getInstance();
			ResultSet rs=da.getData(sql);
			String data="ID\t Name\t\t\tJoining Year\n";
			while(rs.next()){
				data=data+rs.getInt("aID")+"\t";
				data=data+rs.getString("Name")+"\t\t";
				data=data+rs.getString("Joining Year")+"\n";
				
			}
			ta.setText(data);
		}
		catch(Exception ex){
			System.out.println("Exception in View");
		}
	}
    public void addData(){
		try{
            
            String sql="INSERT INTO accountants (Name, password) VALUES ('"+t1.getText()+"', '"+t2.getText()+"')";
			System.out.println(sql);
            
			da=da.getInstance();
            if(isEmpty(t1)||isEmpty(t2)){
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
			loadData();
			this.setVisible(false);
			acremove.setVisible(true);
			acremove.loadData();
			
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