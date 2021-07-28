import javax.swing.JOptionPane;
import java.awt.*;

import java.awt.event.*;

import java.sql.*;
import javax.swing.*;

public class SLogin extends JFrame implements ActionListener
{
	public DataAccess da;
	private TextField sID;
	private Frame parent;
	private JPasswordField pass;
	private JButton loginButton,cancelButton;
	private Label l,l2;
	public SLogin()
	{
		super("Student Login Window");
		l=new Label("Student ID :");
		sID=new TextField(10);
		l2=new Label("Password :");
		pass=new JPasswordField(6);
		loginButton=new JButton("Login");
		cancelButton=new JButton("Cancel");
		
		add(l);add(sID); add(l2); add(pass);
		add(loginButton);add(cancelButton);
		
		
		loginButton.addActionListener(this);
		cancelButton.addActionListener(this);
		setLayout(null);
		
		l.setBounds(20,50,80,30);
		sID.setBounds(100,50,200,30);
		l2.setBounds(20,100,80,30);
		pass.setBounds(100,100,200,30);
		loginButton.setBounds(100,150,90,25);
		cancelButton.setBounds(210,150,90,25);
		
		setSize(400,500);
	}
	public void setParent(Frame f){
		parent=f;
	}
		private boolean checkAuth(){
		boolean flag=false;
		String n=sID.getText();
		String p=pass.getText();
		String sql="select * from student where sID='"+n+"' and Password='"+p+"'";
		System.out.println(sql);
		try{
		 
			da=da.getInstance();
			ResultSet rs=da.getData(sql);
			while(rs.next()){
					flag=true;
			}
		}
		catch(Exception ex){
			System.out.println("Exception occurred");
		}
		return flag;
	}
			
	public void actionPerformed(ActionEvent e){
		String sig=e.getActionCommand();
		if(sig.equals("Login")){
			if(checkAuth()){
				System.out.println("Success");
				Navigate.view.setVisible(true);
				Navigate.view.setParent(this);
				Navigate.view.loadData(sID.getText());
				this.setVisible(false);
			}
			else{
				System.out.println("Incorrect");
			}
		}
		else if(sig.equals("Cancel")){
			parent.setVisible(true);
			this.setVisible(false);
		}
	}
		
			
					
}	

