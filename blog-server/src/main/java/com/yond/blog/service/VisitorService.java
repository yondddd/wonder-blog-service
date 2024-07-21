package com.yond.blog.service;

import com.yond.blog.entity.VisitorDO;
import com.yond.blog.web.blog.view.dto.VisitLogUuidTime;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.scheduling.annotation.Async;

import java.util.Date;
import java.util.List;

public interface VisitorService {

    List<VisitorDO> getVisitorListByDate(String startDate, String endDate);

    Pair<Integer, List<VisitorDO>> page(Integer pageNo, Integer pageSize, Date startDate, Date endDate);

    List<String> getNewVisitorIpSourceByYesterday();

    boolean hasUUID(String uuid);

    @Async
    void saveVisitor(VisitorDO visitor);

    void updatePVAndLastTimeByUUID(VisitLogUuidTime dto);

    void deleteVisitor(Long id, String uuid);
}
