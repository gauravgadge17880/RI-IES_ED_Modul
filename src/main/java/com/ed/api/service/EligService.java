package com.ed.api.service;

import com.ed.api.binding.EligInfo;

public interface EligService {
	public EligInfo determineEligibility(Integer caseNum);
}
