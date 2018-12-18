package com.marialinda.twitter.controller;


import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.marialinda.twitter.model.Pessoa;
import com.marialinda.twitter.repository.PessoaRepository;




@RestController
public class PessoaController {
	
	@Autowired
	private PessoaRepository pessoa;
	
	@GetMapping("/pessoa")
	public Page<Pessoa> retornePessoas (Pageable pageable) {
		
		return pessoa.findAll(pageable);
	}
	
	@GetMapping("/pessoa/{id}")
	public Pessoa retornarPessoaPeloId(@PathVariable Integer id) {
		return pessoa.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("usuario nao encontrado: " + id));
	}
	@PostMapping("/pessoa")
	public Pessoa salvarPessoa(@Valid @RequestBody Pessoa pessoa) {
		
		return pessoa.save(pessoa);
	}

}
