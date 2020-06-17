package com.qixi.redlock.demo.service.impl;

import com.qixi.redlock.demo.service.TestService;
import com.qixi.redlock.demo.util.RedLockUtils;
import com.qixi.redlock.demo.util.Tools;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 测试
 *
 * @author ZhengNC
 * @date 2020/6/5 9:55
 */
@Service
@Slf4j
public class TestServiceImpl implements TestService {

    /**
     * 测试分布式锁
     *
     * @param source
     * @return
     */
    @Override
    public Boolean testTryLock(String source) {
        log.info("尝试加锁:{}", source);
        Boolean result = RedLockUtils.tryLock(source);
        if (result){
            log.info("加锁成功:{}", source);
            //生成一个随机数，模拟业务需要处理的时间
            int time = Tools.randomNum(20, 40);
            log.info("执行业务, 处理:{}, 需要时间:{}秒", source, time);
            try {
                Thread.sleep(time * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.info("业务执行完毕，释放锁");
            RedLockUtils.unLock(source);
            log.info("释放锁成功，处理完毕:{}", source);
        }else {
            log.info("加锁失败:{}", source);
        }
        return result;
    }

    /**
     * 测试分布式锁的可重入特性
     * @param source
     * @return
     */
    @Override
    public Boolean testTryLockAgain(String source) {
        log.info("第一遍尝试加锁:{}", source);
        Boolean result = RedLockUtils.tryLock(source);
        if (result){
            log.info("第一遍加锁成功:{}", source);
            //生成一个随机数，模拟业务需要处理的时间
            int time = Tools.randomNum(2, 4);
            log.info("执行业务, 处理:{}, 需要时间:{}秒", source, time);
            try {
                Thread.sleep(time * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            log.info("\t第二遍尝试加锁:{}", source);
            Boolean againResult = RedLockUtils.tryLock(source);
            if (againResult){
                log.info("\t第二遍加锁成功:{}", source);
                int time2 = Tools.randomNum(4, 8);
                log.info("\t执行业务, 处理:{}, 需要时间:{}秒", source, time2);
                try {
                    Thread.sleep(time2 * 1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                log.info("\t第二遍业务执行完毕，释放锁");
                RedLockUtils.unLock(source);
                log.info("\t第二遍释放锁成功，处理完毕:{}", source);
            }else {
                log.info("\t第二遍加锁失败:{}", source);
            }

            log.info("第一遍业务执行完毕，释放锁");
            RedLockUtils.unLock(source);
            log.info("第一遍释放锁成功，处理完毕:{}", source);
        }else {
            log.info("第一遍加锁失败:{}", source);
        }
        return result;
    }

    @Override
    public Boolean testLock(String source) {
        log.info("加锁:{}", source);
        RedLockUtils.lock(source);
        log.info("加锁成功:{}", source);
        //生成一个随机数，模拟业务需要处理的时间
        int time = Tools.randomNum(20, 40);
        log.info("执行业务, 处理:{}, 需要时间:{}秒", source, time);
        try {
            Thread.sleep(time * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("业务执行完毕，释放锁");
        RedLockUtils.unLock(source);
        log.info("释放锁成功，处理完毕:{}", source);
        return true;
    }
}
