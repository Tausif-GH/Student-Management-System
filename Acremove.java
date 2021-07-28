import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
public class Acremove extends Frame implements ActionListener, WindowListener
{
	public DataAccess da;
	private Button removeButton,backButton,addButton;
	private TextArea ta;
	private Frame parent;
	public static Aremove2 aremove2=new Aremove2();
	public static Acadd acadd=new Acadd();
	public Acremove(){
		super("Accountant Information Window");
        addButton=new Button("Add");
		removeButton=new Button("Remove");
		backButton=new Button("Back");
		ta=new TextArea(10,50);
		ta.setEditable(false);
		add(ta);add(addButton);
		add(removeButton);add(backButton);
		backButton.addActionListener(this);
		removeButton.addActionListener(this);
		addButton.addActionListener(this);
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
    public void loadData()
	{
		String sql="select * from accountants";
		try
		{
			
			da=da.getInstance();
			ResultSet rs=da.getData(sql);
			String data="Accountants\n\nID\tName\t\t\tJoining Year\n";
			while(rs.next())
			{
				data=data+rs.getString("aID")+"\t";
				data=data+rs.getString("Name")+"\t\t";
				data=data+rs.getString("Joining Year")+"\n";
			
			}
			ta.setText(data);
		}
		catch(Exception ex)
		{
			System.out.println("Exception in View");
		}
	}
    
    public void setParent(Frame f){
		parent=f;
	}
	public void actionPerformed(ActionEvent e){
		if(e.getSource()==removeButton){
			this.setVisible(false);
			aremove2.setVisible(true);
			aremove2.setParent(this);
			aremove2.loadData();
		}
		else if(e.getSource()==addButton)
		{
			this.setVisible(false);
			acadd.setParent(this);
			acadd.setVisible(true);
			acadd.loadData();
		}
		else if(e.getSource()==backButton)
		{
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