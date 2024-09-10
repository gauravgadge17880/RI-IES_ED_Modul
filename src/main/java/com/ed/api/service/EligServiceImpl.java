package com.ed.api.service;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

import org.aspectj.weaver.patterns.PerObject;
import org.springframework.beans.factory.annotation.Autowired;

import com.ed.api.binding.EligInfo;
import com.ed.api.binding.Kids;
import com.ed.api.entity.AppEntity;
import com.ed.api.entity.CorresponceNotice;
import com.ed.api.entity.DcEducationEntity;
import com.ed.api.entity.DcIncomeEntity;
import com.ed.api.entity.DcKidEntity;
import com.ed.api.entity.EligibilityDetermination;
import com.ed.api.entity.PlanEntity;
import com.ed.api.repo.AppEntityRepo;
import com.ed.api.repo.CitizenAppRepo;
import com.ed.api.repo.CorresponceNoticeRepo;
import com.ed.api.repo.DcEducationRepo;
import com.ed.api.repo.DcIncomeRepo;
import com.ed.api.repo.DcKidRepo;
import com.ed.api.repo.EligibilityDeteminationRepo;
import com.ed.api.repo.PlanEntityRepo;
import com.ed.api.repo.UserEntityRepo;

public class EligServiceImpl implements EligService {

	@Autowired
	private EligibilityDeteminationRepo eligRepo;

	@Autowired
	private PlanEntityRepo planRepo;

	@Autowired
	private AppEntityRepo appRepo;

	@Autowired
	private CitizenAppRepo citizenAppRepo;

	@Autowired
	private DcEducationRepo edRepo;

	@Autowired
	private DcIncomeRepo incomeRepo;

	@Autowired
	private DcKidRepo kidRepo;

	@Autowired
	private UserEntityRepo userRepo;
	
	@Autowired
	private CorresponceNoticeRepo coRepo;


	@Override
	public EligInfo determineEligibility(Integer caseNum) {

		AppEntity appInfo = appRepo.getById(caseNum);
		PlanEntity plan = appInfo.getPlanId();
		String planName = plan.getPlanName();
		
		EligInfo eligibility = new EligInfo();
		
		DcIncomeEntity incomeInfo = incomeRepo.findByCaseNum(caseNum);
		
		eligibility.setPlanName(plan);
		eligibility.setCaseNum(caseNum);
		
		if (planName.equals("SNAP")) {
			if (incomeInfo.getSalaryIncome() <= 300) {
				eligibility.setPlanStatus("Approved");
			}else {
				eligibility.setPlanStatus("Denied");
				eligibility.setDenialResone("High Income");
			}

		} else if (planName.equals("CCAP")) {
			Double income = incomeInfo.getSalaryIncome();
			List<DcKidEntity> kids = kidRepo.findByCaseNum(caseNum);
			boolean isValid = true;
			for (DcKidEntity kid : kids) {
				int years = Period.between(kid.getKidDob(), LocalDate.now()).getYears();
				if (years > 16) {
					isValid = false;
					break;
				}
			}
			if (isValid && income <= 300 && !kids.isEmpty()) {
				eligibility.setPlanStatus("Approved");
			}else {
				eligibility.setPlanStatus("Denied");
				}
			if(!isValid) {
				eligibility.setDenialResone("kid age above 16")	;
			}
			if(income>300) {
				eligibility.setDenialResone("High Income")	;
			}
			if(kids.isEmpty()) {
				eligibility.setDenialResone("No kids Available")	;
			}
		} else if (planName.equals("Medicaid")) {
		
			Double propertyIncome = incomeInfo.getPropertyIncome();
			Double rentIncome = incomeInfo.getRentIncome();
			Double salaryIncome = incomeInfo.getSalaryIncome();

			if (salaryIncome <= 300 && ((propertyIncome + rentIncome) <= 0)) {
				eligibility.setPlanStatus("Approved");
			}else {
				eligibility.setPlanStatus("Denied");
				eligibility.setDenialResone("High income");
			}
		} else if (planName.equals("Medicare")) {
			LocalDate dob = appInfo.getDob();
			int years = Period.between(dob, LocalDate.now()).getYears();
			if (years >= 65) {
				eligibility.setPlanStatus("Approved");
			}
			else {
				eligibility.setPlanStatus("Denied");
				eligibility.setDenialResone("Age condition not match");
			}
		} else if (planName.equals("RIW")) {
			DcEducationEntity education = edRepo.findByCaseNum(caseNum);
			Integer graduationYear = education.getGraduationYear();
			if (graduationYear != null && incomeInfo != null) {
				eligibility.setPlanStatus("Approved");
			}else {
				eligibility.setPlanStatus("Denied");
				eligibility.setDenialResone("Undergraduate");
			}
		}
		if(eligibility.getPlanStatus().equals("Approved")) {
			eligibility.setPlanStartDate(LocalDate.now());
			eligibility.setPlanEndDate(LocalDate.now().plusMonths(6));
			eligibility.setBenifitAmount(2000.00);
		}
		
		return eligibility;
	}
	
	private void generateCorrespondence(AppEntity app) {
		CorresponceNotice entity = new CorresponceNotice();
		entity.setNoticeStatus("pending");
		entity.setApp(app);
		coRepo.save(entity);
	}

}
