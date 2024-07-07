package com.yond.blog.service;

import com.yond.blog.entity.MomentDO;

import java.util.List;

public interface MomentService {
	List<MomentDO> getMomentList();

	List<MomentDO> getMomentVOList(Integer pageNum, boolean adminIdentity);

	void addLikeByMomentId(Long momentId);

	void updateMomentPublishedById(Long momentId, Boolean published);

	MomentDO getMomentById(Long id);

	void deleteMomentById(Long id);

	void saveMoment(MomentDO moment);

	void updateMoment(MomentDO moment);
}
