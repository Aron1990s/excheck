package com.aron.excheck.service.imp.templateImp;

import com.aron.excheck.dao.template.TemplatePrincipleDao;
import com.aron.excheck.entity.template.TemplatePrinciple;
import com.aron.excheck.service.intf.template.TemplatePrincipleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName TemplatePrincipleServiceImp
 * @Description TODO
 * @Author aron
 * @Date 2019/5/16 14:11
 **/
@Service
public class TemplatePrincipleServiceImp implements TemplatePrincipleService {

    @Autowired
    private TemplatePrincipleDao templatePrincipleDao;

    //根据id获取全部模板规则
    public List<TemplatePrinciple> findRecordsById (TemplatePrinciple templatePrinciple){
        return templatePrincipleDao.findRecordsById(templatePrinciple);
    }

}
