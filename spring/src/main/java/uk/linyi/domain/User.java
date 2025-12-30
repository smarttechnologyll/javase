package uk.linyi.domain;

import lombok.Data;

@Data // 自动生成 Getter, Setter, toString
public class User {
    private Long id;
    private String name;
    private Integer age;
}