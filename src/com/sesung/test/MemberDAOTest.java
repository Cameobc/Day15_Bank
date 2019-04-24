package com.sesung.test;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.Test;

import com.sesung.bank.BankDAO;
import com.sesung.bank.BankDTO;
import com.sesung.bankbook.BankbookDAO;
import com.sesung.bankbook.BankbookDTO;
import com.sesung.member.MemberDAO;
import com.sesung.member.MemberDTO;
import com.sesung.util.DBConnector;

public class MemberDAOTest {
	
//	@Test
//	public void testBookv() throws Exception {
//		BankDAO dao = new BankDAO();
//		ArrayList<BankDTO> ar = new ArrayList<BankDTO>();
//		ar = dao.selectListBank();
//		assertNotNull(ar);
//	}
	@Test
	public void testBook() {
		BankbookDAO kdao = new BankbookDAO();
		BankbookDTO kdto = new BankbookDTO();
		kdto.setAccount("1234-56788-001");
		kdto.setB_Action(200);
		kdto.setB_Day("2019-04-23");
		kdto.setStandard(1);
		Connection con = null;
		int result =0;
		try {
			con = DBConnector.connector();
			con.setAutoCommit(false);
			result = kdao.insertBook(con, kdto);
			if(result<1) {
				throw new Exception();
			}
			BankDAO bdao = new BankDAO();
			BankDTO bdto = new BankDTO();
			bdto.setMoney(kdto.getB_Action());
			bdto.setAccount(kdto.getAccount());
			result = bdao.updateWithdraw(con, kdto);
			
			if(result<1) {
				throw new Exception();
			}
			con.commit();
		}catch(Exception e) {
			try {
				con.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		}finally {
			try {
				con.close();
				con.setAutoCommit(true);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			

		}
		assertNotEquals(0, result);
	}

	//	@Test
	//	public void testBank() throws Exception {
	//		BankDAO dao = new BankDAO();
	//		BankDTO dto = new BankDTO();
	//		dto.setAccount("1234-56788-001");
	//		dto.setId("iu");
	//		dto.setO_Day("2019-03-23");
	//		dto.setbName("청약");
	//		dto.setMoney(100);
	//		int result = dao.insertBank(dto);
	//		assertNotEquals(0, result);
	//	}

	//	@Test
	//	public void test() throws Exception {
	//		MemberDAO dao = new MemberDAO();
	//		MemberDTO dto = new MemberDTO();
	//		dto.setId("iu");
	//		dto.setPw("iu");
	//		dto.setName("uu");
	//		dto.setPhone("010-1234-5678");
	//		dto.setEmail("ab@ab.bc");
	//		int result = dao.insertMember(dto);
	//		assertNotEquals(0, result);
	//		
	//	}

}
