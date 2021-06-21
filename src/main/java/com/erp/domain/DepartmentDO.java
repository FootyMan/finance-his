package com.erp.domain;

import lombok.Data;

import java.util.Date;

@Data
public class DepartmentDO {

    private int id;
    private String organization_name;
    private String department_name;
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
    private double western_medicine_fee;
    private double chinese_patent_medicine;
    private double pediatric_treatment_fee2;
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
     * 治疗一次性医药用材
     */
    private double treatment_medical_materials;
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
     * 挂号费
     */
    private double registrationFee;
    /**
     * 手术一次性费用
     */
    private double operationMedicalMaterials;
    /**
     * 其他费用
     */
    private double otherFee;
    /**
     * 修复费
     */
    private double repairFee;
    /**
     * 正畸治疗费
     */
    private double orthodonticCureFee;
    /**
     * 数字化修复费
     */
    private double numberRepairFee;
    /**
     * 一次性费用
     */
    private double oneTimeFee;
    /**
     * 种植手术费
     */
    private double plantingOperationFee;
    /**
     * 种植修复费
     */
    private double plantingRepairFee;
    /**
     * 种植修复材料费
     */
    private double plantingRepairMaterialFee;


}
