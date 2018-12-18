package com.marialinda.twitter.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;




@Entity
public class Pessoa {
	@Id
	@GeneratedValue
	private Integer id;
	@Column
	@NotBlank
	private String nome;
	@Column
	private String telefone;
	@Column(unique = true)
	@NotBlank
	private String email;
	@Column
	private String senha;
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Twitter> twitters = new ArrayList<>();
	private List<com.marialinda.twitter.model.Twitter> twitter;
	public void adicionarTwitter(Twitter twitter) {
		this.twitter.add(twitter);
	}
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
	public List<Twitter> getTwitter() {
		return getTwitter();
	}

	public void setTweets(List<Twitter> twitter) {
		this.twitter = twitter;
	}



}
