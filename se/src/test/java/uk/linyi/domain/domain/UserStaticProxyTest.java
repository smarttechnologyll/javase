package uk.linyi.domain.domain;


import org.junit.Test;
import uk.linyi.domain.User;
import uk.linyi.domain.UserStaticProxy;

public class UserStaticProxyTest {
    private User user=new UserStaticProxy();

    @Test
    public void testUserLogin(){
        user.login("alan","1234");
    }

    @Test
    public void testUserRegister(){
        user.register("alan","1234","110@gmail.com");
    }




}
