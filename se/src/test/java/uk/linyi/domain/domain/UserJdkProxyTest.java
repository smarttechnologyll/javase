package uk.linyi.domain.domain;

import org.junit.BeforeClass;
import org.junit.Test;
import uk.linyi.domain.Operator;
import uk.linyi.domain.User;
import uk.linyi.domain.UserJdkProxyFactory;

import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class UserJdkProxyTest {
    private static Operator proxyInstance;

    @BeforeClass
    public static void setup() {
//        UserJdkProxyFactory factory = new UserJdkProxyFactory(new User());
//        proxyInstance = (Operator)factory.getProxyInstance();
        User user = new User();
        proxyInstance = (Operator) Proxy.newProxyInstance(user.getClass().getClassLoader(), user.getClass().getInterfaces(),
                (Object proxy, Method m, Object[] args) -> {
                    Object invoke = m.invoke(user, args);
                    System.out.println(m.getName() + "finish");
                    return invoke;
        });
    }

    @Test
    public void testLogin() {
        proxyInstance.login("alan", "1234");
    }

    @Test
    public void testRegister() {
        proxyInstance.register("alan", "1234", "110@gmail.com");
    }


}
