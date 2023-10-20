package com.oven.server.feign;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Component
@FeignClient(name = "flask", url = "http://flask:6000")
public interface FlaskFeignClient {
    @RequestMapping(method = RequestMethod.GET, value = "/spring")
    String getDataFromFlask(@RequestParam("userId") String userId);

}
