package com.aron.excheck.dao.template;

import com.aron.excheck.entity.template.TemplateInfo;
import com.aron.excheck.entity.template.TemplatePrinciple;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


@Mapper
public interface TemplatePrincipleDao extends BaseMapper<TemplatePrinciple> {

    //根据id获取全部模板规则
    List<TemplatePrinciple> findRecordsById (TemplatePrinciple templatePrinciple);

}
