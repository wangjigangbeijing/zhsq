package com.template.service.oa;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.template.model.oa.Contact;
import com.template.service.base.BaseServiceImpl;
@Transactional
@Service
public class ContactService extends BaseServiceImpl<Contact>
{
}
