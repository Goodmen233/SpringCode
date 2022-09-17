package com.ccb.test;

import org.springframework.beans.BeansException;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessorAdapter;

import java.beans.PropertyDescriptor;

public class MyInstantiationAwareBeanPostProcessor extends InstantiationAwareBeanPostProcessorAdapter {

    /**
     * 在bean实例化之前被调用
     * 如果该方法返回的是non-null对象 ，这个bean的创建过程就不会执行postProcessAfterInstantiation、postProcessProperty等方法;
     *      会直接调用BeanPostProcessor的postProcessAfterInitialization，也就是跳过了Bean的实例化过程
     * 如果方法返回值为null, 则会继续默认的bean的实例化过程。
     */
    @Override
    public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException {
        System.out.println("Bean实例化开始，postProcessBeforeInstantiation====================run");
        return null;
    }

    /**
     * 在bean实例化后在填充bean属性之前回调
     * 如果返回true则进行下一步的属性填充postProcessProperties
     * 如果返回false:则不进行属性填充
     */
    @Override
    public boolean postProcessAfterInstantiation(Object bean, String beanName) throws BeansException {
        System.out.println("Bean实例化结束，postProcessAfterInstantiation=====================run");
        return true;
    }

    /**
     * 返回null调用postProcessPropertyValues
     * 返回no-null，则不调用postProcessPropertyValues
     */
    @Override
    public PropertyValues postProcessProperties(PropertyValues pvs, Object bean, String beanName) throws BeansException {
        System.out.println("postProcessProperties设置属性前给指定bean属性赋值");
        return null;
    }

    /**
     * 返回null 则不对属性赋值
     */
    @Override
    public PropertyValues postProcessPropertyValues(PropertyValues pvs, PropertyDescriptor[] pds, Object bean, String beanName) throws BeansException {
        System.out.println("过时的postProcessPropertyValues方法");
        return pvs;
    }

    /**
     * 返回null，则后续的processor则不运行
     */
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("Bean初始化开始，postProcessBeforeInitialization===================run");
        return bean;
    }

    /**
     * 返回null，则后续的processor则不运行
     */
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("Bean初始化结束，postProcessAfterInitialization====================run");
        return bean;
    }
}
