package com.sesung.controller;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import com.sesung.bank.BankDAO;
import com.sesung.bank.BankDTO;
import com.sesung.bankbook.BankbookDAO;
import com.sesung.bankbook.BankbookDTO;
import com.sesung.input.Input;
import com.sesung.member.MemberDAO;
import com.sesung.member.MemberDTO;
import com.sesung.util.DBConnector;
import com.sesung.view.BankView;

public class BankController {
	private Scanner sc;
	private MemberDAO dao;
	private BankDAO bdao;
	private BankbookDAO kdao;
	private Input ip;
	private BankView bv;

	public BankController() {
		sc= new Scanner(System.in);
		dao = new MemberDAO();
		bdao = new BankDAO();
		kdao = new BankbookDAO();
		ip = new Input();
		bv = new BankView();
	}

	public void start() throws Exception {
		boolean check=true;
		int select =0;
		BankbookDTO kdto = new BankbookDTO();
		BankDTO bdto = new BankDTO();
		MemberDTO dto = new MemberDTO();
		ArrayList<MemberDTO> ar = new ArrayList<MemberDTO>();
		ArrayList<BankbookDTO> ark = new ArrayList<BankbookDTO>();
		ArrayList<BankDTO> arb = new ArrayList<BankDTO>();
		while(check) {
			System.out.println("1.회원가입");
			System.out.println("2.통장개설");
			System.out.println("3.통장조회");
			System.out.println("4.회원조회");
			System.out.println("5.입금하기");
			System.out.println("6.출금하기");
			System.out.println("7.종     료");
			select = sc.nextInt();
			switch(select) {
			case 1:
				dto=ip.memberInput();
				ar.add(dto);
				int result;
				result = dao.insertMember(dto);
				break;
			case 2:
				bdto=ip.bankInput();
				arb.add(bdto);
				result =bdao.insertBank(bdto);
				break;
			case 3:
				System.out.println("3.통장조회");
				while(check) {
					System.out.println("1.전체조회");
					System.out.println("2.기간조회");
					System.out.println("3.입금내역");
					System.out.println("4.출금내역");
					System.out.println("5.종     료");
					select = sc.nextInt();
					switch(select) {
					case 1: 
						String account = ip.inputAccount();
						ark=kdao.selectListBook(account);
						bdto=bdao.selectOneBank(account);
						bv.view(ark, bdto);
						break;
					case 2:
						account = ip.inputAccount();
						String month = ip.inputMonth();
						bdto=bdao.selectOneBank(account);
						ark = kdao.selectMonth(account, month);
						bv.view(ark, bdto);
						break;
					case 3:
						account = ip.inputAccount();
						ark=kdao.selectListBook(account);
						bdto=bdao.selectOneBank(account);
						bv.depositView(ark, bdto);
						break;
					case 4:
						account = ip.inputAccount();
						ark=kdao.selectListBook(account);
						bdto=bdao.selectOneBank(account);
						bv.withdrawView(ark, bdto);
						break;
					case 5:
						System.out.println("프로그램을 종료합니다.");
						check=!check;
						break;
					default:
						System.out.println("잘못 누르셨습니다.");
					}
				}
				break;
			case 4:
				String id = ip.inputId();
				dto=dao.selectOne(id);
				if(dto!=null) {
				arb=bdao.selectIdBank(id);
				bv.memberView(dto, arb);
				}else {
					System.out.println("없는 아이디입니다.");
				}
				break;
			case 5:
				String str= "입금";
				kdto=ip.bookInput(str);
				ark.add(kdto);
				Connection con = null;
				result = 0;
				try{
					con = DBConnector.connector();
					con.setAutoCommit(false);
					result = kdao.insertBook(con, kdto);
					if(result<1) {
						throw new Exception();
					}
					result = bdao.updateDeposit(con, kdto);
					if(result<1) {
						throw  new Exception();
					}
					con.commit();
				}catch(Exception e) {
					try {
						con.rollback();
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}finally {
					try {
						con.setAutoCommit(true);
						con.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
				break;
			case 6:
				str = "출금";
				kdto=ip.bookInput(str);
				ark.add(kdto);
				con = null;
				result = 0;
				try{
					con = DBConnector.connector();
					con.setAutoCommit(false);
					result = kdao.insertBook(con, kdto);
					if(result<1) {
						throw new Exception();
					}
					result = bdao.updateWithdraw(con, kdto);
					if(result<1) {
						throw  new Exception();
					}
					con.commit();
				}catch(Exception e) {
					e.printStackTrace();
					try {
						con.rollback();
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}finally {
					try {
						con.setAutoCommit(true);
						con.close();

					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
				break;
			case 7:
				System.out.println("프로그램을 종료합니다.");
				check=!check;
				break;
			default:
				System.out.println("잘못 누르셨습니다.");
			}
		}
	}
}
