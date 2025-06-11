package com.github.zylczu.test;

import org.openjdk.jol.info.ClassLayout;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.locks.ReentrantReadWriteLock;

public class BiasedLockTest {
    static final int THRESHOLD = 5; // 重偏向阈值
    static final int REVOKE_THRESHOLD = 10; // 撤销阈值


    static Logger log = LoggerFactory.getLogger( my.class );
    public static void main(String[] args) throws InterruptedException {
        // 睡眠 5s
        Thread.sleep(5000);

        Object o = new Object();
        log.info("未生成 hashcode，MarkWord 为：");
        log.info(ClassLayout.parseInstance(o).toPrintable());

        synchronized (o){
            log.info(("进入同步块，MarkWord 为："));
            log.info(ClassLayout.parseInstance(o).toPrintable());
        }

        o.hashCode();
        log.info("生成 hashcode");
        log.info(ClassLayout.parseInstance(o).toPrintable());
        synchronized (o){
            log.info(("同一线程再次进入同步块，MarkWord 为："));
            log.info(ClassLayout.parseInstance(o).toPrintable());
        }
    }
}