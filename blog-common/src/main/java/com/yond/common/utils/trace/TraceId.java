package com.yond.common.utils.trace;

import java.time.DateTimeException;

/**
 * @Author Yond
 */
public class TraceId {

    protected static SnowflakeIdWorker idGenerator = new SnowflakeIdWorker();
    
    public static String nextId() {
        long id = 1L;
        for (int i = 0, size = 3; i < size; i++) {
            try {
                id = idGenerator.nextId();
                break;
            } catch (DateTimeException ex) {
                // 机器时间重拨
                rebuildSnowflakeIdWorker();
            }
        }

        return String.valueOf(id);
    }

    private synchronized static void rebuildSnowflakeIdWorker() {
        idGenerator = new SnowflakeIdWorker();
    }

    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            System.out.println(TraceId.nextId());
        }
    }

}
