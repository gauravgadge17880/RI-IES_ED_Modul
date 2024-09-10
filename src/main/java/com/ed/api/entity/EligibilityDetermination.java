package com.ed.api.entity;

import java.time.LocalDate;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name ="ELIG_DTLS")
public class EligibilityDetermination {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer EdId;
	
	@ManyToOne(cascade = CascadeType.ALL)
	private PlanEntity planName;
	
	private String planStatus;
	
	private LocalDate planStartDate;
	
	private LocalDate planEndDate;
	
	private Double benifitAmount;
	
	private String denialResone;
	
	
	//which plan id
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="plan_id")
	private PlanEntity plan;
	
	//which case num
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="case_num")
	private AppEntity app;
	
	
}
