package com.template.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.template.model.SysTable;
import com.template.service.base.BaseServiceImpl;

@Transactional
@Service
public class TableService extends BaseServiceImpl<SysTable>
{
	
}
