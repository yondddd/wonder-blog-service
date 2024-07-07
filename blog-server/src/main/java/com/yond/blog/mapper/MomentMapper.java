package com.yond.blog.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import com.yond.blog.entity.MomentDO;

import java.util.List;

/**
 * @Description: 博客动态持久层接口
 * @Author: Naccl
 * @Date: 2020-08-24
 */
@Mapper
@Repository
public interface MomentMapper {
	List<MomentDO> getMomentList();

	int addLikeByMomentId(Long momentId);

	int updateMomentPublishedById(Long momentId, Boolean published);

	MomentDO getMomentById(Long id);

	int deleteMomentById(Long id);

	int saveMoment(MomentDO moment);

	int updateMoment(MomentDO moment);
}
