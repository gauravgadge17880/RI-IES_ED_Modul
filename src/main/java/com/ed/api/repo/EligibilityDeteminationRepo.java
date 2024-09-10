package com.ed.api.repo;

import org.springframework.data.jpa.repository.JpaRepository;


import com.ed.api.entity.EligibilityDetermination;

public interface EligibilityDeteminationRepo extends JpaRepository<EligibilityDetermination, Integer> {

	public EligibilityDetermination findByCaseNum(Integer caseNum);
}
