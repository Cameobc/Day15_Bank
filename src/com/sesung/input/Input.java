package com.sesung.input;

import java.util.Calendar;
import java.util.Scanner;

import com.sesung.bank.BankDTO;
import com.sesung.bankbook.BankbookDTO;
import com.sesung.member.MemberDTO;

public class Input {
	private Scanner sc;

	public Input() {
		sc= new Scanner(System.in);
	}
	
	public String inputId() {
		System.out.println("조회할 아이디를 입력해주세요.");
		String id = sc.next();
		return id;
	}
	
	public String inputAccount() {
		System.out.println("계좌번호를 입력해주세요.");
		String account = sc.next();
		return account;
	}
	
	public String inputMonth() {
		System.out.println("조회할 기간을 입력해주세요.");
		String month = sc.next();
		return month;
	}
	
	public BankbookDTO bookInput(String str) {
		BankbookDTO kdto = new BankbookDTO();
		System.out.println("계좌번호를 입력하세요.");
		kdto.setAccount(sc.next());
		System.out.println(str+"금액을 입력하세요.");
		kdto.setB_Action(sc.nextInt());
		System.out.println("날짜를 입력하세요.");
		kdto.setB_Day(sc.next());
		if(str.equals("출금")) {
			kdto.setStandard(1);
		}else {
			kdto.setStandard(0);
		}
		return kdto;
	}
	
	public BankDTO bankInput() {
		BankDTO bdto = new BankDTO();
		System.out.println("계좌번호를 입력하세요.");
		bdto.setAccount(sc.next());
		System.out.println("아이디를 입력하세요.");
		bdto.setId(sc.next());
		System.out.println("개설일 입력하세요.");
		bdto.setO_Day(sc.next());
		System.out.println("통장종류를 입력하세요.");
		bdto.setbName(sc.next());
		System.out.println("잔고를 입력하세요.");
		bdto.setMoney(sc.nextInt());
		return bdto;
	}

	public MemberDTO memberInput() {
		MemberDTO dto = new MemberDTO();
		System.out.println("아이디를 입력하세요");
		dto.setId(sc.next());
		System.out.println("패스워드를 입력하세요");
		dto.setPw(sc.next());
		System.out.println("이름을 입력하세요");
		dto.setName(sc.next());
		System.out.println("핸드폰번호를 입력하세요");
		dto.setPhone(sc.next());
		System.out.println("이메일을 입력하세요");
		dto.setEmail(sc.next());
		return dto;
	}
	
	
}
