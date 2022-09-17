package com.ccb.test;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 1、postProcessBeforeInstantiation返回一个对象，直接阻止bean实例化
 * 2、postProcessAfterInstantiation返回false，不进行自定义属性填充
 * 3、postProcessProperties返回null，执行过时方法
 * 4、postProcessPropertyValues返回null，所有属性为默认值
 * 5、postProcessAfterInitialization/postProcessBeforeInitialization返回null，后面的processor不会运行.
 * 6、@PostConstruct/@PreDestroy使用InitDestroyAnnotationBeanPostProcessor实现
 * 7、EnvironmentAware EmbeddedValueResolverAware ApplicationContextAware等通过ApplicationContextAwareProcessor的postProcessBeforeInitialization方法来执行方法
 *
 *
 *  AbstractAutowireCapableBeanFactory：
 *  createBean{
 *      resolveBeforeInstantiation-[调用postProcessBeforeInstantiation]
 *      doCreateBean{
 *          createBeanInstance
 *          populateBean{
 *              调用postProcessAfterInstantiation
 *              调用postProcessProperties
 *              调用postProcessPropertyValues
 *              applyPropertyValues-[设置属性值]
 *          }
 *          initializeBean{
 *              invokeAwareMethods-[调用BeanNameAware/BeanClassLoaderAware/BeanFactoryAware接口方法]
 *              applyBeanPostProcessorsBeforeInitialization-[调用postProcessBeforeInitialization(EnvironmentAware/EmbeddedValueResolverAware/ApplicationContextAware接口方法/@PostConstruct方法)]
 *              invokeInitMethods{
 *                  调用afterPropertiesSet
 *                  invokeCustomInitMethod-[调用自定义方法]
 *              }
 *              applyBeanPostProcessorsAfterInitialization-[调用postProcessAfterInitialization]
 *          }
 *      }
 *  }
 *
 *  DisposableBeanAdapter
 *  destroy{
 *      postProcessBeforeDestruction-[调用@PreDestroy方法]
 *      调用DisposableBean的destroy方法
 *      invokeCustomDestroyMethod-[调用自定义方法]
 *  }
 */
public class Test {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext application = new ClassPathXmlApplicationContext("application.xml");
        MyBean myBean = (MyBean) application.getBean("myBean");
        System.out.println("程序运行中...    " + myBean);
        application.close();
    }
}
