package com.basic.use;

/**
 * @author : chensy
 * Date : 2020-03-11 23:56
 */
@RequestContext(requestName = "helloService")
public interface HelloService {
    @RequestContext(requestName = "helloServiceRequest", requestId = "helloServiceId")
    String sayHello(String str);
}
