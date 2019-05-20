package com.aron.excheck.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * User类
 * @author aron
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@TableName("sys_user")
public class User implements Serializable {

    private static final long serialVersionUID = -8657442456247728073L;

    //用户id
    private Integer id;

    //用户姓名
    private String name;

    //用户年龄
    private Integer age;

    //密码
    @TableField(exist = false)
    private String password;
}
