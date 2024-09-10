package com.ed.api.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ed.api.entity.DcEducationEntity;

public interface DcEducationRepo extends JpaRepository<DcEducationEntity, Integer> {
	public DcEducationEntity findByCaseNum(Integer caseNum);

}
