package com.yond.blog.service.impl;

import com.yond.blog.cache.local.MomentCache;
import com.yond.blog.entity.MomentDO;
import com.yond.blog.mapper.MomentMapper;
import com.yond.blog.service.MomentService;
import com.yond.blog.util.markdown.MarkdownUtils;
import com.yond.common.enums.EnableStatusEnum;
import com.yond.common.exception.PersistenceException;
import com.yond.common.utils.page.PageUtil;
import jakarta.annotation.Resource;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.List;

/**
 * @Description: 博客动态业务层实现
 * @Author: Yond
 */
@Service
public class MomentServiceImpl implements MomentService {
    
    @Resource
    private MomentMapper momentMapper;
    
    private static final String PRIVATE_MOMENT_CONTENT = "<p>此条为私密动态，仅发布者可见！</p>";
    
    @Override
    public List<MomentDO> listEnable() {
        List<MomentDO> cache = MomentCache.get();
        if (cache != null) {
            return cache;
        }
        cache = momentMapper.listByStatus(EnableStatusEnum.ENABLE.getVal());
        MomentCache.set(cache);
        return cache;
    }
    
    @Override
    public Pair<Integer, List<MomentDO>> page(boolean admin, boolean frontView, Integer pageNo, Integer pageSize) {
        List<MomentDO> all = this.listEnable();
        List<MomentDO> page = PageUtil.pageList(all, pageNo, pageSize);
        for (MomentDO moment : page) {
            if (!frontView) {
                continue;
            }
            if (admin || moment.getPublished()) {
                moment.setContent(MarkdownUtils.markdownToHtmlExtensions(moment.getContent()));
            } else {
                moment.setContent(PRIVATE_MOMENT_CONTENT);
            }
        }
        return Pair.of(all.size(), page);
    }
    
    @Override
    public MomentDO getById(Long id) {
        MomentDO exist = this.listEnable().stream()
                .filter(x -> id.equals(x.getId())).findFirst().orElse(null);
        Assert.notNull(exist, "动态不存在:" + id);
        return exist;
    }
    
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void incrLikeById(Long momentId) {
        if (momentMapper.incrLikeById(momentId) != 1) {
            throw new PersistenceException("操作失败");
        }
        MomentCache.del();
    }
    
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void insertSelective(MomentDO moment) {
        if (momentMapper.insertSelective(moment) != 1) {
            throw new PersistenceException("动态添加失败");
        }
        MomentCache.del();
    }
    
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updateSelective(MomentDO moment) {
        momentMapper.updateSelective(moment);
        MomentCache.del();
    }
    
}
