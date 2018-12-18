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

import com.marialinda.twitter.exception.ResourceNotFoundException;
import com.marialinda.twitter.model.Pessoa;
import com.marialinda.twitter.model.Twitter;
import com.marialinda.twitter.repository.PessoaRepository;


@RestController
public class PessoaController {
	
	@Autowired
	private PessoaRepository pessoaA;
	
	@GetMapping("/pessoa")
	public Page<Pessoa> retornePessoas (Pageable pageable) {
		
		return pessoaA.findAll(pageable);
	}
	
	@GetMapping("/pessoa/{id}")
	public Pessoa retornarPessoaPeloId(@PathVariable Integer id) {
		return pessoaA.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("usuario nao encontrado: " + id));
	}
	@PostMapping("/pessoa")
	public Pessoa salvarPessoa(@Valid @RequestBody Pessoa pessoa) {
		
		return pessoaA.save(pessoa);
	}
	@PostMapping("/pessoa/{id}/adicionarTwitter")
	public Pessoa adicionarTwitter(@PathVariable Integer id, @Valid @RequestBody Twitter twitter) {
		
		return pessoaA.findById(id).map(pessoa -> {
			pessoa.adicionarTwitter(twitter);
			
			return pessoaA.save(pessoa);
		}).orElseThrow(() -> new ResourceNotFoundException("Tweet nao encontrado: " +id));
	}
	
	
	@PostMapping("/login")
	public Pessoa login(@RequestBody Map<String, String> params) {
		return pessoaA.findByEmailAndSenha(params.get("email"), params.get("senha"));
	}
	
	@PutMapping("/pessoa/{id}")
	public Pessoa atualizarPessoa(@PathVariable Integer id, @Valid @RequestBody Pessoa pessoaRequest) {
		return pessoaA.findById(id).map(pessoa -> {
			pessoa.setNome(pessoaRequest.getNome());
			pessoa.setTelefone(pessoaRequest.getTelefone());
			pessoa.setEmail(pessoaRequest.getEmail());
			pessoa.setSenha(pessoaRequest.getSenha());
			
			return pessoaA.save(pessoa);
		}).orElseThrow(() -> new ResourceNotFoundException("usuario nao encontrado: " + id));
	}
	
	@DeleteMapping("/pessoa/{id}")
	public ResponseEntity<?> deletarPessoa(@PathVariable Integer id){
		return pessoaA.findById(id).map(pessoa -> {
			pessoaA.delete(pessoa);
			
			return ResponseEntity.ok().build();
		}).orElseThrow(() -> new ResourceNotFoundException("usuario nao encontrado: " + id));
	}

}
