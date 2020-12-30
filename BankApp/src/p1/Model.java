package p1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Model 
{
	private String name;
	private String accno;
	private String balance;
	private String custid;
	private String pw;
	private String npwd;
	private String amt;
	private String saccno;
	public String getAmt() {
		return amt;
	}
	public void setAmt(String amt) {
		this.amt = amt;
	}
	public String getSaccno() {
		return saccno;
	}
	public void setSaccno(String saccno) {
		this.saccno = saccno;
	}
	public String getNpwd() {
		return npwd;
	}
	public void setNpwd(String npwd) {
		this.npwd = npwd;
	}

	private String email;
	private Connection con;
	private PreparedStatement pstmt;
	private ResultSet res;
	ArrayList al = new ArrayList();
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAccno() {
		return accno;
	}
	public void setAccno(String accno) {
		this.accno = accno;
	}
	public String getBalance() {
		return balance;
	}
	public void setBalance(String balance) {
		this.balance = balance;
	}
	public String getCustid() {
		return custid;
	}
	public void setCustid(String custid) {
		this.custid = custid;
	}
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	Model()
	{
		try
		{
			Class.forName("oracle.jdbc.OracleDriver");
			con = DriverManager.getConnection("jdbc:oracle:thin:@//localhost:1521/XE", "system", "system");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	public boolean login()
	{
		try
		{
			pstmt = con.prepareStatement("SELECT * FROM BANK WHERE CUSTID = ? AND PW = ?");
			pstmt.setString(1, custid);
			pstmt.setString(2, pw);
			res = pstmt.executeQuery();
			if(res.next()==true)
			{
				accno = res.getString("ACCNO");
				return true;
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean checkBalance()
	{
		try
		{
			pstmt = con.prepareStatement("SELECT * FROM BANK WHERE ACCNO=?");
			pstmt.setString(1, accno);
			res = pstmt.executeQuery();
			if(res.next()==true)
			{
				balance = res.getString("BALANCE");
				return true;
			}
			
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return false;
		
	}
	
	public boolean applyLoan()
	{
		try 
		{
			pstmt = con.prepareStatement("SELECT * FROM BANK WHERE ACCNO=?");
			pstmt.setString(1, accno);
			res = pstmt.executeQuery();
			if(res.next()==true)
			{
				name = res.getString("NAME");
				email = res.getString("EMAIL");
				return true;
			}
		}
		catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	public boolean changePwd()
	{
		try 
		{
			pstmt =con.prepareStatement("UPDATE BANK SET PW=? WHERE ACCNO=?");
			pstmt.setString(1, npwd);
			pstmt.setString(2, accno);
			int row = pstmt.executeUpdate();
			if(row==1)
			{
				return true;
			}
			
		} 
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return false;
	}

	public boolean transfer()
	{
		try 
		{
			pstmt =con.prepareStatement("SELECT  * FROM BANK WHERE ACCNO=?");
			pstmt.setString(1,saccno);
			res = pstmt.executeQuery();
			if(res.next()==true)
			{
				pstmt =con.prepareStatement("UPDATE BANK SET BALANCE= BALANCE-? WHERE ACCNO=?");
				pstmt.setString(1, amt);
				pstmt.setString(2, accno);
				int x = pstmt.executeUpdate();
				if(x==1)
				{
					pstmt= con.prepareStatement("UPDATE BANK SET BALANCE = BALANCE + ? WHERE ACCNO =?");
					pstmt.setString(1, amt);
					pstmt.setString(2, saccno);
					int temp = pstmt.executeUpdate();
					if(temp==1)
					{
						pstmt=con.prepareStatement("INSERT INTO STATEMENT VALUES(?,?)");
						pstmt.setString(1, accno);
						pstmt.setString(2, amt);
						pstmt.executeUpdate();
						
						return true;
					}
					
				}
			}
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return false;
	}

	public ArrayList getStatement()
	{
		try
		{
			pstmt= con.prepareStatement("SELECT * FROM STATEMENT WHERE ACCNO=?");
			pstmt.setString(1, accno);
			res = pstmt.executeQuery();
			while(res.next()==true)
			{
				String amount  = res.getString("AMT");
				
				al.add(amount);
			}
			return al;
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return al;
	}
	
	public boolean forgotPwd()
	{
		try {
			pstmt= con.prepareStatement("UPDATE BANK SET PW = ? WHERE EMAIL= ?");

			pstmt.setString(1, npwd);
			pstmt.setString(2, email);
			int x = pstmt.executeUpdate();
			
			if(x==1)
				return true;
		} 
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
}









