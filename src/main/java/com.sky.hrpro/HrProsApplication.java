package com.sky.hrpro;


import com.sky.hrpro.kafkaService.TaskRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName:HrProsApplication
 * @Description:
 * HrProsApplication.java 是SpringBoot应用程序入口，或者叫主程序。
 * 注解@SpringBootApplication 标注他是一个SpringBoot应用，main方法使他成为一个主程序，将在应用启动时首先被执行。
 * 注解@RestController 标注这也是一个控制器。
 */

/**
 * @author CarryJey
 * @date 2018年9月27日 上午9:36:42
 */

@SpringBootApplication
@RestController
public class HrProsApplication{

    @RequestMapping("/")
    public String  hello(){
        return "hello git";
    }

    public static void main(String args[]){
        SpringApplication.run(HrProsApplication.class, args);
    }

    /**
     * 启动类中初始化，项目启动时就会启动服务，调用消费者类订阅处理消息队列
     * 项目运行不起来，可以将以下方法注释掉，更好的测试方法到kafkaconsumerDemo中，将start方法改为public static void main(),这样会方便测试
     */
    @Bean
    TaskRunner taskRunner(){
        return new TaskRunner();
    }


    @Bean
    public TaskScheduler taskScheduler(){
        ThreadPoolTaskScheduler taskScheduler = new ThreadPoolTaskScheduler();
        taskScheduler.setPoolSize(10);
        taskScheduler.initialize();
        return taskScheduler;
    }
}