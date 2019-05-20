package com.aron.excheck.service.imp.templateImp;

import com.aron.excheck.dao.template.TemplateInfoDao;
import com.aron.excheck.entity.template.TemplateInfo;
import com.aron.excheck.service.intf.template.TemplateInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName TemplateInfoServiceImp
 * @Description TODO
 * @Author aron
 * @Date 2019/5/16 14:10
 **/
@Service
public class TemplateInfoServiceImp implements TemplateInfoService {

    @Autowired
    private TemplateInfoDao templateInfoDao;

    //根据id获取模板信息
    public TemplateInfo findById(Integer id){
        return templateInfoDao.selectById(id);
    }

    //根据模板id获取信息
    public TemplateInfo findByTemplateId(String templateId){
        return templateInfoDao.findByTemplateId(templateId);
    }

}
