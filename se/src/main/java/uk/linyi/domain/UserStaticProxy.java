package uk.linyi.domain;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class UserStaticProxy extends User {

    @Override
    public boolean login(String username, String password) {
        long startTime = System.currentTimeMillis();
        boolean res = super.login(username, password);
        long endTime = System.currentTimeMillis();
        log.info("cost time from {} to {}, total {} ms", startTime, endTime, endTime - startTime);
        return res;
    }

    @Override
    public boolean register(String username, String password, String email) {
        long startTime = System.currentTimeMillis();
        boolean res = super.register(username, password, email);
        long endTime = System.currentTimeMillis();
        log.info("cost time from {} to {}, total {} ms", startTime, endTime, endTime - startTime);
        return res;
    }
}
