package com.springcloud.clientfeign.service;

import feign.hystrix.FallbackFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Feign 熔断器实现类
 * @author YUDI
 * @date 2020/5/26 14:32
 */
@Component
public class FeignServiceHystrix implements FeignService, FallbackFactory<FeignService> {

    private Logger logger = LoggerFactory.getLogger(FeignServiceHystrix.class);

    @Override
    public String helloClientOne(String name) {
        return "Feign Error,this is fallBack method! Bye bye,"+name;
    }

    @Override
    public FeignService create(Throwable throwable) {
        logger.error("错误原因：" + throwable);
        return new FeignService() {
            @Override
            public String helloClientOne(String name) {
                return "错误啦。";
            }
        };
    }
}
