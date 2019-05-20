package com.aron.excheck.service.intf.template;

import com.aron.excheck.entity.template.TemplateInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @ClassName TemplateInfoService
 * @Description TODO
 * @Author aron
 * @Date 2019/5/16 13:16
 **/
public interface TemplateInfoService {

    //根据id获取模板信息
    TemplateInfo findById (Integer id);

    //根据模板id获取信息
    TemplateInfo findByTemplateId(String templateId);

}
