package com.wish.blog.service;

import com.wish.blog.entity.MomentDO;
import org.apache.commons.lang3.tuple.Pair;

import java.util.List;

public interface MomentService {

	List<MomentDO> listEnable();

	Pair<Integer, List<MomentDO>> page(boolean admin, boolean frontView, Integer pageNo, Integer pageSize);

	MomentDO getById(Long id);

	void incrLikeById(Long id);

	void insertSelective(MomentDO moment);

	void updateSelective(MomentDO moment);

}
