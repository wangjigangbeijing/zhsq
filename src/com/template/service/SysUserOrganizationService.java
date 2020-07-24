package com.template.service;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.template.model.SysUser;
import com.template.model.SysUserOrganization;
import com.template.service.base.BaseServiceImpl;
@Transactional
@Service
public class SysUserOrganizationService extends BaseServiceImpl<SysUserOrganization>
{
}
