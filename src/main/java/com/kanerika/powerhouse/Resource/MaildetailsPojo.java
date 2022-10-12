package com.kanerika.powerhouse.Resource;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "powerhouse")
public class MaildetailsPojo {

	@Column
	private String name;
	@Id
	private String email_id;
	@Column
	private int send_mails_count;
	@Column
	private String created_by;
	@Column
	private Calendar send_mail_date;

	@Override
	public String toString() {
		return "MaildetailsPojo [name=" + name + ", email_id=" + email_id + ", send_mails_count=" + send_mails_count
				+ ", created_by=" + created_by + ", send_mail_date=" + send_mail_date + ", updated_by=" + updated_by
				+ ", reply_subject=" + reply_subject + ", replied_date=" + replied_date + ", mail_subject="
				+ mail_subject + ", mail_body=" + mail_body + "]";
	}

	@Column
	private String updated_by;

	@Column
	private String reply_subject;

	public String getReply_subject() {
		return reply_subject;
	}

	public void setReply_subject(String reply_subject) {
		this.reply_subject = reply_subject;
	}

	@Column
	private Calendar replied_date;

	public String getName() {
		return name;
	}

	public MaildetailsPojo(String name, String email_id, int mails_count, String created_by, Calendar created_date,
			String updated_by, String reply_subject, Calendar updated_date, String mail_subject, String mail_body) {
		super();
		this.name = name;
		this.email_id = email_id;
		this.send_mails_count = mails_count;
		this.created_by = created_by;
		this.send_mail_date = created_date;
		this.updated_by = updated_by;
		this.reply_subject = reply_subject;
		this.replied_date = updated_date;
		this.mail_subject = mail_subject;
		this.mail_body = mail_body;
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

	public int getMails_count() {
		return send_mails_count;
	}

	public void setMails_count(int mails_count) {
		this.send_mails_count = mails_count;
	}

	public String getCreated_by() {
		return created_by;
	}

	public void setCreated_by(String created_by) {
		this.created_by = created_by;
	}

	public Calendar getCreated_date() {
		return send_mail_date;
	}

	public void setCreated_date(Calendar created_date) {
		this.send_mail_date = created_date;
	}

	public String getUpdated_by() {
		return updated_by;
	}

	public void setUpdated_by(String updated_by) {
		this.updated_by = updated_by;
	}

	public Calendar getUpdated_date() {
		return replied_date;
	}

	public MaildetailsPojo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public void setUpdated_date(Calendar updated_date) {
		this.replied_date = updated_date;
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

	@Column
	private String mail_subject;
	@Column
	private String mail_body;

}
