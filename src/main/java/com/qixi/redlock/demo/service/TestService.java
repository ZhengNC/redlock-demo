package com.qixi.redlock.demo.service;

/**
 * 测试
 *
 * @author ZhengNC
 * @date 2020/6/5 9:54
 */
public interface TestService {

    /**
     * 测试分布式锁
     *
     * @param source
     * @return
     */
    Boolean testTryLock(String source);

    /**
     * 测试分布式锁的可重入特性
     * @param source
     * @return
     */
    Boolean testTryLockAgain(String source);

    /**
     * 测试分布式锁
     *
     * @param source
     * @return
     */
    Boolean testLock(String source);
}
