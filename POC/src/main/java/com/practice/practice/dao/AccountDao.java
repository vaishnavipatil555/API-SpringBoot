package com.practice.practice.dao;

import com.practice.practice.model.*;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "account", path = "account")
public interface AccountDao extends JpaRepository<Account, Integer> {
	List<Account> findByCity(String city);
	
	@Transactional
	@Modifying
	@Query("update Account a set a.balance = a.balance+ :balance where a.id= :id")
	int updateBalance(@Param("id") Integer id, @Param("balance") Double balance);
}
