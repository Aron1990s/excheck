package com.aron.excheck.dao.template;

import com.aron.excheck.entity.PagePlugin;
import com.aron.excheck.entity.User;
import com.aron.excheck.entity.template.TemplateInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


@Mapper
public interface TemplateInfoDao extends BaseMapper<TemplateInfo> {

    //根据模板id获取信息
    TemplateInfo findByTemplateId(String templateId);

}
