package com.mendel.challenge.transaction;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@OpenAPIDefinition(info = @Info(title = "Transaction API", version = "1.0", description = "Transactions manage"))
class ApiTransactionApplicationTests {

	@Test
	void contextLoads() {
	}

}
