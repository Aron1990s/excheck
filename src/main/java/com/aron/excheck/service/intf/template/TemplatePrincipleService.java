package com.aron.excheck.service.intf.template;


import com.aron.excheck.entity.template.TemplatePrinciple;

import java.util.List;

/**
 * @ClassName TemplatePrincipleService
 * @Description TODO
 * @Author aron
 * @Date 2019/5/16 13:16
 **/
public interface TemplatePrincipleService {

    //根据id获取全部模板规则
    List<TemplatePrinciple> findRecordsById (TemplatePrinciple templatePrinciple);
}
