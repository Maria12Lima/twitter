package com.marialinda.twitter.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.marialinda.twitter.model.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Integer>{
	public Pessoa findByEmailAndSenha(String email, String senha);

}
