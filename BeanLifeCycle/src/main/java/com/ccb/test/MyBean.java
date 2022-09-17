package com.ccb.test;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.EmbeddedValueResolverAware;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;
import org.springframework.util.StringValueResolver;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class MyBean implements InitializingBean, DisposableBean,
        BeanNameAware, BeanFactoryAware, BeanClassLoaderAware,
        EnvironmentAware, EmbeddedValueResolverAware, ApplicationContextAware {
    private String name;
    private int age;

    @Override
    public void setBeanClassLoader(ClassLoader classLoader) {
        System.out.println("BeanClassLoaderAware的setBeanClassLoader()");
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        System.out.println("BeanFactoryAware的setBeanFactory()");
    }

    @Override
    public void setBeanName(String s) {
        System.out.println("BeanNameAware的setBeanName()");
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        System.out.println("ApplicationContextAware的setApplicationContext()");
    }

    @Override
    public void setEmbeddedValueResolver(StringValueResolver stringValueResolver) {
        System.out.println("EmbeddedValueResolverAware的setEmbeddedValueResolver()");
    }

    @Override
    public void setEnvironment(Environment environment) {
        System.out.println("EnvironmentAware的setEnvironment()");
    }

    @PostConstruct
    public void initAnnotations(){
        System.out.println("@PostConstruct:-------------------------------run");
    }

    @PreDestroy
    public void destroyAnnotations(){
        System.out.println("@PreDestroy:----------------------------------run");
    }

    public void initConfig(){
        System.out.println("自定义初始化方法：----------------------------run");
    }

    public void destroyConfig(){
        System.out.println("自定义销毁方法：------------------------------run");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("DisposableBean的Destroy方法------------------run");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("InitializingBean的afterPropertiesSet方法-----run");
    }

    // -----------------------------getter and setter and toString------------------------------------------------
    @Override
    public String toString() {
        return "MyBean{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public MyBean() {
    }

    public MyBean(String name) {
        this.name = name;
    }

    public MyBean(String name, int age) {
        this.name = name;
        this.age = age;
    }
}
