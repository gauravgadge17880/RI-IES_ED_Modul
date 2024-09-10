package com.ed.api.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.ed.api.binding.EligInfo;
import com.ed.api.service.EligService;

@RestController
public class EdRestController {

	 @Autowired
	 private EligService edsevice;
	 
	 @GetMapping("/elig/{caseNum}")
	 public ResponseEntity<EligInfo> FetchEligInfo(@PathVariable Integer caseNum){
		EligInfo determineEligibility = edsevice.determineEligibility(caseNum);
		 return new ResponseEntity<EligInfo>(determineEligibility,HttpStatus.OK);
	 }
}
