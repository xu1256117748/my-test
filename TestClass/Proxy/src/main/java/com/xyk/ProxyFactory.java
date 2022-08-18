package com.xyk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author 徐亚奎
 * @date 23/07/2021 00:15
 */
public class ProxyFactory {
    public static Object getProxy(Object o){
        Object proxy =  Proxy.newProxyInstance(o.getClass().getClassLoader(), o.getClass().getInterfaces(), new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                System.out.println("before");
                Object invoke = method.invoke(o, args);
                System.out.println("after");
                return invoke;
            }
        });
        return proxy;
    }
}
