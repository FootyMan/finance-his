package com.erp.domain;

import java.util.Date;

public class OutpatientDO {

	private int id;
	private String title;
	private String hospital_name;
	private Date summary_date_begin;
	private Date summary_date_end;
	private String subject;
	private double amount_received;
	private String subject1;
	private String received_no;
	private double received_money;
	private String discard_no;
	private double discard_money;
	private String rush_accounts_no;
	private double rush_accounts_money;
	private String reviewer;
	private String table_name;
	private String table_date;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getHospital_name() {
		return hospital_name;
	}
	public void setHospital_name(String hospital_name) {
		this.hospital_name = hospital_name;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public double getAmount_received() {
		return amount_received;
	}
	public void setAmount_received(double amount_received) {
		this.amount_received = amount_received;
	}
	public String getSubject1() {
		return subject1;
	}
	public void setSubject1(String subject1) {
		this.subject1 = subject1;
	}
	public String getReceived_no() {
		return received_no;
	}
	public void setReceived_no(String received_no) {
		this.received_no = received_no;
	}
	public double getReceived_money() {
		return received_money;
	}
	public void setReceived_money(double received_money) {
		this.received_money = received_money;
	}
	public String getDiscard_no() {
		return discard_no;
	}
	public void setDiscard_no(String discard_no) {
		this.discard_no = discard_no;
	}
	public double getDiscard_money() {
		return discard_money;
	}
	public void setDiscard_money(double discard_money) {
		this.discard_money = discard_money;
	}
	public String getRush_accounts_no() {
		return rush_accounts_no;
	}
	public void setRush_accounts_no(String rush_accounts_no) {
		this.rush_accounts_no = rush_accounts_no;
	}
	public double getRush_accounts_money() {
		return rush_accounts_money;
	}
	public void setRush_accounts_money(double rush_accounts_money) {
		this.rush_accounts_money = rush_accounts_money;
	}
	public String getReviewer() {
		return reviewer;
	}
	public void setReviewer(String reviewer) {
		this.reviewer = reviewer;
	}
	public String getTable_name() {
		return table_name;
	}
	public void setTable_name(String table_name) {
		this.table_name = table_name;
	}
	public String getTable_date() {
		return table_date;
	}
	public void setTable_date(String table_date) {
		this.table_date = table_date;
	}
	public Date getSummary_date_begin() {
		return summary_date_begin;
	}
	public void setSummary_date_begin(Date summary_date_begin) {
		this.summary_date_begin = summary_date_begin;
	}
	public Date getSummary_date_end() {
		return summary_date_end;
	}
	public void setSummary_date_end(Date summary_date_end) {
		this.summary_date_end = summary_date_end;
	}
	 
	
	
	
}
