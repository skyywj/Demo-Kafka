package com.sky.hrpro.grpc;

import io.grpc.Server;
import io.grpc.inprocess.InProcessServerBuilder;

/**
 * @Author: YanWenjie
 * @Date: 2018/9/27 下午5:25
 */

/**
 * grpc 单元测试还是比较其他的麻烦一些的，涉及request
 * 1、建立TestBase(复制过去就行)，TestBase还需要PrintRequesIdClientIntercepter
 * 2、class继承TestBase
 * 3、重写doGetStub
 * 4、通过getstub（）调用测试方法
 */


public class Test {

//    //定义服务名字用于绑定客户端调用的服务端
//    private static final String UNIQUE_SERVER_NAME = "in-process server for " + Test.class;
//
//    //创建一个进程内的server
//    private final Server inProcessServer = InProcessServerBuilder.forName(UNIQUE_SERVER_NAME).addService(new ()).directExecutor().build();
//    //创建一个进程内的channel
//    private final ManagedChannel inProcessChannel = InProcessChannelBuilder.forName(UNIQUE_SERVER_NAME).directExecutor().build();




}
