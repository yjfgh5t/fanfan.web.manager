package com.bootdo.common.msgQueue;

public interface MsgQueue<T> {

    /**
     * 添加产品
     */
    boolean put(T item);

    /**
     * 开始任务
     * @return
     */
     int startThread(int size);
}
