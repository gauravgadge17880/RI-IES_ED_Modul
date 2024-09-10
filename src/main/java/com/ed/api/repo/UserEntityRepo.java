package com.ed.api.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ed.api.entity.UserEntity;

public interface UserEntityRepo extends JpaRepository<UserEntity , Integer> {
	public UserEntity findByCaseNum(Integer caseNum);

}
