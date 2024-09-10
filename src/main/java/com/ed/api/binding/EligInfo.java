package com.ed.api.binding;

import java.time.LocalDate;

import com.ed.api.entity.PlanEntity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
public class EligInfo {
	
	private Integer CaseNum;

	private PlanEntity planName;

	private String planStatus;

	private LocalDate planStartDate;

	private LocalDate planEndDate;

	private Double benifitAmount;

	private String denialResone;

}
