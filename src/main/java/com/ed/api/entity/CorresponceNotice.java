package com.ed.api.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="CO_NOTICES")
public class CorresponceNotice {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer cId;
	
	private String noticeStatus;
	
	private String noticePrintDate;
	
	private String noticeS3Url;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="case_num")
	private AppEntity app;
	

}
