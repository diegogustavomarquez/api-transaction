package com.mendel.challenge.transaction;

import com.mendel.challenge.transaction.controller.TransactionController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class ApiTransactionApplicationTests {

	@Autowired
	private TransactionController TransactionController;

	@Test
	void contextLoads() {
		assertThat(TransactionController).isNotNull();
	}

}