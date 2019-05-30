package com.app.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.app.domain.BankAccount;
import com.app.repository.BankRepository;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@CrossOrigin
@RestController
//@EnableSwagger2
@RequestMapping("/api")
public class BankAccountController {
	
	@Autowired
	private BankRepository bankRepository;
	
	@GetMapping("/account")
	public List<BankAccount> getAllAccounts() {
		return bankRepository.findAll();
	}
	
	@GetMapping("/account/{id}")
	public BankAccount getStudent(@PathVariable long id) {
		Optional<BankAccount> account = bankRepository.findById(id);

		return account.get();
	}
	
	@DeleteMapping("/account/{id}")
	public void deleteStudent(@PathVariable long id) {
		bankRepository.deleteById(id);
	}
	
	@PostMapping("/account")
	public ResponseEntity<Object> createStudent(@RequestBody BankAccount account) {
		BankAccount createAccount = bankRepository.save(account);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(createAccount.getId()).toUri();

		return ResponseEntity.created(location).build();

	}
	
	@PutMapping("/account/{id}")
	public ResponseEntity<Object> updateStudent(@RequestBody BankAccount account, @PathVariable long id) {

		Optional<BankAccount> bAcc = bankRepository.findById(id);

		if (!bAcc.isPresent())
			return ResponseEntity.notFound().build();

		account.setId(id);
		
		bankRepository.save(account);

		return ResponseEntity.noContent().build();
	}

}
