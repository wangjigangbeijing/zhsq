package com.template.service.oa;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.template.model.oa.Worklog;
import com.template.service.base.BaseServiceImpl;
@Transactional
@Service
public class WorklogService extends BaseServiceImpl<Worklog>
{
}
