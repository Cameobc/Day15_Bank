package com.sesung.view;

import java.util.ArrayList;

import com.sesung.bank.BankDAO;
import com.sesung.bank.BankDTO;
import com.sesung.bankbook.BankbookDTO;
import com.sesung.member.MemberDTO;

public class BankView {

	public void memberView(MemberDTO dto, ArrayList<BankDTO> arb) {
		//name, id, account, money
		System.out.println("이     름 : "+dto.getName());
		System.out.println("아이디   : "+dto.getId());
		for(BankDTO bdto : arb) {
		System.out.println("계좌번호 : "+bdto.getAccount());
		System.out.println("잔     액 : "+bdto.getMoney());
		}
	}


	public void depositView(ArrayList<BankbookDTO> ark, BankDTO bdto) {
		for(BankbookDTO kdto : ark) {
			if(kdto.getStandard()==0) {
				System.out.println("거래날짜 : "+kdto.getB_Day());
				System.out.println("입금내역 : "+kdto.getB_Action());
			}
		}
		if(ark.size()>0) {
			System.out.println("잔   고 : " + bdto.getMoney());
		}else {
			System.out.println("거래내역 없음");
		}
	}

	public void withdrawView(ArrayList<BankbookDTO> ark, BankDTO bdto) {
		for(BankbookDTO kdto : ark) {
			if(kdto.getStandard()==1) {
				System.out.println("거래날짜 : "+kdto.getB_Day());
				System.out.println("출금내역 : "+kdto.getB_Action());
			}
		}
		if(ark.size()>0) {
			System.out.println("잔   고 : " + bdto.getMoney());
		}else {
			System.out.println("거래내역 없음");
		}
	}

	public void view(ArrayList<BankbookDTO> ark, BankDTO bdto) {
		for(BankbookDTO kdto : ark) {
			System.out.println("거래날짜 : "+kdto.getB_Day());
			if(kdto.getStandard()==0) {
				System.out.println("입금내역 : "+kdto.getB_Action());
			}else {
				System.out.println("출금내역 : "+kdto.getB_Action());
			}
		}
		if(ark.size()>0) {
			System.out.println("잔   고 : " + bdto.getMoney());
		}else {
			System.out.println("거래내역 없음");
		}
	}


}
