package com.ed.api.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ed.api.entity.CitizenAppEntity;

public interface CitizenAppRepo extends JpaRepository<CitizenAppEntity, Integer> {

}
