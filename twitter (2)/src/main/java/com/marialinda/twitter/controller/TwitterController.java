package com.marialinda.twitter.controller;

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
import com.marialinda.twitter.model.Twitter;
import com.marialinda.twitter.repository.TwitterRepository;

@RestController
public class TwitterController {
	@Autowired
	private TwitterRepository twitterR;
	
	@GetMapping("/twitter")
	public Page<Twitter> retornarTweets(Pageable pageable){
		return twitterR.findAll(pageable);
	}
	
	@PostMapping("/twitter")
	public Twitter salvarTweet(@Valid @RequestBody Twitter twitter) {
		return twitterR.save(twitter);
	}
	
	@PutMapping("/twitter/{id}")
	public Twitter atualizarTweet(@PathVariable Integer id, @Valid @RequestBody Twitter twitterRequest) {
		return twitterR.findById(id).map(tweet -> {
			tweet.setDescricao(twitterRequest.getDescricao());
			
			return twitterR.save(tweet);
		}).orElseThrow(() -> new ResourceNotFoundException("tweet nao encontrado: " + id));
	}
	
	@DeleteMapping("/twitter/{id}")
	public ResponseEntity<?> deletarTweet(@PathVariable Integer id){
		return twitterR.findById(id)
				.map(twitter -> {
					twitterR.delete(twitter);
					
					return ResponseEntity.ok().build();
				}).orElseThrow(() -> new ResourceNotFoundException("tweet nao encontrado: " + id));
	}

}
