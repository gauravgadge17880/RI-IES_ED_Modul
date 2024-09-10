
package com.ed.api.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ed.api.entity.AppEntity;

public interface AppEntityRepo extends JpaRepository<AppEntity, Integer> {

		public AppEntityRepo findByCaseNum(Integer caseNum);
}
