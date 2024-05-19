package com.yond.common.utils.date;

import com.yond.common.utils.api.Factory;
import org.apache.commons.pool2.ObjectPool;

/**
 * 简单对象池（实现了common-pool中的ObjectPool接口
 *
 
 * @version 1.0
 * @created 2018/1/7 00:31
 **/
public class SimplePool<T> implements ObjectPool<T> {

    private final int initialPoolSize;

    private final int maxPoolSize;

    private final Factory<T> factory;

    private transient Object[] pool;

    private transient int nextAvailable;

    private final transient Object lock = new Object();

    public SimplePool(int initialPoolSize, int maxPoolSize, Factory<T> factory) {
        this.initialPoolSize = initialPoolSize;
        this.maxPoolSize = maxPoolSize;
        this.factory = factory;
    }

    @Override
    public T borrowObject() {
        T result;
        synchronized (lock) {
            if (pool == null) {
                pool = new Object[maxPoolSize];
                for (nextAvailable = initialPoolSize; nextAvailable > 0; ) {
                    returnObject(factory.newInstance());
                }
            }
            while (nextAvailable == maxPoolSize) {
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException("Interrupted whilst waiting for a free item in the pool : " + e.getMessage());
                }
            }
            result = (T) pool[nextAvailable++];
            if (result == null) {
                result = factory.newInstance();
                returnObject(result);
                ++nextAvailable;
            }
        }
        return result;
    }

    @Override
    public void returnObject(T object) {
        if (null == object) {
            return;
        }

        synchronized (lock) {
            pool[--nextAvailable] = object;
            lock.notifyAll();
        }
    }


    @Deprecated
    @Override
    public void invalidateObject(T obj) throws Exception {

    }

    @Override
    public void addObject() throws Exception {
    }

    @Deprecated
    @Override
    public int getNumIdle() throws UnsupportedOperationException {
        return nextAvailable;
    }

    @Deprecated
    @Override
    public int getNumActive() throws UnsupportedOperationException {
        return 0;
    }

    @Deprecated
    @Override
    public void clear() throws Exception {
    }

    @Deprecated
    @Override
    public void close() {
    }

    @Override
    public void addObjects(int count) throws Exception {

    }
}
