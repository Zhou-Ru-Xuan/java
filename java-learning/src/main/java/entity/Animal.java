package entity;

import lombok.*;

/**
 * @author zhouruxuan
 * @description
 * @date 2023-04-24
 **/
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Animal {
    private String name;

    private Integer age;


    public int countLegs() {
        return 1;
    }

}
