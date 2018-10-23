package com.bootdo.common.msgQueue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public abstract class AbstractMsgQueue<T> implements MsgQueue<T> {

    private  static Logger logger= LoggerFactory.getLogger(AbstractMsgQueue.class);

    /**
     * 数据队列
     */
    private LinkedBlockingQueue<T> dataQueue;

    /**
     * 线程池
     */
    private ThreadPoolExecutor threadPoolExecutor;

    /**
     * 任务队列
     */
    private LinkedBlockingQueue<Runnable> linkedQueueRunnable;

    /**
     * 条件锁
     */
    private ReentrantLock lock = new ReentrantLock();

    private Condition condition = lock.newCondition();

    /**
     * 最少执行任务的线程数
     */
    private final int minActiveThread;

    /**
     * 新增线程的阈值
     */
    private volatile int threshold;

    public AbstractMsgQueue(){
        this(1,5,20,100);
    }

    /**
     *
     * @param minActiveThread 最小线程运行数量
     * @param threadCoreSize 核心线程数
     * @param threadPoolSize 线程池最大长度
     * @param runnableQueueSize 线程任务队列长度
     */
    public AbstractMsgQueue(int minActiveThread, int threadCoreSize, int threadPoolSize, int runnableQueueSize){
        dataQueue = new LinkedBlockingQueue<>();

        linkedQueueRunnable = new LinkedBlockingQueue<>(runnableQueueSize);

        this.minActiveThread = minActiveThread;

        this.threshold = 100;

        threadPoolExecutor = new ThreadPoolExecutor(threadCoreSize,threadPoolSize,30, TimeUnit.SECONDS,linkedQueueRunnable);
        //销毁超时线程
        threadPoolExecutor.allowCoreThreadTimeOut(true);
    }

    /**
     * 回调处理
     * @param item
     */
    protected abstract void runCallback(T item);


    /**
     * 添加待消费的数据
     * @param item
     * @return
     */
    @Override
    public boolean put(T item) {

        try {

            //添加队列数据
            boolean success = dataQueue.offer(item);

            //待消费的数据队列长度每增长一个阈值 则添加新的线程
            if(!success || dataQueue.size()>(threshold*threadPoolExecutor.getActiveCount())){
                logger.info("新增并新增线程---->活动线程数量{} 当前队列长度{}",threadPoolExecutor.getActiveCount(),dataQueue.size());
                startThread(1);
            }

            return success;
        } catch (Exception ex) {
            logger.error("添加任务出错{}",ex);
        } finally {
            //释放锁
            //lock.unlock();
        }

        return false;
    }

    /**
     * 开启线程
     * @param size
     * @return
     */
    @Override
    public synchronized int startThread(int size){

        //执行任务
        for(int i=0;i<minActiveThread;i++) {
            //线程池中添加线程
            System.out.println("活动线程数："+threadPoolExecutor.getActiveCount()+" 线程大小："+threadPoolExecutor.getMaximumPoolSize());
            if(threadPoolExecutor.getActiveCount()==0 || threadPoolExecutor.getActiveCount() < threadPoolExecutor.getMaximumPoolSize()) {
                threadPoolExecutor.execute(() -> {
                    boolean canExecute=true;
                    while (canExecute) {
                        canExecute = execute();

                        //始终保持最少线程在读取任务
                        if(threadPoolExecutor.getActiveCount()<=minActiveThread){
                            canExecute=true;
                        }
                    }
                });
            }
        }

        return threadPoolExecutor.getActiveCount();
    }

    /**
     * 执行任务
     */
    private boolean execute() {

        boolean canexecute = false, isunLock = false;
        try {
            //获取锁
            lock.lock();

            //数据队列中没有数据 线程等待
            if (dataQueue.size() == 0) {
                //等待时间为10s
                condition.await(10L, TimeUnit.SECONDS);
            }

            //获取队列数据
            T tModel = dataQueue.poll();

            //处理回调方法
            if (tModel != null) {
                canexecute = true;
                runCallback(tModel);
            }

        } catch (Exception e) {
            logger.error("处理任务出错-->{}", e);
        } finally {
            lock.unlock();
        }

        return canexecute;
    }
}
