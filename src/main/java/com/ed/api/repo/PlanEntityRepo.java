package com.ed.api.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ed.api.entity.PlanEntity;

public interface PlanEntityRepo extends JpaRepository<PlanEntity, Integer>{
	public PlanEntity findByCaseNum(Integer caseNum);

}
