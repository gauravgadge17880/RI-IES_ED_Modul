package com.ed.api.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ed.api.entity.DcKidEntity;

public interface DcKidRepo extends JpaRepository<DcKidEntity, Integer> {
	public List<DcKidEntity> findByCaseNum(Integer caseNum);

}
