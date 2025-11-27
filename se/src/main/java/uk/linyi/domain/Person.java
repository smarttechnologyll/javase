package uk.linyi.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public abstract class Person {
    private Long id;
    private String name;
    private String email;
    private Integer age;

    public abstract String getRole();
}