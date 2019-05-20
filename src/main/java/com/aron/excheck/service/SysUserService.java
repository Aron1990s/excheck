package com.aron.excheck.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.aron.excheck.entity.PagePlugin;
import com.aron.excheck.entity.User;

import java.util.List;


public interface SysUserService {
	
	boolean add(User user);
	
	List<User> findAll();

	IPage<User> selectUserPage(Page<User> page, String state);

	IPage<User> selectPageExt(User user, int page, int pageSize);

	List<User> selectByParam(PagePlugin page);

	User te(Integer id);

}
