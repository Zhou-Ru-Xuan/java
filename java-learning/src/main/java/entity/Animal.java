package entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author zhouruxuan
 * @description
 * @date 2023-04-24
 **/
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Animal {
    private String name;

    private Integer age;


    public int countLegs() {
        return 1;
    }

}
