package com.template.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name = "SMS_MESSAGE")
@DynamicInsert(true)
@DynamicUpdate(true)
public class SMSMessage implements java.io.Serializable {
	
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
	
	@Column(name = "MESSAGE_CONTENT", nullable = true)
	public String messageContent;
	public String getMessageContent()
	{
		return messageContent;
	}
	public void setMessageContent(String messageContent)
	{
		this.messageContent = messageContent;
	}
	
	@Column(name = "TARGET", nullable = false)
	public String target;
	public String getTarget()
	{
		return target;
	}
	public void setTarget(String target)
	{
		this.target = target;
	}
	
	@Column(name = "SEND_STATUS", nullable = false)
	public String sendStatus;
	public String getSendStatus()
	{
		return sendStatus;
	}
	public void setSendStatus(String sendStatus)
	{
		this.sendStatus = sendStatus;
	}
	
	@Column(name = "TIMER_SEND", nullable = true)
	public Date timerSend;
	public Date getTimerSend()
	{
		return timerSend;
	}
	public void setTimerSend(Date timerSend)
	{
		this.timerSend = timerSend;
	}
	
	@Column(name = "CREATE_DATE", nullable = true)
	public Date createDate;
	public Date getCreateDate()
	{
		return createDate;
	}
	public void setCreateDate(Date createDate)
	{
		this.createDate = createDate;
	}
	
	@Column(name = "TARGET_CNT", nullable = true)
	public Integer targetCnt;
	public Integer getTargetCnt()
	{
		return targetCnt;
	}
	public void setTargetCnt(Integer targetCnt)
	{
		this.targetCnt = targetCnt;
	}
	
	@Column(name = "SUCCESS_CNT", nullable = true)
	public Integer successCnt;
	public Integer getSuccessCnt()
	{
		return successCnt;
	}
	public void setSuccessCnt(Integer successCnt)
	{
		this.successCnt = successCnt;
	}
	
	@Column(name = "FAIL_CNT", nullable = true)
	public Integer failCnt;
	public Integer getFailCnt()
	{
		return failCnt;
	}
	public void setFailCnt(Integer failCnt)
	{
		this.failCnt = failCnt;
	}
	
	@Column(name = "SENDER", nullable = false)
	public String sender;
	public String getSender()
	{
		return sender;
	}
	public void setSender(String sender)
	{
		this.sender = sender;
	}
	
}
