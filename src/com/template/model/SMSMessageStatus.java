package com.template.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name = "SMS_MESSAGE_STATUS")
@DynamicInsert(true)
@DynamicUpdate(true)
public class SMSMessageStatus implements java.io.Serializable {
	
	/*
	 * 属性定义必须public,否则在HQLFilter中反射的时候会提示找不到属性
	 */
	@Id
    @Column(name = "ID", nullable = false)
	public String id;
	
	public String getId()
	{
		return id;
	}
	public void setId(String id)
	{
		this.id = id;
	}
	
	@Column(name = "MSGID", nullable = true)
	public String msgid;
	public String getMsgId()
	{
		return msgid;
	}
	public void setMsgId(String msgid)
	{
		this.msgid = msgid;
	}
	
	@Column(name = "STATUS", nullable = true)
	public String status;
	public String getStatus()
	{
		return status;
	}
	public void setStatus(String status)
	{
		this.status = status;
	}
	
	@Column(name = "SENDTIME", nullable = true)
	public String sendTime;
	public String getSendTime()
	{
		return sendTime;
	}
	public void setSendTime(String sendTime)
	{
		this.sendTime = sendTime;
	}
	
	@Column(name = "MOBILE", nullable = false)
	public String mobile;
	public String getMobile()
	{
		return mobile;
	}
	public void setMobile(String mobile)
	{
		this.mobile = mobile;
	}
	
	@Column(name = "SEND_ID", nullable = true)
	public String sendId;
	public String getSendId()
	{
		return sendId;
	}
	public void setSendId(String sendId)
	{
		this.sendId = sendId;
	}
}
