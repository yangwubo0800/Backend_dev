package com.wg.spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.wg.helloworld.HelloWorld;
import com.wg.helloworld.HelloWorldService;

public class HelloProgram {
	public static void main(String[] args) {
        
        ApplicationContext context =
                new ClassPathXmlApplicationContext("beans.xml");
         
        HelloWorldService service =
             (HelloWorldService) context.getBean("helloWorldService");
          
        HelloWorld hw= service.getHelloWorld();
         
        hw.sayHello();
    }
}
