package com.template.service;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.template.model.SysDictionary;
import com.template.model.SysMenu;
import com.template.service.base.BaseServiceImpl;
@Transactional
@Service
public class DictionaryService extends BaseServiceImpl<SysDictionary>
{
	
}
