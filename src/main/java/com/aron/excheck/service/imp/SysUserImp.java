package com.aron.excheck.service.imp;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.aron.excheck.dao.UserDao;
import com.aron.excheck.entity.PagePlugin;
import com.aron.excheck.entity.User;
import com.aron.excheck.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class SysUserImp implements SysUserService {
	
	@Autowired
	private UserDao userDao;

	@Override
	public boolean add(User user) {
		return userDao.add(user);
	}

	@Override
	public List<User> findAll() {
		return userDao.findAll();
	}

	@Override
	public IPage<User> selectUserPage(Page<User> page, String state) {
		// 不进行 count sql 优化，解决 MP 无法自动优化 SQL 问题，这时候你需要自己查询 count 部分
		// page.setOptimizeCountSql(false);
		// 当 total 为非 0 时(默认为 0),分页插件不会进行 count 查询
		// 要点!! 分页返回的对象与传入的对象是同一个
		return userDao.selectPageVo(page, state);
	}

	public IPage<User> selectPageExt(User user, int page, int pageSize) throws RuntimeException {
		try {
			Page<User> p = new Page<>(page, pageSize);
			p.setRecords(userDao.selectPageExt(p, user));
			return p;
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	@Override
	public List<User> selectByParam (PagePlugin page){
		return userDao.selectByParam(page);
	}

    public User te(Integer id){
        return userDao.selectById(id);
    }
}
