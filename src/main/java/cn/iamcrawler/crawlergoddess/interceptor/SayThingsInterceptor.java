package cn.iamcrawler.crawlergoddess.interceptor;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * Created by liuliang on 2019/3/11.
 */
public class SayThingsInterceptor implements MethodInterceptor {

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("before:"+method.getName());
        Object o1 = methodProxy.invokeSuper(o, objects);
        System.out.println("after:"+method.getName());
        return o1;
    }


}
