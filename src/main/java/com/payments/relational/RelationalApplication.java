package com.payments.relational;

import com.payments.relational.entity.Bank;
import com.payments.relational.entity.Customer;
import com.payments.relational.repository.BankRepository;
import com.payments.relational.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;

@SpringBootApplication
public class  RelationalApplication implements CommandLineRunner {
	public static void main(String[] args) {
		SpringApplication.run(RelationalApplication.class, args);
	}

	@Autowired
	BankRepository bankRepository;

	@Autowired
	CustomerRepository customerRepository;

	@Override
	public void run(String... args) throws Exception {
		Bank bank1 = new Bank("BancoAgrario", "11243355", "9 de julio 456", "310776543");
		Customer customer1 = new Customer("Juan Perez", "34567890", "20345678901", "Calle Falsa 123", "123456789", LocalDate.parse("2023-01-01"));
		bankRepository.save(bank1);
		customerRepository.save(customer1);
		bank1.getMembers().add(customer1);
		bankRepository.save(bank1);
	}
}
