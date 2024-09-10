package com.ed.api.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ed.api.entity.DcIncomeEntity;

public interface DcIncomeRepo extends JpaRepository<DcIncomeEntity, Integer> {
	public DcIncomeEntity findByCaseNum(Integer caseNum);

}
