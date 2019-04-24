package com.sesung.bank;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.sesung.bankbook.BankbookDTO;
import com.sesung.util.DBConnector;

public class BankDAO {

	public ArrayList<BankDTO> selectListBank() throws Exception {
		ArrayList<BankDTO> ar = new ArrayList<BankDTO>();
		Connection con = DBConnector.connector();
		String sql = "SELECT * FROM BANK";
		PreparedStatement st = con.prepareStatement(sql);
		ResultSet rs = st.executeQuery();
		while(rs.next()) {
			BankDTO bdto = new BankDTO();
			bdto.setAccount(rs.getString("ACCOUNT"));
			bdto.setId(rs.getString("ID"));
			bdto.setO_Day(rs.getString("O_DAY"));
			bdto.setbName(rs.getString("BNAME"));
			bdto.setMoney(rs.getInt("MONEY"));
			ar.add(bdto);
		}
		DBConnector.disconnector(con, st, rs);
		return ar;
	}
	
	public ArrayList<BankDTO> selectIdBank(String acc) throws Exception {
		ArrayList<BankDTO> ar = new ArrayList<BankDTO>();
		Connection con = DBConnector.connector();
		String sql = "SELECT * FROM BANK WHERE ID=?";
		PreparedStatement st = con.prepareStatement(sql);
		st.setString(1, acc);
		ResultSet rs = st.executeQuery();
		while(rs.next()) {
			BankDTO bdto = new BankDTO();
			bdto.setAccount(rs.getString("ACCOUNT"));
			bdto.setId(rs.getString("ID"));
			bdto.setO_Day(rs.getString("O_DAY"));
			bdto.setbName(rs.getString("BNAME"));
			bdto.setMoney(rs.getInt("MONEY"));
			ar.add(bdto);
		}
		DBConnector.disconnector(con, st, rs);
		return ar;
	}

	public BankDTO selectOneBank(String acc) throws Exception {
		BankDTO bdto=null;
		Connection con = DBConnector.connector();
		String sql = "SELECT * FROM BANK WHERE ACCOUNT=?";
		PreparedStatement st = con.prepareStatement(sql);
		st.setString(1, acc);
		ResultSet rs = st.executeQuery();
		if(rs.next()) {
			bdto= new BankDTO();
			bdto.setAccount(rs.getString("ACCOUNT"));
			bdto.setId(rs.getString("ID"));
			bdto.setO_Day(rs.getString("O_DAY"));
			bdto.setbName(rs.getString("BNAME"));
			bdto.setMoney(rs.getInt("MONEY"));
		}
		DBConnector.disconnector(con, st, rs);
		return bdto;
	}

	public int updateWithdraw(Connection con, BankbookDTO kdto) throws Exception {
		int result =0;
		String sql ="UPDATE BANK SET MONEY=MONEY-? WHERE ACCOUNT=?";
		PreparedStatement st = con.prepareStatement(sql);
		st.setInt(1, kdto.getB_Action());
		st.setString(2, kdto.getAccount());
		result = st.executeUpdate();
		st.close();
		return result;
	}

	public int updateDeposit(Connection con, BankbookDTO kdto) throws Exception {
		String sql ="UPDATE BANK SET MONEY=MONEY+? WHERE ACCOUNT=?";
		PreparedStatement st = con.prepareStatement(sql);
		st.setInt(1, kdto.getB_Action());
		st.setString(2, kdto.getAccount());
		int result = st.executeUpdate();
		st.close();
		return result;
	}

	public int insertBank(BankDTO bdto) throws Exception {
		Connection con = DBConnector.connector();
		String sql ="INSERT INTO BANK VALUES (?, ?, ?, ?, ?)";
		PreparedStatement st = con.prepareStatement(sql);
		st.setString(1, bdto.getAccount());
		st.setString(2, bdto.getId());
		st.setString(3, bdto.getO_Day());
		st.setString(4, bdto.getbName());
		st.setInt(5, bdto.getMoney());
		int result = st.executeUpdate();
		DBConnector.disconnector(con, st);
		return result;
	}

}
