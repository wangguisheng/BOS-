package cn.itcast.bos.service.base.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.itcast.bos.dao.base.AreaDao;
import cn.itcast.bos.domain.base.Area;
import cn.itcast.bos.service.base.AreaService;

@Service
@Transactional
public class AreaServiceImpl implements AreaService {

	// 注入DAO
	@Autowired
	private AreaDao areaDao;

	@Override
	public void saveBatch(List<Area> areas) {
		areaDao.save(areas);
	}

	@Override
	public Page<Area> findPageData(Specification<Area> specification,
			Pageable pageable) {
		return areaDao.findAll(specification, pageable);
	}

}