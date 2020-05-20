package com.practice.practice.controller;

import com.practice.practice.dao.*;
import com.practice.practice.model.*;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/account")
public class Control {
	@Autowired
	AccountDao dao;
	
	@PostMapping("")//To store account data- Path: localhost:8080/account
	public ResponseEntity<String> entry(Account a) {
		
		int id = a.getId();
		System.out.println(id);
		Account s = dao.findById(id).orElse(new Account());//To check for already existing account
		if(s.getName() == null) {
			if(a.check()) {
				dao.save(a);
				return new ResponseEntity<String>("ID: " + a.getId() + " generated!", HttpStatus.CREATED);
			}
			else {
				return new ResponseEntity<String>("Enter all details!", HttpStatus.NOT_ACCEPTABLE);
			}
		}
		else {
			return new ResponseEntity<String>("Already user exists!", HttpStatus.CONFLICT);
		}
	}
	
	@DeleteMapping("/{id}")//To delete account details- Path: localhost:8080/account/{id}
	public ResponseEntity<String> delet(@PathVariable("id") int id) {
		try {
			dao.deleteById(id);;
			return new ResponseEntity<String>("Deleted!", HttpStatus.NO_CONTENT);
		}
		catch(Exception e) {
			return new ResponseEntity<String>("No entry with id " + id + " exists!", HttpStatus.NOT_FOUND);
		}
	}
	
	@PutMapping("/{id}")//To update account data- Path: localhost:8080/account/{id}
	public ResponseEntity<String> updat(@PathVariable("id") int id,Account a) {
		a.setId(id);
		dao.save(a);
		return new ResponseEntity<String>("Updated!", HttpStatus.OK);
		
	}
	
	@GetMapping("/{id}")//To get account data- Path: localhost:8080/account/{id}
	public ResponseEntity<Account> show(@PathVariable("id") int id) {
		Account a = dao.findById(id).orElse(new Account());
		return new ResponseEntity<Account>(a, HttpStatus.OK);
	}
	
	@GetMapping("")//To get all accounts data- Path: localhost:8080/account
	public ResponseEntity<List<Account>> show(){
		return new ResponseEntity<List<Account>>(dao.findAll(), HttpStatus.OK);
	}
	
	@GetMapping("/city/{city}")//To get account data citywise- Path: localhost:8080/account/city/{cityname}
	public ResponseEntity<List<Account>> showByCity(@PathVariable String city){
		return new ResponseEntity<List<Account>>(dao.findByCity(city), HttpStatus.OK);
	}
	
	@RequestMapping(value="/balUpdate/{id}", method=RequestMethod.PUT)//To update account balance- Path: localhost:8080/account/balUpdate/{id}
	public ResponseEntity<String> update(@PathVariable("id") int id, double balance) {
		dao.updateBalance(id, balance);
		return new ResponseEntity<String>("Updated!", HttpStatus.OK);
	}
}
