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
	private double materialCost;
	/**
	 * 麻醉费
	 */
	private double anesthesiaCost;
	/**
	 * 正畸材料费
	 */
	private double zjMaterialCost;
	/**
	 * 检查一次性医药用材
	 */
	private double checkMedicalMaterials;
	/**
	 * 种植材料费
	 */
	private double plantingMaterialCost;

	/**
	 * 医疗小计
	 */
	private double subtotalMedicalTreatment;
	/**
	 * 药品小计
	 */
	private double subtotalDrugs;
	/**
	 * 自付合计
	 */
	private double totalPocket;
	/**
	 * 应收合计
	 */
	private double totalReceivables;
	/**
	 * 检查单
	 */
	private double checkList;

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

	public double getMaterialCost() {
		return materialCost;
	}

	public void setMaterialCost(double materialCost) {
		this.materialCost = materialCost;
	}

	public double getAnesthesiaCost() {
		return anesthesiaCost;
	}

	public void setAnesthesiaCost(double anesthesiaCost) {
		this.anesthesiaCost = anesthesiaCost;
	}

	public double getZjMaterialCost() {
		return zjMaterialCost;
	}

	public void setZjMaterialCost(double zjMaterialCost) {
		this.zjMaterialCost = zjMaterialCost;
	}

	public double getCheckMedicalMaterials() {
		return checkMedicalMaterials;
	}

	public void setCheckMedicalMaterials(double checkMedicalMaterials) {
		this.checkMedicalMaterials = checkMedicalMaterials;
	}

	public double getPlantingMaterialCost() {
		return plantingMaterialCost;
	}

	public void setPlantingMaterialCost(double plantingMaterialCost) {
		this.plantingMaterialCost = plantingMaterialCost;
	}

	public double getSubtotalMedicalTreatment() {
		return subtotalMedicalTreatment;
	}

	public void setSubtotalMedicalTreatment(double subtotalMedicalTreatment) {
		this.subtotalMedicalTreatment = subtotalMedicalTreatment;
	}

	public double getSubtotalDrugs() {
		return subtotalDrugs;
	}

	public void setSubtotalDrugs(double subtotalDrugs) {
		this.subtotalDrugs = subtotalDrugs;
	}

	public double getTotalPocket() {
		return totalPocket;
	}

	public void setTotalPocket(double totalPocket) {
		this.totalPocket = totalPocket;
	}

	public double getTotalReceivables() {
		return totalReceivables;
	}

	public void setTotalReceivables(double totalReceivables) {
		this.totalReceivables = totalReceivables;
	}

	public double getCheckList() {
		return checkList;
	}

	public void setCheckList(double checkList) {
		this.checkList = checkList;
	}
}
