package com.mendel.challenge.transaction;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Transaction API", version = "1.0", description = "Transactions manage"))
public class ApiTransactionApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiTransactionApplication.class, args);
	}

}
