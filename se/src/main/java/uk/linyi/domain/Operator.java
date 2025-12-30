package uk.linyi.domain;

public interface Operator {
    boolean login(String username, String password);
    boolean register(String username, String password, String email);
}
