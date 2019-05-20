package com.aron.excheck.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.aron.excheck.entity.PagePlugin;
import com.aron.excheck.entity.User;
import com.aron.excheck.service.SysUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class SysUserController {
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
	private SysUserService userService;
	
	@GetMapping("/user/add")
	public boolean add(User user) {
		return userService.add(user);
	}
	
	@GetMapping("user/getAll")
	public List<User> getAll(){
		return userService.findAll();
	}

	@GetMapping("user/pageAll")
	public List<User> pageAll (Page page) {
		Page page1 = new Page();
		page1.setCurrent(1);
		IPage<User> userIPage = userService.selectUserPage(page1, "aaa");
		List<User> users = userIPage.getRecords();
		return users;
	}

	@GetMapping("/findAllUser")
	@ResponseBody
	public List<User> findAllUser(){
		User user = new User();
		int page=2;//当前页
		int pageSize=4;//页面接收数据大小
		IPage<User> iPage = userService.selectPageExt(user, page, pageSize);
		iPage.getRecords();
		return iPage.getRecords();
	}

	@GetMapping("/user/findByParam")
    public List<User> findByParam () {
        int page=1;//当前页
        int pageSize=3;//页面接收数据大小
        PagePlugin pagePlugin = new PagePlugin(page, pageSize);
        Map<String, Object> map = new HashMap<>();
        map.put("name", "dad");
        pagePlugin.setPd(map);
	    return userService.selectByParam(pagePlugin);
    }

    @GetMapping("/user/findByParam2")
    public List<User> findByParam2 (PagePlugin pagePlugin) {
        Map<String, Object> map = new HashMap<>();
        map.put("name", "dad");
        pagePlugin.setPd(map);
        logger.info("aaaaaaa");
        logger.error("aaaaaaaaaaaa");
        try{
        }catch (Exception e){
            logger.error(e.toString());
        }

        return userService.selectByParam(pagePlugin);
    }

    @GetMapping("/user/test")
    public Object test(){
	    Map<String, Object> map = new HashMap<>();
	    try{
            User user = userService.te(1);
            map.put("aaa", user);
        }catch (Exception e){
	        e.printStackTrace();
        }
        return map;
    }
}
