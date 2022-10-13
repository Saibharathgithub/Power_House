
package com.kanerika.powerhouse.Resource;

import javax.persistence.Column;
import javax.persistence.Table;


@Table(name = "powerhouse")
public class Table_Content {

	@Column(name = "name")
	private String name;
	@Column(name = "email_id")
	private String email_id;
	@Column(name = "send_mails_count")
	private Integer send_mails_count;
	@Column(name = "created_by")
	private String created_by;
	@Column(name = "send_mail_date")
	private String send_mail_date;
	@Column(name = "updated_by")
	private String updated_by;
	@Column(name = "replied_date")
	private String replied_date;
	@Column(name="mail_subject")
	private String mail_subject;
	@Column(name="mail_body")
	private String mail_body;
	@Column(name="reply_subject")
	private String reply_subject;
	@Column(name="reply_body")
	private String reply_body;
	@Column(name="reply_status")
	private String reply_status;
	public Table_Content(String name, String email_id, Integer send_mails_count, String created_by,
			String send_mail_date, String updated_by, String replied_date, String mail_subject, String mail_body,
			String reply_subject, String reply_body, String reply_status) {
		super();
		this.name = name;
		this.email_id = email_id;
		this.send_mails_count = send_mails_count;
		this.created_by = created_by;
		this.send_mail_date = send_mail_date;
		this.updated_by = updated_by;
		this.replied_date = replied_date;
		this.mail_subject = mail_subject;
		this.mail_body = mail_body;
		this.reply_subject = reply_subject;
		this.reply_body = reply_body;
		this.reply_status = reply_status;
	}
	public Table_Content() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Table_Content [name=" + name + ", email_id=" + email_id + ", send_mails_count=" + send_mails_count
				+ ", created_by=" + created_by + ", send_mail_date=" + send_mail_date + ", updated_by=" + updated_by
				+ ", replied_date=" + replied_date + ", mail_subject=" + mail_subject + ", mail_body=" + mail_body
				+ ", reply_subject=" + reply_subject + ", reply_body=" + reply_body + ", reply_status=" + reply_status
				+ "]";
	}
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
	public Integer getSend_mails_count() {
		return send_mails_count;
	}
	public void setSend_mails_count(Integer send_mails_count) {
		this.send_mails_count = send_mails_count;
	}
	public String getCreated_by() {
		return created_by;
	}
	public void setCreated_by(String created_by) {
		this.created_by = created_by;
	}
	public String getSend_mail_date() {
		return send_mail_date;
	}
	public void setSend_mail_date(String send_mail_date) {
		this.send_mail_date = send_mail_date;
	}
	public String getUpdated_by() {
		return updated_by;
	}
	public void setUpdated_by(String updated_by) {
		this.updated_by = updated_by;
	}
	public String getReplied_date() {
		return replied_date;
	}
	public void setReplied_date(String replied_date) {
		this.replied_date = replied_date;
	}
	public String getMail_subject() {
		return mail_subject;
	}
	public void setMail_subject(String mail_subject) {
		this.mail_subject = mail_subject;
	}
	public String getMail_body() {
		return mail_body;
	}
	public void setMail_body(String mail_body) {
		this.mail_body = mail_body;
	}
	public String getReply_subject() {
		return reply_subject;
	}
	public void setReply_subject(String reply_subject) {
		this.reply_subject = reply_subject;
	}
	public String getReply_body() {
		return reply_body;
	}
	public void setReply_body(String reply_body) {
		this.reply_body = reply_body;
	}
	public String getReply_status() {
		return reply_status;
	}
	public void setReply_status(String reply_status) {
		this.reply_status = reply_status;
	}
	

	
	
	
}
