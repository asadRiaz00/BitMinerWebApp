package com.starter.bitminer;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.Random;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@SpringBootApplication
@Controller
public class BitMinerWebAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(BitMinerWebAppApplication.class, args);
		Thread worker = new Thread (new Worker());
		worker.start();
	
	
	}
	
	// random........................///////////////////////////////////////////////////////////
	@RequestMapping("/rng")
	@ResponseBody
	String home() {
		return "RNG running on rng";
	}
	@RequestMapping("/rng/{howMany}")
	@ResponseBody
	public String getFRandomNumber(
			  @PathVariable("howMany") int howMany) {
		
		SecureRandom rnd = new SecureRandom();
		byte[] token = new byte[howMany];
		rnd.nextBytes(token);
			    return  Base64.getEncoder().encodeToString(token);
			}
	////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////

	// hasher........................//////////////////////////////////////////////////////////////
	@RequestMapping("/hasher")
	@ResponseBody
	String hellohasher() {
		return "hasher running on hasher";
	}
	@RequestMapping("/hasher/{data}")
	@ResponseBody
	public String gethash(
			  @PathVariable("data") String data) {
		MessageDigest digest;
		try {
			digest = MessageDigest.getInstance("SHA-256");
			byte[] hash = digest.digest(data.getBytes(StandardCharsets.UTF_8));
			  return  Base64.getEncoder().encodeToString(hash);
			
		  
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "error............";
		
		
			}
//////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////
	

}
