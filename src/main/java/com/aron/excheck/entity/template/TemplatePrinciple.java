package com.aron.excheck.entity.template;

import lombok.Data;
import lombok.experimental.Accessors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @ClassName TemplatePrinciple
 * @Description TODO
 * @Author aron
 * @Date 2019/5/16 11:35
 **/
@Data
@Accessors(chain = true)
public class TemplatePrinciple {

    private Integer id;

    private String template_id;

    private String column_number;

    private String column_type;

    private String column_format;

}
