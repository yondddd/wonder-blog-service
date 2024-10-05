package com.wonder.blog.service;

import com.wonder.blog.entity.FriendDO;
import com.wonder.blog.web.admin.dto.FriendConfigDTO;
import org.apache.commons.lang3.tuple.Pair;

import java.util.List;

public interface FriendService {
    
    List<FriendDO> listAll();
    
    Pair<Integer, List<FriendDO>> page(Integer pageNo, Integer pageSize);
    
    void incrViewById(Long id);
    
    void insertSelective(FriendDO friendDO);
    
    void updateSelective(FriendDO friendDO);
    
    FriendConfigDTO getFriendConfig();
    
}
