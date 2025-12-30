package uk.linyi.domain;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

@Slf4j
public class UserJdkProxyFactory {
    private final Object target;

    public UserJdkProxyFactory(Object target) {
        this.target = target;
    }

    public Object getProxyInstance() {
        return Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                long startTime = System.currentTimeMillis();
                Object res = method.invoke(target, args);
                long endTime = System.currentTimeMillis();
                log.info("cost time from {} to {}, total {} ms", startTime, endTime, endTime - startTime);
                return res;
            }
        });
    }
}
