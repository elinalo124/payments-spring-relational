package com.payments.relational;

import com.payments.relational.entity.Bank;
import com.payments.relational.repository.BankRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class  RelationalApplication implements CommandLineRunner {
	public static void main(String[] args) {
		SpringApplication.run(RelationalApplication.class, args);
	}

	@Autowired
	BankRepository bankRepository;

	@Override
	public void run(String... args) throws Exception {
		Bank bancoAgrario = new Bank();
		bancoAgrario.setName("BancoAgrario");
		bancoAgrario.setCuit("11243355");
		bancoAgrario.setAddress("9 de julio 456");
		bancoAgrario.setTelephone("310776543");
		bankRepository.save(bancoAgrario);
	}
}
