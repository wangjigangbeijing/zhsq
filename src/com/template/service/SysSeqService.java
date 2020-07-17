package com.template.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.template.model.SMSMessage;
import com.template.model.SysSeq;
import com.template.service.base.BaseServiceImpl;

@Transactional
@Service
public class SysSeqService extends BaseServiceImpl<SysSeq>
{
	
}
