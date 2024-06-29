package com.yond.common.utils.page;

import java.util.ArrayList;
import java.util.List;

/**
 * 内存分页工具类
 *
 * @author yond
 * @date 2022/8/11
 */
public class PageUtil {
    private PageUtil() {
    }

    /**
     * 默认-页码，从1开始
     */
    public static final int PAGE_OFFSET = 1;

    /**
     * 默认-页条数
     */
    public static final int PAGE_SIZE = 20;

    /**
     * 重新得到处理后的页码
     *
     * @param pageOffset 页码，从1开始
     */
    private static Integer regainPageOffset(Integer pageOffset) {
        pageOffset = null == pageOffset || pageOffset <= 0 ? PAGE_OFFSET : pageOffset;
        return pageOffset;
    }

    /**
     * 重新得到处理后的页条数
     *
     * @param pageSize 页条数，大于0的整数
     */
    private static Integer regainPageSize(Integer pageSize) {
        pageSize = null == pageSize || pageSize <= 0 ? PAGE_SIZE : pageSize;
        return pageSize;
    }

    /**
     * 分页方法，返回指定页的数据集合
     *
     * @param totalList  待分页数据集合，不可为null
     * @param pageOffset 页码，从1开始 [小于等于0时则置为默认值:
     * @param pageSize   页条数 [小于等于0时则置为默认值:
     */
    public static <T> List<T> pageList(List<T> totalList, Integer pageOffset, Integer pageSize) {
        pageOffset = PageUtil.regainPageOffset(pageOffset);
        pageSize = PageUtil.regainPageSize(pageSize);

        int startIndex = (pageOffset - 1) * pageSize;
        int totalCount = totalList.size();
        if (startIndex > totalCount) {
            return new ArrayList<>();
        }
        int endIndex = startIndex + pageSize;
        if (endIndex > totalCount) {
            endIndex = totalCount;
        }
        List<T> page = totalList.subList(startIndex, endIndex);

        return page;
    }

}
