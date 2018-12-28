package com.erp.domain;

import java.util.Date;

/**
 * 医生收入
 * @author HCY
 *
 */
public class DoctorRevenueDo {

	private int id;
	private String organization_name;
	private String department;
	private String job_number;
	private String doctor_name;
	private Date charge_date;
	private double treatment_fee;
	private double radiological_fee;
	private double operation_fee;
	private double inspection_fee;
	private double check_fee;
	private double medical_materials;
	private double dental_implant_fee;
	private double orthodontic_fee;
	private double planting_fee;
	private double pediatric_treatment_fee;
	private double pediatric_treatment_fee2;
	private double western_medicine_fee;
	private double chinese_patent_medicine;
	private double total_fee;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getJob_number() {
		return job_number;
	}
	public void setJob_number(String job_number) {
		this.job_number = job_number;
	}
	public String getDoctor_name() {
		return doctor_name;
	}
	public void setDoctor_name(String doctor_name) {
		this.doctor_name = doctor_name;
	}
	public double getTreatment_fee() {
		return treatment_fee;
	}
	public void setTreatment_fee(double treatment_fee) {
		this.treatment_fee = treatment_fee;
	}
	public double getRadiological_fee() {
		return radiological_fee;
	}
	public void setRadiological_fee(double radiological_fee) {
		this.radiological_fee = radiological_fee;
	}
	public double getOperation_fee() {
		return operation_fee;
	}
	public void setOperation_fee(double operation_fee) {
		this.operation_fee = operation_fee;
	}
	public double getInspection_fee() {
		return inspection_fee;
	}
	public void setInspection_fee(double inspection_fee) {
		this.inspection_fee = inspection_fee;
	}
	public double getCheck_fee() {
		return check_fee;
	}
	public void setCheck_fee(double check_fee) {
		this.check_fee = check_fee;
	}
	public double getMedical_materials() {
		return medical_materials;
	}
	public void setMedical_materials(double medical_materials) {
		this.medical_materials = medical_materials;
	}
	public double getDental_implant_fee() {
		return dental_implant_fee;
	}
	public void setDental_implant_fee(double dental_implant_fee) {
		this.dental_implant_fee = dental_implant_fee;
	}
	public double getOrthodontic_fee() {
		return orthodontic_fee;
	}
	public void setOrthodontic_fee(double orthodontic_fee) {
		this.orthodontic_fee = orthodontic_fee;
	}
	public double getPlanting_fee() {
		return planting_fee;
	}
	public void setPlanting_fee(double planting_fee) {
		this.planting_fee = planting_fee;
	}
	public double getPediatric_treatment_fee() {
		return pediatric_treatment_fee;
	}
	public void setPediatric_treatment_fee(double pediatric_treatment_fee) {
		this.pediatric_treatment_fee = pediatric_treatment_fee;
	}
	public double getWestern_medicine_fee() {
		return western_medicine_fee;
	}
	public void setWestern_medicine_fee(double western_medicine_fee) {
		this.western_medicine_fee = western_medicine_fee;
	}
	public double getChinese_patent_medicine() {
		return chinese_patent_medicine;
	}
	public void setChinese_patent_medicine(double chinese_patent_medicine) {
		this.chinese_patent_medicine = chinese_patent_medicine;
	}
	public double getTotal_fee() {
		return total_fee;
	}
	public void setTotal_fee(double total_fee) {
		this.total_fee = total_fee;
	}
	public Date getCharge_date() {
		return charge_date;
	}
	public void setCharge_date(Date charge_date) {
		this.charge_date = charge_date;
	}
	public double getPediatric_treatment_fee2() {
		return pediatric_treatment_fee2;
	}
	public void setPediatric_treatment_fee2(double pediatric_treatment_fee2) {
		this.pediatric_treatment_fee2 = pediatric_treatment_fee2;
	}
	public String getOrganization_name() {
		return organization_name;
	}
	public void setOrganization_name(String organization_name) {
		this.organization_name = organization_name;
	}
	 
}
