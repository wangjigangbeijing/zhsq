package com.template.service.gis;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.template.model.gis.Gismap;
import com.template.service.base.BaseServiceImpl;
@Transactional
@Service
public class GismapService extends BaseServiceImpl<Gismap>
{
}
