package com.ccb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * SpringBoot2.6之后不允许存在循环依赖，如果需要，需要配置
 *
 * DefaultSingletonBeanRegistry#getSingleton(String, boolean)
 *
 * 大致过程：
 * ClassA 1、执行createBeanInstance,放入三级缓存[singletonFactories]
 *        2、执行populateBean注入属性，发现没有ClassB，于是
 *          ClassB 2.1、执行createBeanInstance,放入三级缓存[singletonFactories]
 *                 2.2、执行populateBean注入ClassA，将三级缓存[singletonFactories]的ClassA拿出，放入二级缓存[earlySingletonObjects]
 *                 2.3、执行initializeBean，初始化完成，将三级缓存[singletonFactories]的ClassB拿出，放入一级缓存[singletonObjects]
 *        3、执行initializeBean，初始化完成，将二级缓存[earlySingletonObjects]的ClassA拿出，放入一级缓存[singletonObjects]
 *
 * 一、放入三级缓存代码：在执行createBeanInstance之后，执行populateBean注入属性之间
 * 二、populateBean从三级缓存拿出，放入二级缓存：
 *  创建ClassB：DefaultListableBeanFactory#resolveDependency#doResolveDependency里面的
 *  *        DefaultListableBeanFactory#findAutowireCandidates->解析属性里面注入的映射值
 *  *            beanNamesForTypeIncludingAncestors：AbstractBeanFactory#isTypeMatch->getSingleton，设置false           获取类名
 *  *            for里面的addCandidateEntry->AbstractBeanFactory#getSingleton,设置false                                 获取类名对应的全限定名称 映射集
 *          resolveCandidate的AbstractBeanFactory#doGetBean->DefaultListableBeanFactory#getSingleton,设置true
 *
 *  三、从二级缓存/三级缓存拿出，放入一级缓存
 *      DefaultListableBeanFactory#addSingleton
 *                      ｜
 *     由DefaultSingletonBeanRegistry#getSingleton(String, ObjectFactory) 调用
 */
@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = SpringApplication.run(Application.class, args);
        ctx.close();
    }
}
