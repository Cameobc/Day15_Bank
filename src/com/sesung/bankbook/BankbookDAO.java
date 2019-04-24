package com.sesung.bankbook;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.sesung.util.DBConnector;

public class BankbookDAO {
	
	public ArrayList<BankbookDTO> selectListBook(String acc) throws Exception {
		ArrayList<BankbookDTO> ar = new ArrayList<BankbookDTO>();
		Connection con = DBConnector.connector();
		String sql = "SELECT * FROM BANKBOOK WHERE ACCOUNT=?";
		PreparedStatement st = con.prepareStatement(sql);
		st.setString(1, acc);
		ResultSet rs = st.executeQuery();
		while(rs.next()) {
			BankbookDTO bdto = new BankbookDTO();
			bdto.setAccount(rs.getString("ACCOUNT"));
			bdto.setbNum(rs.getInt("BNUM"));
			bdto.setB_Action(rs.getInt("B_ACTION"));
			bdto.setB_Day(rs.getString("B_DAY"));
			bdto.setStandard(rs.getInt("STANDARD"));
			ar.add(bdto);
		}
		DBConnector.disconnector(con, st, rs);
		return ar;
	}
	
	public ArrayList<BankbookDTO> selectMonth(String acc, String month) throws Exception {
		Connection con = DBConnector.connector();
		String sql = "SELECT * FROM BANKBOOK WHERE ACCOUNT=? and B_DAY LIKE ?";
		PreparedStatement st = con.prepareStatement(sql);
		st.setString(1, acc);
		st.setString(2, "___"+month+"___");
		ResultSet rs = st.executeQuery();
		ArrayList<BankbookDTO> ar = new ArrayList<BankbookDTO>();
		while(rs.next()) {
			BankbookDTO bdto = new BankbookDTO();
			bdto.setAccount(rs.getString("ACCOUNT"));
			bdto.setbNum(rs.getInt("BNUM"));
			bdto.setB_Action(rs.getInt("B_ACTION"));
			bdto.setB_Day(rs.getString("B_DAY"));
			bdto.setStandard(rs.getInt("STANDARD"));
			ar.add(bdto);
		}
		DBConnector.disconnector(con, st, rs);
		return ar;
	}
	
	public int insertBook(Connection con, BankbookDTO kdto) throws Exception {
		int result =0;
		String sql = "INSERT INTO BANKBOOK VALUES (?, BANKBOOK_SEQ.NEXTVAL, ?, ?, ?)";
		PreparedStatement st = con.prepareStatement(sql);
		st.setString(1, kdto.getAccount());
		st.setInt(2, kdto.getB_Action());
		st.setString(3, kdto.getB_Day());
		st.setInt(4, kdto.getStandard());
		result = st.executeUpdate();
		st.close();
		return result;
	}

}
