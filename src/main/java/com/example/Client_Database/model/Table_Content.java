package com.example.Client_Database.model;

import javax.persistence.Column;
import javax.persistence.Table;


@Table(name = "powerhouse")
public class Table_Content {

	@Column(name = "name")
	private String name;
	@Column(name = "email_id")
	private String email_id;
	@Column(name = "send_mails")
	private Integer send_mails;
	@Column(name = "create_by")
	private String create_by;
	@Column(name = "created_date")
	private String created_date;
	@Column(name = "update_by")
	private String update_by;
	@Column(name = "update_date")
	private String update_date;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail_id() {
		return email_id;
	}

	public void setEmail_id(String email_id) {
		this.email_id = email_id;
	}

	public Integer getSend_mails() {
		return send_mails;
	}

	public void setSend_mails(Integer send_mails) {
		this.send_mails = send_mails;
	}

	public String getCreate_by() {
		return create_by;
	}

	public void setCreate_by(String create_by) {
		this.create_by = create_by;
	}

	public String getCreated_date() {
		return created_date;
	}

	public void setCreated_date(String created_date) {
		this.created_date = created_date;
	}

	public String getUpdate_by() {
		return update_by;
	}

	public void setUpdate_by(String update_by) {
		this.update_by = update_by;
	}

	public String getUpdate_date() {
		return update_date;
	}

	public void setUpdate_date(String update_date) {
		this.update_date = update_date;
	}

	@Override
	public String toString() {
		return "Table_Content [name=" + name + ", email_id=" + email_id + ", send_mails=" + send_mails + ", create_by="
				+ create_by + ", created_date=" + created_date + ", update_by=" + update_by + ", update_date="
				+ update_date + "]";
	}

	public Table_Content(String name, String email_id, int send_mails, String create_by, String created_date,
			String update_by, String update_date) {

		this.name = name;
		this.email_id = email_id;
		this.send_mails = send_mails;
		this.create_by = create_by;
		this.created_date = created_date;
		this.update_by = update_by;
		this.update_date = update_date;
	}

	public Table_Content() {
		super();
		
	}
	
	
}
