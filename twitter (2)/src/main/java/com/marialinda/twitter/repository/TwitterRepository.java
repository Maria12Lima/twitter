package com.marialinda.twitter.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.marialinda.twitter.model.Twitter;



public interface TwitterRepository extends JpaRepository<Twitter, Integer>{

}
