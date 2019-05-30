package com.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.domain.BankAccount;

@Repository
public interface BankRepository extends JpaRepository<BankAccount, Long> {

}
