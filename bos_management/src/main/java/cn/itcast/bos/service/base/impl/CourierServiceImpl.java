package cn.itcast.bos.service.base.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.itcast.bos.dao.base.CourierDao;
import cn.itcast.bos.domain.base.Courier;
import cn.itcast.bos.service.base.CourierService;

@Service
@Transactional
public class CourierServiceImpl implements CourierService {

	//注入Dao对象
	@Autowired
	private CourierDao courierDao;
	
	@Override
	public void save(Courier courier) {
		// TODO Auto-generated method stub
		courierDao.save(courier);
	}

	@Override
	public Page<Courier> findPageDate(Pageable pageable) {
		// TODO Auto-generated method stub
		return courierDao.findAll(pageable);
	}

	@Override
	public Page<Courier> findPageData(Specification<Courier> specification, Pageable pageable) {
		// TODO Auto-generated method stub
		return courierDao.findAll(specification, pageable);
	}

	@Override
	public void delBatch(String[] idArray) {
		// TODO Auto-generated method stub
		//调用DAO实现update修改操作,将deltag修改为1
		for (String string : idArray) {
			Integer id = Integer.parseInt(string);
			courierDao.updateDeTag(id);
		}
	}

}
