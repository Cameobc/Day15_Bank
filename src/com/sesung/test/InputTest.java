package com.sesung.test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.sesung.bankbook.BankbookDTO;
import com.sesung.input.Input;

public class InputTest {

	@Test
	public void test() {
		Input ip = new Input();
		BankbookDTO kdto = new BankbookDTO();
		kdto = ip.bookInput("입금");
	}

}
