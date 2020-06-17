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
	private String table_name;
	/**
	 * 材料费
	 */
	private double material_cost;
	/**
	 * 麻醉费
	 */
	private double anesthesia_cost;
	/**
	 * 正畸材料费
	 */
	private double zj_material_cost;
	/**
	 * 检查一次性医药用材
	 */
	private double check_medical_materials;
	/**
	 * 种植材料费
	 */
	private double planting_material_cost;

	/**
	 * 医疗小计
	 */
	private double subtotal_medical_treatment;
	/**
	 * 药品小计
	 */
	private double subtotal_drugs;
	/**
	 * 自付合计
	 */
	private double total_pocket;
	/**
	 * 应收合计
	 */
	private double total_receivables;
	/**
	 * 检查单
	 */
	private double check_list;

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
	public String getTable_name() {
		return table_name;
	}
	public void setTable_name(String table_name) {
		this.table_name = table_name;
	}

	public double getCheck_list() {
		return check_list;
	}

	public void setCheck_list(double check_list) {
		this.check_list = check_list;
	}

	public double getMaterial_cost() {
		return material_cost;
	}

	public void setMaterial_cost(double material_cost) {
		this.material_cost = material_cost;
	}

	public double getAnesthesia_cost() {
		return anesthesia_cost;
	}

	public void setAnesthesia_cost(double anesthesia_cost) {
		this.anesthesia_cost = anesthesia_cost;
	}

	public double getZj_material_cost() {
		return zj_material_cost;
	}

	public void setZj_material_cost(double zj_material_cost) {
		this.zj_material_cost = zj_material_cost;
	}

	public double getCheck_medical_materials() {
		return check_medical_materials;
	}

	public void setCheck_medical_materials(double check_medical_materials) {
		this.check_medical_materials = check_medical_materials;
	}

	public double getPlanting_material_cost() {
		return planting_material_cost;
	}

	public void setPlanting_material_cost(double planting_material_cost) {
		this.planting_material_cost = planting_material_cost;
	}

	public double getSubtotal_medical_treatment() {
		return subtotal_medical_treatment;
	}

	public void setSubtotal_medical_treatment(double subtotal_medical_treatment) {
		this.subtotal_medical_treatment = subtotal_medical_treatment;
	}

	public double getSubtotal_drugs() {
		return subtotal_drugs;
	}

	public void setSubtotal_drugs(double subtotal_drugs) {
		this.subtotal_drugs = subtotal_drugs;
	}

	public double getTotal_pocket() {
		return total_pocket;
	}

	public void setTotal_pocket(double total_pocket) {
		this.total_pocket = total_pocket;
	}

	public double getTotal_receivables() {
		return total_receivables;
	}

	public void setTotal_receivables(double total_receivables) {
		this.total_receivables = total_receivables;
	}
}
