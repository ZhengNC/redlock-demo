package com.qixi.redlock.demo.util;

import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;

/**
 * @author ZhengNC
 * @date 2020/6/4 18:25
 */
public class RedLockUtils {

    private static RedissonClient redissonClient;

    public RedLockUtils(RedissonClient redissonClient){
        RedLockUtils.redissonClient = redissonClient;
    }

    public static boolean tryLock(String source){
        RLock rLock = redissonClient.getLock(source);
        return rLock.tryLock();
    }

    public static void lock(String source){
        RLock rLock = redissonClient.getLock(source);
        rLock.lock();
    }

    public static void unLock(String source){
        RLock rLock = redissonClient.getLock(source);
        rLock.unlock();
    }
}
