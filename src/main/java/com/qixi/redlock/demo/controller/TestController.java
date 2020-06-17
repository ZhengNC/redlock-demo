package com.qixi.redlock.demo.controller;

import com.qixi.redlock.demo.common.ResponseEntity;
import com.qixi.redlock.demo.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotBlank;

/**
 * 测试接口
 *
 * @author ZhengNC
 * @date 2020/6/5 9:35
 */
@RestController
@RequestMapping("test")
@Validated
public class TestController {

    @Autowired
    private TestService testService;

    /**
     * 测试分布式锁（加锁失败立即返回）
     *
     * @param source
     * @return
     */
    @GetMapping("testTryLock")
    public ResponseEntity<Boolean> testTryLock(
            @RequestParam("source")
            @NotBlank(message = "参数不能为空")
                    String source){
        return ResponseEntity.success(testService.testTryLock(source));
    }

    /**
     * 测试分布式锁的可重入性
     *
     * @param source
     * @return
     */
    @GetMapping("testTryLockAgain")
    public ResponseEntity<Boolean> testTryLockAgain(
            @RequestParam("source")
            @NotBlank(message = "参数不能为空")
                    String source){
        return ResponseEntity.success(testService.testTryLockAgain(source));
    }

    /**
     * 测试分布式锁（加锁失败自旋等待）
     *
     * @param source
     * @return
     */
    @GetMapping("testLock")
    public ResponseEntity<Boolean> testLock(
            @RequestParam("source")
            @NotBlank(message = "参数不能为空")
                    String source){
        return ResponseEntity.success(testService.testLock(source));
    }

    @GetMapping("hello")
    public ResponseEntity<String> hello(){
        return ResponseEntity.success("hello");
    }
}
