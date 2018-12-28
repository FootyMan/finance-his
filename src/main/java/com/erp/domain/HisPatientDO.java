package com.erp.domain;

import java.util.Date;

public class HisPatientDO {

	private int id;
	private String case_number;
	private String name;
	private String sex;
	private int age;
	private String nature;
	private String patient_nature;
	private String invoice_number;
	private String yb_number;
	private String pay_type;
	private Date visiting_time;
	private Date charge_time;
	private double amount;
	private String phone;
	private String department;
	private String doctor;
	private String diagnosis_msg;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCase_number() {
		return case_number;
	}
	public void setCase_number(String case_number) {
		this.case_number = case_number;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getNature() {
		return nature;
	}
	public void setNature(String nature) {
		this.nature = nature;
	}
	public String getPatient_nature() {
		return patient_nature;
	}
	public void setPatient_nature(String patient_nature) {
		this.patient_nature = patient_nature;
	}
	public String getInvoice_number() {
		return invoice_number;
	}
	public void setInvoice_number(String invoice_number) {
		this.invoice_number = invoice_number;
	}
	public String getYb_number() {
		return yb_number;
	}
	public void setYb_number(String yb_number) {
		this.yb_number = yb_number;
	}
	public String getPay_type() {
		return pay_type;
	}
	public void setPay_type(String pay_type) {
		this.pay_type = pay_type;
	}
	public Date getVisiting_time() {
		return visiting_time;
	}
	public void setVisiting_time(Date visiting_time) {
		this.visiting_time = visiting_time;
	}
	public Date getCharge_time() {
		return charge_time;
	}
	public void setCharge_time(Date charge_time) {
		this.charge_time = charge_time;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getDoctor() {
		return doctor;
	}
	public void setDoctor(String doctor) {
		this.doctor = doctor;
	}
	public String getDiagnosis_msg() {
		return diagnosis_msg;
	}
	public void setDiagnosis_msg(String diagnosis_msg) {
		this.diagnosis_msg = diagnosis_msg;
	}
	
	
}
