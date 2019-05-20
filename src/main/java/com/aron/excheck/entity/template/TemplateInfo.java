package com.aron.excheck.entity.template;

import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @ClassName TemplateInfo
 * @Description TODO
 * @Author aron
 * @Date 2019/5/16 11:31
 **/
@Data
public class TemplateInfo {

    private Integer id;

    private String template_id;

    private String template_name;

    private String remark;

    private String create_time;

}
